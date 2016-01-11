package org.thailandsbc.cloneplanting.landmanagement;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.database.Database;
import org.thailandsbc.cloneplanting.dialog.SelectPlaceToSendDialog;
import org.thailandsbc.cloneplanting.model.CloneDetailData;
import org.thailandsbc.cloneplanting.model.ColumnName;
import org.thailandsbc.cloneplanting.model.FamilyModel;
import org.thailandsbc.cloneplanting.model.LandDetailModel;
import org.thailandsbc.cloneplanting.receive.ReceiveFamilyModel;
import org.thailandsbc.cloneplanting.utils.Land;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LandDetailFragment extends Fragment {

    private static final String TAG = "LandDetailFragment";
    private ListView mLandDetailList;
    private ListView mListViewOrder;
    private SimpleAdapter mOrderAdapter;
    private SimpleAdapter adapter;

    // Detail Panel Views
    private TextView textViewFatherName;
    private TextView textViewMotherName;
    private TextView textViewNameTent;
    private TextView textViewReceiveAmount;
    private TextView textViewPlantedAmount;
    private TextView textViewSurviveAmount;
    /**
     * textViewOrder คือลำดับการปลูกเรียงจากต้นจนจบ
     */
    private TextView textViewOrder;

    private  LandDetailModel landDetailModel;


    public LandDetailFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance(LandDetailModel landDetail) {

        Fragment fragment = new LandDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(Land.LAND_DETAIL,landDetail);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_land_detail, container, false);
        mListViewOrder          = (ListView) v.findViewById(R.id.listViewOrder);
        textViewFatherName      = (TextView) v.findViewById(R.id.textViewFatherName);
        textViewMotherName      = (TextView) v.findViewById(R.id.textViewMotherName);
        textViewNameTent        = (TextView) v.findViewById(R.id.textViewNameTent);
        textViewReceiveAmount   = (TextView) v.findViewById(R.id.textViewReceiveAmount);
        textViewPlantedAmount   = (TextView) v.findViewById(R.id.textViewPlantedAmount);
        textViewSurviveAmount   = (TextView) v.findViewById(R.id.textViewSurviveAmount);
        textViewOrder = (TextView) v.findViewById(R.id.textViewOrder);


        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            landDetailModel = getArguments().getParcelable(Land.LAND_DETAIL);
            createDataList();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PlantedOrderListAdapter mAdapter= new PlantedOrderListAdapter(createDataList(),getActivity());
        mListViewOrder.setAdapter(mAdapter);
        mListViewOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                blindViewData((ReceiveFamilyModel) parent.getItemAtPosition(position));
            }
        });
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    private ArrayList<ReceiveFamilyModel> createDataList(){
        ArrayList<ReceiveFamilyModel> famList = new ArrayList<>();
        ContentResolver contentResolver = getActivity().getContentResolver();

        String selection = ColumnName.ReceivedClone.LandID+" = "+landDetailModel.getLandID();
        String[] selectionArgs  = null;
        String sortOrder = ColumnName.ReceivedClone.RowNumber+" ASC ,"+ColumnName.ReceivedClone.OrderInRow+" ASC";
        Cursor c = contentResolver.query(Database.RECEIVEDCLONE,null,selection,selectionArgs,sortOrder);
        while (c.moveToNext()){
            Log.d(TAG, "createDataList: "+c.getString(c.getColumnIndex(ColumnName.ReceivedClone.FamilyCode)));

            ReceiveFamilyModel f = new ReceiveFamilyModel();

            f.setFamilyCode(c.getString(c.getColumnIndex(ColumnName.ReceivedClone.FamilyCode)));
            f.setFatherCode(c.getString(c.getColumnIndex(ColumnName.ReceivedClone.FatherCode)));
            f.setMotherCode(c.getString(c.getColumnIndex(ColumnName.ReceivedClone.MotherCode)));
            f.setReceivedAmount(c.getInt(c.getColumnIndex(ColumnName.ReceivedClone.ReceivedAmount)));
            f.setPlantedAmount(c.getInt(c.getColumnIndex(ColumnName.ReceivedClone.PlantedAmount)));
            f.setSurviveAmount(
                    //นับจำนวนต้นที่รอด
                    countSurviveAmount(landDetailModel.getLandID(),c.getString(c.getColumnIndex(ColumnName.ReceivedClone.FamilyCode)))
            );
            f.setOrder((c.getPosition()+1));
            famList.add(f);
        }

        return famList;

    }

    private int countSurviveAmount(Integer landID, String familyCode) {
        ContentResolver contentResolver = getActivity().getContentResolver();
        int surviveAmount = 0;

        String selection =
                ColumnName.PlantedClone.LandID+" = "+landID+" AND "+
                ColumnName.PlantedClone.isDead+ " = 0 AND "+
                ColumnName.PlantedClone.FamilyCode+" = ?";

        String[] selectionArgs  = {familyCode};
        String sortOrder = ColumnName.PlantedClone.CloneCode+" ASC ";
        Cursor c = contentResolver.query(Database.PLANTEDCLONE,null,selection,selectionArgs,sortOrder);
        surviveAmount = c.getCount();
        c.close();
        if (c.isClosed()){
            Log.d(TAG, "countSurviveAmount: Cursor is Close");
        }
        return surviveAmount;

    }



    private void blindViewData(ReceiveFamilyModel cloneDetail) {
        textViewMotherName.setText(cloneDetail.getMotherCode());
        textViewFatherName.setText(cloneDetail.getFatherCode());
        textViewOrder.setText(cloneDetail.getOrder()+"");
        textViewReceiveAmount.setText(cloneDetail.getReceivedAmount()+"");
        textViewPlantedAmount.setText(cloneDetail.getPlantedAmount()+"");
        textViewSurviveAmount.setText(cloneDetail.getSurviveAmount()+"");
        textViewNameTent.setText(cloneDetail.getFamilyCode());
    }


}
