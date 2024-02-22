package exercise02;

import exercise02.EventSession.Lunch;
import exercise02.EventSession.NetworkingEvent;
import exercise02.EventSession.SessionEvent;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ThaiBuddhistChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProcessSchedule {
    private LocalDate currentDate = LocalDate.now();
    //final
    private  LocalTime afternoonSession= LocalTime.of(13,0) ;
    private  LocalTime morningSession = LocalTime.of(9,0) ;
    private LocalTime lunchTime = LocalTime.of(12,0) ;
    private LocalTime networkingEventTime  = LocalTime.of(16,0) ;
    private LocalTime scheduleTime = morningSession ;
    private int dayCounter =1;
    private boolean isFirstLine = true;
    private DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter outputDateFormat = DateTimeFormatter
            .ofPattern("dd/MM/yyyy", new Locale("th", "TH"))
            .withChronology(ThaiBuddhistChronology.INSTANCE);
    private static List<String> outputList = new ArrayList<>();

    public void process(String line) {
        if (isFirstLine) {
            checkDateLine(line);
            isFirstLine = false;
        } else if (line.matches(".*\\d+min")) {
            checkSessionLine(line);
        }
    }

    private void checkDateLine(String line) {
        try {
            currentDate = LocalDate.parse(line, inputDateFormat);
            if (isWeekend(currentDate)) {
                currentDate = currentDate.plusDays(2);
            }
            //
            String result = "Day " + dayCounter + " - " + currentDate.format(outputDateFormat) + " :";
            outputList.add(result);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }
    }

    private void checkSessionLine(String line) {
        try {
            int duration = Integer.parseInt(line.replaceAll("\\D", ""));
            Event event;
            if (scheduleTime .equals(lunchTime)) {
                event = new Lunch(scheduleTime);
                outputList.add(event.getDetail());
                scheduleTime = afternoonSession;
            }
            else if (scheduleTime.isAfter(networkingEventTime)) {
                event = new NetworkingEvent(scheduleTime);
                outputList.add(event.getDetail());
                startNewDay();
            }
            String sessionDescription = line.replaceAll("\\d+min", "");
            event = new SessionEvent(scheduleTime,sessionDescription,duration);
            outputList.add(event.getDetail());
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
        String result = "Day " + dayCounter + " - " + currentDate.format(outputDateFormat) + " :";
        outputList.add(result);
        scheduleTime = morningSession;
    }

    private boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
    public static List<String> AllOutput() {
        return outputList;
    }
}


