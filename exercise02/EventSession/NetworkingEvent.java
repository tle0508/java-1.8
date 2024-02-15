package exercise02.EventSession;

import exercise02.Event;

import java.time.LocalTime;

public class NetworkingEvent implements Event {
    private LocalTime localTime;
    public NetworkingEvent(LocalTime localTime) {
        this.localTime = localTime;
    }
    @Override
    public void printDetail() {
        System.out.println(localTime.format(timeFormat) + " Networking Event" + "\n");
    }
}