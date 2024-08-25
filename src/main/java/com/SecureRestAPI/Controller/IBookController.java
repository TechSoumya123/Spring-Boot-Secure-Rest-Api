package com.SecureRestAPI.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.SecureRestAPI.model.Book;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Validated
@RequestMapping(path = { "/api/v1/books" })
public interface IBookController {

	@GetMapping(path = { "/getAll" })
	public ResponseEntity<List<Book>> getAllBooks();

	@GetMapping(path = { "/book/{id}" })
	public ResponseEntity<?> getById(
			@PathVariable("id") @Min(value = 1, message = "Book id must be grater than 0") final Long bookId);

	@PostMapping(path = { "/addBook" })
	public ResponseEntity<Book> addBook(@RequestBody(required = true) @Valid Book book);

	@PutMapping(path = { "/update/{id}" })
	public ResponseEntity<?> updateBook(
			@PathVariable("id") @Min(value = 1, message = "Book id must be grater than 0") final Long bookId,
			@RequestBody @Valid Book book);

	@DeleteMapping(path = { "/book/delete/{id}" })
	public ResponseEntity<Object> deleteBook(
			@PathVariable("id") @Min(value = 1, message = "Book id must be grater than 0") Long bookId);

}
