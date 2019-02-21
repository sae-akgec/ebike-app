package in.saeakgec.ebike.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.ButterKnife;
import in.saeakgec.ebike.R;
import in.saeakgec.ebike.fragment.auth.AuthFragment;
import in.saeakgec.ebike.fragment.auth.SignInFragment;
import in.saeakgec.ebike.listener.AuthFragmentListener;

public class AuthActivity extends AppCompatActivity implements AuthFragmentListener {
    private AuthFragment authFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
        authFragment = new AuthFragment();
        authFragment.setAuthFragmentListener(this);
        getSupportFragmentManager().beginTransaction().add(R.id.auth_container, authFragment).commit();
    }

    @Override
    public void signIn() {
        getSupportFragmentManager().beginTransaction().replace(R.id.auth_container, new SignInFragment()).commit();
    }

    @Override
    public void signUp() {
        Toast.makeText(this, "Sign Up", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void google() {
        Toast.makeText(this, "Google", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void facebook() {
        Toast.makeText(this, "Facebook", Toast.LENGTH_SHORT).show();
    }
}
