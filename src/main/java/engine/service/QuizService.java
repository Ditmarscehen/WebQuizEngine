package engine.service;

import engine.model.*;
import org.springframework.data.domain.Page;


public interface QuizService {
    Quiz findById(int id);
    Page <Quiz> findAll(Integer page);
    Quiz addQuiz(Quiz quiz);
    Answer getAnswer(int id, UserAnswer answer, User user);
    void delete(Quiz quiz);
    Page<CompletedQuiz> getCompletedQuizzes(Integer page, User user);
}
