package com.tripoin.dto.app;

/**
 * Created on 10/7/2015 : 11:30 AM.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */

public class DTOUserLogin {

    String userName;
    String userCode;

    public DTOUserLogin() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Override
    public String toString() {
        return "UserLogin{" +
                "userCode='" + userName + '\'' +
                ", userCode='" + userCode + '\'' +
                '}';
    }
}
