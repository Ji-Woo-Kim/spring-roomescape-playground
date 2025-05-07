package roomescape.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import roomescape.domain.Reservation;

import java.time.LocalDate;
import java.time.LocalTime;

public class ReservationRequestDto {

    @NotBlank(message = "이름은 필수 입력 항목입니다.")
    private String name;

    @NotNull(message = "예약 날짜는 필수 입력 항목입니다.")
    private LocalDate date;

    @NotNull(message = "예약 시간은 필수 입력 항목입니다.")
    private LocalTime time;

    public ReservationRequestDto(String name, LocalDate date, LocalTime time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return this.name;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public Reservation toEntity(Long id) {
        return new Reservation(id, name, date, time);
    }
}
