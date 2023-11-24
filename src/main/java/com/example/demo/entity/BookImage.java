package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class BookImage {
    @Id
    @Column(name = "book_image_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "book_ori_image_name")
    private String bookOriImageName;

    @Column(name = "book_image_name")
    private String bookImageName;

    @Column(name = "book_image_url")
    private String bookImageUrl;

    @Builder
    public BookImage(Book book, String bookOriImageName, String bookImageName, String bookImageUrl) {
        this.book = book;
        this.bookOriImageName = bookOriImageName;
        this.bookImageName = bookImageName;
        this.bookImageUrl = bookImageUrl;
    }

    public void updateBookImage(String bookOriImageName, String bookImageName, String bookImageUrl){
        this.bookOriImageName = bookOriImageName;
        this.bookImageName = bookImageName;
        this.bookImageUrl = bookImageUrl;
    }
}
