package exercise02.DTO.read;

import java.time.LocalTime;

public class Schedule {

    private String sessionDescription;
    private int duration;

    public Schedule( String sessionDescription, int duration) {

        this.sessionDescription = sessionDescription;
        this.duration = duration;
    }

    public Schedule() {
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
