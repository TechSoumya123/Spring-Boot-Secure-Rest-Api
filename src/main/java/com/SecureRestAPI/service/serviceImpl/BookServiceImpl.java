package com.SecureRestAPI.service.serviceImpl;

import static com.SecureRestAPI.Util.Class_Mapping.*;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.SecureRestAPI.exception.BookNotFoundException;
import com.SecureRestAPI.model.Book;
import com.SecureRestAPI.repository.BookRepository;
import com.SecureRestAPI.service.BookService;

@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Optional<Book> findById(Long bookId) {
		return Optional.ofNullable(bookRepository.findById(bookId)
				.orElseThrow(() -> new BookNotFoundException("No Book found with this id : " + bookId)));
	}

	@Override
	public Book addBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public void deleteBookById(Long bookId) {
		findById(bookId).ifPresent(bookRepository::delete);
	}

	@Transactional
	@Override
	public Book updateBook(Long bookId, Book book) {
		return findById(bookId).map(existingBook -> updatedBook(existingBook, book)).get();
	}

}
