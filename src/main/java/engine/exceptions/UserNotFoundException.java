package engine.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class UserNotFoundException extends RuntimeException {
    private final String email;
    @Override
    public String getMessage() {
        return email;
    }
}
