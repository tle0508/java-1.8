package exercise02.EventSession;

import java.time.LocalTime;

public class Schedule {
    private LocalTime scheduleTime;
    private String sessionDescription;
    private int duration;

    public Schedule(LocalTime scheduleTime, String sessionDescription, int duration) {
        this.scheduleTime = scheduleTime;
        this.sessionDescription = sessionDescription;
        this.duration = duration;
    }

    public Schedule() {

    }

    public LocalTime getScheduleTime() {
        return scheduleTime;
    }

    public void setScheduleTime(LocalTime scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    public String getSessionDescription() {
        return sessionDescription;
    }

    public void setSessionDescription(String sessionDescription) {
        this.sessionDescription = sessionDescription;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
