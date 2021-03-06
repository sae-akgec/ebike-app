package in.saeakgec.ebike.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import in.saeakgec.ebike.R;
import in.saeakgec.ebike.fragment.connect.MapFragment;

public class GeoFencingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo_fencing);
        getSupportFragmentManager().beginTransaction().add(R.id.connect_container, new MapFragment()).commit();
    }

}