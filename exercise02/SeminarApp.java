package exercise02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;

public class SeminarApp {
    public static void main(String[] args) {
        String filePath = "exercise02/input.txt";
        try (
                FileReader in = new FileReader(filePath);
                BufferedReader bufferedReader = new BufferedReader(in);
        ) {
            String line;
            SeminarSchedule seminarSchedule = new SeminarSchedule(LocalDate.now());

            while ((line = bufferedReader.readLine()) != null) {
                seminarSchedule.process(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
