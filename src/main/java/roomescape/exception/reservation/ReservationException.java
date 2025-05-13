package roomescape.exception.reservation;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ReservationException extends RuntimeException{

    private final HttpStatus httpStatus;
    private final String errorType;

    public ReservationException(String message, HttpStatus httpStatus, String errorType) {
        super(message);
        this.httpStatus = httpStatus;
        this.errorType = errorType;
    }
}
