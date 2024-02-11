package exercise02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SeminarScheduler {

    private LocalDate startDate;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("exercise02/input.txt"))) {
            StringBuilder inputTextBuilder = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                inputTextBuilder.append(line).append("\n");
            }

            String inputText = inputTextBuilder.toString().trim();
            SeminarScheduler seminarScheduler = new SeminarScheduler();
            String output = seminarScheduler.scheduleSeminar(inputText);
            System.out.println(output);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Seminar {
        String title;
        Duration duration;

        Seminar(String title, Duration duration) {
            this.title = title;
            this.duration = duration;
        }
    }

    private String scheduleSeminar(String inputText) {
        String[] lines = inputText.split("\n");
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            startDate = LocalDate.parse(lines[0], dateFormat);
        } catch (Exception e) {
            e.printStackTrace();
            return "Error parsing start date.";
        }

        List<Seminar> seminars = new ArrayList<>();
        for (int i = 1; i < lines.length; i++) {
            if (!lines[i].isEmpty()) {
                String[] parts = lines[i].split(" ");
                String title = parts[0] + " " + String.join(" ", Arrays.copyOfRange(parts, 1, parts.length - 1));
                Duration duration = Duration.ofMinutes(Integer.parseInt(parts[parts.length - 1].replaceAll("[^0-9]", "")));
                seminars.add(new Seminar(title, duration));
            }
        }

        return generateOutput(scheduleSeminars(seminars));
    }

    private List<String> scheduleSeminars(List<Seminar> seminars) {
        List<String> schedule = new ArrayList<>();
        LocalDate currentDate = startDate;
        LocalTime currentTime = LocalTime.of(9, 0);

        for (Seminar seminar : seminars) {
            String formattedTime = currentTime.format(DateTimeFormatter.ofPattern("hh:mma"));
            schedule.add(String.format("%s %s %dmin", formattedTime, seminar.title, seminar.duration.toMinutes()));
            currentTime = currentTime.plusMinutes(seminar.duration.toMinutes());

            if (currentTime.equals(LocalTime.NOON)) {
                schedule.add("12:00PM Lunch");
                currentTime = LocalTime.of(13, 0);
            }
        }

        return schedule;
    }

    private String generateOutput(List<String> schedule) {
        StringBuilder output = new StringBuilder();
        LocalDate currentDate = startDate;
        int dayCount = 1;

        for (String entry : schedule) {
            String[] parts = entry.split(" ");
            String time = parts[0];

            // Extract title without duration
            String title = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length - 1));

            // If it's not a lunch or networking event entry, increment the day count
            if (!title.equals("Lunch") && !title.equals("Networking")) {
                output.append(String.format("%s %s\n", time, entry));
            } else {
                output.append(String.format("%s %s\n", time, title));
            }

            // Reset day count for the next day
//            if (time.equals("09:00AM")) {
//                output.append(String.format("\nDay %d - %s :\n", dayCount, currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
//                currentDate = currentDate.plusDays(1);
//                dayCount++;
//            }
        }

        return output.toString();
    }
}
