package roomescape.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.dto.*;
import roomescape.service.ReservationService;

import java.net.URI;
import java.util.*;

@RestController
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/reservation")
    public String reservationPage() {
        return "reservation";
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<ReservationResponseDto>> findAll() {
        return ResponseEntity.ok(reservationService.findAllReservations());
    }

    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponseDto> create(@Valid @RequestBody ReservationRequestDto requestDto) {
        ReservationResponseDto newReservation = reservationService.addReservation(requestDto);

        URI location = URI.create("/reservations/" + newReservation.getId());
        return ResponseEntity.created(location).body(newReservation);
    }

    @DeleteMapping("/reservations/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
