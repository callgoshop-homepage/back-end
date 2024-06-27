package org.example.suggestion.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.global.rs.RsData;
import org.example.product.entity.Product;
import org.example.suggestion.entity.Suggestion;
import org.example.suggestion.survice.SuggestionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/suggestion", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class SuggestionController {

    private final SuggestionService suggestionService;

    //    추천 상품을 등록하는 구문
    @Data
    public static class SuggestionRequest {
        private List<Product> products;
    }

    @AllArgsConstructor
    @Getter
    public static class SuggestionResponse {
        private final Suggestion suggestions;
    }

    @PostMapping(value = "/add", consumes = APPLICATION_JSON_VALUE)
    public RsData<SuggestionResponse> createSuggestion(@RequestBody SuggestionRequest suggestionRequest) {

        Suggestion suggestions = suggestionService.save(suggestionRequest.getProducts());

        return RsData.of("S-30", "추천 목록 저장", new SuggestionResponse(suggestions));
    }
}
