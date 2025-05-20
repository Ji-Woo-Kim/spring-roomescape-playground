package roomescape.exception.reservation;

import org.springframework.http.HttpStatus;

public class ReservationInsertFailedException extends ReservationException{

    public ReservationInsertFailedException(String message) {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR, "RESERVATION_INSERT_FAILED");
    }
}
