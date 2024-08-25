package com.SecureRestAPI.service;

import java.util.List;
import java.util.Optional;

import com.SecureRestAPI.model.Book;

public interface BookService {

	List<Book> getAllBooks();

	Book addBook(Book book);

	Optional<Book> findById(Long bookId);

	Book updateBook(Long bookId, Book book);

	void deleteBookById(Long bookId);

}
