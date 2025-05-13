package roomescape.exception;

import org.springframework.http.HttpStatus;

public class InvalidRequestException extends ReservationException {

    public InvalidRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST, "INVALID_REQUEST");
    }
}
