package logic.csv;

import logic.ErrorsLog;
import logic.csv.csvFileBlocks.EldEvents;
import logic.csv.csvFileBlocks.EldFileHeaderSegment;
import logic.dao.DriverDAO;
import logic.dao.EventDAO;
import logic.entities.Driver;
import logic.entities.Event;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvAnalyzer {

    private static ArrayList<String> errorsLog = new ArrayList<>();

    private void checkAllRequireHeaders(List<String[]> csvFileRowsList) {
        int headerIndex = 0;
        String currentHeader;
        for (String[] row : csvFileRowsList) {
            if (row.length == 1 && headerIndex < HeadersCsvReport.getHeadersList().size()) {
                currentHeader = Arrays.toString(row).replaceAll("\\[", "").replaceAll("]", "");
                if (currentHeader.equals(HeadersCsvReport.getHeadersList().get(headerIndex))) {
                    errorsLog.add("Incorrect header: Actual = " + currentHeader + ", but Expected = " + HeadersCsvReport.getHeadersList().get(headerIndex));
                }
                headerIndex++;
            }
        }
        if (errorsLog.size() > 0) {
            ErrorsLog.writeErrorsFromCsvFile(errorsLog);
            errorsLog.clear();
        }
    }

    private void checkEldFileHeaderSegmentAttribute(EldFileHeaderSegment eldFileHeaderSegment, Driver driver){
        if(!driver.getFirstName().equals(eldFileHeaderSegment.getDriverFirstName())
        || !driver.getLastName().equals(eldFileHeaderSegment.getDriverLastName())
                || !driver.getLicenseNumber().equals(eldFileHeaderSegment.getDriverLicenseNumber())
                || !driver.getLicenseIssuingState().equals(eldFileHeaderSegment.getDriverLicenseIssuingState())
                || !driver.getOrganizationName().equals(eldFileHeaderSegment.getCarrierName())
                || !driver.getLoginName().equals(eldFileHeaderSegment.getDriverEldUserName())
                || !driver.getUsdotNumber().equals(eldFileHeaderSegment.getCarriersUSDOTNumber())
        )
            errorsLog.add("Find error! Driver from DB: " + driver.toString() + " ; Driver from CSV:" + eldFileHeaderSegment.toString());
    }
    public void toAnalyzeCsvFile() throws Exception {
        CsvReader csvReader = new CsvReader();
        csvReader.readCsvFile("/home/evgeniy/Desktop/Reports/Salov7343082720-4WI1E56AY.txt");
        checkAllRequireHeaders(csvReader.getCsvFileRowsList());
        csvReader.parseEldEvents();
        EldFileHeaderSegment eldFileHeaderSegment = csvReader.parseEldHeader();
        Driver driver = DriverDAO.getDriver("34132");
        if(eldFileHeaderSegment!=null && driver!= null){
            checkEldFileHeaderSegmentAttribute(eldFileHeaderSegment, driver);
        }
        ErrorsLog.writeErrorsFromCsvFile(errorsLog);
        List<Event> events = EventDAO.getEvents();
        csvReader.getEldEventsList().forEach(eldEvents -> eldEvents.compareEventsFromCsvAndDb(events));
        csvReader.getCmvEnginePowerUpAndShutDownEvents().forEach(eldEvents -> eldEvents.compareEventsFromCsvAndDb(events));
        csvReader.getDriversCertificationActions().forEach(eldEvents -> eldEvents.compareEventsFromCsvAndDb(events));
        csvReader.getEldLoginAndLogoutEvents().forEach(eldEvents -> eldEvents.compareEventsFromCsvAndDb(events));
        csvReader.getMalfunctionsAndDataDiagnosticEvents().forEach(eldEvents -> eldEvents.compareEventsFromCsvAndDb(events));
        csvReader.getUnidentifiedEvents().forEach(eldEvents -> eldEvents.compareEventsFromCsvAndDb(events));

    }
    public static void main(String[] args) throws Exception {
        String path = "/home/evgeniy/Desktop/Reports/Salov7343082720-4WI1E56AY.txt";
        CsvReader csvReader = new CsvReader();
        try {
            csvReader.readCsvFile(path);
            csvReader.parseEldHeader();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ErrorsLog.createReportFile("34132", 40);
        CsvAnalyzer csvAnalyzer = new CsvAnalyzer();
        csvAnalyzer.toAnalyzeCsvFile();
        ErrorsLog.writeResultsToFile();
    }
}
