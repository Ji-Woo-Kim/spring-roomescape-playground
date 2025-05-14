package roomescape;

import static java.sql.DriverManager.getConnection;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import org.springframework.boot.test.context.SpringBootTest;
import roomescape.dao.ReservationDAO;
import roomescape.domain.Reservation;
import roomescape.dto.ReservationRequestDto;

@SpringBootTest
class ReservationDAOTest {

    private final ReservationDAO reservationDAO = new ReservationDAO();

    @Test
    void connection() {
        try (final var connection = reservationDAO.getConnection()){
            assertThat(connection).isNotNull();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void addReservation() {
        final var reservation = new Reservation(
                1L,
                "testUser",
                LocalDate.parse("2023-08-05"),
                LocalTime.parse("19:25"));

        reservationDAO.addReservation(reservation);
    }

    @Test
    void findReservationById() {
        final var reservation = new Reservation(
                1L,
                "testUser",
                LocalDate.parse("2023-08-05"),
                LocalTime.parse("19:25"));

        reservationDAO.addReservation(reservation);
        var findReservation = reservationDAO.findById(1L);

        assertThat(findReservation).isEqualTo(reservation);
    }

    @Test
    void updateReservation() {
        final var originalReservation = new Reservation(
                1L,
                "testUser",
                LocalDate.parse("2023-08-05"),
                LocalTime.parse("19:25"));
        reservationDAO.addReservation(originalReservation);

        final var request= new ReservationRequestDto(
                "updatedUser",
                LocalDate.parse("2023-08-05"),
                LocalTime.parse("19:30"));
        reservationDAO.updateReservation(originalReservation.getId(), request);
        final var findReservation = reservationDAO.findById(1L);

        assertThat(findReservation.getName()).isEqualTo("updatedUser");
    }

    @Test
    void deleteReservation() {
        final var reservation = new Reservation(
                3L,
                "testUser",
                LocalDate.parse("2023-08-05"),
                LocalTime.parse("19:25"));
        reservationDAO.addReservation(reservation);

        reservationDAO.deleteReservationById(3L);
        final var findReservation = reservationDAO.findById(3L);

        assertThat(findReservation).isNull();  // findById는 결과 없으면 null 반환
    }




}
