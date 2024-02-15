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

class ProcessSchedule {
    private static final int LUNCH_HOUR = 12;
    private static final int NETWORKING_HOUR = 16;
    private static final int NETWORKING_MINUTE = 0;
    private static final int MORNING_SESSION_HOUR = 9;
    private static final int MORNING_SESSION_MINUTE = 0;
    private static final int AFTERNOON_SESSION_HOUR = 13;
    private static final int AFTERNOON_SESSION_MINUTE = 0;
    private LocalDate currentDate;
    private LocalTime scheduleTime;
    private int dayCounter;

    private DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter outputDateFormat = DateTimeFormatter
            .ofPattern("dd/MM/yyyy", new Locale("th", "TH"))
            .withChronology(ThaiBuddhistChronology.INSTANCE);

    ProcessSchedule() {
        this.currentDate = LocalDate.now();
        this.scheduleTime = LocalTime.of(MORNING_SESSION_HOUR, MORNING_SESSION_MINUTE);
        this.dayCounter = 1;
    }

    void process(String line) {
        if (line.matches("\\w{4}-\\w{2}-\\w{2}")) {
            ShowDateLine(line);
        } else if (line.matches(".*\\d+min")) {
            ShowSessionLine(line);
        }
    }

    private void ShowDateLine(String line) {
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

    private void ShowSessionLine(String line) {
        try {
            int duration = Integer.parseInt(line.replaceAll("\\D", ""));
            Event event;
            if (scheduleTime.getHour() == LUNCH_HOUR) {
                event = new Lunch(scheduleTime);
                event.printDetail();
                scheduleTime = LocalTime.of(AFTERNOON_SESSION_HOUR, AFTERNOON_SESSION_MINUTE);
            }
            else if (scheduleTime.getHour() >= NETWORKING_HOUR) {
                event = new NetworkingEvent(scheduleTime);
                event.printDetail();
                startNewDay();
            }
            event = new Session(scheduleTime, line);
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
        scheduleTime = LocalTime.of(MORNING_SESSION_HOUR, MORNING_SESSION_MINUTE);
    }

    private boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}


