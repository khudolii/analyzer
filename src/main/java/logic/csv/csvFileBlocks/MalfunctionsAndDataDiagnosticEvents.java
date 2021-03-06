package logic.csv.csvFileBlocks;

import logic.ErrorsLog;
import logic.entities.Event;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class MalfunctionsAndDataDiagnosticEvents extends EventCSV {
    @Override
    public String toString() {
        return "MalfunctionsAndDataDiagnosticEvents{" +
                "eventSequence='" + eventSequence + '\'' +
                ", eventCode=" + eventCode +
                ", malfunctionOrDiagnosticCode='" + malfunctionOrDiagnosticCode + '\'' +
                ", eventDate=" + eventDate +
                ", eventTime=" + eventTime +
                ", totalVehicleMiles='" + totalVehicleMiles + '\'' +
                ", totalEngineHours='" + totalEngineHours + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                '}';
    }

    @Override
    public void compareEventsFromCsvAndDb(List<Event> eventsFromDb) {
        Optional<Event> foundEvent = findEventFromCsvInDb(eventsFromDb);
        try{
            log.info("*** CHECK: Compare event form CSV and DB: ELD Sequence = " + foundEvent.get().getEldSequence());
            checkIntValue(foundEvent.get().getEventCode(), getEventCode(), "getEventCode",foundEvent.get().getEldSequence());
            //checkStringValue(foundEvent.get().getMalfunctionDiagnosticCode(),getMalfunctionOrDiagnosticCode(), "getEgetMalfunctionOrDiagnosticCodeventCode",foundEvent.get().getEldSequence());
            checkStringValue(buildEventTimestampByMilis(foundEvent.get().getEventTimestamp().getTime()) , csvTimeFormatToTimestamp(getEventDate(), getEventTime()), "getEventTimeStamp",foundEvent.get().getEldSequence());
            checkDoubleValue(foundEvent.get().getTotalVehicleMiles(),getTotalVehicleMiles(),"getTotalVehicleMiles",foundEvent.get().getEldSequence());
            checkDoubleValue(foundEvent.get().getTotalEngineHours(), getTotalEngineHours() , "getTotalEngineHours",foundEvent.get().getEldSequence());
            if(errorLogs.size()>0)
                ErrorsLog.writeErrorsFromCsvFile(errorLogs);
            errorLogs.clear();
        } catch (NoSuchElementException e){
            log.error("* * * * No Such Event!");
           // ErrorLogs.setNoSuchElementLogs("No Such Event! " + "CSV -> " + this.toString() + "DB -> " + foundEvent.toString() + "\n");
        }
    }

    public static class Builder {
        private MalfunctionsAndDataDiagnosticEvents newMalfunctionsAndDataDiagnosticEvents;
        public Builder(){
            newMalfunctionsAndDataDiagnosticEvents = new MalfunctionsAndDataDiagnosticEvents();
        }
        public Builder setEventSequence(String eventSequence){
            newMalfunctionsAndDataDiagnosticEvents.eventSequence =eventSequence;
            return this;
        }
        public Builder setEventTime(String eventTime){
            newMalfunctionsAndDataDiagnosticEvents.eventTime=eventTime;
            return this;
        }
        public Builder setEventDate(String eventDate){
            newMalfunctionsAndDataDiagnosticEvents.eventDate=eventDate;
            return this;
        }
        public Builder setEventCode(int eventCode){
            newMalfunctionsAndDataDiagnosticEvents.eventCode=eventCode;
            return this;
        }
        public Builder setOrderNumber(String orderNumber){
            newMalfunctionsAndDataDiagnosticEvents.orderNumber=orderNumber;
            return this;
        }
        public Builder setMalfunctionOrDiagnosticCode(String malfunctionOrDiagnosticCode){
            newMalfunctionsAndDataDiagnosticEvents.malfunctionOrDiagnosticCode=malfunctionOrDiagnosticCode;
            return this;
        }
        public Builder setTotalVehicleMiles(double totalVehicleMiles){
            newMalfunctionsAndDataDiagnosticEvents.totalVehicleMiles=totalVehicleMiles;
            return this;
        }
        public Builder setTotalEngineHours(double totalEngineHours){
            newMalfunctionsAndDataDiagnosticEvents.totalEngineHours=totalEngineHours;
            return this;
        }
        public MalfunctionsAndDataDiagnosticEvents build(){
            return newMalfunctionsAndDataDiagnosticEvents;
        }
    }
}