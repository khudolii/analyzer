package logic;

import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class);
    //private static final String DRIVER_ID = "34243";
    public static void main(String[] args) throws SQLException {
        //String driverId = System.getProperty("driverId");
        String driverId = "2199";
        List<Event> events = EventDAO.getEvents(driverId);
        ErrorsLog.createReportFile(driverId,events.size());
        new Analyzer(events).toAnalyzeEvent();
        //events.forEach(new Analyzer(eventsList)::toAnalyzeEvent);
        ErrorsLog.getErrorsLog().forEach(log::error);
        ErrorsLog.writeResultsToFile();
        System.out.println("Done.");
    }
}
