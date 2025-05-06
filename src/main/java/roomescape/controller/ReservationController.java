package roomescape.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.domain.Reservation;
import roomescape.dto.ReservationRequestDto;
import roomescape.dto.ReservationResponseDto;
import roomescape.exception.InvalidReservationRequestException;
import roomescape.exception.NotFoundReservationException;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private List<Reservation> reservations = new ArrayList<>();
    private AtomicLong index = new AtomicLong(1);

    public ReservationController() {
        reservations.add(new Reservation(1L, "브라운", LocalDate.of(2023, 1, 1), LocalTime.of(10, 0)));
        reservations.add(new Reservation(2L, "브라운", LocalDate.of(2023, 1, 2), LocalTime.of(11, 0)));
        reservations.add(new Reservation(3L, "브라운", LocalDate.of(2023, 1, 3), LocalTime.of(12, 0)));
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponseDto>> read() {
        List<ReservationResponseDto> responseDtos = reservations.stream()
                .map(ReservationResponseDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDtos);
    }

    @PostMapping
    public ResponseEntity<ReservationResponseDto> create(@RequestBody ReservationRequestDto requestDto) {
        if (requestDto.getName().isEmpty() || requestDto.getDate().isEmpty() || requestDto.getTime().isEmpty()) {
            throw new InvalidReservationRequestException("필수 정보가 누락되었습니다.");
        }

        Reservation newReservation = new Reservation(
                index.getAndIncrement(),
                requestDto.getName(),
                LocalDate.parse(requestDto.getDate()),
                LocalTime.parse(requestDto.getTime())
        );
        reservations.add(newReservation);
        URI location = URI.create("/reservations/" + newReservation.getId());
        return ResponseEntity.created(location).body(new ReservationResponseDto(newReservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        Reservation reservation = reservations.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElseThrow(() -> new NotFoundReservationException("Reservation not found"));

        reservations.remove(reservation);

        return ResponseEntity.noContent().build();
    }
}
