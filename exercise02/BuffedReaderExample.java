package exercise02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BuffedReaderExample {

    public static void main(String[] args) {
        String filePath = "exercise02/input.txt";

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {

            String line;
            int dayCounter = 1;

            SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy", new Locale("th", "TH"));

            SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mma");

            // Set the initial time for the schedule
            Calendar scheduleTime = Calendar.getInstance();
            scheduleTime.set(Calendar.HOUR_OF_DAY, 9);
            scheduleTime.set(Calendar.MINUTE, 0);

            // Track the current date
            Date currentDate = null;

            while ((line = bufferedReader.readLine()) != null) {
                if (line.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    try {
                        currentDate = inputDateFormat.parse(line);
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(currentDate);
                        Date newDate = calendar.getTime();
                        String formattedDate = outputDateFormat.format(newDate);
                        System.out.println("Day " + dayCounter + " - " + formattedDate + " :");

                        // Reset the time for each new day
                        scheduleTime.set(Calendar.HOUR_OF_DAY, 9);
                        scheduleTime.set(Calendar.MINUTE, 0);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                } else if (line.matches(".*\\d+min")) {
                    // Parse event duration only if the line contains a duration
                    try {
                        int duration = Integer.parseInt(line.replaceAll("\\D", ""));
                        String formattedTime = timeFormat.format(scheduleTime.getTime());
                        System.out.println(formattedTime + " " + line);
                        scheduleTime.add(Calendar.MINUTE, duration);

                        // Check if lunchtime
                        if (scheduleTime.get(Calendar.HOUR_OF_DAY) == 12 && scheduleTime.get(Calendar.MINUTE) == 0) {
                            System.out.println("12:00PM Lunch");
                            scheduleTime.set(Calendar.HOUR_OF_DAY, 13);
                            scheduleTime.set(Calendar.MINUTE, 0);
                        }

                        // Check if Networking Event
                        if (scheduleTime.get(Calendar.HOUR_OF_DAY) >= 17) {
                            scheduleTime.get(Calendar.MINUTE);
                            System.out.println("05:00PM Networking Event" + "\n");

                            // Start a new day
                            scheduleTime.add(Calendar.DAY_OF_MONTH, 1);
                            dayCounter++;
                            // Add 1 day to currentDate
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(currentDate);
                            calendar.add(Calendar.DAY_OF_MONTH, 1);
                            currentDate = calendar.getTime();
                            System.out.println("Day " + dayCounter + " - " + outputDateFormat.format(currentDate) + " :");
                            scheduleTime.set(Calendar.HOUR_OF_DAY, 9);
                            scheduleTime.set(Calendar.MINUTE, 0);
                        }
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                } else {
                    // Handle other lines (non-date, non-duration)
                    System.out.println(line);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
