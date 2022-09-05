package com.onlinequiz;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

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
import com.onlinequiz.dto.request.CreateQuizModel;
import com.onlinequiz.dto.request.LoginRequest;
import com.onlinequiz.dto.request.QuestionAnswerModel;
import com.onlinequiz.dto.request.SignupRequest;

@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class QuizControllerTest {
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
	 void  cccreateQuizModel() throws Exception{
		  
		CreateQuizModel createQuizModel=new CreateQuizModel();
		 createQuizModel.setName("ddddddd-ddddd-dddd");
		 createQuizModel.setUserCreatedId("xxxxx-xxxxx-xxxxxxx");
		 QuestionAnswerModel questionAnswerModel=new QuestionAnswerModel();
		 questionAnswerModel.setQuestion("who is rama?");
		 questionAnswerModel.setOp1("ss");
		 questionAnswerModel.setOp2("ss");
		 questionAnswerModel.setOp3("ss");
		 questionAnswerModel.setOp4("ss");
		 questionAnswerModel.setCorrect("ss");
		 List<QuestionAnswerModel> list=new ArrayList<>();
		 list.add(questionAnswerModel);
		 createQuizModel.setQuestionAnswers(list);
		 SignupRequest signupRequest=new SignupRequest("Rakesh1", "Rakesh1@gmail.com", "Faculty", "saichand.", "SDPT","1234567891");
		 signup(signupRequest);
		 String token=login("Rakesh1", "saichand.");
		 mockMvc.perform(post("/quiz", 42L)
				 .header("Authorization", "Bearer ".concat(token))
		            .contentType("application/json")
		            .content(objectMapper.writeValueAsString(createQuizModel)))
		            .andExpect(status().isOk());
		 
		 
		 
		  
	  }
	  
	  @Test
	  @Order(2)
	  void updateUQuiz() throws Exception{
		  CreateQuizModel createQuizModel=new CreateQuizModel();
			 createQuizModel.setName("ddddddd-ddddd-dddd");
			 createQuizModel.setUserCreatedId("xxxxx-xxxxx-xxxxxxx");
			 QuestionAnswerModel questionAnswerModel=new QuestionAnswerModel();
			 questionAnswerModel.setQuestion("who is rama?");
			 questionAnswerModel.setOp1("ss");
			 questionAnswerModel.setOp2("ss");
			 questionAnswerModel.setOp3("ss");
			 questionAnswerModel.setOp4("ss");
			 questionAnswerModel.setCorrect("ss");
			 List<QuestionAnswerModel> list=new ArrayList<>();
			 list.add(questionAnswerModel);
			 createQuizModel.setQuestionAnswers(list);
			 String token=login("Rakesh1", "saichand.");
			 mockMvc.perform(put("/quiz?id=12", 42L)
					 .header("Authorization", "Bearer ".concat(token))
			            .contentType("application/json")
			            .content(objectMapper.writeValueAsString(createQuizModel)))
			            .andExpect(status().isOk());
	  }
	  @Test
	  @Order(3)
	   void viewAllQUizes() throws Exception{
		  String token=login("Rakesh1", "saichand.");
			 mockMvc.perform(get("/quiz/all?id=xxxxx-xxxxx-xxxxxxx", 42L)
					 .header("Authorization", "Bearer ".concat(token))
			            .contentType("application/json")
			            .content(objectMapper.writeValueAsString(null)))
			            .andExpect(status().isOk());
	  }
	  @Test
	  @Order(4)
	   void viewQuizById() throws Exception{
		  String token=login("Rakesh1", "saichand.");
			 mockMvc.perform(get("/quiz?id=12", 42L)
					 .header("Authorization", "Bearer ".concat(token))
			            .contentType("application/json")
			            .content(objectMapper.writeValueAsString(null)))
			            .andExpect(status().isOk());
	  }
	  
	  
	  
	  @Test
	  @Order(5)
	  void deleteUser() throws Exception
	  {
		  String token =login("Rakesh1", "saichand.");
			 MvcResult resultdelete= mockMvc.perform(delete("/user?id="+"Rakesh1", 42L)
			            .header("Authorization", "Bearer ".concat( token))
			            .content(objectMapper.writeValueAsString(null)))
			            .andExpect(status().isOk()).andReturn();
	  }
	  
	  
}
