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
        processSessionDayList.forEach(this::printSessionDay);
    }

    private void printSessionDay(ProcessSessionDay sessionDay) {
        System.out.println(sessionDay.getDate().format(outputDateFormat));
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
