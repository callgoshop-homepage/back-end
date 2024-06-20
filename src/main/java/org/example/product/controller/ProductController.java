package org.example.product.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.global.rs.RsData;
import org.example.product.entity.Product;
import org.example.product.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
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

    @Data
    public class ProductOptionRequest {
        private String optionName;
        private Long optionPrice;
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RsData<ProductResponse> create(@RequestParam("files") List<MultipartFile> files,
                                          @RequestParam("detailfiles") List<MultipartFile> detailfiles,
                                          @RequestParam("productName") String productName,
                                          @RequestParam("price") Long price,
                                          @RequestParam("productNumber") Long productNumber,
                                          @RequestParam("type") String type,
                                          @RequestParam("parcel") String parcel,
                                          @RequestParam("optionNames") List<String> optionNames,
                                          @RequestParam("optionPrices") List<Long> optionPrices) throws Exception {

        Product product = productService.createProduct(files, detailfiles, optionNames, optionPrices, productName, price, productNumber, type, parcel);
        return RsData.of("S-10", "상품 등록 성공", new ProductResponse(product));
    }

    //    상품 타입에 따른 리스트 조회
    @AllArgsConstructor
    @Getter
    public static class ProductsListResponse {
        private final List<Product> products;
    }

    @Data
    public static class ProductsRequest {
        private String type;
    }

    @PostMapping(value = "/lists", consumes = APPLICATION_JSON_VALUE)
    public RsData<ProductsListResponse> lists(@RequestBody ProductsRequest productsRequest) {
        List<Product> productList = this.productService.findByType(productsRequest.getType());
        return RsData.of("S-11", "상품 조회 성공", new ProductsListResponse(productList));
    }

//    상품을 클릭했을 때, 디테일로 들어가는 구문
    @AllArgsConstructor
    @Getter
    public static class detailResponse {
        private final Product product;
    }

    @GetMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    public RsData<detailResponse> productDetail(@PathVariable(value = "id") Long id) {

        Product product = productService.findById(id);

        return RsData.of("S-8", "성공", new detailResponse(product));
    }
}