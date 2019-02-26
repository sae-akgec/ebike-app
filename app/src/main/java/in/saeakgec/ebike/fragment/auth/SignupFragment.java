package in.saeakgec.ebike.fragment.auth;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.saeakgec.ebike.R;
import in.saeakgec.ebike.data.models.RegisterModel;
import in.saeakgec.ebike.data.models.SignInModel;
import in.saeakgec.ebike.data.models.TokenModel;
import in.saeakgec.ebike.data.network.ApiClient;
import in.saeakgec.ebike.data.network.ApiService;
import in.saeakgec.ebike.data.utils.PrefUtils;
import in.saeakgec.ebike.listener.SignupFragmentListener;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {

    private ApiService apiService;
    private RegisterModel registerModel;

    @BindView(R.id.auth_signup_layout)
    ConstraintLayout constraintLayout;

    @BindView(R.id.auth_signup_email)
    EditText emailEditText;

    @BindView(R.id.auth_signup_first_name)
    EditText firstNameEditText;

    @BindView(R.id.auth_signup_last_name)
    EditText lastNameEditText;

    @BindView(R.id.auth_signup_password)
    EditText passwordEditText;

    @BindView(R.id.auth_signup_confirm_password)
    EditText confirmPasswordEditText;

    private SignupFragmentListener signupFragmentListener;

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_signup, container, false);
        ButterKnife.bind(this, view);
        registerModel = new RegisterModel();
        apiService = ApiClient.getClient(getContext()).create(ApiService.class);
        return view;
    }

    public void setSignupFragmentListener(SignupFragmentListener signupFragmentListener) {
        this.signupFragmentListener = signupFragmentListener;
    }

    @OnClick(R.id.auth_sign_in_button)
    void  signUp(){
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String firstName = firstNameEditText.getText().toString();

        registerModel.setEmail(email);
        registerModel.setPassword(password);
        registerModel.setConfirmPassword(confirmPassword);
        registerModel.setLastName(lastName);
        registerModel.setFirstName(firstName);

        if(email.equals("") || password.equals("") || firstName.equals("") || confirmPassword.equals(""))
        {
            showSnackBar("Please fill information Correctly");
        }else{
            register();
        }
    }

    @SuppressLint("CheckResult")
    public void register(){
        apiService.signUp(registerModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<Object>>() {
                    @Override
                    public void onSuccess(Response<Object> response) {
                        if (response.code() == 200) {
                            showSnackBar("Successfully Registered! Please confirm your email");
                        }
                        else {
                            showSnackBar("Unable Register! Please fill your information correctly");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        showSnackBar("No internet connection!");
                    }
                });

    }


    @OnClick(R.id.auth_sign_in_back)
    void onBackPressed() {
        signupFragmentListener.backSignup();
    }

    public void showSnackBar(String msg) {
        final Snackbar snackbar = Snackbar.make(constraintLayout , msg, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("CLOSE", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
            }
        });
        snackbar.setActionTextColor(Color.WHITE);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }
}
