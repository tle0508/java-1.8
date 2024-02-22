package exercise02.EventSession;

import java.time.LocalDate;

public class SessionDay {
    private LocalDate day ;
    private Schedule schedule = new Schedule();
    public SessionDay(){

    }
    public SessionDay(LocalDate day, Schedule schedule) {
        this.day = day;
        this.schedule = schedule;
    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
