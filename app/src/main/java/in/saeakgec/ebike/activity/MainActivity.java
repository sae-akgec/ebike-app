package in.saeakgec.ebike.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import in.saeakgec.ebike.R;
import in.saeakgec.ebike.fragment.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().add(R.id.main_container, new MainFragment()).commit();
    }
}
