package logic.csv.csvFileBlocks;

public class UserList {
    private int assignedUserOrderNumber;
    private char userEldAccountType;
    private String userLastName;
    private String userFirstName;

    public static class Builder {
        private UserList userList;

        public Builder() {
            this.userList = new UserList();
        }

        public Builder setAssignedUserNumber(int assignedUserOrderNumber) {
            userList.assignedUserOrderNumber = assignedUserOrderNumber;
            return this;
        }

        public Builder setUserEldAccountType(char userEldAccountType) {
            userList.userEldAccountType = userEldAccountType;
            return this;
        }

        public Builder setUserLastName(String userLastName) {
            userList.userLastName = userLastName;
            return this;
        }

        public Builder setUserFirstName(String userFirstName) {
            userList.userFirstName = userFirstName;
            return this;
        }

        public UserList build() {
            return userList;
        }
    }
}
