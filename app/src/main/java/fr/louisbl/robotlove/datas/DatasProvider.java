package fr.louisbl.robotlove.datas;

import com.squareup.otto.Subscribe;

import fr.louisbl.robotlove.user.UserLoginEvent;
import hugo.weaving.DebugLog;

/**
 * Created by louis on 13/10/15.
 */
public class DatasProvider {

    private static final DatasProvider DATAS = new DatasProvider();

    public DatasProvider getInstance() {
        return DATAS;
    }

    private DatasProvider() {
    }

    @DebugLog
    @Subscribe
    public void onUserLogin(UserLoginEvent event) {
    }
}
