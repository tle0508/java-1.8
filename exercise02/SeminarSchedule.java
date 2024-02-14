package exercise02;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ThaiBuddhistChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

class SeminarSchedule {
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
    private DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mma");

    SeminarSchedule(LocalDate localDate) {
        this.currentDate = localDate;
        this.scheduleTime = LocalTime.of(MORNING_SESSION_HOUR, MORNING_SESSION_MINUTE);
        this.dayCounter = 1;
    }

    void process(String line) {
        if (line.matches("\\d{4}-\\d{2}-\\d{2}")) {
            ShowDateLine(line);
        } else if (line.matches(".*\\d+min")) {
            ShowSessionLine(line);
        } else {
            System.out.println(line);
        }
    }

    private void ShowDateLine(String line) {
        try {
            currentDate = LocalDate.parse(line, inputDateFormat);
            if (isWeekend(currentDate)) {
                System.out.println("วันนี้เป็นวันหยุด!");
            } else {
                System.out.println("Day " + dayCounter + " - " + currentDate.format(outputDateFormat) + " :");
            }
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
    }

    private void ShowSessionLine(String line) {
        try {
            int duration = Integer.parseInt(line.replaceAll("\\D", ""));
            printSession(line);
            scheduleTime = scheduleTime.plusMinutes(duration);

            if (scheduleTime.getHour() == LUNCH_HOUR) {
                printLunch();
            }

            if (scheduleTime.getHour() >= NETWORKING_HOUR) {
                printNetworkingEvent();
                startNewDay();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void printSession( String line) {
        String formattedTime = scheduleTime.format(timeFormat);
        System.out.println(formattedTime +" "+ line );
    }

    private void printLunch() {
        System.out.println(scheduleTime.format(timeFormat) + " Lunch");
        scheduleTime = LocalTime.of(AFTERNOON_SESSION_HOUR, AFTERNOON_SESSION_MINUTE);
    }

    private void printNetworkingEvent() {
        System.out.println(scheduleTime.format(timeFormat) + " Networking Event" + "\n");
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


