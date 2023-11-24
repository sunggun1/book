package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.BookImage;
import com.example.demo.global.customException.BookImageCustomException;
import com.example.demo.repository.BookImageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookImageService {

    private final BookImageRepository bookImageRepository;
    private final FileService fileService;

    @Value("${uploadPath}")
    public String uploadPath;

    public void saveImage(Book savedBook, MultipartFile bookImageFile) throws Exception{
        String bookOriImageName = bookImageFile.getOriginalFilename();
        String bookImageName = fileService.uploadImage(bookImageFile);
        String fileImageSource = uploadPath + "/" + bookImageName;

        BookImage bookImage = BookImage.builder()
                .book(savedBook)
                .bookOriImageName(bookOriImageName)
                .bookImageName(bookImageName)
                .bookImageUrl(fileImageSource)
                .build();

        bookImageRepository.save(bookImage);
    }

    public void updateImage(Long bookImageId, MultipartFile bookImageFile) throws Exception{
        BookImage savedBookImage = bookImageRepository.findById(bookImageId).orElseThrow(() -> BookImageCustomException.ERORR_FROM_GET_IMAGE);
        if(!StringUtils.isEmpty(savedBookImage.getBookImageName())){
            fileService.deleteFile(savedBookImage.getBookImageUrl());
        }

        String oriImageName = bookImageFile.getOriginalFilename();
        String imageName = fileService.uploadImage(bookImageFile);
        String imgUrl = uploadPath + "/" + imageName;
        savedBookImage.updateBookImage(oriImageName, imageName, imgUrl);
    }
}
