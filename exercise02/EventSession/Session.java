package exercise02.EventSession;
import exercise02.Event;

import java.time.LocalTime;

public class Session implements Event {
    private final String line;
    private LocalTime localTime;
    public Session(LocalTime startTime,String line) {
        this.localTime = startTime;
        this.line = line;
    }

    @Override
    public void printDetail() {
        System.out.println(localTime.format(timeFormat) +" "+ line );
    }
}
