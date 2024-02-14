package xyz.xyz.xyz.workdarnit.account;

import java.security.InvalidParameterException;

public class LoginException extends InvalidParameterException {
    // I'm well aware this might be an anti-pattern
    final boolean nameFail, pwdFail, unspecifiedFail;
    public LoginException(boolean nameFail, boolean pwdFail) {
        super(nameFail ?
                "name fail..." :
                (pwdFail ? "password fail..." : "unspecified fail..."));
        this.nameFail = nameFail;
        this.pwdFail = pwdFail;
        this.unspecifiedFail = !(nameFail || pwdFail);
    }

}
