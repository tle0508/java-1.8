package exercise02.DTO.process;

import java.util.ArrayList;
import java.util.List;

public class ProcessData {
    private List<ProcessSessionDay> processSessionDayList =new ArrayList<>();

    public ProcessData() {
    }

    public ProcessData(List<ProcessSessionDay> processSessionDayList) {
        this.processSessionDayList = processSessionDayList;
    }

    public List<ProcessSessionDay> getProcessSessionDayList() {
        return processSessionDayList;
    }

    public void setProcessSessionDayList(List<ProcessSessionDay> processSessionDayList) {
        this.processSessionDayList = processSessionDayList;
    }
}
