package exercise02;

import exercise02.DTO.process.ProcessSessionDay;
import exercise02.DTO.read.SessionDay;
import exercise02.service.PrintOutput;
import exercise02.service.Process;
import exercise02.service.ReadInput;

import java.util.List;


public class SeminarApp {
    public static final String FILE_PATH = "exercise02/input.txt";
    public static void main(String[] args) {
        // Read input
        ReadInput reader = new ReadInput();
        SessionDay sessionDay = reader.read(FILE_PATH);
        // Process the data
        Process processSchedule = new Process();
        List<ProcessSessionDay> processedDataList = processSchedule.process(sessionDay);
        // Print the output
        PrintOutput printOutput = new PrintOutput();
        printOutput.print(processedDataList);
    }
}
