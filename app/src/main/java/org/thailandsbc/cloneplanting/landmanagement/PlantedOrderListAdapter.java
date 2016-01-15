package org.thailandsbc.cloneplanting.landmanagement;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import android.content.Context;
import android.widget.TextView;

import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.receive.ReceiveFamilyModel;

/**
 * Created by Icanzenith on 1/8/2016 AD.
 */
public class PlantedOrderListAdapter extends BaseAdapter {

    public ArrayList<ReceiveFamilyModel> data;
    public Context context;
    public LayoutInflater layoutInflater;



    public PlantedOrderListAdapter(ArrayList<ReceiveFamilyModel> data, Context context) {
        this.context = context;
        this.data = data;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.single_list_clone_in_order, parent, false);
            holder.Order = (TextView) convertView.findViewById(R.id.textViewOrder);
            holder.NameTent = (TextView) convertView.findViewById(R.id.textViewNameTent);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();

        }
        holder.Order.setText(""+data.get(position).getOrder());
        holder.NameTent.setText(""+data.get(position).getNameTent());
        return convertView;
    }

    class ViewHolder {
        TextView Order;
        TextView NameTent;
    }

}