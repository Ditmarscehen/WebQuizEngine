package engine.controller;

import engine.exceptions.PermissionDeniedException;
import engine.model.*;
import engine.service.QuizServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    @Autowired
    private QuizServiceImpl service;

    @PostMapping(consumes = "application/json")
    public Quiz createQuiz(@AuthenticationPrincipal User user, @Valid @RequestBody Quiz quiz) {
        quiz.setUser(user);
        return service.addQuiz(quiz);
    }

    @GetMapping("/{id}")
    public Quiz getQuizById(@PathVariable int id) {
        return service.findById(id);
    }

    @GetMapping
    public Page<Quiz> getAllQuizzes(@RequestParam(required = false) Integer page) {
        return service.findAll(page);
    }

    @PostMapping(path = "/{id}/solve")
    public Answer getAnswer(@PathVariable int id, @RequestBody UserAnswer answer, @AuthenticationPrincipal User user) {
       return service.getAnswer(id,answer,user);
    }

    @GetMapping("/completed")
    public Page<CompletedQuiz> getCompletedQuizzes(@RequestParam(required = false) Integer page, @AuthenticationPrincipal User user) {
        return service.getCompletedQuizzes(page, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@AuthenticationPrincipal User user, @PathVariable Integer id) {
        Quiz quiz = service.findById(id);
        if (quiz.getUser().equals(user)) {
            service.delete(quiz);
            return ResponseEntity.noContent().build();
        } else throw new PermissionDeniedException(id);
    }

}
