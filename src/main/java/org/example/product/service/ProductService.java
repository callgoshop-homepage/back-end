package org.example.product.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.board.controller.DetailFileHandler;
import org.example.board.controller.FileHandler;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    private final FileHandler fileHandler;
    private final DetailFileHandler detailFileHandler;


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
    @Transactional
    public Product modifyProduct(Long productId, List<MultipartFile> files, List<MultipartFile> detailfiles, List<String> mainFilesName, List<String> mainDetailFilesName, List<ProductOptionRequest> optionRequests, String productName, Long price, Long productNumber, String type, String parcel) throws Exception {
        Product existingProduct = findById(productId);

        // 기존 컬렉션을 명시적으로 업데이트
        if ((files != null && !files.isEmpty()) || (!mainFilesName.isEmpty() && mainFilesName != null)) {
            updateBoards(existingProduct, mainFilesName, files);
        }
        if ((detailfiles != null && !detailfiles.isEmpty()) || (!mainDetailFilesName.isEmpty() && mainDetailFilesName != null)) {
            updateDetailBoards(existingProduct, mainDetailFilesName, detailfiles);
        }
        if (optionRequests != null || !optionRequests.isEmpty()) {
            updateProductOptions(existingProduct, optionRequests);
        }

        existingProduct.setProductName(productName);
        existingProduct.setPrice(price);
        existingProduct.setProductNumber(productNumber);
        existingProduct.setType(type);
        existingProduct.setParcel(parcel);

        return productRepository.save(existingProduct);
    }

    private void updateBoards(Product product, List<String> mainFilesName, List<MultipartFile> files) throws Exception {
        List<Board> existingBoards = product.getBoards();

        String basePath = "/Users/yunchaehyeon/Desktop/GitHub/svelte-flow2/front-app/static/img";

        //        데이터베이스에 파일 이름 존재하는지 확인
        List<String> existionFileNames = existingBoards.stream()
                .map(Board::getOriginalFileName)
                .collect(Collectors.toList());

        //        파일 이름이 존재한다면 새 board리스트에 해당 데이터를 이전
        List<Board> boardsToKeep = existingBoards.stream()
                .filter(board -> mainFilesName.contains(board.getStoredFileName()))
                .collect(Collectors.toList());

        //        삭제할 파일 리스트
        List<Board> boardsToRemove = existingBoards.stream()
                .filter(board -> !mainFilesName.contains(board.getStoredFileName()))
                .collect(Collectors.toList());

        // 파일 시스템에서 실제 파일 삭제
        for (Board board : boardsToRemove) {
            File file = new File(basePath + board.getStoredFileName());
            if (file.exists()) {
                file.delete();
            }
        }

        existingBoards.clear();
        existingBoards.addAll(boardsToKeep);

        if (files != null && !files.isEmpty()) {
            List<Board> newBoards = fileHandler.parseFileInfo(files);
            for (Board board : newBoards) {
                board.setProduct(product);
                existingBoards.add(board);
            }
        }
    }

    private void updateDetailBoards(Product product, List<String> mainDetailFilesName, List<MultipartFile> detailfiles) throws Exception {
        List<DetailBoard> existingDetailBoards = product.getDetailBoards();
        String basePath = "/Users/choegyeonghyeon/Desktop/callgo shop 프로젝트/front-app/static/img/";

        //        데이터베이스에 파일 이름 존재하는지 확인
        List<String> existionDetailFileNames = existingDetailBoards.stream()
                .map(DetailBoard::getOriginalFileName)
                .collect(Collectors.toList());

        //        파일 이름이 존재한다면 새 board리스트에 해당 데이터를 이전
        List<DetailBoard> boardsToKeep = existingDetailBoards.stream()
                .filter(detailBoard -> mainDetailFilesName.contains(detailBoard.getStoredFileName()))
                .collect(Collectors.toList());

        //        삭제할 파일 리스트
        List<DetailBoard> boardsToRemove = existingDetailBoards.stream()
                .filter(detailBoard -> !mainDetailFilesName.contains(detailBoard.getStoredFileName()))
                .collect(Collectors.toList());

        // 파일 시스템에서 실제 파일 삭제
        for (DetailBoard detailBoard : boardsToRemove) {
            File file = new File(basePath + detailBoard.getStoredFileName());
            if (file.exists()) {
                file.delete();
            }
        }

        existingDetailBoards.clear();
        existingDetailBoards.addAll(boardsToKeep);

        if (detailfiles != null && !detailfiles.isEmpty()) {
            List<DetailBoard> newDetailBoards = detailFileHandler.parseFileInfo(detailfiles);
            for (DetailBoard detailBoard : newDetailBoards) {
                detailBoard.setProduct(product);
                existingDetailBoards.add(detailBoard);
            }
        }
    }

    private void updateProductOptions(Product product, List<ProductOptionRequest> optionRequests) {
        List<ProductOption> existingProductOptions = product.getProductOptions();
        existingProductOptions.clear();
        if (optionRequests != null && !optionRequests.isEmpty()) {
            for (ProductOptionRequest optionRequest : optionRequests) {
                ProductOption option = ProductOption.builder()
                        .optionName(optionRequest.getOptionName())
                        .optionPrice(optionRequest.getOptionPrice())
                        .build();
                option.setProduct(product);
                existingProductOptions.add(option);
            }
        }
    }
}