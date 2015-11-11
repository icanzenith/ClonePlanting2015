package org.thailandsbc.cloneplanting.planting;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.dialog.ListManagementDialog;
import org.thailandsbc.cloneplanting.model.ColumnName;
import org.thailandsbc.cloneplanting.model.SendFamilyModel;
import org.thailandsbc.cloneplanting.receive.ReceiveFamilyModel;
import org.thailandsbc.cloneplanting.utils.SelectionMode;

import java.util.List;

/**
 * Created by Icanzenith on 10/30/2015 AD.
 */
public class PlantRecyclerListAdapter extends RecyclerView.Adapter<PlantRecyclerListAdapter.ViewHolder>
implements  View.OnClickListener,View.OnLongClickListener{

    private List<ReceiveFamilyModel> dataSet;
    private AppCompatActivity activity;


    public PlantRecyclerListAdapter(List<ReceiveFamilyModel> dataSet, AppCompatActivity activity) {
        this.dataSet = dataSet;
        this.activity = activity;
    }

    public List<ReceiveFamilyModel> getDataSet() {
        return dataSet;
    }

    public void setDataSet(List<ReceiveFamilyModel> dataSet) {
        this.dataSet = dataSet;
    }

    public AppCompatActivity getActivity() {
        return activity;
    }

    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_send_row_item, parent, false);
        v.setOnLongClickListener(this);
        v.setOnClickListener(this);
        ViewHolder viewHolder = new ViewHolder(v);
        v.setTag(viewHolder);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        dataSet.get(position).setPositionInList(position);
        holder.textViewOrder.setText(""+(position+1));
        holder.textViewFamilyCode.setText(dataSet.get(position).getFamilyCode());
        holder.textViewAmount.setText("" + dataSet.get(position).getPlantedAmount());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public void onClick(View v) {
        ViewHolder holder = (ViewHolder) v.getTag();
        int position = holder.getLayoutPosition();
        ReceiveFamilyModel item = dataSet.get(position);
    }

    @Override
    public boolean onLongClick(View v) {
        ViewHolder holder = (ViewHolder) v.getTag();
        int position = holder.getLayoutPosition();
        ReceiveFamilyModel item = dataSet.get(position);
        ListManagementDialog dialog = ListManagementDialog.newInstance(item, SelectionMode.MODE_EDIT_PLANT_CLONE);
        dialog.show(activity.getSupportFragmentManager(), "list");
        return true;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewFamilyCode;
        private TextView textViewAmount;
        private TextView textViewOrder;

        public ViewHolder(View v) {
            super(v);
            textViewAmount = (TextView) v.findViewById(R.id.textViewAmount);
            textViewFamilyCode = (TextView) v.findViewById(R.id.textViewFamilyCode);
            textViewOrder = (TextView) v.findViewById(R.id.textViewOrder);

        }
    }

    public void addNewDataItem(ReceiveFamilyModel item) {
        dataSet.add(item);
        notifyItemChanged(dataSet.size() - 1);
    }

    public void deleteDataItem(ReceiveFamilyModel data, int listPosition) {
        //TODO Keep data
        dataSet.remove(listPosition);
        notifyItemRemoved(listPosition);
        notifyDataSetChanged();
    }
}
