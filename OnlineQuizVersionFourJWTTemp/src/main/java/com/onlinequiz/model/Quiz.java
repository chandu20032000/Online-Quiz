package com.onlinequiz.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table
public class Quiz {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@Column
	String name;
	@Column
	String userCreatedId;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	 @JoinTable(  name = "quiz_qa", 
     joinColumns = @JoinColumn(name = "quiz_id"), 
     inverseJoinColumns = @JoinColumn(name = "question_id"))
	 List<QuestionAnswer> questionsAndAnswers;
	public Quiz()
	{
		
	}
	
	public Quiz(String name, String userCreatedId, List<QuestionAnswer> questionsAndAnswers) {
		super();
		this.name = name;
		this.userCreatedId = userCreatedId;
		this.questionsAndAnswers = questionsAndAnswers;
	}

	public String getUserCreatedId() {
		return userCreatedId;
	}

	public void setUserCreatedId(String userCreatedId) {
		this.userCreatedId = userCreatedId;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<QuestionAnswer> getQuestionsAndAnswers() {
		return questionsAndAnswers;
	}
	public void setQuestionsAndAnswers(List<QuestionAnswer> questionsAndAnswers) {
		this.questionsAndAnswers = questionsAndAnswers;
	}
	
}
