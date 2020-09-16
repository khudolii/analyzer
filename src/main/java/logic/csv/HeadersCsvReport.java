package logic.csv;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class HeadersCsvReport {
    public final static String ELD_FILE_HEADER_SEGMENT = "ELD File Header Segment:";
    public final static String USER_LIST = "User List:";
    public final static String CMV_LIST = "CMV List:";
    public final static String END_OF_FILE = "End of File:";
    public final static String ELD_EVENT_LIST = "ELD Event List:";
    public final static String ELD_EVENT_ANNOTATIONS_OR_COMMENTS = "ELD Event Annotations or Comments:";
    public final static String DRIVERS_CERTIFICATION_ACTIONS = "Driver's Certification/Recertification Actions:";
    public final static String MALFUNCTIONS_AND_DATA_DIAGNOSTIC_EVENTS = "Malfunctions and Data Diagnostic Events:";
    public final static String ELD_LOGIN_LOGOUT_REPORT = "ELD Login/Logout Report:";
    public final static String CMV_ENGINE_POWER_UP_AND_SHUT_DOWN_ACTIVITY = "CMV Engine Power-Up and Shut Down Activity:";
    public final static String UNIDENTIFIED_DRIVER_PROFILE_RECORDS = "Unidentified Driver Profile Records:";

    public static List<String> getHeadersList(){
        return Arrays.asList(ELD_FILE_HEADER_SEGMENT,USER_LIST,CMV_LIST,ELD_EVENT_LIST,ELD_EVENT_ANNOTATIONS_OR_COMMENTS
        ,DRIVERS_CERTIFICATION_ACTIONS,MALFUNCTIONS_AND_DATA_DIAGNOSTIC_EVENTS,ELD_LOGIN_LOGOUT_REPORT,CMV_ENGINE_POWER_UP_AND_SHUT_DOWN_ACTIVITY
        ,UNIDENTIFIED_DRIVER_PROFILE_RECORDS,END_OF_FILE);
    }
}
