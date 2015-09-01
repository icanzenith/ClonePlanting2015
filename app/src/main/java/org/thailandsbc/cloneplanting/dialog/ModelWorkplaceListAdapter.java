package org.thailandsbc.cloneplanting.dialog;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.model.WorkPlaceModel;

public class ModelWorkplaceListAdapter extends BaseAdapter {

    private List<WorkPlaceModel> objects = new ArrayList<>();

    private Context context;
    private LayoutInflater layoutInflater;

    public ModelWorkplaceListAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        objects = CreateSampleWorkPlaceList();
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public WorkPlaceModel getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.model_workplace_list, null);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.frameSymbol = (FrameLayout) convertView.findViewById(R.id.frameSymbol);
            viewHolder.texViewFullName = (TextView) convertView.findViewById(R.id.texViewFullName);
            viewHolder.textViewSymbol = (TextView) convertView.findViewById(R.id.textViewSymbol);

            convertView.setTag(viewHolder);
        }
        initializeViews(getItem(position), (ViewHolder) convertView.getTag());
        return convertView;
    }

    private void initializeViews(WorkPlaceModel object, ViewHolder holder) {
        holder.textViewSymbol.setText(object.getWorkPlace());
        holder.texViewFullName.setText(object.getWorkPlaceFullName());
    }

    protected class ViewHolder {
        private FrameLayout frameSymbol;
        private TextView texViewFullName;
        private TextView textViewSymbol;
    }

    private List<WorkPlaceModel> CreateSampleWorkPlaceList() {
        ArrayList<WorkPlaceModel> data = new ArrayList<>();
        WorkPlaceModel w[] = new WorkPlaceModel[12];

        String[] symbol = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",};
        String[] fullName = new String[]{"Kasetsart University",
                "OCSB 01", "OCSB 02", "OCSB 03", "OCSB 04", "F State", "G WorkPlace", "H WorkPlace", "I WorkPlace", "J WorkPlace", "K WorkPlace", "L WorkPlace", "M Work Place",};
        for (int i = 0; i < 12; i++) {
            w[i] = new WorkPlaceModel();
            w[i].setWorkPlace(symbol[i]);
            w[i].setWorkPlaceFullName(fullName[i]);
            data.add(w[i]);
        }


        return data;
    }
}
