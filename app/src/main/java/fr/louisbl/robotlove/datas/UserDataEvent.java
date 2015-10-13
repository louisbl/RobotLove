package fr.louisbl.robotlove.datas;

import fr.louisbl.robotlove.user.User;

/**
 * Created by louis on 13/10/15.
 */
public class UserDataEvent {
    private final User mUser;

    public UserDataEvent(User user) {
        mUser = user;
    }

    public User getmUser() {
        return mUser;
    }
}
