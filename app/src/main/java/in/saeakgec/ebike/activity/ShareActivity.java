package in.saeakgec.ebike.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.saeakgec.ebike.R;
import in.saeakgec.ebike.data.models.ShareCarModel;
import in.saeakgec.ebike.data.network.ApiClient;
import in.saeakgec.ebike.data.network.ApiService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class ShareActivity extends AppCompatActivity {

    private String carId;

    private ApiService apiService;

    @BindView(R.id.share_detail)
    public EditText editText;

    @BindView(R.id.share_button_progress)
    public ProgressBar progressBar;

    @BindView(R.id.share_layout)
    public ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        carId = intent.getStringExtra("carId");
        apiService = ApiClient.getClient(this).create(ApiService.class);
    }

    @SuppressLint("CheckResult")
    @OnClick(R.id.share_button)
    public void shareButton(){
        progressBar.setVisibility(View.VISIBLE);
        String email = editText.getText().toString();
        if(email.equals("")){
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            progressBar.setVisibility(View.INVISIBLE);
        } else {
            ShareCarModel shareCarModel = new ShareCarModel();
            shareCarModel.setCarId(carId);
            shareCarModel.setEmail(email);

            apiService.share(shareCarModel)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(new DisposableSingleObserver<Response<Object>>() {
                        @Override
                        public void onSuccess(Response<Object> response) {
                            if (response.code() == 200) {
                                showSnackBar("Added new driver");
                            } else {
                                showSnackBar("Unable to add driver");

                            }
                            progressBar.setVisibility(View.INVISIBLE);
                        }

                        @Override
                        public void onError(Throwable e) {
                            showSnackBar("No internet connection!");
                            progressBar.setVisibility(View.INVISIBLE);
                        }
                    });

        }
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
