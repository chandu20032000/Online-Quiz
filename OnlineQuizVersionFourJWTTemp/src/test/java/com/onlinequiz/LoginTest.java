package com.onlinequiz;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinequiz.dto.request.LoginRequest;

@AutoConfigureMockMvc
@SpringBootTest
public class LoginTest{ 
	
	 @Autowired
	  private MockMvc mockMvc;
	  @Autowired
	  private ObjectMapper objectMapper;
	  
	  
	  void loginTest() throws Exception{
		  LoginRequest loginRequest=new LoginRequest("rakesh","saichand.");
		  
		  MvcResult result= mockMvc.perform(post("/login", 42L)
		            .contentType("application/json")
		            .content(objectMapper.writeValueAsString(loginRequest)))
		            .andExpect(status().isOk()).andReturn();
		  
		 
	  }
	 
	  void deleteUser() throws Exception{
		 
		 
         LoginRequest loginRequest=new LoginRequest("rakesh","saichand.");
		  
		  MvcResult result= mockMvc.perform(post("/login", 42L)
		            .contentType("application/json")
		            .content(objectMapper.writeValueAsString(loginRequest)))
		            .andExpect(status().isOk()).andReturn();
		  String content=result.getResponse().getContentAsString();
		  JSONParser parser = new JSONParser(content);  
		  String token= parser.getToken(4).toString(); 
		  MvcResult resultdelete= mockMvc.perform(delete("/user?id="+"rakesh", 42L)
		            .header("Authorization", "Bearer ".concat( token.substring(1,token.length()-1)))
		            .content(objectMapper.writeValueAsString(null)))
		            .andExpect(status().isOk()).andReturn();
		 
		   
		 
	  }
	  

}
