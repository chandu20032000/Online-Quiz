package com.onlinequiz.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class QuestionAnswer {
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 Long id;
 @Column
 String question;
 @Column
 String op1;
 @Column
 String op2;
 @Column
 String op3;
 @Column
 String op4;
 @Column
 String correct;
 
public QuestionAnswer(String question, String op1, String op2, String op3, String op4, String correct) {
	super();
	this.question = question;
	this.op1 = op1;
	this.op2 = op2;
	this.op3 = op3;
	this.op4 = op4;
	this.correct = correct;
}
public QuestionAnswer()
{
	
}
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getQuestion() {
	return question;
}
public void setQuestion(String question) {
	this.question = question;
}
public String getOp1() {
	return op1;
}
public void setOp1(String op1) {
	this.op1 = op1;
}
public String getOp2() {
	return op2;
}
public void setOp2(String op2) {
	this.op2 = op2;
}
public String getOp3() {
	return op3;
}
public void setOp3(String op3) {
	this.op3 = op3;
}
public String getOp4() {
	return op4;
}
public void setOp4(String op4) {
	this.op4 = op4;
}
public String getCorrect() {
	return correct;
}
public void setCorrect(String correct) {
	this.correct = correct;
}
 
}
