package org.thailandsbc.cloneplanting.checkclone;

import android.content.ContentValues;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.database.Database;
import org.thailandsbc.cloneplanting.model.CloneData;
import org.thailandsbc.cloneplanting.model.ColumnName;
import org.thailandsbc.cloneplanting.receive.ReceiveFamilyModel;

import java.util.ArrayList;

/**
 * Created by Icanzenith on 11/11/2015 AD.
 */
public class CloneListAdapter extends RecyclerView.Adapter<CloneListAdapter.ViewHolder> {

    private static final String TAG = "CloneListAdapter";
    private ArrayList<CloneData> cloneDatas;
    private AppCompatActivity activity;

    public CloneListAdapter(ArrayList<CloneData> cloneDatas, AppCompatActivity activity) {
        this.cloneDatas = cloneDatas;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_clone_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        v.setTag(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewOrder.setText(" " + (position + 1));
        holder.textViewCloneCode.setText(cloneDatas.get(position).getCloneCode());
        holder.checkBoxIsDead.setTag(cloneDatas.get(position));
        if (cloneDatas.get(position).isDead()) {
            holder.checkBoxIsDead.setChecked(true);
        } else {
            holder.checkBoxIsDead.setChecked(false);
        }
        holder.checkBoxIsDead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox c = (CheckBox) v;
                CloneData cloneData = (CloneData) v.getTag();
                String where = ColumnName.PlantedClone.CloneCode + " = ? AND " + ColumnName.PlantedClone.LandID + " = " + cloneData.getLandID();
                String[] selectionArgs = {cloneData.getCloneCode()};

                ContentValues value = new ContentValues();

                if (c.isChecked()) {
                    c.setChecked(true);
                    value.put(ColumnName.PlantedClone.isDead, 1);
                    int update = activity.getContentResolver().update(Database.PLANTEDCLONE, value, where, selectionArgs);
                } else {
                    c.setChecked(false);

                    value.put(ColumnName.PlantedClone.isDead, 0);
                    int update = activity.getContentResolver().update(Database.PLANTEDCLONE, value, where, selectionArgs);
                }
                snackbar = Snackbar.make(v, "Update " + cloneData.getCloneCode(), Snackbar.LENGTH_LONG).setAction("Done", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        snackbar.dismiss();
                    }
                });

                snackbar.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return cloneDatas.size();
    }

    Snackbar snackbar;

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewOrder;
        private TextView textViewCloneCode;
        private CheckBox checkBoxIsDead;

        public ViewHolder(View v) {
            super(v);
            textViewCloneCode = (TextView) v.findViewById(R.id.textViewCloneCode);
            textViewOrder = (TextView) v.findViewById(R.id.textViewOrder);
            checkBoxIsDead = (CheckBox) v.findViewById(R.id.checkboxIsDead);
        }
    }

}
