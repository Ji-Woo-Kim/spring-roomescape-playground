package roomescape.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.dao.ReservationDao;
import roomescape.domain.Reservation;
import roomescape.dto.ReservationRequestDto;
import roomescape.dto.ReservationResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationDao reservationDAO;

    public List<ReservationResponseDto> findAllReservations() {
        return List.copyOf(reservationDAO.findAll()
                .stream()
                .map(ReservationResponseDto::new)
                .collect(Collectors.toList())
        );
    }

    public ReservationResponseDto addReservation(@Valid ReservationRequestDto requestDto) {
        Reservation reservation = reservationDAO.addReservation(requestDto);
        return new ReservationResponseDto(reservation);
    }

    public void deleteReservation(Long id) {
        reservationDAO.deleteReservationById(id);
    }
}
