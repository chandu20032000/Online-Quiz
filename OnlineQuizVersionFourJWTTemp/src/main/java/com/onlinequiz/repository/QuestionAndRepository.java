package com.onlinequiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinequiz.model.QuestionAnswer;

public interface QuestionAndRepository extends JpaRepository<QuestionAnswer, Long> {

}
