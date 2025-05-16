package roomescape.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import roomescape.domain.Reservation;
import roomescape.dto.ReservationRequestDto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Component
public class ReservationDao {

    private final JdbcTemplate jdbcTemplate;

    public ReservationDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Reservation addReservation(ReservationRequestDto dto) {
        jdbcTemplate.update(
                "INSERT INTO reservation (name, date, time) VALUES (?, ?, ?)",
                dto.getName(),
                dto.getDate().toString(),
                dto.getTime().toString()
        );
        return null;
    }

    public Reservation findById(Long id) {
        return jdbcTemplate.queryForObject(
                "SELECT * FROM reservation WHERE id = ?",
                (rs, rowNum) -> new Reservation(
                        rs.getLong("id"),
                        rs.getString("name"),
                        LocalDate.parse(rs.getString("date")),
                        LocalTime.parse(rs.getString("time"))
                ),
                id
        );
    }

    public List<Reservation> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM reservation",
                (rs, rowNum) -> new Reservation(
                        rs.getLong("id"),
                        rs.getString("name"),
                        LocalDate.parse(rs.getString("date")),
                        LocalTime.parse(rs.getString("time"))
                )
        );
    }

    public void updateReservation(Long id, ReservationRequestDto dto) {
        jdbcTemplate.update(
                "UPDATE reservation SET name = ?, date = ?, time = ? WHERE id = ?",
                dto.getName(),
                dto.getDate().toString(),
                dto.getTime().toString(),
                id
        );
    }

    public void deleteReservationById(Long id) {
        jdbcTemplate.update("DELETE FROM reservation WHERE id = ?", id);
    }
}
