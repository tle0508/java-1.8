package exercise02.EventSession;
import exercise02.Event;

import java.time.LocalTime;

public class Lunch implements Event {
    private LocalTime localTime;
    public Lunch(LocalTime localTime) {
        this.localTime = localTime;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    @Override
    public String getDetail() {
        return (localTime.format(timeFormat) + " Lunch");
    }
}