package in.saeakgec.ebike.adapters;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import in.saeakgec.ebike.R;
import in.saeakgec.ebike.data.models.DriverHistoryModel;

public class CarHistoryAdapter extends RecyclerView.Adapter<CarHistoryAdapter.MyViewHolder> {

    private List<DriverHistoryModel> driverHistory;

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

    public CarHistoryAdapter(List<DriverHistoryModel> driverHistory) {
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
        Uri uri = Uri.parse("https://i.imgur.com/8HSge50.png");
        holder.numberTextView.setText(driverBike.getDriverName());
        holder.timeTextView.setText(driverBike.getStartTime().substring(4, 15));
        holder.imageView.setImageURI(uri);


    }

    @Override
    public int getItemCount() {
        return driverHistory.size();
    }

    public void setDriverHistory(List<DriverHistoryModel> driverHistory) {
        this.driverHistory = driverHistory;
    }
}