package logic.csv;

import au.com.bytecode.opencsv.CSVReader;
import logic.ErrorsLog;
import logic.csv.csvFileBlocks.*;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Integer.parseInt;
import static logic.csv.HeadersCsvReport.*;

public class CsvReader {
    private EldFileHeaderSegment eldFileHeaderSegment;
    private List<UserList> userList = new ArrayList<>();
    private List<CmvList> cmvList = new ArrayList<>();
    private List<EldEvents> eldEventsList = new ArrayList<>();
    private List<DriversCertificationActions> driversCertificationActions = new ArrayList<>();
    private List<CmvEnginePowerUpAndShutDownEvents> cmvEnginePowerUpAndShutDownEvents = new ArrayList<>();
    private List<EldEventAnnotationOrComments> eldEventAnnotationOrComments = new ArrayList<>();
    private List<EldLoginAndLogoutEvents> eldLoginAndLogoutEvents = new ArrayList<>();
    private List<MalfunctionsAndDataDiagnosticEvents> malfunctionsAndDataDiagnosticEvents = new ArrayList<>();
    private List<UnidentifiedEvents> unidentifiedEvents = new ArrayList<>();
    private static ArrayList<String> errorsLog = new ArrayList<>();

    public CsvReader(InputStreamReader reader) {
        CSVReader csvReader = new CSVReader(reader, ',', '"', 8);
        try {
            csvFileRowsList = csvReader.readAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public CsvReader() {
    }

    public List<String[]> getCsvFileRowsList() {
        return csvFileRowsList;
    }

    public List<UserList> getUserList() {
        return userList;
    }

    public List<CmvList> getCmvList() {
        return cmvList;
    }

    public List<EldEvents> getEldEventsList() {
        return eldEventsList;
    }

    public List<DriversCertificationActions> getDriversCertificationActions() {
        return driversCertificationActions;
    }

    public List<CmvEnginePowerUpAndShutDownEvents> getCmvEnginePowerUpAndShutDownEvents() {
        return cmvEnginePowerUpAndShutDownEvents;
    }

    public List<EldEventAnnotationOrComments> getEldEventAnnotationOrComments() {
        return eldEventAnnotationOrComments;
    }

    public List<EldLoginAndLogoutEvents> getEldLoginAndLogoutEvents() {
        return eldLoginAndLogoutEvents;
    }

    public List<MalfunctionsAndDataDiagnosticEvents> getMalfunctionsAndDataDiagnosticEvents() {
        return malfunctionsAndDataDiagnosticEvents;
    }

    public List<UnidentifiedEvents> getUnidentifiedEvents() {
        return unidentifiedEvents;
    }

    public List<String[]> csvFileRowsList;

    public void readCsvFile(String pathToCsvFile) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(pathToCsvFile), ',', '"', 0);
        this.csvFileRowsList = reader.readAll();
    }

