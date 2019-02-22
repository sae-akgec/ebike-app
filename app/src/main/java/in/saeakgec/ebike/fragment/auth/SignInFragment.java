package in.saeakgec.ebike.fragment.auth;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.saeakgec.ebike.R;
import in.saeakgec.ebike.activity.AuthActivity;
import in.saeakgec.ebike.activity.MainActivity;
import in.saeakgec.ebike.data.network.ApiClient;
import in.saeakgec.ebike.data.network.ApiService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SignInFragment extends Fragment {
    private ApiService apiService;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_sign_in, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.auth_sign_in_submit)
       void  clicked(){
        checkAuth2();
    }


    @OnClick(R.id.auth_sign_in_back)
    void onBackPressed() {
        Intent back_sign= new Intent(getActivity(), AuthActivity.class);
        startActivity(back_sign);
    }
    @OnClick(R.id.auth_sign_in_submit)
    public void submitPressed(){
        Intent submit_sign= new Intent(getActivity(), MainActivity.class);
        startActivity(submit_sign);
    }
    @OnClick(R.id.auth_forgot_password)
    void forgetPressed()
    {
        Intent forgetpass= new Intent(getActivity(), MainActivity.class);
        startActivity(forgetpass);
    }
    public void checkAuth2(){
        apiService.checkAuth2()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<Object>>() {
                    @Override
                    public void onSuccess(Response<Object> response) {
                        if (response.code() == 200) {
                            Intent submit_sign = new Intent(getActivity(), MainActivity.class);
                            startActivity(submit_sign);
                        }
                        else {
                            Toast.makeText(getActivity(), "PAssword Entered is Incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(), "PAssword Entered is Incorrect", Toast.LENGTH_SHORT).show();
                    }
                });


            }
}
