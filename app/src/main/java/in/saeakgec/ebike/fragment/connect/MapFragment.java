package in.saeakgec.ebike.fragment.connect;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.saeakgec.ebike.R;
import in.saeakgec.ebike.activity.ConnectActivity;
import in.saeakgec.ebike.services.Util;

public class MapFragment extends Fragment implements OnMapReadyCallback, LocationListener {

    GoogleMap googlemap;
    private LatLng myLocation;

    @BindView(R.id.tv_Address)
    TextView tv_Address;

    private Address address;
    public static String strAddress = null, s_address;
    private LocationManager locationManager;
    private static final long MIN_TIME = 400;
    private static final float MIN_DISTANCE = 1000;
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 35;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        ButterKnife.bind(this, view);

        if (Util.isPermissionRequired(getActivity())) {
            Util.requestPermission(getActivity(), REQUEST_PERMISSIONS_REQUEST_CODE);
        } else {
            initMap();
        }
        return view;
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this); //You can also use LocationManager.GPS_PROVIDER and LocationManager.PASSIVE_PROVIDER
        } else {
            Util.requestPermission(getActivity(), REQUEST_PERMISSIONS_REQUEST_CODE);
        }


    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        if (googleMap != null) {


            this.googlemap = googleMap;
            if (ContextCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                googleMap.setMyLocationEnabled(true);
            } else {
                // Show rationale and request permission.
            }

            googleMap.getUiSettings().setMyLocationButtonEnabled(true);
            Log.v("onActivityResult", " nullllllllll--" + myLocation);

            googleMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
                @Override
                public void onCameraIdle() {


                    getAddressGEOCODE(googleMap.getCameraPosition().target);

                }
            });

        }


    }

    public void getAddressGEOCODE(final LatLng currenLatLng) {
        new Thread(new Runnable() {
            @Override
            public void run() {

                Geocoder gCoder = new Geocoder(getContext());
                try {
                    final List<Address> list = gCoder.getFromLocation(
                            currenLatLng.latitude, currenLatLng.longitude, 1);
                    if (list != null && list.size() > 0) {
                        address = list.get(0);
                        StringBuilder sb = new StringBuilder();
                        if (address.getAddressLine(0) != null) {
                            if (address.getMaxAddressLineIndex() > 0) {
                                for (int i = 0; i < address
                                        .getMaxAddressLineIndex(); i++) {
                                    sb.append(address.getAddressLine(i))
                                            .append("\n");
                                }
                                sb.append(",");
                                sb.append(address.getCountryName());
                            } else {
                                sb.append(address.getAddressLine(0));
                            }
                        }

                        strAddress = sb.toString();
                        strAddress = strAddress.replace(",null", "");
                        strAddress = strAddress.replace("null", "");
                        strAddress = strAddress.replace("Unnamed", "");


                    }
                    Log.v("location_add", "strAddress:" + strAddress);

                    if (list.size() < 1) {


                    } else {


                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!TextUtils.isEmpty(strAddress)) {
                                    tv_Address.setFocusable(false);
                                    tv_Address.setFocusableInTouchMode(false);
                                    tv_Address.setText(strAddress);

                                    tv_Address.setText(strAddress);

                                    myLocation = currenLatLng;

                                    s_address = strAddress;


                                    SharedPreferences pre = getActivity().getSharedPreferences(
                                            "source_destination",
                                            Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = pre
                                            .edit();
                                    editor.putString("event_address",
                                            strAddress);
                                    editor.putString("latitude_source",
                                            String.valueOf(currenLatLng.latitude));
                                    editor.putString("logtitude_source",
                                            String.valueOf(currenLatLng.longitude));
                                    editor.commit();

                                } else {

                                }

                            }
                        });

                    }


                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location != null & googlemap != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
            googlemap.animateCamera(cameraUpdate);
            locationManager.removeUpdates(this);
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (!Util.isPermissionRequired(getActivity())) {
                initMap();
            }
        }

    }

}
