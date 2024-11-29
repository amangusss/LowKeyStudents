package alatoo.edu.kg.lowkeystudents.api.exceptions;

import org.springframework.http.HttpStatus;

public class UsernameTakenException extends BaseException {
    public UsernameTakenException() {
        super("Username is already taken", HttpStatus.CONFLICT);
    }
}