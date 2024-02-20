package exercise02;

import exercise02.EventSession.Lunch;
import exercise02.EventSession.NetworkingEvent;
import exercise02.EventSession.Session;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ThaiBuddhistChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public class ProcessSchedule {
    private LocalDate currentDate = LocalDate.now();
    private LocalTime afternoonSession= LocalTime.of(13,0) ;
    private LocalTime morningSession = LocalTime.of(9,0) ;

    private LocalTime networkingEventTime  = LocalTime.of(16,59) ;
    private LocalTime scheduleTime = morningSession ;
    private int dayCounter =1;

    private DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter outputDateFormat = DateTimeFormatter
            .ofPattern("dd/MM/yyyy", new Locale("th", "TH"))
            .withChronology(ThaiBuddhistChronology.INSTANCE);

    public void process(String line) {
        if (line.matches("\\d{4}-\\d{2}-\\d{2}")) {
            showDateLine(line);
        } else if (line.matches(".*\\d+min")) {
            showSessionLine(line);
        }
    }

    private void showDateLine(String line) {
        try {
            currentDate = LocalDate.parse(line, inputDateFormat);
            if (isWeekend(currentDate)) {
                currentDate = currentDate.plusDays(2);
            }
            System.out.println("Day " + dayCounter + " - " + currentDate.format(outputDateFormat) + " :");
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
    }

    private void showSessionLine(String line) {
        try {
            int duration = Integer.parseInt(line.replaceAll("\\D", ""));
            Event event;
            if (scheduleTime .equals(LocalTime.NOON)) {
                event = new Lunch(scheduleTime);
                event.printDetail();
                scheduleTime = afternoonSession;
            }
            else if (scheduleTime.isAfter(networkingEventTime)) {
                event = new NetworkingEvent(scheduleTime);
                event.printDetail();
                startNewDay();
            }
            String sessionDescription = line.replaceAll("\\d+min", "").trim();
            event = new Session(scheduleTime,sessionDescription,duration);
            event.printDetail();
            scheduleTime = scheduleTime.plusMinutes(duration);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void startNewDay() {
        dayCounter++;
        currentDate = currentDate.plusDays(1);
        if (isWeekend(currentDate)) {
            currentDate = currentDate.plusDays(2);
        }
        System.out.println("Day " + dayCounter + " - " + currentDate.format(outputDateFormat) + " :");
        scheduleTime = morningSession;
    }

    private boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}


