package roomescape.dto;

import lombok.Getter;
import roomescape.domain.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
public class ReservationResponseDto {

    private Long id;
    private String name;
    private LocalDate date;
    private LocalTime time;

    public ReservationResponseDto(Reservation reservation) {
        this.id = reservation.getId();
        this.name = reservation.getName();
        this.date = reservation.getDate();
        this.time = reservation.getTime().getTime();
    }
}
