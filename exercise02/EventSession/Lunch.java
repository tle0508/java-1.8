package exercise02.EventSession;
import exercise02.Event;

import java.time.LocalTime;

public class Lunch implements Event {
    private LocalTime localTime;
    public Lunch(LocalTime localTime) {
        this.localTime = localTime;
    }

    @Override
    public void printDetail() {
        System.out.println(localTime.format(timeFormat) + " Lunch");
    }
}