package engine.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class TakenEmailException extends RuntimeException{
    String email;
    @Override
    public String getMessage() {
        return email;
    }
}
