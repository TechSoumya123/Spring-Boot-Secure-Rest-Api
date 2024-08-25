package com.SecureRestAPI.service.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.SecureRestAPI.Util.Class_Mapping.*;

import com.SecureRestAPI.dto.UserRecord;
import com.SecureRestAPI.exception.UserAlreadyExistException;
import com.SecureRestAPI.exception.UserNotFoundException;
import com.SecureRestAPI.model.User;
import com.SecureRestAPI.repository.UserRepository;
import com.SecureRestAPI.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	private PasswordEncoder passwordEncoder;

	
	@Override
	public User addUser(User user) {
		if (userRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new UserAlreadyExistException("User Already Exists");
		} else {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			userRepository.save(user);
			return user;
		}
	}

	@Override
	public List<UserRecord> getAllusers() {
		return userRepository.findAll().stream().map(user -> new com.SecureRestAPI.dto.UserRecord(user.getId(),
				user.getFirstName(), user.getLastName(), user.getEmail())).collect(Collectors.toList());
	}

	@Override
	public Optional<User> getUser(String email) {
		return Optional
				.of(userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found!")));
	}

	@Transactional
	@Override
	public User updataUser(String email, User user) {
		return getUser(email).map(existingUser -> existingToUpdated(existingUser, user)).get();
	}

//	@Override
//	public User updataUser(User user) {
//		user.setRoles(user.getRoles());
//		return userRepository.save(user);
//	}

	@Override
	public void delete(String email) {
		getUser(email).ifPresent(userRepository::delete);
	}

}
