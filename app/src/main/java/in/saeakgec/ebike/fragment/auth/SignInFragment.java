package in.saeakgec.ebike.fragment.auth;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import in.saeakgec.ebike.data.models.SignInModel;
import in.saeakgec.ebike.data.models.TokenModel;
import in.saeakgec.ebike.data.network.ApiClient;
import in.saeakgec.ebike.data.network.ApiService;
import in.saeakgec.ebike.data.utils.PrefUtils;
import in.saeakgec.ebike.listener.SignInFragmentListener;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;


public class SignInFragment extends Fragment {
    private SignInFragmentListener signInFragmentListener;
    private ApiService apiService;
    private SignInModel signInModel;

    @BindView(R.id.auth_sign_in_login_id)
    EditText emailEditText;

    @BindView(R.id.auth_sign_in_password)
    EditText passwordEditText;

    @BindView(R.id.auth_sign_in_layout)
    ConstraintLayout constraintLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        ButterKnife.bind(this, view);
        signInModel = new SignInModel();
        apiService = ApiClient.getClient(getContext()).create(ApiService.class);
        return view;
    }

    @OnClick(R.id.auth_sign_in_button)
    void signIn() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        signInModel.setEmail(email);
        signInModel.setPassword(password);
        if (email.equals("") || password.equals("")) {
            showSnackBar("Please fill information Correctly");
        } else {
            checkAuth();
        }
    }


    @OnClick(R.id.auth_sign_in_back)
    void onBackPressed() {
        signInFragmentListener.backSignIn();
    }

//    @OnClick(R.id.auth_forgot_password)
//    void forgetPressed()
//    {
//        Intent forgetpass= new Intent(getActivity(), MainActivity.class);
//        startActivity(forgetpass);
//    }

    @SuppressLint("CheckResult")
    public void checkAuth() {
        apiService.signIn(signInModel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<TokenModel>>() {
                    @Override
                    public void onSuccess(Response<TokenModel> response) {
                        if (response.code() == 200) {
                            PrefUtils.storeToken(getContext(), response.body().getToken());
                            signInFragmentListener.mainActivity();
                        } else {
                            showSnackBar("Unable to log in with provided credentials");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        showSnackBar("No internet connection!");
                    }
                });

    }

    public void setSignInFragmentListener(SignInFragmentListener signInFragmentListener) {
        this.signInFragmentListener = signInFragmentListener;
    }

    public void showSnackBar(String msg) {
        final Snackbar snackbar = Snackbar.make(constraintLayout, msg, Snackbar.LENGTH_INDEFINITE);
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
