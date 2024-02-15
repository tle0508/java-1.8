package exercise02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class SeminarApp {
    public static void main(String[] args) {
        String filePath = "exercise02/input.txt";
        ProcessSchedule processSchedule = new ProcessSchedule(LocalDate.now());
        try (
                FileReader in = new FileReader(filePath);
                BufferedReader bufferedReader = new BufferedReader(in);
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                processSchedule.process(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
