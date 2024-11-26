package alatoo.edu.kg.lowkeystudents.api.exceptions;

import org.springframework.http.HttpStatus;

public class AuthException extends BaseException{
    public AuthException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
