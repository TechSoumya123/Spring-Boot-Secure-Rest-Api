package com.SecureRestAPI.service;

import java.util.List;
import java.util.Optional;

import com.SecureRestAPI.dto.UserRecord;
import com.SecureRestAPI.model.User;

public interface UserService {

	User addUser(User user);

	List<UserRecord> getAllusers();

	Optional<User> getUser(String email);

	User updataUser(String email, User user);

	void delete(String email);

}