   /* public static void main(String[] args) {
        String path = "/home/evgeniy/Desktop/Reports/Salov7343082720-4WI1E56AY.txt";
        CsvReader csvReader = new CsvReader();
        try {
            csvReader.readCsvFile(path);
            csvReader.parseEldHeader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public EldFileHeaderSegment parseEldHeader() {
        if (Arrays.toString(csvFileRowsList.get(0)).replaceAll("\\[", "").replaceAll("\\]", "").equals(ELD_FILE_HEADER_SEGMENT)) {
            return new EldFileHeaderSegment
                    .Builder()
                    .setDriverLastName(csvFileRowsList.get(1)[0])
                    .setDriverFirstName(csvFileRowsList.get(1)[1])
                    .setDriverEldUserName(csvFileRowsList.get(1)[2])
                    .setDriverLicenseIssuingState(csvFileRowsList.get(1)[3])
                    .setDriverLicenseNumber(csvFileRowsList.get(1)[4])
                    .setCoDriverLastName(csvFileRowsList.get(2)[0])
                    .setCoDriverFirstName(csvFileRowsList.get(2)[1])
                    .setCoDriverEldUserName(csvFileRowsList.get(2)[2])
                    .setCmvPowerUnitNumber(csvFileRowsList.get(3)[0])
                    .setCmvVinNumber(csvFileRowsList.get(3)[1])
                    .setTrailerNumber(csvFileRowsList.get(3)[2])
                    .setCarriersUSDOTNumber(csvFileRowsList.get(4)[0])
                    .setCarrierName(csvFileRowsList.get(4)[1])
                    .setMultiDayBasisUsed(csvFileRowsList.get(4)[2])
                    .setTimeZoneOffsetFromUtc(csvFileRowsList.get(4)[4])
                    .setShippingDocumentNumber(csvFileRowsList.get(5)[0])
                    .setExemptDriverConfiguration(csvFileRowsList.get(5)[1])
                    .setCurrentDate(csvFileRowsList.get(6)[0])
                    .setCurrentTime(csvFileRowsList.get(6)[1])
                    .setCurrentLatitude(csvFileRowsList.get(6)[2])
                    .setCurrentLongitude(csvFileRowsList.get(6)[3])
                    .setCurrentTotalVehicleMiles(Double.parseDouble(csvFileRowsList.get(6)[4]))
                    .setCurrentTotalEngineHours(Double.parseDouble(csvFileRowsList.get(6)[5]))
                    .setEldRegistrationId(csvFileRowsList.get(7)[0])
                    .setEldIdentifier(csvFileRowsList.get(7)[1])
                    .setEldAuthenticated(csvFileRowsList.get(7)[2])
                    .setOutputFileComment(csvFileRowsList.get(7)[3])
                    .build();
        }
        return null;
    }

    public void parseEldEvents() throws Exception {
        String header = "";
        int expectedElementsInRow = 0;
        for (String[] row : csvFileRowsList) {
            if (row.length == 1) {
                header = Arrays.toString(row).replaceAll("\\[", "").replaceAll("\\]", "");
                continue;
            }
            switch (header) {
                case ELD_EVENT_LIST: {
                    expectedElementsInRow = 18;
                    eldEventsList.add(new EldEvents
                            .Builder()
                            .setEventSequence(row[0])
                            .setRecordStatus(parseInt(row[1]))
                            .setRecordOrigin(parseInt(row[2]))
                            .setEventType(parseInt(row[3]))
                            .setEventCode(parseInt(row[4]))
                            .setEventDate(row[5])
                            .setEventTime(row[6])
                            .setAccumulatedVehicleMiles(Double.parseDouble(row[7]))
                            .setElapsedEngineHours(Double.parseDouble(row[8]))
                            .setLatitude(row[9])
                            .setLongitude(row[10])
                            .setDistanceLastValidCoordinates(Double.parseDouble(row[11]))
                            .setOrderNumber(row[12])
                            .setOrderNumberForRecordOriginator(row[13])
                            .setMalfunctionIndicatorStatus(parseInt(row[14]))
                            .setDataDiagnosticEventIndicatorStatus(parseInt(row[15]))
                            .setEventDataCheckValue(row[16])
                            .build());
                }
                break;
                case ELD_EVENT_ANNOTATIONS_OR_COMMENTS: {
                    expectedElementsInRow = 7;
                    eldEventAnnotationOrComments.add(new EldEventAnnotationOrComments
                            .Builder()
                            .setEventSequence(row[0])
                            .setEldUserName(row[1])
                            .setCommentTextOrAnnotation(row[2])
                            .setEventDate(row[3])
                            .setEventTime(row[4])
                            .setDriverLocationDescription(row[5])
                            .build());
                }
                break;
                case DRIVERS_CERTIFICATION_ACTIONS: {
                    expectedElementsInRow = 7;
                    driversCertificationActions.add(new DriversCertificationActions
                            .Builder()
                            .setEventSequence(row[0])
                            .setEventCode(parseInt(row[1]))
                            .setEventDate(row[2])
                            .setEventTime(row[3])
                            .setDateOfTheCertifiedRecord(row[4])
                            .setOrderNumber(row[5])
                            .build());
                }
                break;
                case MALFUNCTIONS_AND_DATA_DIAGNOSTIC_EVENTS: {
                    expectedElementsInRow = 9;
                    malfunctionsAndDataDiagnosticEvents.add(new MalfunctionsAndDataDiagnosticEvents
                            .Builder()
                            .setEventSequence(row[0])
                            .setEventCode(parseInt(row[1]))
                            .setMalfunctionOrDiagnosticCode(row[2])
                            .setEventDate(row[3])
                            .setEventTime(row[4])
                            .setTotalVehicleMiles(Double.parseDouble(row[5]))
                            .setTotalEngineHours(Double.parseDouble(row[6]))
                            .setOrderNumber(row[7])
                            .build());
                }
                break;
                case ELD_LOGIN_LOGOUT_REPORT: {
                    expectedElementsInRow = 8;
                    eldLoginAndLogoutEvents.add(new EldLoginAndLogoutEvents
                            .Builder()
                            .setEventSequence(row[0])
                            .setEventCode(parseInt(row[1]))
                            .setEldUserName(row[2])
                            .setEventDate(row[3])
                            .setEventTime(row[4])
                            .setTotalVehicleMiles(Double.parseDouble(row[5]))
                            .setTotalEngineHours(Double.parseDouble(row[6]))
                            .build());
                }
                break;
                case CMV_ENGINE_POWER_UP_AND_SHUT_DOWN_ACTIVITY: {
                    expectedElementsInRow = 13;
                    cmvEnginePowerUpAndShutDownEvents.add(new CmvEnginePowerUpAndShutDownEvents
                            .Builder()
                            .setEventSequence(row[0])
                            .setEventCode(parseInt(row[1]))
                            .setEventDate(row[2])
                            .setEventTime(row[3])
                            .setTotalVehicleMiles(Double.parseDouble(row[4]))
                            .setTotalEngineHours(Double.parseDouble(row[5]))
                            .setLatitude(row[6])
                            .setLongitude(row[7])
                            .setPowerUnitNumber(row[8])
                            .setVinNumber(row[9])
                            .setTrailerNumber(row[10])
                            .setShippingNumber(row[11])
                            .build());
                }
                break;
                case UNIDENTIFIED_DRIVER_PROFILE_RECORDS: {
                    expectedElementsInRow = 16;
                    unidentifiedEvents.add(new UnidentifiedEvents
                            .Builder()
                            .setEventSequence(row[0])
                            .setRecordStatus(parseInt(row[1]))
                            .setRecordOrigin(parseInt(row[2]))
                            .setEventType(parseInt(row[3]))
                            .setEventCode(parseInt(row[4]))
                            .setEventDate(row[5])
                            .setEventTime(row[6])
                            .setAccumulatedVehicleMiles(Double.parseDouble(row[7]))
                            .setElapsedEngineHours(Double.parseDouble(row[8]))
                            .setLatitude(row[9])
                            .setLongitude(row[10])
                            .setDistanceLastValidCoordinates(Double.parseDouble(row[11]))
                            .setOrderNumber(row[12])
                            .setMalfunctionIndicatorStatus(parseInt(row[13]))
                            .setEventDataCheckValue(row[14])
                            .build());
                }
            }
            if (row.length != expectedElementsInRow && !header.equals(ELD_FILE_HEADER_SEGMENT) && !header.equals(END_OF_FILE)
            && !header.equals(CMV_LIST) && !header.equals(USER_LIST)) {
                errorsLog.add("Block: " + header + ". Invalid number of elements per line, actual= " + row.length + " ," +
                        " but expected = " + expectedElementsInRow + ".\n Line: " + Arrays.toString(row));
            }
        }
        if (errorsLog.size() > 0) {
            ErrorsLog.writeErrorsFromCsvFile(errorsLog);
            errorsLog.clear();
        }
    }
}
