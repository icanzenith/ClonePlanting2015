package org.thailandsbc.cloneplanting.createland;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.landmanagement.LandManagementActivity;
import org.thailandsbc.cloneplanting.model.LandDetailModel;
import org.thailandsbc.cloneplanting.planting.RowListActivity;
import org.thailandsbc.cloneplanting.utils.Land;
import org.thailandsbc.cloneplanting.utils.SelectionMode;

import java.util.List;

/**
 * Created by Icanzenith on 10/30/2015 AD.
 */
public class LandListAdapter extends RecyclerView.Adapter<LandListAdapter.ViewHolder> implements OnClickListener{

    private List<LandDetailModel> dataSet;
    private AppCompatActivity activity;
    private String SelectionMode;

    public LandListAdapter(Context activity,List<LandDetailModel> dataSet,String selectionMode ) {
        this.dataSet = dataSet;
        this.activity = (AppCompatActivity) activity;
        SelectionMode = selectionMode;
    }

    public List<LandDetailModel> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<LandDetailModel> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_land_list, parent, false);
        v.setOnClickListener(this);
        ViewHolder viewHolder = new ViewHolder(v);
        v.setTag(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        dataSet.get(position).setPositionInList(position);
        holder.textViewSugarcaneSelectionType.setText(String.format("%d Selection", dataSet.get(position).getSugarcaneSelectionType()));
        holder.textViewLandName.setText(dataSet.get(position).getLandName());
        holder.model = dataSet.get(position);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public void onClick(View v) {

        ViewHolder d = (ViewHolder) v.getTag();
        if (SelectionMode.equals(org.thailandsbc.cloneplanting.utils.SelectionMode.MODE_PLANT_CLONE)) {
            Intent intent = new Intent(activity, RowListActivity.class);
            intent.putExtra(Land.LAND_DETAIL, d.model);
            activity.startActivity(intent);
        }
        if (SelectionMode.equals(org.thailandsbc.cloneplanting.utils.SelectionMode.MODE_LANDMANAGEMENT)){
            Intent intent = new Intent(activity, LandManagementActivity.class);
            activity.startActivity(intent);
        }


    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView textViewLandName;
        private TextView textViewSugarcaneSelectionType;
        private LandDetailModel model;
        public ViewHolder(View itemView) {
            super(itemView);
            textViewLandName = (TextView) itemView.findViewById(R.id.textViewLandName);
            textViewSugarcaneSelectionType = (TextView) itemView.findViewById(R.id.textViewSugarcaneSelectionType);

        }
    }

    public void addNewDataItem(LandDetailModel item) {
        dataSet.add(item);
        notifyItemChanged(dataSet.size() - 1);
    }

    public void deleteDataItem(LandDetailModel data, int listPosition) {
        //TODO Keep data
        dataSet.remove(listPosition);
        notifyItemRemoved(listPosition);
        notifyDataSetChanged();
    }


}
