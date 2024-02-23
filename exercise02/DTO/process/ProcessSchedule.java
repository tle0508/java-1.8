package exercise02.DTO.process;

import java.time.LocalTime;

public class ProcessSchedule {
    private LocalTime time ;
    private String sessionDescription;
    private int duration;

    public ProcessSchedule() {
    }

    public ProcessSchedule(LocalTime time, String sessionDescription, int duration) {
        this.time = time;
        this.sessionDescription = sessionDescription;
        this.duration = duration;
    }

    public ProcessSchedule(LocalTime scheduleTime, String networkingTime) {

    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
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
