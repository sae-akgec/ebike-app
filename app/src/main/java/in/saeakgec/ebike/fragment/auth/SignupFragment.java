package in.saeakgec.ebike.fragment.auth;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import in.saeakgec.ebike.R;
import in.saeakgec.ebike.listener.SignupFragmentListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {

    private SignupFragmentListener signupFragmentListener;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    public void setSignupFragmentListener(SignupFragmentListener signupFragmentListener) {
        this.signupFragmentListener = signupFragmentListener;
    }
}
