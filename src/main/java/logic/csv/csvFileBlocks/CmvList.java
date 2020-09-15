package logic.csv.csvFileBlocks;

public class CmvList {
    private int assignedCmvOrderNumber;
    private String cmvPowerUnitNumber;
    private String cmvVinNumber;

    public static class Builder {
        private CmvList cmvList;

        public Builder() {
            this.cmvList = new CmvList();
        }

        public Builder setAssignedUserNumber(int assignedCmvOrderNumber) {
            cmvList.assignedCmvOrderNumber = assignedCmvOrderNumber;
            return this;
        }

        public Builder setCmvPowerUnitNumber(String cmvPowerUnitNumber) {
            cmvList.cmvPowerUnitNumber = cmvPowerUnitNumber;
            return this;
        }

        public Builder setCmvVinNumber(String cmvVinNumber) {
            cmvList.cmvVinNumber = cmvVinNumber;
            return this;
        }

        public CmvList build() {
            return cmvList;
        }
    }
}
