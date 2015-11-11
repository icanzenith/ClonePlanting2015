package org.thailandsbc.cloneplanting.planting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.thailandsbc.cloneplanting.R;

import java.util.List;

/**
 * Created by Icanzenith on 11/9/2015 AD.
 */
public class RowListAdapter extends RecyclerView.Adapter<RowListAdapter.ViewHolder>
implements View.OnClickListener,View.OnLongClickListener{

    private List<Integer> dataSet;
    private AppCompatActivity activity;
    private LandDetailModel landDetail;

    public RowListAdapter(List<Integer> dataSet, AppCompatActivity activity,LandDetailModel landDetail) {
        this.dataSet = dataSet;
        this.activity = activity;
        this.landDetail = landDetail;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_row_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(v);
        v.setTag(viewHolder);
        v.setOnLongClickListener(this);
        v.setOnClickListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewRowNumber.setText("แถวที่ "+dataSet.get(position));
        holder.mRowNumber = dataSet.get(position);
        holder.landDetail = landDetail;
    }


    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public void onClick(View v) {
             ViewHolder vh = (ViewHolder) v.getTag();
        Intent intent = new Intent(activity,PlantingCloneActivity.class);
        intent.putExtra("RowNumber",vh.mRowNumber);
        intent.putExtra("LandDetail",vh.landDetail);
        intent.putExtra("LandID",vh.landDetail.getLandID());
        Log.d("TAG ROW",vh.mRowNumber+"");
        activity.startActivity(intent);
    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewRowNumber;
        private Integer mRowNumber;
        private LandDetailModel landDetail;

        public ViewHolder(View v) {
            super(v);
            textViewRowNumber= (TextView) v.findViewById(R.id.textViewRowNumber);

        }
    }
}
