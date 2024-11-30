package alatoo.edu.kg.lowkeystudents.api.exceptions;


import org.springframework.http.HttpStatus;

public class TokenRefreshException extends BaseException{
    public TokenRefreshException(String token, String message) {
        super("Phone number is already taken", HttpStatus.CONFLICT);
    }
}
