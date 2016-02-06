package eng.soft.schoolfinder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class Activity_School_Details extends AppCompatActivity {

    int SchoolID;
    String SchoolName;
    String SchoolAddress;
    String SchoolTracks;

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
        SchoolAddress = "Address: " + intent.getStringExtra(SchoolAdapter.EXTRA_SCHOOL_Address);
        SchoolTracks = "Tracks offered: " + intent.getStringExtra(SchoolAdapter.EXTRA_SCHOOL_TRACKS);

        TextView txtSchoolName = (TextView) findViewById(R.id.txtSchoolName);
        TextView txtSchoolAddress = (TextView) findViewById(R.id.txtSchoolAddress);
        TextView txtSchoolTracks = (TextView) findViewById(R.id.txtSchoolTracks);

        txtSchoolName.setText(SchoolName);
        txtSchoolAddress.setText(SchoolAddress);
        txtSchoolTracks.setText(SchoolTracks);


    }

}
