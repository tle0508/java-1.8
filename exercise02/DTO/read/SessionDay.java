package exercise02.DTO.read;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SessionDay {
    private LocalDate day ;
    private List<Schedule> data = new ArrayList<>();

    public SessionDay() {

    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public List<Schedule> getScheduleList() {
        return data;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.data = scheduleList;
    }

}
