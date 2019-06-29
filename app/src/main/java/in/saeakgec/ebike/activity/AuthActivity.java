package in.saeakgec.ebike.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.ButterKnife;
import in.saeakgec.ebike.R;
import in.saeakgec.ebike.fragment.auth.AuthFragment;
import in.saeakgec.ebike.fragment.auth.FacebookFragment;
import in.saeakgec.ebike.fragment.auth.GoogleFragment;
import in.saeakgec.ebike.fragment.auth.SignInFragment;
import in.saeakgec.ebike.fragment.auth.SignupFragment;
import in.saeakgec.ebike.listener.AuthFragmentListener;
import in.saeakgec.ebike.listener.FacebookFragmentListener;
import in.saeakgec.ebike.listener.GoogleFragmentListener;
import in.saeakgec.ebike.listener.SignInFragmentListener;
import in.saeakgec.ebike.listener.SignupFragmentListener;

public class AuthActivity extends AppCompatActivity implements AuthFragmentListener, SignInFragmentListener, SignupFragmentListener, GoogleFragmentListener, FacebookFragmentListener {
    private AuthFragment authFragment;
    private SignInFragment signInFragment;
    private SignupFragment signupFragment;
    private GoogleFragment googleFragment;
    private FacebookFragment facebookFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
        authFragment = new AuthFragment();
        signInFragment = new SignInFragment();
        signupFragment = new SignupFragment();
        googleFragment = new GoogleFragment();
        facebookFragment=new FacebookFragment();
        signInFragment.setSignInFragmentListener(this);
        signupFragment.setSignupFragmentListener(this);
        googleFragment.setGoogleFragmentListener(this);
        facebookFragment.setFacebookFragmentListener(this);
        authFragment.setAuthFragmentListener(this);
        getSupportFragmentManager().beginTransaction().add(R.id.auth_container, authFragment).commit();
    }

    @Override
    public void signIn() {
        getSupportFragmentManager().beginTransaction().replace(R.id.auth_container, signInFragment).commit();
    }

    @Override
    public void signUp() {
        getSupportFragmentManager().beginTransaction().replace(R.id.auth_container, signupFragment).commit();
    }
    public void google(){
        getSupportFragmentManager().beginTransaction().replace(R.id.auth_container, googleFragment).commit();
    }
    public void facebook(){
        getSupportFragmentManager().beginTransaction().replace(R.id.auth_container, facebookFragment).commit();
    }
    @Override
    public void backSignIn() {
        getSupportFragmentManager().beginTransaction().replace(R.id.auth_container, authFragment).commit();
    }

    @Override
    public void backSignup() {
        getSupportFragmentManager().beginTransaction().replace(R.id.auth_container, authFragment).commit();
    }

    @Override
    public void mainActivity() {
        Intent mainIntent = new Intent(AuthActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }

    @Override
    public void resetPassword() {

    }
}
