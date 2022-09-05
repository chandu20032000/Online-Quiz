package com.onlinequiz.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlinequiz.model.Score;
import com.onlinequiz.repository.ScoreRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/score")
@PreAuthorize("hasRole('ROLE_STUDENT') or hasRole('ROLE_ADMIN') or hasRole('ROLE_FACULTY')")
public class ScoreController {
	@Autowired
	ScoreRepository scoreRepository;
	@PostMapping
	ResponseEntity<?> addscore(@RequestBody Score score)
	{
		scoreRepository.save(score);
		return ResponseEntity.ok("score updated!");
		
	}
	
	@GetMapping("/all")
	ResponseEntity<?> getAllScore()
	{
		
		return ResponseEntity.ok(scoreRepository.findAll());
		
	}
	@GetMapping
	ResponseEntity<?> getByStudentId(@RequestParam String id)
	{
		List<Score> list =scoreRepository.findAll();
		list.removeIf(score->(!Objects.equals(score.getStudenId(), id)));
		return ResponseEntity.ok(list);
		
	}
	@GetMapping("/quiz")
	ResponseEntity<?> getByQuizId(@RequestParam String id)
	{
		List<Score> list =scoreRepository.findAll();
		list.removeIf(score->(!Objects.equals(score.getQuizId(), Long.parseLong(id))));
		return ResponseEntity.ok(list);
		
	}
	

}
