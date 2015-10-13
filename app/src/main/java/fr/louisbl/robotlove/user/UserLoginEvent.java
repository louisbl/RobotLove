package fr.louisbl.robotlove.user;

/**
 * Created by louis on 13/10/15.
 */
public class UserLoginEvent {
    private final String mEmail;
    private final String mPassword;

    public UserLoginEvent(String email, String password) {
        mEmail = email;
        mPassword = password;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPassword() {
        return mPassword;
    }
}
