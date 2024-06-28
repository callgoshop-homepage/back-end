package org.example.board.service;

import lombok.RequiredArgsConstructor;
import org.example.board.controller.DetailFileHandler;
import org.example.board.controller.MainFileHandler;
import org.example.board.entity.Board;
import org.example.board.entity.DetailBoard;
import org.example.board.entity.MainBoard;
import org.example.board.repository.DetailBoardRepository;
import org.example.board.repository.MainBoardRepository;
import org.example.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MainBoardService {

    private final MainBoardRepository mainBoardRepository;
    private final MainFileHandler mainFileHandler;

    @Autowired
    public MainBoardService(MainBoardRepository mainBoardRepository) {
        this.mainBoardRepository = mainBoardRepository;
        this.mainFileHandler = new MainFileHandler();
    }

    public List<MainBoard> addMainBoard(List<String> mainFilesName, List<MultipartFile> files) throws Exception {
        List<MainBoard> existingMainBoards = mainBoardRepository.findAll();
        String basePath = "/Users/choegyeonghyeon/Desktop/callgo shop 프로젝트/front-app/static/img/";

        //        유지할 파일 리스트
        List<MainBoard> boardsToKeep = existingMainBoards.stream()
                .filter(board -> mainFilesName.contains(board.getStoredFileName()))
                .collect(Collectors.toList());

        //        삭제할 파일 리스트
        List<MainBoard> boardsToRemove = existingMainBoards.stream()
                .filter(board -> !mainFilesName.contains(board.getStoredFileName()))
                .collect(Collectors.toList());

        // 파일 시스템에서 실제 파일 삭제
        for (MainBoard mainBoard : boardsToRemove) {
            File file = new File(basePath + mainBoard.getStoredFileName());
            if (file.exists()) {
                file.delete();
            }
            mainBoardRepository.delete(mainBoard);
        }

        if (files != null && !files.isEmpty()) {
            List<MainBoard> newBoards = mainFileHandler.parseFileInfo(files);
            for (MainBoard mainBoard : newBoards) {
                existingMainBoards.add(mainBoard);
            }
        }

        // 새로운 파일 추가
        List<MainBoard> newBoards = new ArrayList<>();
        if (files != null && !files.isEmpty()) {
            newBoards = mainFileHandler.parseFileInfo(files);
            for (MainBoard mainBoard : newBoards) {
                mainBoardRepository.save(mainBoard);
                boardsToKeep.add(mainBoard); // 유지할 파일 리스트에 새로 추가된 파일도 포함
            }
        }

        // 최종 결과 리스트 생성
        List<MainBoard> resultList = new ArrayList<>(boardsToKeep);

        return resultList;
    }


    public List<MainBoard> getMainBoardList() {
       return mainBoardRepository.findAll();
    }
}
