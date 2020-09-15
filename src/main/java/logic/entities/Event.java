package logic.entities;

import logic.EventType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
    private EventType eventName;
    private long eventSequence;
    private String eldSequence;
    private long driverId1;
    private long driverId2;
    private long truckId;
    private String truckVin;
    private String truckNumber;
    private Date eventTimestamp;
    private String timeZoneOffset;
    private String trailerNumber;
    private String shippingNumber;
    private long orgId;
    private String eLogAppMode;
    private int recordOrigin;
    private int recordStatus;
    private int eventType;
    private int eventCode;
    private double totalVehicleMiles;
    private double accumulatedVehicleMiles;
    private double totalEngineHours;
    private double elapsedEngineHours;
    private String latitude;
    private String longitude;
    private String comment;

    public EventType getEventName() {
        return eventName;
    }

    public void setEventName() {
        this.eventName = defineEventType(eventType,eventCode);
    }

    public long getEventSequence() {
        return eventSequence;
    }

    public String getEldSequence() {
        return eldSequence;
    }

    public long getDriverId1() {
        return driverId1;
    }

    public long getDriverId2() {
        return driverId2;
    }

    public long getTruckId() {
        return truckId;
    }

    public String getTruckNumber() {
        return truckNumber;
    }

    public String getTruckVin() {
        return truckVin;
    }

    public Date getEventTimestamp() {
        return eventTimestamp;
    }

    public String getTimeZoneOffset() {
        return timeZoneOffset;
    }

    public String getTrailerNumber() {
        return trailerNumber;
    }

    public String getShippingNumber() {
        return shippingNumber;
    }

    public long getOrgId() {
        return orgId;
    }

    public String geteLogAppMode() {
        return eLogAppMode;
    }

    public int getRecordOrigin() {
        return recordOrigin;
    }

    public int getRecordStatus() {
        return recordStatus;
    }

    public int getEventType() {
        return eventType;
    }

    public int getEventCode() {
        return eventCode;
    }

    public double getTotalVehicleMiles() {
        return totalVehicleMiles;
    }

    public double getAccumulatedVehicleMiles() {
        return accumulatedVehicleMiles;
    }

    public double getTotalEngineHours() {
        return totalEngineHours;
    }

    public double getElapsedEngineHours() {
        return elapsedEngineHours;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getComment() {
        return comment;
    }

    private EventType defineEventType(int eventType, int eventCode) {
        switch (eventType) {
            case 1: {
                switch (eventCode) {
                    case 1:
                        return EventType.OFF_DUTY;
                    case 2:
                        return EventType.SLEEP;
                    case 3:
                        return EventType.DRIVING;
                    case 4:
                        return EventType.ON_DUTY;
                }
            }
            case 2: {
                return EventType.INTERMEDIATE_EVENT;
            }
            case 3: {
                switch (eventCode) {
                    case 0:
                        return EventType.PC_CLEARED;
                    case 1:
                        return EventType.PERSONAL_USE;
                    case 2:
                        return EventType.YARD_MOVE;
                }
            }
            case 5: {
                switch (eventCode) {
                    case 1: {
                        return EventType.LOGIN;
                    }
                    case 2: {
                        return EventType.LOGOUT;
                    }
                }
            }
            case 6: {
                switch (eventCode) {
                    case 1:
                    case 2: {
                        return EventType.POWER_UP;
                    }
                    case 3:
                    case 4: {
                        return EventType.POWER_DOWN;
                    }
                }
            }
            case 7: {
                switch (eventCode) {
                    case 1:
                        return EventType.MALFUNCTION_LOGGED;
                    case 2:
                        return EventType.MALFUNCTION_CLEARED;
                    case 3:
                        return EventType.DATA_DIAGNOSTIC_LOGGED;
                    case 4:
                        return EventType.DATA_DIAGNOSTIC_CLEARED;
                }
            }
        }
        return EventType.CERTIFICATION;
    }
    public static class Builder{
        private Event event;
        public Builder(){
            this.event=new Event();
        }
        public Builder setEventSequence(long eventSequence) {
            event.eventSequence = eventSequence;
            return this;
        }

        public Builder setEldSequence(String eldSequence) {
            event.eldSequence = eldSequence;
            return this;
        }

        public Builder setDriverId1(long driverId1) {
            event.driverId1 = driverId1;
            return this;
        }

        public Builder setDriverId2(long driverId2) {
            event.driverId2 = driverId2;
            return this;
        }

        public Builder setTruckId(long truckId) {
            event.truckId = truckId;
            return this;
        }

        public Builder setTruckVin(String truckVin) {
            event.truckVin = truckVin;
            return this;
        }

        public Builder setEventTimestamp(String eventTimestamp) {
            try {
                event.eventTimestamp =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(eventTimestamp);
            } catch (ParseException e) {
                System.out.println(e);
            }
            return this;
        }

        public Builder setTimeZoneOffset(String timeZoneOffset) {
            event.timeZoneOffset = timeZoneOffset;
            return this;
        }

        public Builder setTrailerNumber(String trailerNumber) {
            event.trailerNumber = trailerNumber;
            return this;
        }

        public Builder setShippingNumber(String shippingNumber) {
            event.shippingNumber = shippingNumber;
            return this;
        }

        public Builder setOrgId(long orgId) {
            event.orgId = orgId;
            return this;
        }

        public Builder seteLogAppMode(String eLogAppMode) {
            event.eLogAppMode = eLogAppMode;
            return this;
        }

        public Builder setRecordOrigin(int recordOrigin) {
            event.recordOrigin = recordOrigin;
            return this;
        }

        public Builder setRecordStatus(int recordStatus) {
            event.recordStatus = recordStatus;
            return this;
        }

        public Builder setEventType(int eventType) {
            event.eventType = eventType;
            return this;
        }

        public Builder setEventCode(int eventCode) {
            event.eventCode = eventCode;
            return this;
        }

        public Builder setTotalVehicleMiles(double totalVehicleMiles) {
            event.totalVehicleMiles = totalVehicleMiles;
            return this;
        }

        public Builder setAccumulatedVehicleMiles(double accumulatedVehicleMiles) {
            event.accumulatedVehicleMiles = accumulatedVehicleMiles;
            return this;
        }

        public Builder setTotalEngineHours(double totalEngineHours) {
            event.totalEngineHours = totalEngineHours;
            return this;
        }

        public Builder setElapsedEngineHours(double elapsedEngineHours) {
            event.elapsedEngineHours = elapsedEngineHours;
            return this;
        }

        public Builder setLatitude(String latitude) {
            event.latitude = latitude;
            return this;
        }

        public Builder setLongitude(String longitude) {
            event.longitude = longitude;
            return this;
        }

        public Builder setComment(String comment) {
            event.comment = comment;
            return this;
        }
        public Event build(){
            return event;
        }
    }

    @Override
    public String toString() {
        return
                eventName +
                ", TIME=" + eventTimestamp +
                ", RO=" + recordOrigin +
                ", RS=" + recordStatus +
                ", TVM=" + totalVehicleMiles +
                ", AVM=" + accumulatedVehicleMiles +
                ", TEH=" + totalEngineHours +
                ", EEH=" + elapsedEngineHours +
                ", LAT='" + latitude + '\'' +
                ", LONG='" + longitude + '\'';
    }

}
