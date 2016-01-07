package org.thailandsbc.cloneplanting.landmanagement;


import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LandDetailFragment extends Fragment {

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


    public LandDetailFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        Fragment fragment = new LandDetailFragment();
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


        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    private void createOrderList(int landID) {
        //TODO Query Here to get List of NameTent or FamilyCode of this LandID

        ContentResolver cr = getActivity().getContentResolver();
        String sortOrder = ColumnName.ReceivedClone.RowNumber+" ASC, "+ColumnName.ReceivedClone.OrderInRow+" ASC";
        String selection = ColumnName.ReceivedClone.LandID+" = "+landID;

        Cursor c = cr.query(Database.RECEIVEDCLONE,null,selection,null,sortOrder);

        while (c.moveToNext()){

        }
        c.close();

        List<HashMap<String, Object>> mList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            HashMap<String, Object> data = new HashMap<>();
            data.put("order", "" + (i + 1));
            data.put("nameTent", "A1500" + i);
            mList.add(data);

        }

        mOrderAdapter = new SimpleAdapter(getActivity(), mList, R.layout.single_list_clone_in_order,
                new String[]{"order", "nameTent"}, new int[]{R.id.textViewOrder, R.id.textViewNameTent});
        mListViewOrder.setAdapter(mOrderAdapter);
        mListViewOrder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


//                getCloneDetail();
            }
        });

    }

    private void getCloneDetail(int landID) {


        CloneDetailData cloneDetail = new CloneDetailData();


        ContentResolver cr = getActivity().getContentResolver();
        String sortOrder = ColumnName.ReceivedClone.RowNumber+" ASC, "+ColumnName.ReceivedClone.OrderInRow+" ASC";
        String selection = ColumnName.ReceivedClone.LandID+" = "+landID;


        Cursor c = cr.query(Database.PLANTEDCLONE, null, selection, null, null);
        if (c.moveToFirst()) {

            cloneDetail.setMotherName(c.getString(c.getColumnIndex(ColumnName.ReceivedClone.MotherCode)));
            cloneDetail.setFatherName(c.getString(c.getColumnIndex(ColumnName.ReceivedClone.FatherCode)));
            cloneDetail.setPlantedAmount(c.getInt(c.getColumnIndex(ColumnName.ReceivedClone.PlantedAmount)) + "");
            cloneDetail.setReceiveAmount(c.getInt(c.getColumnIndex(ColumnName.ReceivedClone.ReceivedAmount)) + "");

        }
        c.close();

        //Check Survive Amount
        selection = ColumnName.PlantedClone.LandID + " = ? AND " +
                ColumnName.PlantedClone.FamilyCode + " = ?";
        c = cr.query(Database.PLANTEDCLONE, null, selection, null, null);

        int countLiveAmount = 0;

        while (c.moveToNext()) {
            if (c.getInt(c.getColumnIndex(ColumnName.PlantedClone.isDead)) == 1) {

            } else {

                countLiveAmount++;
            }
        }

        cloneDetail.setSurvivedAmount(countLiveAmount + "");

        blindViewData(cloneDetail);

    }

    private void blindViewData(CloneDetailData cloneDetail) {
        textViewMotherName.setText(cloneDetail.getMotherName());
        textViewFatherName.setText(cloneDetail.getFatherName());
        textViewOrder.setText(cloneDetail.getOrder()+"");
        textViewReceiveAmount.setText(cloneDetail.getReceiveAmount());
        textViewPlantedAmount.setText(cloneDetail.getPlantedAmount());
        textViewSurviveAmount.setText(cloneDetail.getSurvivedAmount());
        textViewNameTent.setText(cloneDetail.getNameTent());
    }

}
