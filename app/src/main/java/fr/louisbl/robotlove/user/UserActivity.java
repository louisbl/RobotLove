package fr.louisbl.robotlove.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.squareup.otto.Subscribe;

import fr.louisbl.robotlove.R;
import fr.louisbl.robotlove.utils.BusProvider;
import hugo.weaving.DebugLog;

public class UserActivity extends AppCompatActivity implements UserLoginFragment.InteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user);

        changeFragment(R.id.mainContainer, new UserLoginFragment());
    }

    @Override
    protected void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        BusProvider.getInstance().unregister(this);
        super.onPause();
    }

    private void changeFragment(int id, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(id, fragment)
                .commit();
    }

    @DebugLog
    @Subscribe
    public void onUserAuthenticate(UserAuthenticateEvent event) {
        // TODO: go to home activity
    }

    @DebugLog
    @Override
    public void onLinkSignupClick() {
        // changeFragment(R.id.mainContainer, new UserSignupFragment());
    }
}
