package exercise02.service;

import exercise02.DTO.process.ProcessSchedule;
import exercise02.DTO.process.ProcessSessionDay;

import java.time.chrono.ThaiBuddhistChronology;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class PrintOutput {
    private DateTimeFormatter outputDateFormat = DateTimeFormatter
            .ofPattern("dd/MM/yyyy", new Locale("th", "TH"))
            .withChronology(ThaiBuddhistChronology.INSTANCE);
    private DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mma");

    public void print(List<ProcessSessionDay> processSessionDays) {
        for (int i = 0; i < processSessionDays.size(); i++) {
            ProcessSessionDay processSessionDay = processSessionDays.get(i);
            System.out.println("Day " + (i + 1) + ": " + processSessionDay.getDate().format(outputDateFormat));
            for (ProcessSchedule processSchedule : processSessionDay.getProcessSchedule()) {
                if (processSchedule.getDuration() == 0){
                    System.out.println(processSchedule.getTime().format(timeFormat) + " " +
                            processSchedule.getSessionDescription() + " ");
                }else {
                    System.out.println(processSchedule.getTime().format(timeFormat) + " " +
                            processSchedule.getSessionDescription() + " " +
                            processSchedule.getDuration() + " min");
                }
            }
            System.out.println();
        }
    }
}
