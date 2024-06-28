package org.example.board.service;

import lombok.RequiredArgsConstructor;
import org.example.Main;
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

    public List<MainBoard> addMainBoard(List<MultipartFile> files) throws Exception {
        // 파일을 저장하고 그 DetailBoard 에 대한 list 를 가지고 있는다
        List<MainBoard> list = mainFileHandler.parseFileInfo(files);

        if (list.isEmpty()){
            // TODO : 파일이 없을 땐 어떻게 해야할까.. 고민을 해보아야 할 것
        }
        // 파일에 대해 DB에 저장하고 가지고 있을 것
        else{
            List<MainBoard> pictureBeans = new ArrayList<>();
            for (MainBoard mainBoard : list) {
                pictureBeans.add(mainBoardRepository.save(mainBoard));
            }
        }
        return list;
    }

    public void modifyMainBoard(List<MultipartFile> files, List<String> mainFileName) throws Exception {
        if ((files != null && !files.isEmpty()) || (!mainFileName.isEmpty() && mainFileName != null)) {
            updateMainBoards(files, mainFileName);
        }
    }

    private void updateMainBoards(List<MultipartFile> files, List<String> mainFilesName) throws Exception {
        List<MainBoard> existingBoards = mainBoardRepository.findAll();
//        /Users/choegyeonghyeon/Desktop/callgo shop 프로젝트/front-app/static/img/
        String basePath = "/Users/choegyeonghyeon/Desktop/callgo shop 프로젝트/front-app/static/img/";

        //        데이터베이스에 파일 이름 존재하는지 확인
        List<String> existionFileNames = existingBoards.stream()
                .map(MainBoard::getOriginalFileName)
                .collect(Collectors.toList());

        //        파일 이름이 존재한다면 새 board리스트에 해당 데이터를 이전
        List<MainBoard> boardsToKeep = existingBoards.stream()
                .filter(board -> mainFilesName.contains(board.getStoredFileName()))
                .collect(Collectors.toList());

        //        삭제할 파일 리스트
        List<MainBoard> boardsToRemove = existingBoards.stream()
                .filter(board -> !mainFilesName.contains(board.getStoredFileName()))
                .collect(Collectors.toList());

        // 파일 시스템에서 실제 파일 삭제
        for (MainBoard mainBoard : boardsToRemove) {
            File file = new File(basePath + mainBoard.getStoredFileName());
            if (file.exists()) {
                file.delete();
            }
        }

        existingBoards.clear();
        existingBoards.addAll(boardsToKeep);

        if (files != null && !files.isEmpty()) {
            List<MainBoard> newBoards = mainFileHandler.parseFileInfo(files);
            for (MainBoard mainBoard : newBoards) {
                existingBoards.add(mainBoard);
            }
        }
    }

    public List<MainBoard> getMainBoardList() {
       return mainBoardRepository.findAll();
    }
}
