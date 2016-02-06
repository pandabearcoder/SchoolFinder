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

    private ArrayList<SchoolObj> schoolData;

    public static final String EXTRA_SCHOOL_ID = "id";
    public static final String EXTRA_SCHOOL_NAME = "schoolname";
    public static final String EXTRA_SCHOOL_Address = "schooladdress";
    public static final String EXTRA_SCHOOL_TRACKS = "tracks";

    Context mContext;

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

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SchoolViewHolder holder = (SchoolViewHolder) view.getTag();
            int position = holder.getLayoutPosition();

            SchoolObj schoolObj = schoolData.get(position);

            Intent intent = new Intent(mContext,Activity_School_Details.class);
            intent.putExtra(EXTRA_SCHOOL_ID,schoolObj.schoolID);
            intent.putExtra(EXTRA_SCHOOL_NAME,schoolObj.schoolName);
            intent.putExtra(EXTRA_SCHOOL_Address,schoolObj.schoolAddress);
            intent.putExtra(EXTRA_SCHOOL_TRACKS,schoolObj.schoolTracks);
            mContext.startActivity(intent);

        }
    };
}
