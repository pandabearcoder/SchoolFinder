package eng.soft.schoolfinder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SchoolViewHolder extends RecyclerView.ViewHolder {

    protected LinearLayout schoolBoxView;
    protected TextView schoolName;

    public SchoolViewHolder(View view) {
        super(view);
        this.schoolBoxView = (LinearLayout) view.findViewById(R.id.schoolBoxView);
        this.schoolName = (TextView) view.findViewById(R.id.lbl_schoolName);
    }
}
