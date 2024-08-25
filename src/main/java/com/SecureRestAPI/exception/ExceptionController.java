package com.SecureRestAPI.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionController {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handlerException(MethodArgumentNotValidException exception) {
		Map<String, String> errors = new HashMap<>();
		exception.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> userNotFound(UserNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("error " + ex.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserAlreadyExistException.class)
	public ResponseEntity<String> userAlreadyExist(UserAlreadyExistException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("error " + exception.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<String> bookNotFound(BookNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("error " + exception.getMessage());
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException constraintViolationException) {
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(constraintViolationException
				.getConstraintViolations().stream().map(cv -> cv.getMessage()).findFirst().get());
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<Object> handleHttpRequestMethod(HttpRequestMethodNotSupportedException exception) {
		String error = "Requested method is " + exception.getMethod() + ", Supported method are "
				+ exception.getSupportedHttpMethods().stream().findFirst().get();
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(error);
	}

}
