package com.onlinequiz.controller;

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
@GetMapping
@PreAuthorize(" hasRole('ROLE_ADMIN') or hasRole('ROLE_FACULTY')")
ResponseEntity<?> getAllUser()
{
	
	return ResponseEntity.ok(userRepository.findAll());
	
}
}
