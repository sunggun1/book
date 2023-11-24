package com.example.demo.entity;

import com.example.demo.dto.book.request.BookUpdateRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Book {

    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(name = "book_use_yn")
    private boolean bookUseYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Book(String name, boolean bookUseYn, User user) {
        this.name = name;
        this.bookUseYn = bookUseYn;
        this.user = user;
    }

    public void updateBook(BookUpdateRequestDto bookUpdateRequestDto){
        this.name = bookUpdateRequestDto.getName();
        this.bookUseYn = bookUpdateRequestDto.getUseYn();
    }

    public void borrowBook(){
        this.bookUseYn = true;
    }

    public void returnBook(){
        this.bookUseYn = false;
    }
}
