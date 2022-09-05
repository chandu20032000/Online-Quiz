package com.onlinequiz.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlinequiz.dto.request.UserUpdate;
import com.onlinequiz.model.User;
import com.onlinequiz.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_STUDENT') or hasRole('ROLE_ADMIN') or hasRole('ROLE_FACULTY')")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserDetailsService userDetailsService;

	// ----get user information by id----
	@GetMapping
	ResponseEntity<Optional<User>> getUserById(@RequestParam String id) {
		String methodName = "getUserById";

		LOGGER.info(methodName + " " + "called");
		Optional<User> user = userRepository.findByUsername(id);
		return ResponseEntity.ok(user);

	}

	// ---updating an existing user information---
	@PutMapping
	ResponseEntity<?> updateUser(@RequestBody UserUpdate userUpdate, @RequestParam String id) {

		String methodName = "updateUser";

		LOGGER.info(methodName + " " + "called");
		userUpdate.setPassword(encoder.encode(userUpdate.getPassword()));

		if (userRepository.findByUsername(id).isPresent()) {
			User user = userRepository.findByUsername(id).get();
			user.setAddress(userUpdate.getAddress());
			user.setEmail(userUpdate.getEmail());
			user.setMobile(userUpdate.getMobile());
			user.setPassword(userUpdate.getPassword());
			userRepository.save(user);
			return ResponseEntity.ok(user);
		} else {
			return ResponseEntity.ok("Not updated");
		}

	}

	// ---deleting a particular user using id---
	@DeleteMapping
	ResponseEntity<?> delete(@RequestParam String id) {

		String methodName = "delete";

		LOGGER.info(methodName + " " + "called");

		if (userRepository.findByUsername(id).isPresent()) {
			User user = userRepository.findByUsername(id).get();
			userRepository.delete(user);
			return ResponseEntity.ok("User deleted!");
		} else {
			return ResponseEntity.ok("Not updated");
		}

	}

}
