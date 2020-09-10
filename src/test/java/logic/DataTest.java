package logic;

import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

public class DataTest {
    @Test
    public void startTest() throws SQLException {
        String driverId = System.getProperty("driverId");
        //String driverId = "34243";
        List<Event> events = EventDAO.getEvents(driverId);
        ErrorsLog.createReportFile(driverId,events.size());
        new Analyzer(events).toAnalyzeEvent();
        //events.forEach(new Analyzer(eventsList)::toAnalyzeEvent);
       // ErrorsLog.getErrorsLog().forEach(log::error);
        ErrorsLog.writeResultsToFile();
        System.out.println("Done.");
    }
}
