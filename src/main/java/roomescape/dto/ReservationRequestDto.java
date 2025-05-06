package roomescape.dto;

public class ReservationRequestDto {
    private String name;
    private String date; // "2023-08-05"
    private String time; // "15:40"

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
