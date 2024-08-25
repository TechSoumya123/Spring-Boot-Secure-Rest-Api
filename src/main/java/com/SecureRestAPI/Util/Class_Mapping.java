package com.SecureRestAPI.Util;

import org.springframework.stereotype.Component;

import com.SecureRestAPI.model.Book;
import com.SecureRestAPI.model.User;

@Component
public class Class_Mapping {

	public static Book updatedBook(Book existingBook, Book book) {
		return existingBook.setTitle(book.getTitle()).setAuthor(book.getAuthor()).setEdition(book.getEdition());

	}

	public static User existingToUpdated(User existingUser, User user) {
		return existingUser.setEmail(user.getEmail()).setFirstName(user.getFirstName()).setLastName(user.getLastName()).setRoles(user.getRoles());

	}

}
