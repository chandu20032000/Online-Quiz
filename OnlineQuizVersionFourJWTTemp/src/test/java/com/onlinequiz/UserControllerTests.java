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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onlinequiz.dto.request.LoginRequest;
import com.onlinequiz.dto.request.PasswordChangeModel;
import com.onlinequiz.dto.request.SignupRequest;
import com.onlinequiz.dto.request.UserUpdate;

@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class UserControllerTests {
    public String token1;
	@Autowired
	  private MockMvc mockMvc;

	  @Autowired
	  private ObjectMapper objectMapper;

	  @Order(1)
	  @Test
		void signupStudnet() throws Exception {
			
			SignupRequest signupRequest=new SignupRequest("Rakesh1", "Rakesh1@gmail.com", "Student", "saichand.", "SDPT","1234567891");
			
			 mockMvc.perform(post("/signup", 42L)
			            .contentType("application/json")
			            .content(objectMapper.writeValueAsString(signupRequest)))
			            .andExpect(status().isOk());
			

	  	}
	  
/*	  String loginTest() throws Exception{
		  LoginRequest loginRequest=new LoginRequest("Rakesh1","saichand.");
		  
		  MvcResult result= mockMvc.perform(post("/login", 42L)
		            .contentType("application/json")
		            .content(objectMapper.writeValueAsString(loginRequest)))
		            .andExpect(status().isOk()).andReturn();
		  String content=result.getResponse().getContentAsString();
		  JSONParser parser = new JSONParser(content);  
		  String token= parser.getToken(4).toString(); 
		  token1=token.substring(1,token.length()-1);
		  return token1;
		 
	  }*/
	  
	  @Order(2)
	  @Test
	  	void getUserBynameTest() throws Exception{
		  
		  LoginRequest loginRequest=new LoginRequest("Rakesh1","saichand.");
		  
		  MvcResult result= mockMvc.perform(post("/login", 42L)
		            .contentType("application/json")
		            .content(objectMapper.writeValueAsString(loginRequest)))
		            .andExpect(status().isOk()).andReturn();
		  String content=result.getResponse().getContentAsString();
		  JSONParser parser = new JSONParser(content);  
		  String token= parser.getToken(4).toString(); 
		   String token1=token.substring(1,token.length()-1);
		  
		  MvcResult resultget=mockMvc.perform(get("/user?id="+"Rakesh1",42L)
				    .header("Authorization", "Bearer ".concat( token1))
		            .content(objectMapper.writeValueAsString(null)))
		            .andExpect(status().isOk()).andReturn();
	  }
	  
	  @Order(3)
	  @Test
	  	void getUserByIdTest() throws Exception{
		  LoginRequest loginRequest=new LoginRequest("Rakesh1","saichand.");
		  
		  MvcResult result= mockMvc.perform(post("/login", 42L)
		            .contentType("application/json")
		            .content(objectMapper.writeValueAsString(loginRequest)))
		            .andExpect(status().isOk()).andReturn();
		  String content=result.getResponse().getContentAsString();
		  JSONParser parser = new JSONParser(content);  
		  String token= parser.getToken(4).toString(); 
		  String token1=token.substring(1,token.length()-1);
		  String idString=parser.getToken(12).toString();
		  String idString2=idString.substring(1,idString.length()-1);
		  
		  MvcResult resultget=mockMvc.perform(get("/user?id="+idString2,42L)
				    .header("Authorization", "Bearer ".concat( token1))
		            .content(objectMapper.writeValueAsString(null)))
		            .andExpect(status().isOk()).andReturn();
		  
	  }
	  @Order(6)
	  @Test
	  void deleteUser() throws Exception{
			 
			 
	         LoginRequest loginRequest=new LoginRequest("Rakesh1","saichand..");
			  
			  MvcResult result= mockMvc.perform(post("/login", 42L)
			            .contentType("application/json")
			            .content(objectMapper.writeValueAsString(loginRequest)))
			            .andExpect(status().isOk()).andReturn();
			  String content=result.getResponse().getContentAsString();
			  JSONParser parser = new JSONParser(content);  
			  String token= parser.getToken(4).toString(); 
			  MvcResult resultdelete= mockMvc.perform(delete("/user?id="+"Rakesh1", 42L)
			            .header("Authorization", "Bearer ".concat( token.substring(1,token.length()-1)))
			            .content(objectMapper.writeValueAsString(null)))
			            .andExpect(status().isOk()).andReturn();
	  
	  
}
	  @Order(4)
	  @Test
	  void updateUserTest() throws Exception{
		  LoginRequest loginRequest=new LoginRequest("Rakesh1","saichand.");
		  
		  MvcResult result= mockMvc.perform(post("/login", 42L)
		            .contentType("application/json")
		            .content(objectMapper.writeValueAsString(loginRequest)))
		            .andExpect(status().isOk()).andReturn();
		  String content=result.getResponse().getContentAsString();
		  JSONParser parser = new JSONParser(content);  
		  String token= parser.getToken(4).toString();
		  
		  UserUpdate userUpdate=new UserUpdate("7418520963", "Hyd", "saichand.", "Rakesh1@gmail.com");
		 
		  
		  mockMvc.perform( MockMvcRequestBuilders
			      .put("/user?id="+"Rakesh1", 42L)
			      .header("Authorization", "Bearer ".concat( token.substring(1,token.length()-1)))
			      .contentType("application/json")
			      .content(objectMapper.writeValueAsString(userUpdate)))
			      .andExpect(status().isOk()).andReturn();
	  }
	  
	  @Order(5)
	  @Test
	  void updatePasswordTest() throws Exception{
LoginRequest loginRequest=new LoginRequest("Rakesh1","saichand.");
		  
		  MvcResult result= mockMvc.perform(post("/login", 42L)
		            .contentType("application/json")
		            .content(objectMapper.writeValueAsString(loginRequest)))
		            .andExpect(status().isOk()).andReturn();
		  String content=result.getResponse().getContentAsString();
		  JSONParser parser = new JSONParser(content);  
		  String token= parser.getToken(4).toString();
		  
		  PasswordChangeModel passwordChangeModel=new PasswordChangeModel("saichand.", "saichand..");
		  
		  mockMvc.perform( MockMvcRequestBuilders
			      .put("/user/password?id="+"Rakesh1", 42L)
			      .header("Authorization", "Bearer ".concat( token.substring(1,token.length()-1)))
			      .contentType("application/json")
			      .content(objectMapper.writeValueAsString(passwordChangeModel)))
			      .andExpect(status().isOk()).andReturn();
	  }
}