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
        final var reservation = new Reservation(1L, "testUser", LocalDate.parse("2023-08-05"),
                LocalTime.parse("19:25"));
        reservationDAO.addReservation(reservation);
    }

    @Test
    void findById() {
        final var reservation = new Reservation(1L, "testUser", LocalDate.parse("2023-08-05"),
                LocalTime.parse("19:25"));
        reservationDAO.addReservation(reservation);

        assertThat(reservation).isEqualTo(reservationDAO.findById(1L));
    }
}
