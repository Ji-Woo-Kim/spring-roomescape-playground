package roomescape.exception.reservation;

import org.springframework.http.HttpStatus;

public class NotReservationFoundException extends ReservationException {

    public NotReservationFoundException(String message) {
        super(message, HttpStatus.BAD_REQUEST, "RESOURCE_NOT_FOUND");
    }
}
