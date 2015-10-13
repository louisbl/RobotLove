package fr.louisbl.robotlove;

import android.app.Application;

import com.firebase.client.Firebase;

import fr.louisbl.robotlove.datas.DatasProvider;

public class RobotLoveApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
        DatasProvider.setAndroidContext(this);
    }
}
