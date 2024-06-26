package org.example.suggestion.survice;

import lombok.RequiredArgsConstructor;
import org.example.product.entity.Product;
import org.example.product.repository.ProductRepository;
import org.example.suggestion.entity.Suggestion;
import org.example.suggestion.repository.SuggestionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SuggestionService {

    //    추천 상품을 등록하는 구문
    private final SuggestionRepository suggestionRepository;
    private final ProductRepository productRepository;

    public List<Suggestion> save(List<Product> products) {
        List<Suggestion> suggestions = suggestionRepository.findAll();

        for (Suggestion suggestion : suggestions) {
            List<Product> existProduct = suggestion.getProducts();

            List<Product> productsToRemove = existProduct.stream()
                    .filter(product -> !products.contains(product))
                    .collect(Collectors.toList());

            for (Product product : productsToRemove) {
                product.addSuggestion(suggestion);
                product.setSuggestion(null);
                productRepository.delete(product);
            }

            for (Product product : products) {
                if (!existProduct.contains(product)) {
                    product.addSuggestion(suggestion);
                    product.setSuggestion(suggestion);
                    productRepository.save(product);
                }
            }

            suggestion.setProducts(products);
            suggestionRepository.save(suggestion);
        }

        return suggestions;
    }
}
