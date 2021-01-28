package engine.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {
    boolean success;
    String feedback;
    public static Answer ON_SUCCESS = new Answer(true,"Congratulations, you're right!");
    public static Answer ON_FAILURE = new Answer(false,"Wrong answer! Please, try again.");
}
