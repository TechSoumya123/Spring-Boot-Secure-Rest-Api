package com.SecureRestAPI.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SecureRestAPI.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
