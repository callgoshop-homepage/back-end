package org.example.suggestion.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.global.rs.RsData;
import org.example.product.entity.Product;
import org.example.suggestion.entity.Suggestion;
import org.example.suggestion.survice.SuggestionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.util.MimeTypeUtils.ALL_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/suggestion", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
public class SuggestionController {

    private final SuggestionService suggestionService;

    //    추천 상품을 등록하는 구문
    @Data
    public static class SuggestionRequest {
        private List<Long> ids;
    }

    @AllArgsConstructor
    @Getter
    public static class SuggestionResponse {
        private final Suggestion suggestions;
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public RsData<SuggestionResponse> createSuggestion(@RequestBody SuggestionRequest suggestionRequest) {

        Suggestion suggestions = suggestionService.saveByIds(suggestionRequest.getIds());

        return RsData.of("S-30", "추천 목록 저장", new SuggestionResponse(suggestions));
    }

//    추천 상품을 불러오는 구문
    @GetMapping(value = "/list", consumes = ALL_VALUE)
    public RsData<SuggestionResponse> listSuggestions() {
        Suggestion suggestions = suggestionService.findAll();

        return RsData.of("S-31", "리스트 불러오기 성공", new SuggestionResponse(suggestions));
    }
}