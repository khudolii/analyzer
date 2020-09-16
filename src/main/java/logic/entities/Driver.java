package logic.entities;

public class Driver {
    private String firstName;
    private String lastName;
    private String loginName;
    private String licenseIssuingState;
    private String licenseNumber;
    private String organizationName;
    private String usdotNumber;

    public Driver(String firstName, String lastName, String loginName, String licenseIssuingState, String licenseNumber, String organizationName, String usdotNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.loginName = loginName;
        this.licenseIssuingState = licenseIssuingState;
        this.licenseNumber = licenseNumber;
        this.organizationName = organizationName;
        this.usdotNumber = usdotNumber;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", loginName='" + loginName + '\'' +
                ", licenseIssuingState='" + licenseIssuingState + '\'' +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", organizationName='" + organizationName + '\'' +
                ", usdotNumber='" + usdotNumber + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLicenseIssuingState() {
        return licenseIssuingState;
    }

    public void setLicenseIssuingState(String licenseIssuingState) {
        this.licenseIssuingState = licenseIssuingState;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getUsdotNumber() {
        return usdotNumber;
    }

    public void setUsdotNumber(String usdotNumber) {
        this.usdotNumber = usdotNumber;
    }
}
