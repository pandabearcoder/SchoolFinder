package eng.soft.schoolfinder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import eng.soft.schoolfinder.obj.SchoolObj;

public class SchoolAdapter extends RecyclerView.Adapter<SchoolViewHolder> {

    public static final String EXTRA_SCHOOL_ID = "ID";
    public static final String EXTRA_SCHOOL_NAME = "SCHOOLNAME";
    public static final String EXTRA_SCHOOL_ADDRESS = "ADDRESS";
    public static final String EXTRA_SCHOOL_PRINCIPAL = "PRINCIPAL";
    public static final String EXTRA_SCHOOL_Contact = "CONTACT";
    public static final String EXTRA_SCHOOL_READY = "READY";
    public static final String EXTRA_SCHOOL_TRACKS = "tracks";
    Context mContext;
    private ArrayList<SchoolObj> schoolData;
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SchoolViewHolder holder = (SchoolViewHolder) view.getTag();
            int position = holder.getLayoutPosition();

            SchoolObj schoolObj = schoolData.get(position);

            Intent intent = new Intent(mContext,Activity_School_Details.class);
            intent.putExtra(EXTRA_SCHOOL_ID,schoolObj.schoolID);
            intent.putExtra(EXTRA_SCHOOL_NAME,schoolObj.schoolName);
            intent.putExtra(EXTRA_SCHOOL_ADDRESS,schoolObj.schoolAddress);
            intent.putExtra(EXTRA_SCHOOL_PRINCIPAL,schoolObj.schoolPrincipal);
            intent.putExtra(EXTRA_SCHOOL_Contact,schoolObj.schoolContact);
            intent.putExtra(EXTRA_SCHOOL_READY,schoolObj.schoolReady);
            intent.putExtra(EXTRA_SCHOOL_TRACKS,schoolObj.schoolTracks);
            mContext.startActivity(intent);

        }
    };

    public SchoolAdapter(Context context, ArrayList<SchoolObj> schoolDataList) {
        this.schoolData = schoolDataList;
        mContext = context;
    }

    @Override
    public SchoolViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_school_item, viewGroup, false);

        SchoolViewHolder viewHolder = new SchoolViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SchoolViewHolder customViewHolder, int i) {
        SchoolObj schoolList = schoolData.get(i);

        customViewHolder.schoolName.setText(schoolList.schoolName);

        customViewHolder.schoolBoxView.setOnClickListener(clickListener);
        customViewHolder.schoolName.setOnClickListener(clickListener);

        customViewHolder.schoolBoxView.setTag(customViewHolder);
        customViewHolder.schoolName.setTag(customViewHolder);
    }

    @Override
    public int getItemCount() {
        return (null != schoolData ? schoolData.size() : 0);
    }
}
