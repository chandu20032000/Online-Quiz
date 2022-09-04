
package com.onlinequiz.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.onlinequiz.dto.request.CreateQuizModel;
import com.onlinequiz.dto.request.QuestionAnswerModel;
import com.onlinequiz.model.QuestionAnswer;
import com.onlinequiz.model.Quiz;
import com.onlinequiz.repository.QuestionAndRepository;
import com.onlinequiz.repository.QuizRepository;

@Controller
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	QuizRepository quizRepository;

	@Autowired
	QuestionAndRepository questionAndRepository;

	private static final Logger LOGGER = LoggerFactory.getLogger(QuizController.class);

	// ----Creating quiz with Question,4 Options and a correct answer ----
	@PreAuthorize(" hasRole('ROLE_FACULTY')")
	@PostMapping
	ResponseEntity<?> createQuiz(@RequestBody CreateQuizModel quizmodel) {
		String methodName = "createQuiz";

		LOGGER.info(methodName + " " + "called");
		Quiz quiz = new Quiz();
		quiz.setName(quizmodel.getName());
		List<QuestionAnswer> list = new ArrayList<>();
		for (QuestionAnswerModel qamodel : quizmodel.getQuestionAnswers()) {
			QuestionAnswer qa = new QuestionAnswer(qamodel.getQuestion(), qamodel.getOp1(), qamodel.getOp2(),
					qamodel.getOp3(), qamodel.getOp4(), qamodel.getCorrect());
			list.add(qa);
		}
		quiz.setQuestionsAndAnswers(list);
		quizRepository.save(quiz);
		return ResponseEntity.ok("quiz created!");

	}

	// ----getting all the created quiz information---
	@PreAuthorize("hasRole('ROLE_FACULTY') or hasRole('ROLE_STUDENT') ")
	@GetMapping("/all")
	ResponseEntity<?> getAllQuiz() {
		String methodName = "getAllQuiz";

		LOGGER.info(methodName + " " + "called");

		return ResponseEntity.ok(quizRepository.findAll());

	}

	// ----getting all the created quiz by id
	@PreAuthorize("hasRole('ROLE_FACULTY') or hasRole('ROLE_STUDENT') ")
	@GetMapping
	ResponseEntity<?> getQuiz(@RequestParam Long id) {
		String methodName = "getQuiz";

		LOGGER.info(methodName + " " + "called");

		return ResponseEntity.ok(quizRepository.findById(id));

	}

	// ----update an existing quiz----
	@PutMapping
	@PreAuthorize("hasRole('ROLE_FACULTY')")
	ResponseEntity<?> updateQuiz(@RequestParam Long id, @RequestBody CreateQuizModel createQuizModel) {
		String methodName = "updateQuiz";

		LOGGER.info(methodName + " " + "called");

		Optional<Quiz> quiz = quizRepository.findById(id);
		Quiz quiz1 = quiz.get();
		if (quiz.isPresent()) {

			quiz1.setName(createQuizModel.getName());
			List<QuestionAnswer> list = new ArrayList<>();
			for (QuestionAnswerModel qamodel : createQuizModel.getQuestionAnswers()) {
				QuestionAnswer qa = new QuestionAnswer(qamodel.getQuestion(), qamodel.getOp1(), qamodel.getOp2(),
						qamodel.getOp3(), qamodel.getOp4(), qamodel.getCorrect());
				list.add(qa);
			}
			quiz1.setQuestionsAndAnswers(list);
		}
		quizRepository.save(quiz1);
		return ResponseEntity.ok("quiz updated!");

	}

	// ---Delete a created quiz with it's id----
	@DeleteMapping
	@PreAuthorize("hasRole('ROLE_FACULTY')")
	ResponseEntity<?> deleteQuiz(@RequestParam Long id) {

		String methodName = "deleteQuiz";

		LOGGER.info(methodName + " " + "called");
		Optional<Quiz> quiz = quizRepository.findById(id);
		Quiz quiz1 = quiz.get();
		if (quiz.isPresent()) {
			questionAndRepository.deleteAll(quiz1.getQuestionsAndAnswers());
			quizRepository.delete(quiz1);

		} else {
			return ResponseEntity.ok("Quiz not found!");
		}

		return ResponseEntity.ok("quiz deleted!");

	}
}
