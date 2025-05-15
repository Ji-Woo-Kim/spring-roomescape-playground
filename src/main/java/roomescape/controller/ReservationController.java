package roomescape.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.dto.*;
import roomescape.service.ReservationService;

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

    @GetMapping("/reservations") //6단계 반영
    public ResponseEntity<List<ReservationResponseDto>> findAll() {
        return ResponseEntity.ok(reservationService.findAllReservations());
    }

//    @PostMapping("/reservations")
//    public ResponseEntity<ReservationResponseDto> create(@Valid @RequestBody ReservationRequestDto requestDto) {
//        Reservation newReservation = Reservation.fromDto(index.getAndIncrement(), requestDto);
//        reservations.add(newReservation);
//
//        URI location = URI.create("/reservations/" + newReservation.getId());
//        return ResponseEntity.created(location).body(new ReservationResponseDto(newReservation));
//    }
//
//    @DeleteMapping("/reservations/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        Reservation reservation = reservations.stream()
//                .filter(it -> Objects.equals(it.getId(), id))
//                .findFirst()
//                .orElseThrow(() -> new NotReservationFoundException("Reservation not found"));
//
//        reservations.remove(reservation);
//
//        return ResponseEntity.noContent().build();
//    }
}
