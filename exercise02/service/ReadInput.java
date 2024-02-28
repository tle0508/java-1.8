package exercise02.service;

import exercise02.DTO.read.Schedule;
import exercise02.DTO.read.SessionDay;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadInput {

    public SessionDay read(String filePath){
        SessionDay sessionDay = new SessionDay();

        try (
                FileReader in = new FileReader(filePath);
                BufferedReader bufferedReader = new BufferedReader(in);
        ) {
            String firstLine = bufferedReader.readLine();
            LocalDate day = LocalDate.parse(firstLine);
            sessionDay.setDay(day);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Schedule schedule = parseSchedule(line);
                sessionDay.getScheduleList().add(schedule);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sessionDay;
    }
    public Schedule parseSchedule(String line) {
        String regexPattern = "(.*?) (\\d+)min";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            String sessionDescription = matcher.group(1);
            int duration = Integer.parseInt(matcher.group(2));
            return new Schedule(sessionDescription, duration);
        } else {
            return null;
        }
    }


}
