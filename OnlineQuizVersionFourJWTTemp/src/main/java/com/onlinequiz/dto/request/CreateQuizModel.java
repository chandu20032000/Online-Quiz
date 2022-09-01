package com.onlinequiz.dto.request;

import java.util.List;



public class CreateQuizModel {
	String name;
	String userCreatedId;
	List<QuestionAnswerModel> questionAnswers;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<QuestionAnswerModel> getQuestionAnswers() {
		return questionAnswers;
	}
	public void setQuestionAnswers(List<QuestionAnswerModel> questionAnswers) {
		this.questionAnswers = questionAnswers;
	}
	public String getUserCreatedId() {
		return userCreatedId;
	}
	public void setUserCreatedId(String userCreatedId) {
		this.userCreatedId = userCreatedId;
	}
	
	
}
