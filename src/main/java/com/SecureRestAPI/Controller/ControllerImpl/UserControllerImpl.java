package com.SecureRestAPI.Controller.ControllerImpl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.SecureRestAPI.Controller.IUserController;
import com.SecureRestAPI.dto.UserRecord;
import com.SecureRestAPI.exception.UserNotFoundException;
import com.SecureRestAPI.model.User;
import com.SecureRestAPI.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements IUserController {

	private final UserService userService;

	@Override
	public ResponseEntity<List<UserRecord>> getAllusers() {
		try {
			var allusers = userService.getAllusers();
			return allusers.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(allusers);
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Override
	public ResponseEntity<?> getByEmail(String email) {
		try {
			return new ResponseEntity<>(userService.getUser(email).get(), HttpStatus.OK);
		} catch (UserNotFoundException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getLocalizedMessage());
		}
	}

	@Override
	public ResponseEntity<User> addUser(User user) {
		try {
			return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@Override
	public ResponseEntity<Object> updateUser(String email, User user) {
		try {
			return new ResponseEntity<>(userService.updataUser(email, user), HttpStatus.OK);
		} catch (UserNotFoundException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}

	@Override
	public ResponseEntity<?> deleteByEmail(String email) {
		try {
			userService.delete(email);
			return ResponseEntity.ok("User deleted successfully");
		} catch (UserNotFoundException exception) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
		}
	}
}
