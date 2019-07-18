package in.saeakgec.ebike.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.saeakgec.ebike.R;
import in.saeakgec.ebike.data.models.CarModel;
import in.saeakgec.ebike.data.network.ApiClient;
import in.saeakgec.ebike.data.network.ApiService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SettingActivity extends AppCompatActivity {

    private ApiService apiService;
    private String carId;
    private CarModel car;

    @BindView(R.id.car_main_title)
    public TextView carTitleView;

    @BindView(R.id.car_main_status)
    public TextView carStatusView;

    @BindView(R.id.car_main_speed)
    public TextView carSpeedView;

    @BindView(R.id.car_main_layout)
    public LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        carId = intent.getStringExtra("carId");
        apiService = ApiClient.getClient(this).create(ApiService.class);

        fetchCar();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    private void fetchCar() {
        apiService.getCarById(carId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<CarModel>>() {
                    @Override
                    public void onSuccess(Response<CarModel> response) {
                        if (response.code() != 200) {
                            showSnackBar("Unable to fetch Car");
                            return;
                        }
                        car = response.body();
                        setCar();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showSnackBar("No internet connection!");
                    }
                });
    }

    @SuppressLint("SetTextI18n")
    private void setCar() {
        carTitleView.setText(car.getCarNumber());
        carSpeedView.setText(car.getSpeed());

        if (car.getCarStatus().isDanger())
            carStatusView.setText("Danger");
        else
            carStatusView.setText("Ok");
    }

    @OnClick(R.id.car_main_history)
    public void history(){
        Intent intent = new Intent(this, HistoryActivity.class);
        intent.putExtra("carId", carId);
        startActivity(intent);
    }

    @OnClick(R.id.car_main_revoke)
    public void revoke(){

    }

    @OnClick(R.id.car_main_request)
    public void request(){
        Intent intent = new Intent(this, RequestActivity.class);
        intent.putExtra("carId", carId);
        startActivity(intent);
    }

    public void showSnackBar(String msg) {
        final Snackbar snackbar = Snackbar.make(linearLayout, msg, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("CLOSE", view -> snackbar.dismiss());
        snackbar.setActionTextColor(Color.WHITE);
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }


}
