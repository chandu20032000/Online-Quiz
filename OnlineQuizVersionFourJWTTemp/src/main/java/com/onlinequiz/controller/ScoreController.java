package com.onlinequiz.controller;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger LOGGER = LoggerFactory.getLogger(ScoreController.class);

	// ---- rest API to add score of a student----
	@PostMapping
	ResponseEntity<?> addscore(@RequestBody Score score) {

		String methodName = "addscore";

		LOGGER.info(methodName + " " + "called");
		scoreRepository.save(score);
		return ResponseEntity.ok("score updated!");

	}

	// ----getting all the score that is obtained by the students
	@GetMapping("/all")
	ResponseEntity<?> getAllScore() {
		String methodName = "getAllScore";

		LOGGER.info(methodName + " " + "called");

		return ResponseEntity.ok(scoreRepository.findAll());

	}

	// ---- get all the student score by their id----
	@GetMapping
	ResponseEntity<?> getByStudentId(@RequestParam String id) {

		String methodName = "getByStudentId";

		LOGGER.info(methodName + " " + "called");
		List<Score> list = scoreRepository.findAll();
		list.removeIf(score -> (!Objects.equals(score.getStudenId(), id)));
		return ResponseEntity.ok(list);

	}

	// ----obtaining all the score with their quizid----
	@GetMapping("/quiz")
	ResponseEntity<?> getByQuizId(@RequestParam String id) {

		String methodName = "getByQuizId";

		LOGGER.info(methodName + " " + "called");
		List<Score> list = scoreRepository.findAll();
		list.removeIf(score -> (!Objects.equals(score.getQuizId(), Long.parseLong(id))));
		return ResponseEntity.ok(list);

	}

}