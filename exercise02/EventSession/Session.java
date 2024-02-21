package exercise02.EventSession;
import exercise02.Event;

import java.time.LocalTime;

public class Session implements Event {
    private final String info;
    private LocalTime localTime;
    private int duration;
    public Session(LocalTime startTime, String info, int duration) {
        this.localTime = startTime;
        this.info = info;
        this.duration = duration;
    }


    @Override
    public String getDetail() {
        return localTime.format(timeFormat) + " " + info + " " + duration + " min";
    }
}
