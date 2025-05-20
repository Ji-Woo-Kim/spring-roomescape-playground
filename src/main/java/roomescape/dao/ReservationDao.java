package roomescape.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import roomescape.domain.Reservation;
import roomescape.dto.ReservationRequestDto;
import roomescape.exception.reservation.NotReservationFoundException;
import roomescape.exception.reservation.ReservationInsertFailedException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Component
public class ReservationDao {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;

    public ReservationDao(NamedParameterJdbcTemplate namedJdbcTemplate) {
        this.namedJdbcTemplate = namedJdbcTemplate;
    }

    private final RowMapper<Reservation> reservationRowMapper = (resultSet, rowNum) -> new Reservation(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            LocalDate.parse(resultSet.getString("date")),
            LocalTime.parse(resultSet.getString("time"))
    );

    public Reservation addReservation(ReservationRequestDto dto) {
        String sql = "INSERT INTO reservation (name, date, time) VALUES (:name, :date, :time)";
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(dto);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(sql, parameterSource, keyHolder, new String[]{"id"});

        Long id = Optional.ofNullable(keyHolder.getKey())
                .map(Number::longValue)
                .orElseThrow(() -> new ReservationInsertFailedException("예약 ID 생성에 실패했습니다."));
        return findById(id);
    }

    public Reservation findById(Long id) {
        String sql = "SELECT * FROM reservation WHERE id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id", id);

        return namedJdbcTemplate.queryForObject(sql, parameterSource, reservationRowMapper);
    }

    public List<Reservation> findAll() {
        String sql = "SELECT * FROM reservation";
        return namedJdbcTemplate.query(sql, reservationRowMapper);
    }

    public void updateReservation(Long id, ReservationRequestDto dto) {
        String sql = "UPDATE reservation SET name = :name, date = :date, time = :time WHERE id = :id";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("name", dto.getName())
                .addValue("date", dto.getDate().toString())
                .addValue("time", dto.getTime().toString());

        namedJdbcTemplate.update(sql, parameterSource);
    }

    public void deleteReservationById(Long id) {
        String sql = "DELETE FROM reservation WHERE id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);

        int result = namedJdbcTemplate.update(sql, params);
        if (result == 0) {
            throw new NotReservationFoundException("존재하지 않는 예약입니다.");
        }
    }
}
