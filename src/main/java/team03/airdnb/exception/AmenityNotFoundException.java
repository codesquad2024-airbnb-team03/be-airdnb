package team03.airdnb.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AmenityNotFoundException extends RuntimeException {

    private final HttpStatus httpStatus;

    public AmenityNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.httpStatus = errorCode.getHttpStatus();
    }
}