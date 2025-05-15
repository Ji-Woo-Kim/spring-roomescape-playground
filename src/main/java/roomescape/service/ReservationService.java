package roomescape.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.dao.ReservationDao;
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

}
