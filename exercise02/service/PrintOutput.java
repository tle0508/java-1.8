package exercise02.service;

import exercise02.DTO.process.ProcessSessionDay;
import exercise02.DTO.process.ProcessSchedule;

import java.time.chrono.ThaiBuddhistChronology;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PrintOutput {
    private final DateTimeFormatter outputDateFormat = DateTimeFormatter
            .ofPattern("dd/MM/yyyy")
            .withChronology(ThaiBuddhistChronology.INSTANCE);
    private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mma");

    public void print(List<ProcessSessionDay> processSessionDayList) {
        for (int i = 0; i < processSessionDayList.size(); i++) {
            printSessionDay(processSessionDayList.get(i), i+1);
        }
    }

    private void printSessionDay(ProcessSessionDay sessionDay, int dayNumber) {
        System.out.println("Day " +dayNumber + " : " +sessionDay.getDate().format(outputDateFormat));
        sessionDay.getProcessSchedule().forEach(this::printSchedule);
        System.out.println();
    }

    private void printSchedule(ProcessSchedule schedule) {
        String startTime = schedule.getTime().format(timeFormat);
        String sessionDescription = schedule.getSessionDescription();
        String duration;
        if (schedule.getDuration() != null) {
            duration = schedule.getDuration() + "min";
        } else {
            duration = "";
        }
        System.out.println(startTime + " " + sessionDescription + " " + duration);
    }
}
