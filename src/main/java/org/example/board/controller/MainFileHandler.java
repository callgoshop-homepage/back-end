package org.example.board.controller;

import org.example.board.entity.Board;
import org.example.board.entity.MainBoard;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class MainFileHandler {

    private final String uploadDir;

    public MainFileHandler(@Value("${image.upload.dir}") String uploadDir) {
        this.uploadDir = uploadDir;
    }

    public List<MainBoard> parseFileInfo(List<MultipartFile> multipartFiles) throws Exception {

        // 반환을 할 파일 리스트
        List<MainBoard> fileList = new ArrayList<>();

        // 파일이 빈 것이 들어오면 빈 것을 반환
        if (multipartFiles.isEmpty()) {
            return fileList;
        }
        // 파일 이름을 업로드 한 날짜로 바꾸어서 저장할 것이다
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//        String current_date = simpleDateFormat.format(new Date());

        // 프로젝트 폴더에 저장하기 위해 절대경로를 설정 (Window 의 Tomcat 은 Temp 파일을 이용한다)
        String absolutePath = new File("").getAbsolutePath() + "/";

//        mac은 역슬래쉬 아님 슬래쉬 한개 / window 는 역슬래쉬 \\두개

        // 경로를 지정하고 그곳에다가 저장
//        /Users/choegyeonghyeon/Desktop/callgo shop 프로젝트/front-app/static/img
        String path = "/Users/choegyeonghyeon/Desktop/callgo shop 프로젝트/front-app/static/img";
//        current_date 일단 생략
        File file = new File(uploadDir);
        // 저장할 위치의 디렉토리가 존지하지 않을 경우
        if (!file.exists()) {
            // mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
            file.mkdirs();
        }

        for (MultipartFile multipartFile : multipartFiles) {
            // 파일이 비어 있지 않을 때 작업을 시작해야 오류가 나지 않는다
            if (!multipartFile.isEmpty()) {
                // jpeg, png, gif 파일들만 받아서 처리할 예정
                String contentType = multipartFile.getContentType();
                String originalFileExtension;
                if (ObjectUtils.isEmpty(contentType)) {
                    break;
                } else {
                    if (contentType.contains("image/jpeg")) {
                        originalFileExtension = ".jpg";
                    } else if (contentType.contains("image/png")) {
                        originalFileExtension = ".png";
                    } else if (contentType.contains("image/gif")) {
                        originalFileExtension = ".gif";
                    }
                    // 다른 파일 명이면 아무 일 하지 않는다
                    else {
                        break;
                    }
                }
                // 각 이름은 겹치면 안되므로 나노 초까지 동원하여 지정
                String new_file_name = System.nanoTime() + originalFileExtension;
                // 생성 후 리스트에 추가
                MainBoard mainBoard = createMainBoardObject(multipartFile, uploadDir, new_file_name);
                fileList.add(mainBoard);

                // 저장된 파일로 변경하여 이를 보여주기 위함
                file = new File(uploadDir + "/" + new_file_name);
                multipartFile.transferTo(file);
            }
        }
        return fileList;
    }
    private MainBoard createMainBoardObject(MultipartFile multipartFile, String path, String new_file_name) {
        return MainBoard.builder()
                .originalFileName(multipartFile.getOriginalFilename())
                .storedFileName(new_file_name)
                .fileSize(multipartFile.getSize())
                .createDate(LocalDateTime.now())
                .build();
    }
}
