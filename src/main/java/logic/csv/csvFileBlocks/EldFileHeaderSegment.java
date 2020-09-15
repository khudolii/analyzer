package logic.csv.csvFileBlocks;

public class EldFileHeaderSegment {
    private String driverFirstName;
    private String driverLastName;
    private String driverEldUserName;
    private String driverLicenseIssuingState;
    private String driverLicenseNumber;
    private String coDriverFirstName;
    private String coDriverLastName;
    private String coDriverEldUserName;
    private String cmvPowerUnitNumber;
    private String cmvVinNumber;
    private String trailerNumber;
    private String carriersUSDOTNumber;
    private String carrierName;
    private String multiDayBasisUsed;
    private String timeZoneOffsetFromUtc;
    private String shippingDocumentNumber;
    private String exemptDriverConfiguration;
    private String currentDate;
    private String currentTime;
    private String currentLatitude;
    private String currentLongitude;
    private double currentTotalVehicleMiles;
    private double currentTotalEngineHours;
    private String eldRegistrationId;
    private String eldIdentifier;
    private String eldAuthenticated;
    private String outputFileComment;

    @Override
    public String toString() {
        return "EldFileHeaderSegment{" +
                "\ndriverFirstName='" + driverFirstName + '\'' +
                ",\n driverLastName='" + driverLastName + '\'' +
                ",\n driverEldUserName='" + driverEldUserName + '\'' +
                ",\n driverLicenseIssuingState='" + driverLicenseIssuingState + '\'' +
                ",\n driverLicenseNumber='" + driverLicenseNumber + '\'' +
                ",\n coDriverFirstName='" + coDriverFirstName + '\'' +
                ",\n coDriverLastName='" + coDriverLastName + '\'' +
                ",\n coDriverEldUserName='" + coDriverEldUserName + '\'' +
                ",\n cmvPowerUnitNumber='" + cmvPowerUnitNumber + '\'' +
                ",\n cmvVinNumber='" + cmvVinNumber + '\'' +
                ",\n trailerNumber='" + trailerNumber + '\'' +
                ",\n carriersUSDOTNumber='" + carriersUSDOTNumber + '\'' +
                ",\n carrierName='" + carrierName + '\'' +
                ",\n multiDayBasisUsed='" + multiDayBasisUsed + '\'' +
                ",\n timeZoneOffsetFromUtc='" + timeZoneOffsetFromUtc + '\'' +
                ",\n shippingDocumentNumber='" + shippingDocumentNumber + '\'' +
                ",\n exemptDriverConfiguration='" + exemptDriverConfiguration + '\'' +
                ",\n currentDate='" + currentDate + '\'' +
                ",\n currentTime='" + currentTime + '\'' +
                ",\n currentLatitude='" + currentLatitude + '\'' +
                ",\n currentLongitude='" + currentLongitude + '\'' +
                ",\n currentTotalVehicleMiles=" + currentTotalVehicleMiles +
                ",\n currentTotalEngineHours=" + currentTotalEngineHours +
                ",\n eldRegistrationId='" + eldRegistrationId + '\'' +
                ",\n eldIdentifier='" + eldIdentifier + '\'' +
                ",\n eldAuthenticated='" + eldAuthenticated + '\'' +
                ",\n outputFileComment='" + outputFileComment + '\'' +
                '}';
    }

    public static class Builder{
        private EldFileHeaderSegment eldFileHeaderSegment;

        public Builder(){
            eldFileHeaderSegment = new EldFileHeaderSegment();
        }
        public Builder setDriverFirstName(String driverFirstName) {
            eldFileHeaderSegment.driverFirstName = driverFirstName;
            return this;
        }

        public Builder setDriverLastName(String driverLastName) {
            eldFileHeaderSegment.driverLastName = driverLastName;
            return this;
        }

        public Builder setDriverEldUserName(String driverEldUserName) {
            eldFileHeaderSegment.driverEldUserName = driverEldUserName;
            return this;
        }

        public Builder setDriverLicenseIssuingState(String driverLicenseIssuingState) {
            eldFileHeaderSegment.driverLicenseIssuingState = driverLicenseIssuingState;
            return this;
        }

