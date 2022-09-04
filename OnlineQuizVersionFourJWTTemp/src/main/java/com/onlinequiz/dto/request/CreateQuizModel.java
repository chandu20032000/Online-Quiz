package com.onlinequiz.dto.request;

import java.util.List;



public class CreateQuizModel {
	String name;
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
	
	
}
