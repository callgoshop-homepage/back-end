package org.example.board.service;

import lombok.RequiredArgsConstructor;
import org.example.board.controller.DetailFileHandler;
import org.example.board.controller.FileHandler;
import org.example.board.entity.Board;
import org.example.board.entity.DetailBoard;
import org.example.board.repository.BoardRepository;
import org.example.board.repository.DetailBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DetailBoardService {

    private final DetailBoardRepository detailBoardRepository;
    private final DetailFileHandler detailFileHandler;

    @Autowired
    public DetailBoardService(DetailBoardRepository detailBoardRepository) {
        this.detailBoardRepository = detailBoardRepository;
        this.detailFileHandler = new DetailFileHandler();
    }
    public List<DetailBoard> addDetailBoard(List<MultipartFile> files) throws Exception {
        // 파일을 저장하고 그 DetailBoard 에 대한 list 를 가지고 있는다
        List<DetailBoard> list = detailFileHandler.parseFileInfo(files);

        if (list.isEmpty()){
            // TODO : 파일이 없을 땐 어떻게 해야할까.. 고민을 해보아야 할 것
        }
        // 파일에 대해 DB에 저장하고 가지고 있을 것
        else{
            List<DetailBoard> pictureBeans = new ArrayList<>();
            for (DetailBoard detailBoard : list) {
                pictureBeans.add(detailBoardRepository.save(detailBoard));
            }
        }
        return list;
    }
}
