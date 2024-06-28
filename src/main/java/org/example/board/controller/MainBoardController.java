package org.example.board.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.board.entity.Board;
import org.example.board.entity.MainBoard;
import org.example.board.service.MainBoardService;
import org.example.global.rs.RsData;
import org.example.product.entity.Product;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.util.MimeTypeUtils.ALL_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/board")
public class MainBoardController {

    private final MainBoardService mainBoardService;

    @AllArgsConstructor
    @Getter
    public static class ProductResponse {
        private final List<MainBoard> mainBoards;
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RsData<ProductResponse> addBoard(@RequestParam(value = "mainfile", required = false) List<MultipartFile> mainfile) throws Exception {

        List<MainBoard> boards = mainBoardService.addMainBoard(mainfile);

        return RsData.of("S-50", "메인 사진 등록 성공", new ProductResponse(boards));
    }

    @PutMapping(value = "/modify", consumes = ALL_VALUE)
    public RsData<?> modifyBoard(@RequestParam(value = "mainfile", required = false) List<MultipartFile> mainfile,
                                 @RequestParam(value = "mainfilesname", required = false) List<String> mainFilesName)throws Exception {
        mainBoardService.modifyMainBoard(mainfile, mainFilesName);

        return RsData.of("S-52", "수정 완", null);
    }

    //    메인 사진 리스트 불러오는 구문
    @GetMapping(value = "/list", consumes = APPLICATION_JSON_VALUE)
    public RsData<ProductResponse> lists() {
        List<MainBoard> boardList = mainBoardService.getMainBoardList();

        return RsData.of("S-51", "메인 사진 불러오기 성공", new ProductResponse(boardList));
    }
}
