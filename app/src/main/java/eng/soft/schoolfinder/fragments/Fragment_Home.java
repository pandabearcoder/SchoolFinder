package eng.soft.schoolfinder.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import eng.soft.schoolfinder.R;

public class Fragment_Home extends Fragment {
    FrameLayout box_finder;
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
        box_finder = (FrameLayout) getActivity().findViewById(R.id.box_finder);
        box_k12 = (FrameLayout) getActivity().findViewById(R.id.box_k12);
        box_schools = (FrameLayout) getActivity().findViewById(R.id.box_schools);
        box_tracks = (FrameLayout) getActivity().findViewById(R.id.box_tracks);

        //box_finder.setOnClickListener({});
        return v;
    }

}
