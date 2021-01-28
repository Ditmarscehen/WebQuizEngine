package engine.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class CompletedQuiz {
    @Id
    @JsonIgnore
    @GeneratedValue
    private int quizzzId;

    private String completedAt;
    private int id;

    @ManyToOne
    @JsonIgnore
    private User user;

    public CompletedQuiz(User user, Quiz quiz){
        this.user = user;
        this.setId(quiz.getId());
        completedAt = LocalDateTime.now().toString();
    }
}
