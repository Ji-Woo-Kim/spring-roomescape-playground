package roomescape.dao;

import jakarta.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import org.springframework.stereotype.Component;
import roomescape.domain.Reservation;
import roomescape.dto.ReservationRequestDto;

@Component
public class ReservationDAO {

    private static final String URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";

    public Connection getConnection() {
        // 드라이버 연결
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (final SQLException e) {
            System.err.println("DB 연결 오류:" + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @PostConstruct
    public void createTable() {
        final var query = "CREATE TABLE reservation(" +
                "id BIGINT PRIMARY KEY, " + // SQL에선 Long이 BIGINT
                "name VARCHAR(10), " +
                "date VARCHAR(10), " +
                "time VARCHAR(5)" +
                ");";
        try (
                final var connection = getConnection();
                final var preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.executeUpdate();
        }
        catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addReservation(final Reservation reservation) {
        final var query = "INSERT INTO reservation (id, name, date, time) VALUES(?, ? , ?, ?)";
        try (
                final var connection = getConnection();
                final var preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setLong(1, reservation.getId());
            preparedStatement.setString(2, reservation.getName());
            preparedStatement.setString(3, reservation.getDate().toString());
            preparedStatement.setString(4, reservation.getTime().toString());
            preparedStatement.executeUpdate();
        }
        catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Reservation findById(final Long id) {
        final var query = "SELECT * FROM reservation WHERE id = ?";
        try (
                final var connection = getConnection();
                final var preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setLong(1, id);

            final var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Reservation(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        LocalDate.parse(resultSet.getString("date")),
                        LocalTime.parse(resultSet.getString("time"))
                );
            }
        }
        catch (final SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public void updateReservation(final Long id, ReservationRequestDto reservationRequestDto) {
        final var query = "UPDATE reservation SET name = ?, date = ?, time = ? WHERE id = ?";

        try (
                final var connection = getConnection();
                final var preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, reservationRequestDto.getName());
            preparedStatement.setString(2, reservationRequestDto.getDate().toString());
            preparedStatement.setString(3, reservationRequestDto.getTime().toString());
            preparedStatement.setLong(4, id);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteReservationById(final Long id) {
        final var query = "DELETE FROM reservation WHERE id = ?";

        try (
                final var connection = getConnection();
                final var preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (final SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
