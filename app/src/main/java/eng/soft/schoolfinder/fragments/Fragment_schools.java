package eng.soft.schoolfinder.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import eng.soft.schoolfinder.R;
import eng.soft.schoolfinder.SchoolAdapter;
import eng.soft.schoolfinder.obj.InitSchools;
import eng.soft.schoolfinder.obj.SchoolObj;

public class Fragment_schools extends Fragment {

    ArrayList<SchoolObj> school_details;
    InitSchools initSchools;
    SchoolAdapter adapter;

    public Fragment_schools() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_schools, container, false);
        initSchools = new InitSchools();
        school_details = initSchools.initItems();
        adapter = new SchoolAdapter(getActivity(), school_details);
        RecyclerView recyView = (RecyclerView) v.findViewById(R.id.schoolView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyView.setLayoutManager(layoutManager);
        recyView.setAdapter(adapter);
        return v;
    }

}
