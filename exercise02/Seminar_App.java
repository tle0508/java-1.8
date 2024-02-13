package exercise02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ThaiBuddhistChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Seminar_App {
    private static final int MORNING_SESSION_HOUR = 9;
    private static final int MORNING_SESSION_MINUTE = 0;
    private static final int AFTERNOON_SESSION_HOUR = 13;
    private static final int AFTERNOON_SESSION_MINUTE = 0;
    private static final int LUNCH_HOUR = 12;
    private static final int NETWORKING_HOUR = 16;
    private static final int NETWORKING_MIN = 0;
    public static void main(String[] args) {
        String filePath = "exercise02/input.txt";

        try (
                FileReader in = new FileReader(filePath);
                BufferedReader bufferedReader = new BufferedReader(in);
        ) {

            String line;
            int dayCounter = 1;

            DateTimeFormatter inputDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter outputDateFormat = DateTimeFormatter
                    .ofPattern("dd/MM/yyyy", new Locale("th", "TH"))
                    .withChronology(ThaiBuddhistChronology.INSTANCE);
            DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mma");

            // Set time to new day
            LocalDate currentDate = null;
            LocalTime scheduleTime = LocalTime.of(MORNING_SESSION_HOUR, MORNING_SESSION_MINUTE);


            while ((line = bufferedReader.readLine()) != null) {
                if (line.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    try {
                        currentDate = LocalDate.parse(line, inputDateFormat);
                        // Check if this day is a weekend
                        if (isWeekend(currentDate)) {
                            System.out.println("วันนี้เป็นวันหยุด!");
                            break;
                        }
                        System.out.println("Day " + dayCounter + " - " + currentDate.format(outputDateFormat) + " :");
                    } catch (DateTimeParseException e) {
                        e.printStackTrace();
                    }
                } else if (line.matches(".*\\d+min")) {
                    try {
                        int duration = Integer.parseInt(line.replaceAll("\\D", ""));
                        String formattedTime = scheduleTime.format(timeFormat);
                        System.out.println(formattedTime + " " + line);
                        scheduleTime = scheduleTime.plusMinutes(duration);
                        String TimeAfterAdd = scheduleTime.format(timeFormat);

                        // Check if lunchtime
                        if (scheduleTime.getHour() == LUNCH_HOUR) {
                            System.out.println(TimeAfterAdd + " Lunch");
                            scheduleTime = LocalTime.of(AFTERNOON_SESSION_HOUR, AFTERNOON_SESSION_MINUTE);
                        }
                        // Check if Networking Event
                        if (scheduleTime.getHour() >= NETWORKING_HOUR && scheduleTime.getMinute() > NETWORKING_MIN) {
                            System.out.println( TimeAfterAdd +" Networking Event" + "\n");
                            // Start a new day
                            // Add 1 day to currentDate
                            dayCounter++;
                            currentDate =currentDate.plusDays(1);
                            //if weekend +2 day
                            if (isWeekend(currentDate)) {
                                currentDate =currentDate.plusDays(2);
                            }
                            System.out.println("Day " + dayCounter + " - " + currentDate.format(outputDateFormat) + " :");
                            scheduleTime = LocalTime.of(MORNING_SESSION_HOUR, MORNING_SESSION_MINUTE);

                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}
