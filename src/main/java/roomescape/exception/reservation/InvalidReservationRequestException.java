package roomescape.exception.reservation;

import org.springframework.http.HttpStatus;

public class InvalidReservationRequestException extends ReservationException {

    public InvalidReservationRequestException(String message) {
        super(message, HttpStatus.BAD_REQUEST, "INVALID_REQUEST");
    }
}
