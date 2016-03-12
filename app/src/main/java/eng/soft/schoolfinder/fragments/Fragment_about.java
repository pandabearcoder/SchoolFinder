package eng.soft.schoolfinder.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import eng.soft.schoolfinder.R;

public class Fragment_about extends Fragment {

    TextView k12philippines;
    TextView k12govph;

    public Fragment_about() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_about, container, false);

        k12philippines = (TextView) v.findViewById(R.id.k12ph);
        k12govph = (TextView) v.findViewById(R.id.k12gov);

        k12philippines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(k12philippines.getText().toString()));
                startActivity(browserIntent);
            }
        });

        k12govph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(k12govph.getText().toString()));
                startActivity(browserIntent);
            }
        });

        return v;
    }

}
