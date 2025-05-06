package roomescape.dto;

import roomescape.domain.Reservation;

public class ReservationResponseDto {
    private Long id;
    private String name;
    private String date;
    private String time;

    public ReservationResponseDto(Reservation reservation) {
        this.id = reservation.getId();
        this.name = reservation.getName();
        this.date = reservation.getDate().toString();
        this.time = reservation.getTime().toString();
    }
}
