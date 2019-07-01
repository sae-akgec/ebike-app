package in.saeakgec.ebike.fragment.main;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

import in.saeakgec.ebike.R;
import in.saeakgec.ebike.data.models.BikeModel;
import in.saeakgec.ebike.data.models.DriverBikeModel;

public class BikesAdapter extends RecyclerView.Adapter<BikesAdapter.MyViewHolder> {

    private ArrayList<DriverBikeModel> driverBikes;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView imageView;
        private TextView textView;
        private LinearLayout adminLayout;

        public MyViewHolder(View view) {
            super(view);
            textView = (TextView) view.findViewById(R.id.main_bikes_item_number);
            imageView = (SimpleDraweeView) view.findViewById(R.id.main_bikes_item_avatar);
            adminLayout = (LinearLayout) view.findViewById(R.id.main_bikes_item_adminLayout);
        }
    }

    public BikesAdapter(ArrayList<DriverBikeModel> driverBikes) {
        this.driverBikes = driverBikes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_bike_recyclerview_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        DriverBikeModel driverBike = driverBikes.get(i);
        BikeModel bike = driverBike.getBike();
        Uri uri = Uri.parse(bike.getImage());
        holder.textView.setText(bike.getNumber());
        holder.imageView.setImageURI(uri);

        if (bike.getAdmin() == driverBike.getUser()) {
            holder.adminLayout.setVisibility(View.VISIBLE);
        } else {
            holder.adminLayout.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return driverBikes.size();
    }

    public void setDriverBikes(ArrayList<DriverBikeModel> driverBikes) {
        this.driverBikes = driverBikes;
    }
}
