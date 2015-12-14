package org.thailandsbc.cloneplanting.personal;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.createland.LandListAdapter;
import org.thailandsbc.cloneplanting.database.Database;
import org.thailandsbc.cloneplanting.model.ColumnName;
import org.thailandsbc.cloneplanting.model.LandDetailModel;
import org.thailandsbc.cloneplanting.utils.SelectionMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Icanzenith on 11/16/2015 AD.
 */
public class LandListFragment extends Fragment {

    private LandListAdapter mAdapter;
    private RecyclerView recyclerView;
    protected RecyclerView.LayoutManager layoutManager;

    public LandListFragment() {
    }

    public static Fragment newInstance(){
        Fragment fragment = new LandListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("PageTitle","รายชื่อแปลง");
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter =new LandListAdapter(this.getActivity(),createDataSet(), SelectionMode.MODE_LANDFRAGMENT);
        layoutManager = new LinearLayoutManager(this.getActivity());

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }


    private List<LandDetailModel> createDataSet() {
        List<LandDetailModel> data = new ArrayList<>();
        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        Cursor c = getActivity().getContentResolver().query(Database.LAND, projection, selection, selectionArgs, sortOrder);
        if (c.getCount()!=0){
            Log.d("TAG", "" + c.getCount());
            while (c.moveToNext()){
                LandDetailModel m = new LandDetailModel();
                m.setLandID(c.getInt(c.getColumnIndex(ColumnName.Land.LandID)));
                Log.d("TAG LANDID",m.getLandID()+"");
                m.setLandName(c.getString(c.getColumnIndex(ColumnName.Land.LandName)));
                m.setLatitude(c.getDouble(c.getColumnIndex(ColumnName.Land.Latitude)));
                m.setLongitude(c.getDouble(c.getColumnIndex(ColumnName.Land.Longitude)));
                m.setLandArea(c.getFloat(c.getColumnIndex(ColumnName.Land.LandArea)));
                m.setLandWidth(c.getFloat(c.getColumnIndex(ColumnName.Land.LandWidth)));
                m.setLandLength(c.getFloat(c.getColumnIndex(ColumnName.Land.LandLength)));
                m.setUserCreate(c.getInt(c.getColumnIndex(ColumnName.Land.UserCreate)));
                m.setCreatedTime(c.getString(c.getColumnIndex(ColumnName.Land.createdTime)));
                m.setUpdatedTime(c.getString(c.getColumnIndex(ColumnName.Land.updatedTime)));
                m.setSugarcaneSelectionType(c.getInt(c.getColumnIndex(ColumnName.Land.SugarcaneSelectionType)));
                m.setYearCrossing(c.getString(c.getColumnIndex(ColumnName.Land.YearCrossing)));
                m.setAddress(c.getString(c.getColumnIndex(ColumnName.Land.Address)));
                data.add(m);
            }
        }
        return data;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_landlist, container, false);
        recyclerView= (RecyclerView) v.findViewById(R.id.recyclerView);
        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
