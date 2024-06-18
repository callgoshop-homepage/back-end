package org.example.product.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.board.entity.Board;
import org.example.board.service.BoardService;
import org.example.global.rs.RsData;
import org.example.product.entity.Product;
import org.example.product.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/product", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class ProductController {

    private final ProductService productService;

    //    제품 등록
    @AllArgsConstructor
    @Getter
    public static class ProductResponse {
        private final Product product;
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RsData<ProductResponse> create(@RequestParam("files") List<MultipartFile> files, @RequestParam("productName") String productName, @RequestParam("price") String price, @RequestParam("productNumber") Long productNumber, @RequestParam("type") String type, @RequestParam("parcel") String parcel) throws Exception {

        Product product = productService.createProduct(files, productName, price, productNumber, type, parcel);
        return RsData.of("S-10", "상품 등록 성공", new ProductResponse(product));
    }
}
