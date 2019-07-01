package in.saeakgec.ebike.fragment.main;


import android.annotation.SuppressLint;
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

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.saeakgec.ebike.R;
import in.saeakgec.ebike.data.models.DriverBikeModel;
import in.saeakgec.ebike.data.models.ListModel;
import in.saeakgec.ebike.data.network.ApiClient;
import in.saeakgec.ebike.data.network.ApiService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.main_bikes_swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.main_bikes_recyclerView)
    RecyclerView recyclerView;

    private BikesAdapter bikesAdapter;

    private ArrayList<DriverBikeModel> driverBikes;

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

        bikesAdapter = new BikesAdapter(driverBikes);
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
        apiService.getAllbikes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Response<ListModel<DriverBikeModel>>>() {
                    @Override
                    public void onSuccess(Response<ListModel<DriverBikeModel>> response) {
                        if (response.code() == 200) {
                            driverBikes = response.body().getResults();
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


}
