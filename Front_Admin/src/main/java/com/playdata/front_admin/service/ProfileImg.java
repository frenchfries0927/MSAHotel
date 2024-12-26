package com.playdata.front_admin.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ProfileImg {

    private final String uploadPath = "C:/EasyStay/img/user"; // 업로드 디렉토리

    // 파일 저장 메소드
    public String saveFile(MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            throw new IllegalArgumentException("파일이 비어있습니다.");
        }

        String originalFileName = multipartFile.getOriginalFilename(); // 원본 파일명
        String storeFilename = createStoreFilename(originalFileName); // UUID로 저장할 파일명 생성

        File targetFile = new File(uploadPath + File.separator + storeFilename);
        multipartFile.transferTo(targetFile);

        return storeFilename;
    }

    // 파일 삭제 메소드
    public void deleteFile(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            throw new IllegalArgumentException("삭제할 파일명이 유효하지 않습니다.");
        }

        File file = new File(uploadPath + File.separator + fileName);
        if (file.exists() && file.isFile()) {
            if (!file.delete()) {
                throw new RuntimeException("파일 삭제에 실패했습니다.");
            }
        }
    }

    // 파일 업데이트(수정) 메소드
    public String updateFile(MultipartFile newFile, String existingFileName) throws IOException {
        if (existingFileName != null && !existingFileName.isEmpty()) {
            deleteFile(existingFileName);
        }

        return saveFile(newFile);
    }

    // 파일명 생성 (UUID 기반)
    private String createStoreFilename(String originalFilename) {
        int pos = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(pos + 1);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }
}
