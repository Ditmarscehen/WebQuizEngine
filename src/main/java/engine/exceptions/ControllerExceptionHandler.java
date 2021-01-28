package engine.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestControllerAdvice
public class ControllerExceptionHandler {
    private static final String NOT_FOUND_QUIZ_MESSAGE = "There is no such quiz with id: ";
    private static final String NOT_FOUND_USER_MESSAGE = "There is no such user with email: ";
    private static final String PERMISSION_DENIED_MESSAGE = "Permission denied for quiz with id: ";
    private static final String TAKEN_EMAIL = "This email is taken: ";


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->
                errors.put(((FieldError) error).getField(), error.getDefaultMessage()));
        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(QuizNotFoundException.class)
    public Map<String, String> handleNotFoundQuiz(QuizNotFoundException e) {
        return abstractHandle(e, NOT_FOUND_QUIZ_MESSAGE);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    public Map<String, String> handleNotFoundUser(UserNotFoundException e) {
        return abstractHandle(e, NOT_FOUND_USER_MESSAGE);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(PermissionDeniedException.class)
    public Map<String, String> handlePermissionDenied(PermissionDeniedException e) {
        return abstractHandle(e, PERMISSION_DENIED_MESSAGE);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(TakenEmailException.class)
    public Map<String, String> handleTakenEmail(TakenEmailException e) {
        return abstractHandle(e, TAKEN_EMAIL);
    }

    private Map<String, String> abstractHandle(RuntimeException e, String message) {
        Map<String, String> response = new HashMap<>();
        response.put("message", message + e.getMessage());
        response.put("error", e.getClass().getSimpleName());
        return response;
    }
}
