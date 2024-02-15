package exercise02;

import java.time.format.DateTimeFormatter;

public interface Event {
    DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mma");

    public void printDetail();
}
