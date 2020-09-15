package logic;


import logic.entities.Event;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Analyzer {
    private static final Logger log = Logger.getLogger(Analyzer.class);
    private Event previousEvent = null;
    private Event powerUp = null;
    private Event login = null;
    private Event malfunction = null;
    private Event dataDiagnostic = null;
    private double timeDifferenceBetweenEvents;
    private final double scale = Math.pow(10, 2);
    private static ArrayList<String> eventErrors = new ArrayList<>();
    private List<Event> eventsList;

    public Analyzer(List eventsList) {
        this.eventsList = eventsList;
    }

    private double roundNumValue(double value) {
        return Math.ceil(value * scale) / scale;
    }

    private double calculateTimeDifferenceBetweenEvents(Event firstEvent, Event secondEvent) {
        return roundNumValue((double) Math.abs(firstEvent.getEventTimestamp().getTime() - secondEvent.getEventTimestamp().getTime()) / 3600000);
    }

    /*The difference TVM and EventTimestamp between the previous and the current event is calculated,
     the speed is calculated, if it is higher than 100 miles per hour, then an error is recorded.*/
    private void checkVehicleMiles(Event event) {
        double diffTotalVehicleMiles = roundNumValue(Math.abs((previousEvent.getTotalVehicleMiles() - event.getTotalVehicleMiles())));
        double speed = 0;
        if (timeDifferenceBetweenEvents != 0.0)
            speed = roundNumValue(diffTotalVehicleMiles / timeDifferenceBetweenEvents);
        else if (diffTotalVehicleMiles > 0)
            eventErrors.add("Very high speed. Time diff = " + timeDifferenceBetweenEvents + " h; Distance = " + diffTotalVehicleMiles + " m/h");
        if (speed > 100) {
            eventErrors.add("Very high speed. Time diff = " + timeDifferenceBetweenEvents + " h; Speed = " + speed + " m/h");
        }

        if (previousEvent.getEventName() == EventType.POWER_DOWN && event.getAccumulatedVehicleMiles() != 0) {
            eventErrors.add("AVM after Power Down must be 0!");
        }
        if (previousEvent.getEventName() == EventType.POWER_UP) {
            if (previousEvent.getAccumulatedVehicleMiles() != 0)
                eventErrors.add("AVM on Power Up must be 0!");
        }
        if (powerUp != null) {
            if (powerUp.getEventName() == EventType.POWER_UP
                    && (roundNumValue(Math.abs(event.getAccumulatedVehicleMiles() - previousEvent.getAccumulatedVehicleMiles())) != diffTotalVehicleMiles ))
                eventErrors.add("AVM error. AVM curr.event - AVM prev.event = " + event.getAccumulatedVehicleMiles() + " - " + previousEvent.getAccumulatedVehicleMiles() +
                        " = " + (roundNumValue(Math.abs(event.getAccumulatedVehicleMiles() - previousEvent.getAccumulatedVehicleMiles()))) + " m, but Expected = " + diffTotalVehicleMiles + " m");
        }
    }

    private void checkEngineHours(Event event) {
        if (roundNumValue(event.getTotalEngineHours() - previousEvent.getTotalEngineHours()) - timeDifferenceBetweenEvents >= 0.1)
            eventErrors.add("TEH error. TEH curr.event - TEH prev.event = " + event.getTotalEngineHours() + " - " + previousEvent.getTotalEngineHours() +
                    " = " + (roundNumValue(event.getTotalEngineHours() - previousEvent.getTotalEngineHours())) + " h, but Expected = " + timeDifferenceBetweenEvents + " h");
        if (previousEvent.getEventName() == EventType.POWER_UP) {
            if (previousEvent.getElapsedEngineHours() != 0)
                eventErrors.add("EEH on Power Up must be 0!");
        }
        if (powerUp != null) {
            if (powerUp.getEventName() == EventType.POWER_UP
                    && (roundNumValue(event.getElapsedEngineHours() - previousEvent.getElapsedEngineHours()) - timeDifferenceBetweenEvents) >= 0.1)
                eventErrors.add("EEH error. EEH curr.event - EEH prev.event = " + event.getTotalEngineHours() + " - " + previousEvent.getTotalEngineHours() +
                        " = " + (roundNumValue(event.getElapsedEngineHours() - previousEvent.getElapsedEngineHours())) + " h, but Expected = " + timeDifferenceBetweenEvents + " h");
        }
    }

    private void isDuplicateVerticalEvent(EventType eventType) {
        if (login != null)
            if ((eventType == EventType.LOGIN && login.getEventName() == EventType.LOGIN)
                    || (eventType == EventType.LOGOUT && login.getEventName() == EventType.LOGOUT))
                eventErrors.add("Repeating the " + eventType + " event." +
                        "\n First " + login.getEventName() + " event started at " + login.getEventTimestamp());
        if (powerUp != null)
            if ((eventType == EventType.POWER_UP && powerUp.getEventName() == EventType.POWER_UP)
                    || (eventType == EventType.POWER_DOWN && powerUp.getEventName() == EventType.POWER_DOWN))
                eventErrors.add("Repeating the " + eventType + " event." +
                        "\n First " + powerUp.getEventName() + " event started at " + powerUp.getEventTimestamp());
        if (malfunction != null)
            if ((eventType == EventType.MALFUNCTION_LOGGED && malfunction.getEventName() == EventType.MALFUNCTION_LOGGED)
                    || (eventType == EventType.MALFUNCTION_CLEARED && malfunction.getEventName() == EventType.MALFUNCTION_CLEARED))
                eventErrors.add("Repeating the " + eventType + " event." +
                        "\n First " + malfunction.getEventName() + " event started at " + malfunction.getEventTimestamp());
        if (dataDiagnostic != null)
            if ((eventType == EventType.DATA_DIAGNOSTIC_LOGGED && dataDiagnostic.getEventName() == EventType.DATA_DIAGNOSTIC_LOGGED)
                    || (eventType == EventType.DATA_DIAGNOSTIC_CLEARED && dataDiagnostic.getEventName() == EventType.DATA_DIAGNOSTIC_CLEARED))
                eventErrors.add("Repeating the " + eventType + " event." +
                        "\n First " + dataDiagnostic.getEventName() + " event started at " + dataDiagnostic.getEventTimestamp());
    }



    private void checkRecordOriginForEvent(Event event) {
        switch (event.getEventName()) {
            case OFF_DUTY:
            case SLEEP:
            case PERSONAL_USE:
            case YARD_MOVE:
            case PC_CLEARED:
            case CERTIFICATION: {
                if (event.getRecordOrigin() == 1) {
                    eventErrors.add(event.getEventName() + " RO=" + event.getRecordOrigin());
                }
            }
            break;
            case LOGIN:
            case LOGOUT:
            case POWER_UP:
            case POWER_DOWN:
            case MALFUNCTION_CLEARED:
            case MALFUNCTION_LOGGED:
            case DATA_DIAGNOSTIC_LOGGED:
            case DATA_DIAGNOSTIC_CLEARED:
            case INTERMEDIATE_EVENT: {
                if (event.getRecordOrigin() == 2) {
                    eventErrors.add(event.getEventName() + " RO=" + event.getRecordOrigin());
                }
            }
            break;
        }
    }

    private boolean isManualEventWithoutData(Event event) {
        try {
            return event.getRecordOrigin() == 2
                    && event.getTotalEngineHours() == 0
                    && event.getAccumulatedVehicleMiles() == 0
                    && event.getElapsedEngineHours() == 0
                    && event.getTotalVehicleMiles() == 0
                    && event.getLatitude().equals("M")
                    && event.getLongitude().equals("M");
        } catch (NullPointerException e) {
            eventErrors.add("Found Null Value in event!");
            return false;
        }
    }

    private boolean isCorrectAttributeOnCertification(Event event) {
        return event.getTotalEngineHours() == 0.
                && event.getAccumulatedVehicleMiles() == 0.
                && event.getElapsedEngineHours() == 0.
                && event.getTotalVehicleMiles() == 0.
                && event.getLatitude() == null
                && event.getLongitude() == null
                && event.getComment() == null;
    }

    private void checkIntermediateEvent(Event event) {
        int index = eventsList.indexOf(event) - 1;
        boolean flag = false;
        double intermediateEventInterval = 1.;
        while (!flag) {
            if (eventsList.get(index).getEventType() > 3) {
                index--;
                continue;
            }
            double timeDifference = calculateTimeDifferenceBetweenEvents(event, eventsList.get(index));
            if (timeDifference - intermediateEventInterval >= 0.1) {
                eventErrors.add("Intermediate events error! Time difference between current and " + eventsList.get(index).getEventName() + "" +
                        "( " + eventsList.get(index).getEventTimestamp() + " ) events more than 1 h. Real time difference = " + timeDifference + " h, Expected = " +
                        intermediateEventInterval + " h");
            }
            intermediateEventInterval++;
            if (eventsList.get(index).getEventName() == EventType.OFF_DUTY
                    || eventsList.get(index).getEventName() == EventType.ON_DUTY
                    || eventsList.get(index).getEventName() == EventType.SLEEP) {
                break;
            } else if (eventsList.get(index).getEventName() == EventType.DRIVING
                    || eventsList.get(index).getEventName() == EventType.PERSONAL_USE
                    || eventsList.get(index).getEventName() == EventType.YARD_MOVE) {
                flag = true;
            } else {
                index--;
                if (index < 0)
                    break;
            }
        }
        if (!flag)
            eventErrors.add(event.getEventName() + " after " + eventsList.get(index).getEventName() + " ( " + eventsList.get(index).getEventTimestamp() + " )");
    }

    private void checkPreviousEventType(Event event, EventType requiredEvent) {
        int index = eventsList.indexOf(event) - 1;
        boolean flag = true;
        while (eventsList.get(index).getEventName() != requiredEvent) {
            if (event.getEventName() == EventType.PC_CLEARED && (eventsList.get(index).getEventName() == EventType.PERSONAL_USE
                    || eventsList.get(index).getEventName() == EventType.YARD_MOVE))
                break;
            if (eventsList.get(index).getEventType() == 1) {
                flag = false;
                break;
            } else {
                index--;
                if (index < 0)
                    break;
            }
        }
        if (!flag)
            eventErrors.add(event.getEventName() + " after " + eventsList.get(index).getEventName());
    }


    private void checkAfterLogoutAllEventsAreManual(Event event) {
        if (login != null && event.getEventName() != EventType.LOGOUT && login.getEventName() == EventType.LOGOUT) {
            if (event.getRecordOrigin() != 2 || event.getEventType() == 2 || event.getEventType() >= 5)
                eventErrors.add("There can only be manual events after the Logout ( ES = " + login.getEventSequence() + "; TIME= " + login.getEventTimestamp() + ")  event. ");
        }
    }

    private void checkClearedHaveDataOfNextEvent(Event event) {
        if (previousEvent.getEventName() == EventType.PC_CLEARED && (event.getEventType() == 1 || event.getEventType() == 3)) {
            if (event.getTotalVehicleMiles() != previousEvent.getTotalVehicleMiles()
                    || event.getAccumulatedVehicleMiles() != previousEvent.getAccumulatedVehicleMiles()
                    || event.getTotalEngineHours() != previousEvent.getTotalEngineHours()
                    || event.getElapsedEngineHours() != previousEvent.getElapsedEngineHours()) {
                eventErrors.add("PC / Cleared event must have next event data");
            }
        }
    }

    public void toAnalyzeEvent() {
        for (Event event : eventsList) {
            if (previousEvent != null) {
                event.setEventName();
                checkRecordOriginForEvent(event);
                checkClearedHaveDataOfNextEvent(event);
                timeDifferenceBetweenEvents = calculateTimeDifferenceBetweenEvents(previousEvent, event);
                switch (event.getEventName()) {
                    case CERTIFICATION: {
                        if (!isCorrectAttributeOnCertification(event)) {
                            eventErrors.add(String.valueOf(EventType.CERTIFICATION));
                        }
                    }
                    break;
                    case OFF_DUTY:
                    case SLEEP:
                    case DRIVING:
                    case ON_DUTY: {
                        if (!isManualEventWithoutData(event)) {
                            checkVehicleMiles(event);
                            checkEngineHours(event);
                        }
                    }
                    break;
                    case PERSONAL_USE: {
                        checkPreviousEventType(event, EventType.OFF_DUTY);
                        checkVehicleMiles(event);
                        checkEngineHours(event);
                    }
                    break;
                    case YARD_MOVE: {
                        checkPreviousEventType(event, EventType.ON_DUTY);
                        checkVehicleMiles(event);
                        checkEngineHours(event);
                    }
                    break;
                    case PC_CLEARED: {
                        checkPreviousEventType(event, EventType.YARD_MOVE);
                    }
                    break;
                    case LOGIN:
                    case LOGOUT: {
                        isDuplicateVerticalEvent(event.getEventName());
                        login = event;
                    }
                    break;
                    case POWER_UP:
                    case POWER_DOWN: {
                        isDuplicateVerticalEvent(event.getEventName());
                        powerUp = event;
                    }
                    break;
                    case MALFUNCTION_CLEARED:
                    case MALFUNCTION_LOGGED: {
                        isDuplicateVerticalEvent(event.getEventName());
                        malfunction = event;
                    }
                    break;
                    case DATA_DIAGNOSTIC_LOGGED:
                    case DATA_DIAGNOSTIC_CLEARED: {
                        isDuplicateVerticalEvent(event.getEventName());
                        dataDiagnostic = event;
                    }
                    break;
                    case INTERMEDIATE_EVENT: {
                        checkIntermediateEvent(event);
                        checkVehicleMiles(event);
                        checkEngineHours(event);
                    }
                }
                checkAfterLogoutAllEventsAreManual(event);
                if (eventErrors.size() > 0)
                    ErrorsLog.writeErrorsFromEvent(eventErrors, event, previousEvent);
                eventErrors.clear();
            }
            if (event.getEventName() != EventType.CERTIFICATION)
                if (!isManualEventWithoutData(event))
                    previousEvent = event;
        }
    }

}
