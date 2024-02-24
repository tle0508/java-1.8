package exercise02.service;

import exercise02.DTO.process.ProcessSchedule;
import exercise02.DTO.process.ProcessSessionDay;

import java.time.chrono.ThaiBuddhistChronology;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class PrintOutput {
    private DateTimeFormatter outputDateFormat = DateTimeFormatter
            .ofPattern("dd/MM/yyyy")
            .withChronology(ThaiBuddhistChronology.INSTANCE);
    private DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("hh:mma");

    public String format(List<ProcessSessionDay> processSessionDays) {
        StringBuilder formattedOutput = new StringBuilder();
        for (int i = 0; i < processSessionDays.size(); i++) {
            ProcessSessionDay processSessionDay = processSessionDays.get(i);
            formattedOutput.append("Day ").append(i + 1).append(": ").append(processSessionDay.getDate().format(outputDateFormat)).append("\n");
            for (ProcessSchedule processSchedule : processSessionDay.getProcessSchedule()) {
                if (processSchedule.getDuration() == 0) {
                    formattedOutput.append(processSchedule.getTime().format(timeFormat)).append(" ")
                            .append(processSchedule.getSessionDescription()).append(" ").append("\n");
                } else {
                    formattedOutput.append(processSchedule.getTime().format(timeFormat)).append(" ")
                            .append(processSchedule.getSessionDescription()).append(" ")
                            .append(processSchedule.getDuration()).append(" min").append("\n");
                }
            }
            formattedOutput.append("\n");
        }
        return formattedOutput.toString();
    }
    public void print(List<ProcessSessionDay> processSessionDays) {
        String formattedOutput = format(processSessionDays);
        System.out.println(formattedOutput);
    }
}
