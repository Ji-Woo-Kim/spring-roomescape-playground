package roomescape.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import roomescape.dto.ReservationRequestDto;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Reservation {

    private Long id;
    private String name;
    private LocalDate date;
    private LocalTime time;
}
