package com.onlinequiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.onlinequiz.model.Score;

public interface ScoreRepository extends JpaRepository<Score, Long>{

}
