package exercise02.DTO.read;

import java.time.LocalTime;

public class Schedule {

    private final String sessionDescription;
    private final int duration;

    public Schedule( String sessionDescription, int duration) {

        this.sessionDescription = sessionDescription;
        this.duration = duration;
    }


    public String getSessionDescription() {
        return sessionDescription;
    }

    public int getDuration() {
        return duration;
    }



}
