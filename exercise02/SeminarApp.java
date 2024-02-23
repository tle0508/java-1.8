package exercise02;

import exercise02.DTO.process.ProcessData;
import exercise02.DTO.read.SessionDay;
import exercise02.service.PrintOutput;
import exercise02.service.Process;
import exercise02.service.ReadInput;


public class SeminarApp {
    public static void main(String[] args) {
        ReadInput reader = new ReadInput();
        reader.read();
        SessionDay sessionDay = reader.getSessionDay();

        // Process the data
        Process processSchedule = new Process();
        processSchedule.process(sessionDay);

        ProcessData processData = processSchedule.getProcessDataList();
        // Print output
        PrintOutput printOutput = new PrintOutput();
        printOutput.print(processData.getProcessSessionDayList());
    }
}
