package eng.soft.schoolfinder.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import eng.soft.schoolfinder.R;
import eng.soft.schoolfinder.SchoolAdapter;
import eng.soft.schoolfinder.data.libs.SchoolModel;
import eng.soft.schoolfinder.obj.SchoolObj;

public class Fragment_schools extends Fragment {

    public static SchoolAdapter schAdapter;
    public static ArrayList<SchoolObj> schDetails;
    SchoolModel schModel;

    public Fragment_schools() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schools, container, false);

        schModel = new SchoolModel(getActivity());
        schDetails = schModel.getAllSchool();
        schAdapter = new SchoolAdapter(getActivity(), schDetails);
        RecyclerView recyView = (RecyclerView) v.findViewById(R.id.schoolView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyView.setLayoutManager(layoutManager);
        recyView.setAdapter(schAdapter);
        return v;
    }

}
