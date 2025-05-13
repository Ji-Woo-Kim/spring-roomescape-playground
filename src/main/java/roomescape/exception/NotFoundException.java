package roomescape.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ReservationException {

    public NotFoundException(String message) {
        super(message, HttpStatus.BAD_REQUEST, "RESOURCE_NOT_FOUND");
    }
}
