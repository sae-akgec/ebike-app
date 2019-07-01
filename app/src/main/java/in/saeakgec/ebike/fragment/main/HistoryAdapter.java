package in.saeakgec.ebike.fragment.main;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import in.saeakgec.ebike.R;
import in.saeakgec.ebike.data.models.BikeModel;
import in.saeakgec.ebike.data.models.DriverHistoryModel;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

    private ArrayList<DriverHistoryModel> driverHistory;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView imageView;
        private TextView numberTextView, timeTextView;

        public MyViewHolder(View view) {
            super(view);
            numberTextView = (TextView) view.findViewById(R.id.main_history_item_name);
            imageView = (SimpleDraweeView) view.findViewById(R.id.main_history_item_avatar);
            timeTextView = (TextView) view.findViewById(R.id.main_history_item_time);
        }
    }

    public HistoryAdapter(ArrayList<DriverHistoryModel> driverHistory) {
        this.driverHistory = driverHistory;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_history_recyclerview_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        DriverHistoryModel driverBike = driverHistory.get(i);
        BikeModel bike = driverBike.getBike();
        Uri uri = Uri.parse(bike.getImage());
        holder.numberTextView.setText(bike.getNumber());
        holder.imageView.setImageURI(uri);


    }

    @Override
    public int getItemCount() {
        return driverHistory.size();
    }

    public void setDriverHistory(ArrayList<DriverHistoryModel> driverHistory) {
        this.driverHistory = driverHistory;
    }
}