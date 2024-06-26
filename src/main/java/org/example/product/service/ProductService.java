package org.example.product.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.board.entity.Board;
import org.example.board.entity.DetailBoard;
import org.example.board.service.BoardService;
import org.example.board.service.DetailBoardService;
import org.example.member.entity.Member;
import org.example.product.controller.ProductController;
import org.example.product.controller.ProductOptionRequest;
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

    public Product createProduct(List<MultipartFile> files, List<MultipartFile> detailfiles, List<ProductOptionRequest> optionRequests, String productName, Long price, Long productNumber, String type, String parcel) throws Exception {
        List<Board> boards = boardService.addBoard(files);
        List<DetailBoard> detailBoards = detailBoardService.addDetailBoard(detailfiles);

        List<ProductOption> productOptions = new ArrayList<>();
        for (ProductOptionRequest optionRequest : optionRequests) {
            ProductOption option = ProductOption.builder()
                    .optionName(optionRequest.getOptionName())
                    .optionPrice(optionRequest.getOptionPrice())
                    .build();
            productOptions.add(option);
        }

        Product product = Product.builder()
                .boards(boards)
                .detailBoards(detailBoards)
                .productOptions(productOptions)
                .productName(productName)
                .price(price)
                .productNumber(productNumber)
                .type(type)
                .parcel(parcel)
                .build();

        for (Board board : boards) {
            board.setProduct(product);
        }

        for (DetailBoard detailBoard : detailBoards) {
            detailBoard.setProduct(product);
        }

        for (ProductOption productOption : productOptions) {
            productOption.setProduct(product);
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

    //    신상품 리스트 조회하는 구문
    public List<Product> recentProduct() {
        return productRepository.findTop4ByOrderByCreateDateDesc();
    }

    //    등록된 상품 삭제하는 구문
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    //    추천 상품 등록하는 구문
    public List<Product> suggestProduct(List<Long> ids) {
        List<Product> products = new ArrayList<>();
        for (Long id : ids) {
            Optional<Product> product = productRepository.findById(id);
            products.add(product.orElse(null));
        }
        return products;
    }

    //    제품 수정하는 구문
    public Product modifyProduct(Long productId, List<MultipartFile> files, List<MultipartFile> detailfiles, List<ProductOptionRequest> optionRequests, String productName, Long price, Long productNumber, String type, String parcel) throws Exception {
        Product existingProduct = findById(productId);

        boardService.deleteBoardsByProduct(existingProduct);
        detailBoardService.deleteDetailBoardsByProduct(existingProduct);

        List<Board> boards = boardService.addBoard(files);
        List<DetailBoard> detailBoards = detailBoardService.addDetailBoard(detailfiles);

        List<ProductOption> productOptions = new ArrayList<>();
        for (ProductOptionRequest optionRequest : optionRequests) {
            ProductOption option = ProductOption.builder()
                    .optionName(optionRequest.getOptionName())
                    .optionPrice(optionRequest.getOptionPrice())
                    .build();
            productOptions.add(option);
        }

        existingProduct.setBoards(boards);
        existingProduct.setDetailBoards(detailBoards);
        existingProduct.setProductOptions(productOptions);
        existingProduct.setProductName(productName);
        existingProduct.setPrice(price);
        existingProduct.setProductNumber(productNumber);
        existingProduct.setType(type);
        existingProduct.setParcel(parcel);

        for (Board board : boards) {
            board.setProduct(existingProduct);
        }

        for (DetailBoard detailBoard : detailBoards) {
            detailBoard.setProduct(existingProduct);
        }

        for (ProductOption productOption : productOptions) {
            productOption.setProduct(existingProduct);
        }

        productRepository.save(existingProduct);

        return existingProduct;
    }
}