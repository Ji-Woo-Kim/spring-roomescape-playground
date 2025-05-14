package roomescape.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import roomescape.dto.ReservationRequestDto;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Reservation {

    private Long id;
    private String name;
    private LocalDate date;
    private LocalTime time;

    public static Reservation fromDto(Long id, ReservationRequestDto dto) {
        return new Reservation(id, dto.getName(), dto.getDate(), dto.getTime());
    }
}
