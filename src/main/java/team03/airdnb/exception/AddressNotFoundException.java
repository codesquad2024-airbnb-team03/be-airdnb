package team03.airdnb.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AddressNotFoundException extends RuntimeException {

    private final HttpStatus httpStatus;

    public AddressNotFoundException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.httpStatus = errorCode.getHttpStatus();
    }
}
