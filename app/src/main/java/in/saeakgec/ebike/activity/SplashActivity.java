package in.saeakgec.ebike.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.saeakgec.ebike.R;
import in.saeakgec.ebike.data.network.ApiClient;
import in.saeakgec.ebike.data.network.ApiService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    @BindView(R.id.splash_layout)
    ConstraintLayout constraintLayout;

    @BindView(R.id.splash_progressBar)
    ProgressBar progressBar;

    private ApiService apiService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        apiService = ApiClient.getClient(getApplicationContext()).create(ApiService.class);
        checkAuth();
    }

    @SuppressLint("CheckResult")
    public void checkAuth() {
        apiService.checkAuth()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<Object>>() {
                    @Override
                    public void onSuccess(Response<Object> response) {
                        if (response.code() == 200) {
                            navigate(true);

                        } else {
                            navigate(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        showSnackBar();
                    }
                });
    }

    private void navigate(boolean b) {
        Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
        Intent authIntent = new Intent(SplashActivity.this, AuthActivity.class);
        if (b) {
            startActivity(mainIntent);
            finish();
        } else {
            startActivity(authIntent);
            finish();
        }
    }

    public void showSnackBar() {
        progress(false);
        final Snackbar snackbar = Snackbar.make(constraintLayout, "No internet connection!", Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("RETRY", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                snackbar.dismiss();
                progress(true);
                checkAuth();
            }
        });
        snackbar.setActionTextColor(Color.WHITE);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }

    private void progress(boolean b) {
        if (b) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
