package com.onlinequiz;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
public class AdminTest {
 
	@Autowired
	  private MockMvc mockMvc;

	  @Autowired
	  private ObjectMapper objectMapper;
	  
	String login(String username ,String password) throws Exception
	{
		 LoginRequest loginRequest=new LoginRequest(username,password);
		 MvcResult result= mockMvc.perform(post("/login", 42L)
		            .contentType("application/json")
		            .content(objectMapper.writeValueAsString(loginRequest)))
		            .andExpect(status().isOk()).andReturn();
		  String content=result.getResponse().getContentAsString();
		  JSONParser parser = new JSONParser(content);  
		  String token= parser.getToken(4).toString();
		return token.substring(1,token.length()-1); 
	}
	
  void signup(SignupRequest signupRequest) throws Exception
  {
  	 mockMvc.perform(post("/signup", 42L)
		            .contentType("application/json")
		            .content(objectMapper.writeValueAsString(signupRequest)))
		            .andExpect(status().isOk());
  }
	@Test
	@Order(1)
	void findall() throws Exception{
		SignupRequest signupRequest=new SignupRequest("admin", "admin@gmail.com", "admin", "saichand.", "SDPT","1234567891");
		signup(signupRequest);
		String token =login("admin", "saichand.");
		 mockMvc.perform(get("/admin", 42L)
				 .header("Authorization", "Bearer ".concat(token))
		            .contentType("application/json")
		            .content(objectMapper.writeValueAsString(null)))
		            .andExpect(status().isOk());
	}
	@Test
	@Order(2)
	void deleteadmin() throws Exception
	{
		String token =login("admin", "saichand.");
		 MvcResult resultdelete= mockMvc.perform(delete("/user?id="+"admin", 42L)
		            .header("Authorization", "Bearer ".concat( token))
		            .content(objectMapper.writeValueAsString(null)))
		            .andExpect(status().isOk()).andReturn();
		
	}
	
	
}
