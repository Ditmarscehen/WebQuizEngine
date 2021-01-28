package engine.service;

import engine.dao.CompletedQuizDao;
import engine.dao.QuizDao;
import engine.model.*;
import engine.exceptions.QuizNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
public class QuizServiceImpl implements QuizService {

    @Autowired
    private QuizDao quizDao;
    
    @Autowired
    private CompletedQuizDao completedQuizDao;

    public Page<Quiz> findAll(Integer page) {
        Pageable pageable = PageRequest.of(page == null ? 0 : page, 10);
        return quizDao.findAll(pageable);
    }

    public Quiz findById(int id) {
        return quizDao.findById(id)
                .orElseThrow(() -> new QuizNotFoundException(id));
    }

    public Quiz addQuiz(Quiz quiz) {
        quiz.nullAnswer();
        return quizDao.save(quiz);
    }

    public Answer getAnswer(int id, UserAnswer answer, User user) {
        Quiz quiz = findById(id);
        if (answer.getAnswer().equals(quiz.getAnswer())) {
            completedQuizDao.save(new CompletedQuiz(user, quiz));
            return Answer.ON_SUCCESS;
        } else return Answer.ON_FAILURE;
    }

    @Override
    public void delete(Quiz quiz) {
        quizDao.delete(quiz);
    }

    @Override
    public Page<CompletedQuiz> getCompletedQuizzes(Integer page, User user) {
        Pageable pageable = PageRequest.of(page == null ? 0 : page, 10);
        return completedQuizDao.findAllByUserOrderByCompletedAtDesc(user, pageable);
    }
}
