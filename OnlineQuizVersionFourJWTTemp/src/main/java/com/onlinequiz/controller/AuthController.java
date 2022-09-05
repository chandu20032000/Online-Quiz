package com.onlinequiz.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.onlinequiz.dto.request.LoginRequest;
import com.onlinequiz.dto.request.SignupRequest;
import com.onlinequiz.dto.response.JwtResponse;
import com.onlinequiz.dto.response.MessageResponse;
import com.onlinequiz.model.ERole;
import com.onlinequiz.model.Role;
import com.onlinequiz.model.User;
import com.onlinequiz.repository.RoleRepository;
import com.onlinequiz.repository.UserRepository;
import com.onlinequiz.security.jwt.JwtUtils;
import com.onlinequiz.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller

public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;
  
  String jwtglobal;
  @ModelAttribute
  public void setResponseHeader(HttpServletResponse response) {
      response.setHeader("Authorization", "");
  }
  
  @ModelAttribute("setRole")
  public String setrole()
  {
	  return new String();
  }
 
  @PostMapping("/login")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
	  //Wrap login info in authentication object
	  
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
//pass authenticate object to Context
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    jwtglobal=jwt;
    //Get roles from UserDetailsImpl
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());
    
//return response in JSON object using JWTResponse class
     
     
     return ResponseEntity.ok( new JwtResponse(jwt, 
             userDetails.getId(), 
             userDetails.getUsername(), 
             userDetails.getEmail(),
             userDetails.getMobile(),
             userDetails.getAddress(),
             roles));
     
  }
  

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest)  {
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
    	return ResponseEntity
    	          .badRequest()
    	          .body(new MessageResponse("Error: Username is already taken!"));
    }

    if (userRepository.existsByEmail(signUpRequest.getEmail())) {
    	 return ResponseEntity
    	          .badRequest()
    	          .body(new MessageResponse("Error: Email is already in use!"));
    }

    // Create new user's account
    User user = new User(signUpRequest.getUsername(), 
               signUpRequest.getEmail(),
               encoder.encode(signUpRequest.getPassword()),
               signUpRequest.getMobile(),
               signUpRequest.getAddress()
               );

    String strRoles = signUpRequest.getRole();
    Set<Role> roles = new HashSet<>();

   /* if (strRoles==null) {
      Role studentRole = roleRepository.findByName(ERole.ROLE_STUDENT)
          .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
      roles.add(studentRole);
    } else {*/
      
        switch (strRoles) {
        case "admin":
          Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(adminRole);

          break;
        case "Student":
          Role studentRole = roleRepository.findByName(ERole.ROLE_STUDENT)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(studentRole);

          break;
        case "Faculty":
            Role facultyRole = roleRepository.findByName(ERole.ROLE_FACULTY)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(facultyRole);

            break;
        default:
          Role userRole = roleRepository.findByName(ERole.ROLE_STUDENT)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
          roles.add(userRole);
        }
      
    

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }
}
