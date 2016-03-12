package eng.soft.schoolfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class Activity_mapview extends AppCompatActivity implements OnMapReadyCallback {

    SupportMapFragment mapFragment;
    PolylineOptions rectOptions;
    Double lat;
    Double lng;
    Intent intent;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        intent = getIntent();

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_gmap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        lat = intent.getDoubleExtra(Activity_School_Details.EXTRA_LAT, 0.0);
        lng = intent.getDoubleExtra(Activity_School_Details.EXTRA_LNG, 0.0);
        LatLng point = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(point));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 15));

        rectOptions = new PolylineOptions();
        rectOptions.add(
                new LatLng(14.349250, 121.008370),
                new LatLng(14.318647, 121.007340),
                new LatLng(14.326298, 121.022446),
                new LatLng(14.338523, 121.036394),
                new LatLng(14.334365, 121.034677),
                new LatLng(14.332369, 121.040943),
                new LatLng(14.341828, 121.061714),
                new LatLng(14.341887, 121.061714),
                new LatLng(14.341912, 121.064289),
                new LatLng(14.345921, 121.065834),
                new LatLng(14.345030, 121.065662),
                new LatLng(14.355341, 121.076262),
                new LatLng(14.370973, 121.070812),
                new LatLng(14.379869, 121.057186),
                new LatLng(14.366067, 121.029420),
                new LatLng(14.349250, 121.008370)).color(R.color.colorPrimary).width(2);
        mMap.addPolyline(rectOptions);
    }

}
