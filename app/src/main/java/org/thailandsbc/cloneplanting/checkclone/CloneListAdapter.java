package org.thailandsbc.cloneplanting.checkclone;

import android.content.ContentValues;
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
public class CloneListAdapter extends RecyclerView.Adapter<CloneListAdapter.ViewHolder> implements CompoundButton.OnCheckedChangeListener{

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
        viewHolder.checkBoxIsDead.setOnCheckedChangeListener(this);
        v.setTag(viewHolder);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textViewOrder.setText("ลำดับที่ "+(position+1));
        holder.textViewCloneCode.setText(cloneDatas.get(position).getCloneCode());
        holder.checkBoxIsDead.setTag(cloneDatas.get(position));
        if (cloneDatas.get(position).isDead()){
            holder.checkBoxIsDead.setChecked(true);
        }else{
            holder.checkBoxIsDead.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return cloneDatas.size();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        CloneData cloneData = (CloneData) buttonView.getTag();
        String where = ColumnName.PlantedClone.CloneCode +" = ? AND "+ColumnName.PlantedClone.LandID+" = "+cloneData.getLandID();
        String[] selectionArgs = {cloneData.getCloneCode()};
        
        ContentValues v = new ContentValues();
        if (isChecked){
            v.put(ColumnName.PlantedClone.isDead,1);
        }else{
            v.put(ColumnName.PlantedClone.isDead,0);
        }
        int update = activity.getContentResolver().update(Database.PLANTEDCLONE,v,where,selectionArgs);
        Log.d("Check update",update+"");
        Log.d("Check update where",where+" ");

    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
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
