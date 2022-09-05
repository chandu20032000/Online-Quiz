package com.onlinequiz;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.tomcat.util.json.JSONParser;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinequiz.dto.request.LoginRequest;
import com.onlinequiz.dto.request.SignupRequest;
@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class SignupUserTaken {
	 @Autowired
	  private MockMvc mockMvc;

	  @Autowired
	  private ObjectMapper objectMapper;
	  
	  @Test
		@Order(1)
	  void signupUser() throws Exception
		{
			SignupRequest signupRequest=new SignupRequest("nikitha", "nikhitha@gmail.com", "Student", "saichand.", "SDPT","1234567891");
			
			 mockMvc.perform(post("/signup", 42L)
			            .contentType("application/json")
			            .content(objectMapper.writeValueAsString(signupRequest)))
			            .andExpect(status().isOk());
			
			
		}
	@Test
	@Order(2)
	void signupUserTaken() throws Exception
	{
		SignupRequest signupRequest=new SignupRequest("nikitha", "nikhitha@gmail.com", "Student", "saichand.", "SDPT","1234567891");
		
		 mockMvc.perform(post("/signup", 42L)
		            .contentType("application/json")
		            .content(objectMapper.writeValueAsString(signupRequest)))
		            .andExpect(status().isBadRequest());
		
		
	}
	@Test
	@Order(3)
 void deleteUser() throws Exception{
		 
		 
         LoginRequest loginRequest=new LoginRequest("nikitha","saichand.");
		  
		  MvcResult result= mockMvc.perform(post("/login", 42L)
		            .contentType("application/json")
		            .content(objectMapper.writeValueAsString(loginRequest)))
		            .andExpect(status().isOk()).andReturn();
		  String content=result.getResponse().getContentAsString();
		  JSONParser parser = new JSONParser(content);  
		  String token= parser.getToken(4).toString(); 
		  MvcResult resultdelete= mockMvc.perform(delete("/user?id="+"nikitha", 42L)
		            .header("Authorization", "Bearer ".concat( token.substring(1,token.length()-1)))
		            .content(objectMapper.writeValueAsString(null)))
		            .andExpect(status().isOk()).andReturn();
		 
		   
		 
	  }
}
