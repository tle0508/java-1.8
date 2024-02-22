package exercise02;

import exercise02.EventSession.SessionDay;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;



public class SeminarApp {
    public static void main(String[] args) {
        String filePath = "exercise02/input.txt";
        ProcessSchedule processSchedule = new ProcessSchedule();
        SessionDay sessionDay = new SessionDay();
        try (
                FileReader in = new FileReader(filePath);
                BufferedReader bufferedReader = new BufferedReader(in);
        ) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                processSchedule.process(line);
            }

            //System.out.println(ProcessSchedule.AllOutput());
            //
            ProcessSchedule.AllOutput().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
