package org.example.suggestion.survice;

import lombok.RequiredArgsConstructor;
import org.example.product.entity.Product;
import org.example.product.repository.ProductRepository;
import org.example.suggestion.entity.Suggestion;
import org.example.suggestion.repository.SuggestionRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SuggestionService {

    //    추천 상품을 등록하는 구문
    private final SuggestionRepository suggestionRepository;
    private final ProductRepository productRepository;

    public Suggestion saveByIds(List<Long> ids) {
        Suggestion suggestion = suggestionRepository.findAll().stream().findFirst().orElse(new Suggestion());

        if (suggestion.getId() == null) {
            suggestion = suggestionRepository.save(suggestion);
        }

        List<Product> existingProducts = suggestion.getProducts();

        if (existingProducts == null) {
            existingProducts = new ArrayList<>();
        }

        List<Product> productsToAdd = new ArrayList<>();

        for (Long id : ids) {
            Optional<Product> optionalProduct = productRepository.findById(id);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                if (!existingProducts.contains(product)) {
                    product.setSuggestion(suggestion);
                    productsToAdd.add(product);
                }
            }
        }

        List<Product> productsToRemove = new ArrayList<>();

        for (Product product : existingProducts) {
            if (!ids.contains(product.getId())) {
                product.setSuggestion(null);
                productsToRemove.add(product);
            }
        }

        for (Product product : productsToRemove) {
            productRepository.delete(product);
        }

        for (Product product : productsToAdd) {
            productRepository.save(product);
        }

        suggestion.setProducts(productsToAdd);

        return suggestionRepository.save(suggestion);
    }

//    추천 상품을 불러오는 구문
    public Suggestion findAll() {
       return suggestionRepository.findAll().stream().findFirst().orElse(new Suggestion());
    }
}
