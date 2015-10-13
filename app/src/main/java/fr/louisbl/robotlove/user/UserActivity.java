package fr.louisbl.robotlove.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import fr.louisbl.robotlove.R;
import hugo.weaving.DebugLog;

public class UserActivity extends AppCompatActivity implements UserLoginFragment.InteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_user);


        changeFragment(R.id.mainContainer, new UserLoginFragment());
    }

    private void changeFragment(int id, Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .add(id, fragment)
                .commit();
    }

    @DebugLog
    @Override
    public void onLinkSignupClick() {
        // changeFragment(R.id.mainContainer, new UserSignupFragment());
    }
}
