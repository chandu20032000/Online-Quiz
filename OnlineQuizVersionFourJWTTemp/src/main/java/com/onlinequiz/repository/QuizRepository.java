package com.onlinequiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinequiz.model.Quiz;

//----Quiz Repository----
public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
