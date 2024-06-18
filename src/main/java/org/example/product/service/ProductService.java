package org.example.product.service;

import lombok.RequiredArgsConstructor;
import org.example.board.entity.Board;
import org.example.board.service.BoardService;
import org.example.product.entity.Product;
import org.example.product.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ProductService {

    private final BoardService boardService;
//    제품 등록 구문
    private final ProductRepository productRepository;


    public Product createProduct(List<MultipartFile> files, String productName, String price, Long productNumber, String type , String parcel) throws Exception {
        List<Board> boards = boardService.addBoard(files);

        Product product = Product.builder()
                .boards(boards)
                .productName(productName)
                .price(price)
                .productNumber(productNumber)
                .type(type)
                .parcel(parcel)
                .build();

        for (Board board : boards) {
            board.setProduct(product);
        }

        this.productRepository.save(product);
        return product;
    }
}
