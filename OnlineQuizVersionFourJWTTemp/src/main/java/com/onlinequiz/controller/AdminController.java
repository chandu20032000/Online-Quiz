package com.onlinequiz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.onlinequiz.repository.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserRepository userRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

	// ----Creating a REST API to get all the users----
	@GetMapping
	@PreAuthorize(" hasRole('ROLE_ADMIN') or hasRole('ROLE_FACULTY')")
	ResponseEntity<?> getAllUser() {
		String methodName = "getAllUser";

		LOGGER.info(methodName + " " + "called");

		return ResponseEntity.ok(userRepository.findAll());

	}
}
