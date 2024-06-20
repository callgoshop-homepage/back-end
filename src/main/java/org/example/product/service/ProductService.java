package org.example.product.service;

import lombok.RequiredArgsConstructor;
import org.example.board.entity.Board;
import org.example.board.entity.DetailBoard;
import org.example.board.service.BoardService;
import org.example.board.service.DetailBoardService;
import org.example.member.entity.Member;
import org.example.product.controller.ProductController;
import org.example.product.entity.Product;
import org.example.product.entity.ProductOption;
import org.example.product.repository.ProductOptionRepository;
import org.example.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ProductService {

    private final BoardService boardService;
    private final DetailBoardService detailBoardService;
//    제품 등록 구문
    private final ProductRepository productRepository;
    private final ProductOptionRepository productOptionRepository;


    public Product createProduct(List<MultipartFile> files, List<MultipartFile> detailfiles, List<String> optionNames, List<Long> optionPrices, String productName, Long price, Long productNumber, String type , String parcel) throws Exception {
        List<Board> boards = boardService.addBoard(files);
        List<DetailBoard> detailBoards = detailBoardService.addDetailBoard(detailfiles);

        List<ProductOption> productOptions = new ArrayList<>();
        for (int i = 0; i < optionNames.size(); i++) {
            ProductOption option = ProductOption.builder()
                    .optionName(optionNames.get(i))
                    .optionPrice(optionPrices.get(i))
                    .build();
            productOptions.add(option);
        }

        Product product = Product.builder()
                .boards(boards)
                .detailBoards(detailBoards)
                .productOption(productOptions.toString())
                .productName(productName)
                .price(price)
                .productNumber(productNumber)
                .type(type)
                .parcel(parcel)
                .build();

        for (Board board : boards) {
            board.setProduct(product);
        }

        productRepository.save(product);

        return product;
    }

//    상품 종류에 따른 리스트
    public List<Product> findByType(String type) {
        if (type == null || type.isEmpty()) {
            return this.productRepository.findAll();
        }
        return this.productRepository.findByType(type);
    }

//    id를 통해서 상품을 찾는 구문
    public Product findById(Long id) {
        return this.productRepository.findById(id).orElse(null);
    }


}