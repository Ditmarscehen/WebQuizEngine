package engine.dao;

import engine.model.CompletedQuiz;
import engine.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CompletedQuizDao extends JpaRepository<CompletedQuiz, Integer> {
    @Query("SELECT c FROM CompletedQuiz c where c.user = :user order by c.completedAt desc")
    Page<CompletedQuiz> findAllByUserOrderByCompletedAtDesc(User user, Pageable pageable);
}
