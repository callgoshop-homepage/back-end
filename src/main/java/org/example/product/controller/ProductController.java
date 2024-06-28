package org.example.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.springframework.util.MimeTypeUtils.ALL_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/product")
public class ProductController {

    private final ProductService productService;
    private final ObjectMapper objectMapper;

    //    제품 등록
    @AllArgsConstructor
    @Getter
    public static class ProductResponse {
        private final Product product;
    }

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RsData<ProductResponse> create(@RequestParam("files") List<MultipartFile> files,
                                          @RequestParam("detailfiles") List<MultipartFile> detailfiles,
                                          @RequestParam("productName") String productName,
                                          @RequestParam("price") Long price,
                                          @RequestParam("productNumber") Long productNumber,
                                          @RequestParam("type") String type,
                                          @RequestParam("parcel") String parcel,
                                          @RequestParam("options") String optionsJson) throws Exception {

        List<ProductOptionRequest> optionRequests = objectMapper.readValue(optionsJson, objectMapper.getTypeFactory().constructCollectionType(List.class, ProductOptionRequest.class));

        Product product = productService.createProduct(files, detailfiles, optionRequests, productName, price, productNumber, type, parcel);
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

    //    신상품 등록 리스트
    @GetMapping(value = "/recentlist", consumes = APPLICATION_JSON_VALUE)
    public RsData<ProductsListResponse> productRecentList() {

        List<Product> recentList = productService.recentProduct();

        return RsData.of("S-12", "신상품 조회 성공", new ProductsListResponse(recentList));
    }

    //    등록된 상품 삭제하는 구문
    @DeleteMapping("/delete/{id}")
    public RsData<?> productDelete(@PathVariable(value = "id") Long id) {
        productService.deleteProduct(id);

        return RsData.of("S-13", "상품 삭제 성공", null);
    }

    //    등록된 상품 수정하는 구문
    @AllArgsConstructor
    @Getter
    public static class ModifyProductResponse {
        private final Product product;
    }

    @PostMapping(value = "/modify", consumes = ALL_VALUE)
    public RsData<ModifyProductResponse> modify(@RequestParam("id") Long id,
                                                @RequestParam(value = "files", required = false) List<MultipartFile> files,
                                                @RequestParam(value = "detailfiles", required = false) List<MultipartFile> detailfiles,
                                                @RequestParam("mainFilesName") List<String> mainFilesName,
                                                @RequestParam("mainDetailFilesName") List<String> mainDetailFilesName,
                                                @RequestParam("productName") String productName,
                                                @RequestParam("price") Long price,
                                                @RequestParam("productNumber") Long productNumber,
                                                @RequestParam("type") String type,
                                                @RequestParam("parcel") String parcel,
                                                @RequestParam("options") String optionsJson) throws Exception {

        List<ProductOptionRequest> optionRequests = objectMapper.readValue(optionsJson, objectMapper.getTypeFactory().constructCollectionType(List.class, ProductOptionRequest.class));

        productService.modifyProduct(id, files, detailfiles, mainFilesName, mainDetailFilesName, optionRequests, productName, price, productNumber, type, parcel);

        return RsData.of("S-15", "수정 성공", null);
    }
}