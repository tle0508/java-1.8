package exercise02.service;

import exercise02.DTO.read.Schedule;
import exercise02.DTO.read.SessionDay;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class ReadInput {
    public SessionDay sessionDay = new SessionDay();
    public void read(){
        String filePath = "exercise02/input.txt";
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
    }  public Schedule parseSchedule(String line) {
        int duration = Integer.parseInt(line.replaceAll("\\D", ""));
        String sessionDescription = line.replaceAll("\\d+min", "");
        return new Schedule(sessionDescription, duration);
    }
    public SessionDay getSessionDay() {
        return sessionDay;
    }
}
