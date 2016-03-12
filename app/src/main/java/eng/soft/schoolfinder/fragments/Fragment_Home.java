package eng.soft.schoolfinder.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import eng.soft.schoolfinder.Activity_main;
import eng.soft.schoolfinder.R;

public class Fragment_Home extends Fragment {
    Fragment_k12 k12_display;
    Fragment_schools schools_display;
    Fragment_tracks tracks_display;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;
    FrameLayout box_k12;
    FrameLayout box_schools;
    FrameLayout box_tracks;

    public Fragment_Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        k12_display = new Fragment_k12();
        schools_display = new Fragment_schools();
        tracks_display = new Fragment_tracks();
        mFragmentManager = getFragmentManager();

        box_k12 = (FrameLayout) v.findViewById(R.id.box_k12);
        box_schools = (FrameLayout) v.findViewById(R.id.box_schools);
        box_tracks = (FrameLayout) v.findViewById(R.id.box_tracks);

        box_k12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.frag_container, k12_display);
                mFragmentTransaction.commit();
                Activity_main.toolbar.setTitle("The K to 12");
            }
        });

        box_schools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.frag_container, schools_display);
                mFragmentTransaction.commit();
                Activity_main.toolbar.setTitle("Schools");
                Activity_main.menuMode = "school";
                getActivity().invalidateOptionsMenu();
            }
        });

        box_tracks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.frag_container, tracks_display);
                mFragmentTransaction.commit();
                Activity_main.toolbar.setTitle("K-12 Tracks");
            }
        });
        return v;
    }

}
