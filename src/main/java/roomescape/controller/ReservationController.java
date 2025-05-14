package roomescape.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.dao.ReservationDAO;
import roomescape.domain.*;
import roomescape.dto.*;
import roomescape.exception.reservation.NotReservationFoundException;

import java.net.URI;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ReservationController {

    private final ReservationDAO reservationDAO;

    public ReservationController(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
    }

    @GetMapping("/reservation")
    public String reservationPage() {
        return "reservation";
    }

    @GetMapping("/reservations") //6단계 반영
    public ResponseEntity<List<ReservationResponseDto>> read() {
        List<ReservationResponseDto> responseDtos = reservationDAO.findAll().stream()
                .map(ReservationResponseDto::new)
                .toList();

        return ResponseEntity.ok(responseDtos);
    }

    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponseDto> create(@Valid @RequestBody ReservationRequestDto requestDto) {
        Reservation newReservation = Reservation.fromDto(index.getAndIncrement(), requestDto);
        reservations.add(newReservation);

        URI location = URI.create("/reservations/" + newReservation.getId());
        return ResponseEntity.created(location).body(new ReservationResponseDto(newReservation));
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Reservation reservation = reservations.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElseThrow(() -> new NotReservationFoundException("Reservation not found"));

        reservations.remove(reservation);

        return ResponseEntity.noContent().build();
    }
}
