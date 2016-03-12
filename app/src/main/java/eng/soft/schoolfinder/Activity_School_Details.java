package eng.soft.schoolfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Activity_School_Details extends AppCompatActivity {

    public static final String EXTRA_LAT = "lat";
    public static final String EXTRA_LNG = "lng";

    int SchoolID;
    String SchoolName;
    String SchoolAddress;
    String SchoolTracks;

    Double lat;
    Double lng;

    RequestQueue requestQueue;

    TextView txtSchoolName;
    TextView txtSchoolAddress;
    TextView txtSchoolTracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_school);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        SchoolID = intent.getIntExtra(SchoolAdapter.EXTRA_SCHOOL_ID, 1);
        SchoolName = intent.getStringExtra(SchoolAdapter.EXTRA_SCHOOL_NAME);
        SchoolAddress = intent.getStringExtra(SchoolAdapter.EXTRA_SCHOOL_Address);
        SchoolTracks = "Tracks offered: " + intent.getStringExtra(SchoolAdapter.EXTRA_SCHOOL_TRACKS);

        txtSchoolName = (TextView) findViewById(R.id.txtSchoolName);
        txtSchoolAddress = (TextView) findViewById(R.id.txtSchoolAddress);
        txtSchoolTracks = (TextView) findViewById(R.id.txtSchoolTracks);

        txtSchoolName.setText(SchoolName);
        txtSchoolAddress.setText(SchoolAddress);
        txtSchoolTracks.setText(SchoolTracks);

        requestQueue = Volley.newRequestQueue(this);
    }

    public void checkMap(View view) {
        String gmapapi_URL = "https://maps.googleapis.com/maps/api/geocode/json?address=";
        //lat = response.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getString("lat");
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, gmapapi_URL + txtSchoolAddress.getText().toString().replace(" ","%20"), null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            lat = response.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lat");
                            lng = response.getJSONArray("results").getJSONObject(0).getJSONObject("geometry").getJSONObject("location").getDouble("lng");

                            Intent intent = new Intent(getApplicationContext(),Activity_mapview.class);
                            intent.putExtra(EXTRA_LAT,lat);
                            intent.putExtra(EXTRA_LNG,lng);

                            finish();
                            startActivity(intent);
                        } catch(JSONException e){e.printStackTrace();}
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        requestQueue.add(jsObjRequest);
    }
}
