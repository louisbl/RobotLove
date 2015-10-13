package fr.louisbl.robotlove.user;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.squareup.otto.Subscribe;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fr.louisbl.robotlove.R;
import fr.louisbl.robotlove.utils.BusProvider;
import hugo.weaving.DebugLog;

/**
 * A placeholder fragment containing a simple view.
 */
public class UserLoginFragment extends Fragment {

    @Bind(R.id.loginBtn)
    Button mLoginBtn;

    @Bind(R.id.loginInputEmail)
    EditText mInputEmail;

    @Bind(R.id.loginInputPassword)
    EditText mInputPassword;

    private InteractionListener mListener;
    private ProgressDialog mProgressDialog;

    public UserLoginFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mListener = (InteractionListener) context;
        } catch (ClassCastException exception) {
            throw new ClassCastException(context.toString()
                    + " must implements UserLoginFragment.InteractionListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_login, container, false);
        ButterKnife.bind(this, view);
        BusProvider.getInstance().register(this);

        return view;
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        BusProvider.getInstance().unregister(this);

        super.onDestroyView();
    }

    @OnClick(R.id.loginLinkSignup)
    void onLinkSignupClick(View view) {
        mListener.onLinkSignupClick();
    }

    @OnClick(R.id.loginBtn)
    void onLoginButtonClick(View view) {
        if (!validate()) {
            return;
        }

        showProgress();

        String email = mInputEmail.getText().toString();
        String password = mInputPassword.getText().toString();

        BusProvider.getInstance().post(new UserLoginEvent(email, password));
    }

    @Subscribe
    public void onUserAuthenticate(UserAuthenticateEvent event) {
        dismissProgress();

        if (event.hasError()) {
            Snackbar.make(getView(), event.getMessage(), Snackbar.LENGTH_LONG)
                    .show();
        }
    }

    void showProgress() {
        mLoginBtn.setEnabled(false);
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(getResources().getString(R.string.login_dialog_authenticate));
        mProgressDialog.show();
    }

    void dismissProgress() {
        mProgressDialog.dismiss();
        mLoginBtn.setEnabled(true);
    }

    @DebugLog
    boolean validate() {
        boolean valid = true;

        String email = mInputEmail.getText().toString();
        String password = mInputPassword.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mInputEmail.setError(getResources().getString(R.string.login_email_error));
            valid = false;
        } else {
            mInputEmail.setError(null);
        }

        if (password.isEmpty()) {
            mInputPassword.setError(getResources().getString(R.string.login_password_error));
            valid = false;
        } else {
            mInputPassword.setError(null);
        }

        return valid;
    }

    public interface InteractionListener {
        void onLinkSignupClick();
    }
}
