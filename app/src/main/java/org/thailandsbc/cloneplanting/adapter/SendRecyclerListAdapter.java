package org.thailandsbc.cloneplanting.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.dialog.ListManagementDialog;
import org.thailandsbc.cloneplanting.model.SendFamilyModel;

import java.util.List;

/**
 * Created by Icanzenith on 8/31/15 AD.
 */
public class SendRecyclerListAdapter extends RecyclerView.Adapter<SendRecyclerListAdapter.ViewHolder> implements View.OnClickListener,View.OnLongClickListener {

    private List<SendFamilyModel> dataSet;
    private AppCompatActivity activity;

    public SendRecyclerListAdapter(Context activity,List<SendFamilyModel> dataSet) {
        this.dataSet = dataSet;
        this.activity = (AppCompatActivity) activity;
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
        dataSet.get(position).setPostionInList(position);
        holder.textViewOrder.setText(""+dataSet.get(position).getOrder());
        holder.textViewFamilyCode.setText(dataSet.get(position).getFamilyCode());
        holder.textViewAmount.setText(""+dataSet.get(position).getSendAmount());



    }



    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public void onClick(View v) {
        ViewHolder holder = (ViewHolder) v.getTag();
        int position =  holder.getLayoutPosition();
        SendFamilyModel item = dataSet.get(position);
        //TODO go to detail activity;

    }

    @Override
    public boolean onLongClick(View v) {
        ViewHolder holder = (ViewHolder) v.getTag();
        int position = holder.getLayoutPosition();
        SendFamilyModel item = dataSet.get(position);
        //TODO Show ListManagement
        ListManagementDialog dialog = ListManagementDialog.newInstance(item);
        dialog.show(activity.getSupportFragmentManager(),"list");

        return true;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

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
    public void addNewDataItem(SendFamilyModel item){
        dataSet.add(item);
        notifyItemChanged(dataSet.size()-1);
    }

    public void deleteDataItem(SendFamilyModel data, int listPosition) {
        //TODO Keep data
        dataSet.remove(listPosition);
        notifyItemRemoved(listPosition);
        notifyDataSetChanged();
    }

}
