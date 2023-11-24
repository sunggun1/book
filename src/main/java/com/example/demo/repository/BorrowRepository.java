package com.example.demo.repository;

import com.example.demo.entity.Book;
import com.example.demo.entity.Borrow;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowRepository extends JpaRepository<Borrow, Long> {
    Optional<List<Borrow>> findBorrowsByBook(Book book);

    Optional<Borrow> findFirstByBookAndUserOrderByIdDesc(Book book, User user);
}
