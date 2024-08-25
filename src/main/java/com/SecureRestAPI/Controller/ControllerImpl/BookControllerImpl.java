package com.SecureRestAPI.Controller.ControllerImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.SecureRestAPI.Controller.IBookController;
import com.SecureRestAPI.exception.BookNotFoundException;
import com.SecureRestAPI.model.Book;
import com.SecureRestAPI.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookControllerImpl implements IBookController {

	private final BookService bookService;

	final static Logger logger = LoggerFactory.getLogger(BookControllerImpl.class);

	@Override
	public ResponseEntity<List<Book>> getAllBooks() {
		try {
			var allBooks = bookService.getAllBooks();
			return allBooks.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(allBooks);
		} catch (Exception exception) {
			logger.error("Error getting resources from path", exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Override
	public ResponseEntity<?> getById(Long bookId) {
		try {
			logger.info("\n Get particular data  from database with this id\n {}", bookId);
			return new ResponseEntity<>(bookService.findById(bookId).get(), HttpStatus.OK);
		} catch (BookNotFoundException exception) {
			logger.error("\n Error getting resources from path \n {}", exception.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} catch (Exception exception) {
			logger.error("\n Error getting resources from path \n {}", exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Override
	public ResponseEntity<Book> addBook(Book book) {
		try {
			return new ResponseEntity<>(bookService.addBook(book), HttpStatus.CREATED);
		} catch (Exception exception) {
			logger.error("\n Error getting resources from path \n {}", exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Override
	public ResponseEntity<?> updateBook(Long bookId, Book book) {
		try {
			logger.info("\n Client requesting for updating book with this id \n {},{} ", bookId, book);
			return ResponseEntity.ok(bookService.updateBook(bookId, book));
		} catch (BookNotFoundException exception) {
			logger.error("\n Error getting resources from path \n {}", exception.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} catch (Exception exception) {
			logger.error("\n Error getting resources from path \n {}", exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@Override
	public ResponseEntity<Object> deleteBook(Long bookId) {
		try {
			logger.info("\n Requesting delete book from database with this id \n {} ", bookId);
			bookService.deleteBookById(bookId);
			return ResponseEntity.ok("Book deleted successfully");
		} catch (BookNotFoundException exception) {
			logger.error("\n Error getting resources from path \n {}", exception.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} catch (Exception exception) {
			logger.error("\n Error getting resources from path \n {}", exception.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

}
