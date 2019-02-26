package in.saeakgec.ebike.fragment.auth;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;
import in.saeakgec.ebike.R;
import in.saeakgec.ebike.listener.AuthFragmentListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class AuthFragment extends Fragment {

    private AuthFragmentListener authFragmentListener;
    public AuthFragment() {
        // Required empty public constructor
    }
    public void setAuthFragmentListener(AuthFragmentListener authFragmentListener) {
        this.authFragmentListener = authFragmentListener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_auth, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.auth_auth_signin)
    void signIn(){
        authFragmentListener.signIn();
    }

    @OnClick(R.id.auth_auth_phone)
    void signUp(){
        authFragmentListener.signUp();
    }

}
