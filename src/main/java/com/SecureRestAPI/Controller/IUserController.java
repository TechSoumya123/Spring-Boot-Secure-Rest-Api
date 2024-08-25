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

import com.SecureRestAPI.dto.UserRecord;
import com.SecureRestAPI.model.User;

import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;

@Validated
@RequestMapping(path = "/api/v1/users")
public interface IUserController {

	@GetMapping(path = { "/getAll" })
	public ResponseEntity<List<UserRecord>> getAllusers();

	@GetMapping(path = { "/byEmail/{email}" })
	public ResponseEntity<?> getByEmail(@Nonnull @PathVariable("email") final String email);

	@PostMapping(path = { "/addUser" })
	public ResponseEntity<User> addUser(@RequestBody @Valid User user);

	@PutMapping(path = { "/update/{email}" })
	public ResponseEntity<Object> updateUser(@Email @PathVariable("email") final String userEmail,
			@RequestBody @Valid User user);

	@DeleteMapping(path = { "/delete/{email}" })
	public ResponseEntity<?> deleteByEmail(@Email @PathVariable("email") final String email);
}
