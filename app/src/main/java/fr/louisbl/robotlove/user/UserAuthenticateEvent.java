package fr.louisbl.robotlove.user;

import com.firebase.client.FirebaseError;

/**
 * Created by louis on 13/10/15.
 */
public class UserAuthenticateEvent {
    private final boolean mHasError;
    private final int mCode;
    private final String mDetails;
    private final String mMessage;

    public UserAuthenticateEvent() {
        mHasError = false;
        mCode = 0;
        mDetails = null;
        mMessage = null;
    }

    public UserAuthenticateEvent(FirebaseError firebaseError) {
        mHasError = true;
        mCode = firebaseError.getCode();
        mDetails = firebaseError.getDetails();
        mMessage = firebaseError.getMessage();
    }

    public int getCode() {
        return mCode;
    }

    public String getDetails() {
        return mDetails;
    }

    public String getMessage() {
        return mMessage;
    }

    public boolean hasError() {
        return mHasError;
    }
}
