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
class Signup {

	 @Autowired
	  private MockMvc mockMvc;

	  @Autowired
	  private ObjectMapper objectMapper;
  
	 
	  void deleteUser(int i) throws Exception{
		 
		 
         LoginRequest loginRequest=new LoginRequest("nikhitha"+i,"saichand.");
		  
		  MvcResult result= mockMvc.perform(post("/login", 42L)
		            .contentType("application/json")
		            .content(objectMapper.writeValueAsString(loginRequest)))
		            .andExpect(status().isOk()).andReturn();
		  String content=result.getResponse().getContentAsString();
		  JSONParser parser = new JSONParser(content);  
		  String token= parser.getToken(4).toString(); 
		  MvcResult resultdelete= mockMvc.perform(delete("/user?id="+"nikhitha"+i, 42L)
		            .header("Authorization", "Bearer ".concat( token.substring(1,token.length()-1)))
		            .content(objectMapper.writeValueAsString(null)))
		            .andExpect(status().isOk()).andReturn();
		 
		   
		 
	  }
	@Order(4)
	@Test
	void signupStudnet() throws Exception {
		
		SignupRequest signupRequest=new SignupRequest("nikhitha1", "nikhitha1@gmail.com", "Student", "saichand.", "SDPT","1234567891");
		
		 mockMvc.perform(post("/signup", 42L)
		            .contentType("application/json")
		            .content(objectMapper.writeValueAsString(signupRequest)))
		            .andExpect(status().isOk());
		
		
	}
	@Order(2)
	@Test
      void signupFaculty() throws Exception {
		
		SignupRequest signupRequest=new SignupRequest("nikhitha2", "nikhitha2@gmail.com", "Faculty", "saichand.", "SDPT","1234567891");
		
		 mockMvc.perform(post("/signup", 42L)
		            .contentType("application/json")
		            .content(objectMapper.writeValueAsString(signupRequest)))
		            .andExpect(status().isOk());
		
	}
	@Order(3)
	@Test
    void signupAdmin() throws Exception {
		
		SignupRequest signupRequest=new SignupRequest("nikhitha3", "nikhitha3@gmail.com", "admin", "saichand.", "SDPT","1234567891");
		
		 mockMvc.perform(post("/signup", 42L)
		            .contentType("application/json")
		            .content(objectMapper.writeValueAsString(signupRequest)))
		            .andExpect(status().isOk());
		
	}
	@Order(1)
	@Test
    void signupNull() throws Exception {
		
		SignupRequest signupRequest=new SignupRequest("nikhitha4", "nikhitha4@gmail.com", "null", "saichand.", "SDPT","1234567891");
		
		 mockMvc.perform(post("/signup", 42L)
		            .contentType("application/json")
		            .content(objectMapper.writeValueAsString(signupRequest)))
		            .andExpect(status().isOk());
		
	}
	@Order(5)
	@Test
    void signupdefault() throws Exception {
		
		SignupRequest signupRequest=new SignupRequest("nikhitha5", "nikhitha5@gmail.com", "xxxxxx", "saichand.", "SDPT","1234567891");
		
		 mockMvc.perform(post("/signup", 42L)
		            .contentType("application/json")
		            .content(objectMapper.writeValueAsString(signupRequest)))
		            .andExpect(status().isOk());
		
	}
	
	@Test
	@Order(6)
	void deletefor() throws Exception
	  {
		  for(int i=1;i<=5;i++)
		  {
			  deleteUser(i);
		  }
	  }
	
	
	
	
	
	
	
	
	
	

}
