package in.saeakgec.ebike.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import butterknife.ButterKnife;
import in.saeakgec.ebike.R;
import in.saeakgec.ebike.data.network.ApiClient;
import in.saeakgec.ebike.data.network.ApiService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {

    private ApiService apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        apiService = ApiClient.getClient(getApplicationContext()).create(ApiService.class);
    }

    public void checkAuth(){
        apiService.checkAuth()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Object>() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(SplashActivity.this, "Already Logged in", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(SplashActivity.this, "Error Login again", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
