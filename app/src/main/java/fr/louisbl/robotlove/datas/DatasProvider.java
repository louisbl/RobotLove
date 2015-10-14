package fr.louisbl.robotlove.datas;

import android.content.Context;

import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.squareup.otto.Subscribe;

import fr.louisbl.robotlove.BuildConfig;
import fr.louisbl.robotlove.user.User;
import fr.louisbl.robotlove.user.UserAuthenticateEvent;
import fr.louisbl.robotlove.user.UserLoginEvent;
import fr.louisbl.robotlove.utils.BusProvider;
import hugo.weaving.DebugLog;

public class DatasProvider implements Firebase.AuthResultHandler {
    private static final DatasProvider DATAS = new DatasProvider();
    private static final String FIREBASE_URL = BuildConfig.FIREBASE_URL;
    private Context mContext;

    private final Firebase mFirebase;

    public static DatasProvider getInstance() {
        return DATAS;
    }

    @DebugLog
    private DatasProvider() {
        mFirebase = new Firebase(FIREBASE_URL);
        BusProvider.getInstance().register(this);
    }

    public static void setAndroidContext(Context context) {
        DATAS.mContext = context;
    }

    @DebugLog
    @Subscribe
    public void onUserLogin(UserLoginEvent event) {
        mFirebase.authWithPassword(event.getEmail(), event.getPassword(), this);
    }

    @Override
    public void onAuthenticated(AuthData authData) {
        BusProvider.getInstance().post(new UserAuthenticateEvent());
        getUserDatas(authData.getUid());
    }

    @DebugLog
    @Override
    public void onAuthenticationError(FirebaseError firebaseError) {
        BusProvider.getInstance().post(new UserAuthenticateEvent(firebaseError));
    }

    private void getUserDatas(String uid) {
        Firebase userRef = mFirebase.child("users").child(uid);
        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               User user = dataSnapshot.getValue(User.class);
               BusProvider.getInstance().post(new UserDataEvent(user));
            }

            @DebugLog
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
}
