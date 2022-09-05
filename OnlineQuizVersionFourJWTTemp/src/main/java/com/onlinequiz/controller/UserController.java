package com.onlinequiz.controller;

import java.util.Optional;

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

import com.onlinequiz.dto.request.PasswordChangeModel;
import com.onlinequiz.dto.request.UserUpdate;
import com.onlinequiz.model.User;
import com.onlinequiz.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('ROLE_STUDENT') or hasRole('ROLE_ADMIN') or hasRole('ROLE_FACULTY')")
public class UserController {
	@Autowired
	  PasswordEncoder encoder;

	@Autowired
	UserRepository userRepository;
	@Autowired
	UserDetailsService userDetailsService;
	@GetMapping
	
    ResponseEntity<Optional<User> > getUserByname(@RequestParam String id)
    {
		 Optional<User> user = userRepository.findByUsername(id);
		return  ResponseEntity.ok(user);
    	
    }
@GetMapping("/byId")
	
    ResponseEntity<Optional<User> > getUserById(@RequestParam String id)
    {
		 Optional<User> user = userRepository.findById(id);
		return  ResponseEntity.ok(user);
    	
    }
	@PutMapping
	 ResponseEntity<?> updateUser(@RequestBody UserUpdate userUpdate,@RequestParam String id)
    {
		userUpdate.setPassword(encoder.encode(userUpdate.getPassword()) );
		
		  if(userRepository.findByUsername(id).isPresent())
		 {
	      User user=userRepository.findByUsername(id).get(); 
		 user.setAddress(userUpdate.getAddress());
		 user.setEmail(userUpdate.getEmail());
		 user.setMobile(userUpdate.getMobile());
		 user.setPassword(userUpdate.getPassword());
		 userRepository.save(user);
		 return  ResponseEntity.ok(user);
		 }
		  else {
		return ResponseEntity.ok("Not updated");
		  }
    	
    }
	@DeleteMapping
	 ResponseEntity<?> delete(@RequestParam String id)
    {
		
		
		  if(userRepository.findByUsername(id).isPresent())
		 {
	      User user=userRepository.findByUsername(id).get(); 
		    userRepository.delete(user);
		 return  ResponseEntity.ok("User deleted!");
		 }
		  else {
		return ResponseEntity.ok("Not updated");
		  }
    	
    }
	@PutMapping("/password")
	  ResponseEntity<?> updatePassword(@RequestBody PasswordChangeModel pass,@RequestParam String id)
   {
		String oldpassword= encoder.encode(pass.getOldPassword()) ;
		
		  if(userRepository.findByUsername(id).isPresent())
		 {
	       User user=userRepository.findByUsername(id).get(); 
		   user.setPassword(encoder.encode(pass.getNewPassword()));
		   userRepository.save(user);
		   return  ResponseEntity.ok("Password  has  changed");
		 }
		  else {
		return ResponseEntity.ok("Password  has not changed due to old password not match or internal server error !Try again");
		  }
   	
   }
	
}
