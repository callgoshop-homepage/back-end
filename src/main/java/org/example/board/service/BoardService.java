package org.example.board.service;

import lombok.RequiredArgsConstructor;
import org.example.board.controller.FileHandler;
import org.example.board.entity.Board;
import org.example.board.repository.BoardRepository;
import org.example.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final FileHandler fileHandler;

    public List<Board> addBoard(List<MultipartFile> files) throws Exception {
        // 파일을 저장하고 그 Board 에 대한 list 를 가지고 있는다
        List<Board> list = fileHandler.parseFileInfo(files);

        if (list.isEmpty()){
            // TODO : 파일이 없을 땐 어떻게 해야할까.. 고민을 해보아야 할 것
        }
        // 파일에 대해 DB에 저장하고 가지고 있을 것
        else{
            List<Board> pictureBeans = new ArrayList<>();
            for (Board boards : list) {
                pictureBeans.add(boardRepository.save(boards));
            }
        }
        return list;
    }

    public Optional<Board> findBoard(Long id) {
        return boardRepository.findById(id);
    }

    public void deleteBoardsByProduct(Product existingProduct) {
        boardRepository.deleteByProduct(existingProduct);
    }
}
