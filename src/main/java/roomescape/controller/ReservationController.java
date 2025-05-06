package roomescape.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import roomescape.domain.Reservation;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private AtomicLong index = new AtomicLong(1);
    private Map<Long, Reservation> reservations = new HashMap<>();

    public ReservationController() {
        reservations.put(1L, new Reservation(1L, "브라운", "2023-01-01", "10:00"));
        reservations.put(2L, new Reservation(2L, "브라운", "2023-01-02", "11:00"));
        reservations.put(3L, new Reservation(3L, "브라운", "2023-01-03", "12:00"));
    }

    @GetMapping
    public List<Reservation> getReservations() {
        return new ArrayList<>(reservations.values());
    }

    @PostMapping
    public ResponseEntity<Reservation> addReservation(@RequestBody Reservation reservation) {
        long id = index.getAndIncrement();
        reservation.setId(id);
        reservations.put(id, reservation);
        URI location = URI.create("/reservations/" + id);
        return ResponseEntity.created(location).body(reservation);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable long id) {
        reservations.remove(id);
        return ResponseEntity.noContent().build();
    }
}