        public Builder setDriverLicenseNumber(String driverLicenseNumber) {
            eldFileHeaderSegment.driverLicenseNumber = driverLicenseNumber;
            return this;
        }

        public Builder setCoDriverFirstName(String coDriverFirstName) {
            eldFileHeaderSegment.coDriverFirstName = coDriverFirstName;
            return this;
        }

        public Builder setCoDriverLastName(String coDriverLastName) {
            eldFileHeaderSegment.coDriverLastName = coDriverLastName;
            return this;
        }

        public Builder setCoDriverEldUserName(String coDriverEldUserName) {
            eldFileHeaderSegment.coDriverEldUserName = coDriverEldUserName;
            return this;
        }

        public Builder setCmvPowerUnitNumber(String cmvPowerUnitNumber) {
            eldFileHeaderSegment.cmvPowerUnitNumber = cmvPowerUnitNumber;
            return this;
        }

        public Builder setCmvVinNumber(String cmvVinNumber) {
            eldFileHeaderSegment.cmvVinNumber = cmvVinNumber;
            return this;
        }

        public Builder setTrailerNumber(String trailerNumber) {
            eldFileHeaderSegment.trailerNumber = trailerNumber;
            return this;
        }

        public Builder setCarriersUSDOTNumber(String carriersUSDOTNumber) {
            eldFileHeaderSegment.carriersUSDOTNumber = carriersUSDOTNumber;
            return this;
        }

        public Builder setCarrierName(String carrierName) {
            eldFileHeaderSegment.carrierName = carrierName;
            return this;
        }

        public Builder setMultiDayBasisUsed(String multiDayBasisUsed) {
            eldFileHeaderSegment.multiDayBasisUsed = multiDayBasisUsed;
            return this;
        }

        public Builder setTimeZoneOffsetFromUtc(String timeZoneOffsetFromUtc) {
            eldFileHeaderSegment.timeZoneOffsetFromUtc = timeZoneOffsetFromUtc;
            return this;
        }

        public Builder setShippingDocumentNumber(String shippingDocumentNumber) {
            eldFileHeaderSegment.shippingDocumentNumber = shippingDocumentNumber;
            return this;
        }

        public Builder setExemptDriverConfiguration(String exemptDriverConfiguration) {
            eldFileHeaderSegment.exemptDriverConfiguration = exemptDriverConfiguration;
            return this;
        }

        public Builder setCurrentDate(String currentDate) {
            eldFileHeaderSegment.currentDate = currentDate;
            return this;
        }

        public Builder setCurrentTime(String currentTime) {
            eldFileHeaderSegment.currentTime = currentTime;
            return this;
        }

        public Builder setCurrentLatitude(String currentLatitude) {
            eldFileHeaderSegment.currentLatitude = currentLatitude;
            return this;
        }

        public Builder setCurrentLongitude(String currentLongitude) {
            eldFileHeaderSegment.currentLongitude = currentLongitude;
            return this;
        }

        public Builder setCurrentTotalVehicleMiles(double currentTotalVehicleMiles) {
            eldFileHeaderSegment.currentTotalVehicleMiles = currentTotalVehicleMiles;
            return this;
        }

        public Builder setCurrentTotalEngineHours(double currentTotalEngineHours) {
            eldFileHeaderSegment.currentTotalEngineHours = currentTotalEngineHours;
            return this;
        }

        public Builder setEldRegistrationId(String eldRegistrationId) {
            eldFileHeaderSegment.eldRegistrationId = eldRegistrationId;
            return this;
        }

        public Builder setEldIdentifier(String eldIdentifier) {
            eldFileHeaderSegment.eldIdentifier = eldIdentifier;
            return this;
        }

        public Builder setEldAuthenticated(String eldAuthenticated) {
            eldFileHeaderSegment.eldAuthenticated = eldAuthenticated;
            return this;
        }

        public Builder setOutputFileComment(String outputFileComment) {
            eldFileHeaderSegment.outputFileComment = outputFileComment;
            return this;
        }
        public EldFileHeaderSegment build(){
            return eldFileHeaderSegment;
        }
    }
}
