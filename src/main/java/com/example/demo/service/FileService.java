package com.example.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class FileService {


    @Value("${uploadPath}")
    public String uploadPath;

    public String uploadImage(MultipartFile bookImageFile) throws Exception{
        String originalFileName = bookImageFile.getOriginalFilename();

        UUID uuid = UUID.randomUUID();
//        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName = uuid.toString() + "_" + originalFileName;

        Path uploadPathVar = Path.of(uploadPath);
        Path filePath = uploadPathVar.resolve(savedFileName);

        if (!Files.exists(uploadPathVar)) {
            Files.createDirectories(uploadPathVar);
        }

        Files.copy(bookImageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return savedFileName;
    }

    public void deleteFile(String filePath) throws Exception{
        File deleteFile = new File(filePath);
        System.out.println(deleteFile);
        if(deleteFile.exists()){
            deleteFile.delete();
            log.info("파일을 삭제하였습니다.");
        }else{
            log.info("파일이 존재하지 않습니다.");
        }
    }
}
