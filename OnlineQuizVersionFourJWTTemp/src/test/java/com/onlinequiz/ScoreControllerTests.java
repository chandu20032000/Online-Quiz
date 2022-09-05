package com.onlinequiz;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.apache.tomcat.util.json.JSONParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinequiz.dto.request.LoginRequest;
import com.onlinequiz.dto.request.SignupRequest;
import com.onlinequiz.model.Score;

@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class ScoreControllerTests {

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
	  void addScoreTest() throws Exception{
		  SignupRequest signupRequest=new SignupRequest("Rakesh1", "Rakesh1@gmail.com", "Student", "saichand.", "SDPT","1234567891");
			signup(signupRequest);
			String token =login("Rakesh1", "saichand.");
			
			Score score=new Score("123", 42L, "98");
			
			 mockMvc.perform(post("/score", 42L)
					 .header("Authorization", "Bearer ".concat( token))
					 .contentType("application/json")
			            .content(objectMapper.writeValueAsString(score)))
			            .andExpect(status().isOk());
			
	  }
	
	    @Test
		@Order(2)
	    void getAllScoreTest() throws Exception{
			String token =login("Rakesh1", "saichand.");
			
			mockMvc.perform(get("/score/all", 42L)
					 .header("Authorization", "Bearer ".concat(token))
			            .contentType("application/json")
			            .content(objectMapper.writeValueAsString(null)))
			            .andExpect(status().isOk());
	  }
	    
	    @Test
		@Order(3)
	    void getScoreById() throws Exception{
			String token =login("Rakesh1", "saichand.");
			
					mockMvc.perform(get("/score?id="+8,42L)
				    .header("Authorization", "Bearer ".concat( token))
				    .contentType("application/json")
		            .content(objectMapper.writeValueAsString(null)))
		            .andExpect(status().isOk()).andReturn();
	  }
	    
	    @Test
		@Order(3)
	    void getScoreByQuizId() throws Exception{
			String token =login("Rakesh1", "saichand.");
			
					mockMvc.perform(get("/score/quiz?id="+123,42L)
				    .header("Authorization", "Bearer ".concat( token))
				    .contentType("application/json")
		            .content(objectMapper.writeValueAsString(null)))
		            .andExpect(status().isOk()).andReturn();
	  }
	    
	    @Test
		@Order(4)
		void deleteUser() throws Exception
		{
			String token =login("Rakesh1", "saichand.");
			        mockMvc.perform(delete("/user?id="+"Rakesh1", 42L)
		            .header("Authorization", "Bearer ".concat( token))
		            .content(objectMapper.writeValueAsString(null)))
		            .andExpect(status().isOk()).andReturn();
  
			
		}

}
