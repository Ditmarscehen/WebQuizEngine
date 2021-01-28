package engine.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class QuizNotFoundException extends RuntimeException{
   private final Integer id;
   @Override
   public String getMessage() {
      return id.toString();
   }
}
