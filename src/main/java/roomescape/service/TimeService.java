package roomescape.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.dao.TimeDao;
import roomescape.domain.Time;
import roomescape.dto.TimeRequestDto;
import roomescape.dto.TimeResponseDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimeService {

    private final TimeDao timeDao;

    public TimeResponseDto saveTime(TimeRequestDto requestDto) {
        Time time = new Time();
        time.setTime(requestDto.getTime());
        Time saved = timeDao.save(time);
        return new TimeResponseDto(saved.getId(), saved.getTime());
    }

    public List<TimeResponseDto> findAllTimes() {
        return timeDao.findAll()
                .stream()
                .map(t -> new TimeResponseDto(t.getId(), t.getTime()))
                .collect(Collectors.toList());
    }

    public void deleteTime(Long id) {
        timeDao.deleteById(id);
    }
}
