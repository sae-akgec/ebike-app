package in.saeakgec.ebike.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.saeakgec.ebike.R;
import in.saeakgec.ebike.adapters.CarHistoryAdapter;
import in.saeakgec.ebike.data.models.DriverHistoryModel;
import in.saeakgec.ebike.data.network.ApiClient;
import in.saeakgec.ebike.data.network.ApiService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.history_swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.history_recyclerView)
    RecyclerView recyclerView;

    private CarHistoryAdapter carHistoryAdapter;

    private List<DriverHistoryModel> driverHistory;

    private ApiService apiService;

    private String carId;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);

        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Intent intent = getIntent();
        carId = intent.getStringExtra("carId");


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        driverHistory = new ArrayList<>();

        carHistoryAdapter = new CarHistoryAdapter(driverHistory);
        recyclerView.setAdapter(carHistoryAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        apiService = ApiClient.getClient(this).create(ApiService.class);

        mSwipeRefreshLayout.post(() -> {

            if (mSwipeRefreshLayout != null) {
                mSwipeRefreshLayout.setRefreshing(true);
            }
            getHistory();
        });
    }

    @Override
    public void onRefresh() {
        getHistory();
    }

    @SuppressLint("CheckResult")
    public void getHistory() {
        mSwipeRefreshLayout.setRefreshing(true);
        apiService.getCarHistory(carId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<DriverHistoryModel>>>() {
                    @Override
                    public void onSuccess(Response<List<DriverHistoryModel>> response) {
                        if (response.code() == 200) {
                            driverHistory = response.body();
                            carHistoryAdapter.setDriverHistory(driverHistory);
                            carHistoryAdapter.notifyDataSetChanged();
                            mSwipeRefreshLayout.setRefreshing(false);
                        } else {
                            showSnackBar("Unable to get data");
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        showSnackBar("No internet connection!");
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                });
    }

    public void showSnackBar(String msg) {
        final Snackbar snackbar = Snackbar.make(mSwipeRefreshLayout, msg, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("CLOSE", view -> snackbar.dismiss());
        snackbar.setActionTextColor(Color.WHITE);
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.YELLOW);
        snackbar.show();
    }

}
