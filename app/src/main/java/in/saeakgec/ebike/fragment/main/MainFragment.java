package in.saeakgec.ebike.fragment.main;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.saeakgec.ebike.R;
import in.saeakgec.ebike.activity.ConnectActivity;
import in.saeakgec.ebike.activity.ShareActivity;
import in.saeakgec.ebike.data.models.CarModel;
import in.saeakgec.ebike.data.network.ApiClient;
import in.saeakgec.ebike.data.network.ApiService;
import in.saeakgec.ebike.listener.BikesAdapterListener;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, BikesAdapterListener {

    @BindView(R.id.main_bikes_swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.main_bikes_recyclerView)
    RecyclerView recyclerView;

    private BikesAdapter bikesAdapter;

    private List<CarModel> driverBikes;

    private ApiService apiService;

    public MainFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        driverBikes = new ArrayList<>();

        bikesAdapter = new BikesAdapter(driverBikes, this);
        recyclerView.setAdapter(bikesAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        apiService = ApiClient.getClient(getContext()).create(ApiService.class);

        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

                if (mSwipeRefreshLayout != null) {
                    mSwipeRefreshLayout.setRefreshing(true);
                }
                getBikes();
            }
        });

        return view;
    }

    @Override
    public void onRefresh() {
        getBikes();
    }

    @SuppressLint("CheckResult")
    public void getBikes() {
        mSwipeRefreshLayout.setRefreshing(true);
        apiService.getAllcars()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<List<CarModel>>>() {
                    @Override
                    public void onSuccess(Response<List<CarModel>> response) {
                        if (response.code() == 200) {
                            driverBikes = response.body();
                            bikesAdapter.setDriverBikes(driverBikes);
                            bikesAdapter.notifyDataSetChanged();
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


    @Override
    public void shareActivity(String id) {
        Intent intent = new Intent(getActivity(), ShareActivity.class);
        intent.putExtra("carId", id);
        startActivity(intent);
    }

    @SuppressLint("CheckResult")
    @Override
    public void turnOn(String id) {
        Toast.makeText(getActivity(), "Turning On", Toast.LENGTH_SHORT).show();
        this.apiService.turnOn(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<Object>>() {
                    @Override
                    public void onSuccess(Response<Object> response) {
                        if (response.code() == 200) {
                            showSnackBar("Car turned on");
                            getBikes();
                        } else {
                            showSnackBar("Unable to turn on car");

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        showSnackBar("No internet connection!");
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void turnOff(String id) {
        Toast.makeText(getActivity(), "Turning Off", Toast.LENGTH_SHORT).show();
        this.apiService.turnOff(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<Object>>() {
                    @Override
                    public void onSuccess(Response<Object> response) {
                        if (response.code() == 200) {
                            showSnackBar("Car turned off");
                            getBikes();
                        } else {
                            showSnackBar("Unable to turn off car");

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        showSnackBar("No internet connection!");
                    }
                });
    }

    @Override
    public void settingActvity() {


    }

    @Override
    public void geoFencing(String id, String latId, String lngId) {
        Intent intent = new Intent(getActivity(), ConnectActivity.class);
        intent.putExtra("carId", id);
        intent.putExtra("latId", latId);
        intent.putExtra("lngId", lngId);
        startActivity(intent);
    }
}
