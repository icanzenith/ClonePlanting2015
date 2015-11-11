package org.thailandsbc.cloneplanting.planting;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.thailandsbc.cloneplanting.BaseApplication;
import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.database.Database;
import org.thailandsbc.cloneplanting.dialog.QrCodeScannerDialog;
import org.thailandsbc.cloneplanting.model.ColumnName;
import org.thailandsbc.cloneplanting.model.FamilyModel;
import org.thailandsbc.cloneplanting.model.ScannerResultModel;
import org.thailandsbc.cloneplanting.model.WorkPlaceModel;
import org.thailandsbc.cloneplanting.receive.ReceiveFamilyModel;
import org.thailandsbc.cloneplanting.utils.PlantStatus;
import org.thailandsbc.cloneplanting.utils.QRMode;
import org.thailandsbc.cloneplanting.utils.SelectionMode;
import org.thailandsbc.cloneplanting.utils.onFragmentInteractionListener;

import java.util.ArrayList;
import java.util.List;

public class PlantingCloneActivity extends AppCompatActivity implements onFragmentInteractionListener {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private Toolbar toolbar;

    private Integer mRowNumber;
    private Integer mLandID;
    private String mLandName;
    private LandDetailModel mLandDetail;

    protected RecyclerView.LayoutManager layoutManager;
    private PlantRecyclerListAdapter mAdapter;
    private BaseApplication baseApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planting_clone);
        baseApplication = (BaseApplication) getApplication();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("ปลูกโคลน");
        setSupportActionBar(toolbar);

        mRowNumber = getIntent().getIntExtra("RowNumber", 0);
        mLandID = getIntent().getIntExtra("LandID", 0);
        mLandName = getIntent().getStringExtra("LandName");
        mLandDetail = getIntent().getParcelableExtra("LandDetail");

        Log.d("Tag","RowNumber "+mRowNumber);
        Log.d("Tag","LandID "+mLandID);
        Log.d("Tag","LandName "+mLandName);


        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Open QRCode
                QrCodeScannerDialog dialog = QrCodeScannerDialog.newInstance(QRMode.MODE_PLANT_FAMILY);
                dialog.show(getSupportFragmentManager(), "QR code Scanner");

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        }
        );

        InitializationViews();
    }


    public void InitializationViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        mAdapter = new PlantRecyclerListAdapter(getPlantedList(), this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        TextView textViewRowNumber = (TextView) findViewById(R.id.textViewRowNumber);
        TextView textViewLandName = (TextView) findViewById(R.id.textViewLandName);

        textViewLandName.setText(mLandName);
        textViewRowNumber.setText("แถวที่ " + mRowNumber);

    }

    private List<ReceiveFamilyModel> getPlantedList() {

        ArrayList<ReceiveFamilyModel> data = new ArrayList<>();

        String[] projection = null;
        String selection = ColumnName.ReceivedClone.LandID + " = " + mLandID + " AND " + ColumnName.ReceivedClone.RowNumber + " = " + mRowNumber;
        String[] selectionArgs = null;
        String sortOrder = ColumnName.ReceivedClone.OrderInRow + " ASC";
        Cursor c = getContentResolver().query(Database.RECEIVEDCLONE, projection, selection, selectionArgs, sortOrder);
        Log.d("Tag", "Count " + c.getCount());
        if (c != null) {
            while (c.moveToNext()) {
                ReceiveFamilyModel m = new ReceiveFamilyModel();
                m.setFamilyCode(c.getString(c.getColumnIndex(ColumnName.ReceivedClone.FamilyCode)));
                m.setLandID(c.getInt(c.getColumnIndex(ColumnName.ReceivedClone.LandID)));
                m.setRowNumber(c.getInt(c.getColumnIndex(ColumnName.ReceivedClone.RowNumber)));
                m.setOrderinRow(c.getInt(c.getColumnIndex(ColumnName.ReceivedClone.OrderInRow)));
                m.setPlantedAmount(c.getInt(c.getColumnIndex(ColumnName.ReceivedClone.PlantedAmount)));
                m.setReceivedAmount(c.getInt(c.getColumnIndex(ColumnName.ReceivedClone.ReceivedAmount)));
                m.setCreatedTime(c.getString(c.getColumnIndex(ColumnName.ReceivedClone.createdTime)));
                m.setUpdatedTime(c.getString(c.getColumnIndex(ColumnName.ReceivedClone.updatedTime)));
                data.add(m);
            }
        }

        return data;
    }

    public void setRowAndLand() {

    }


    public void updateDataToDatabase(ReceiveFamilyModel m) {

        ContentValues v = new ContentValues();
        v.put(ColumnName.ReceivedClone.FamilyCode,m.getFamilyCode());
        v.put(ColumnName.ReceivedClone.PlantedAmount, m.getPlantedAmount());
        v.put(ColumnName.ReceivedClone.PlantedBy, baseApplication.getUserData().getUserID());
        v.put(ColumnName.ReceivedClone.PlantedTime, baseApplication.getTimeUTC());
        v.put(ColumnName.ReceivedClone.RowNumber,mRowNumber);
        v.put(ColumnName.ReceivedClone.OrderInRow,  (getPlantedList().size()+1));
        v.put(ColumnName.ReceivedClone.LandID, mLandID);
        v.put(ColumnName.ReceivedClone.isPlanted, PlantStatus.Planted);

        String where = ColumnName.ReceivedClone.FamilyCode + " = ?";
        String[] selectionArgs = {m.getFamilyCode()};
        int update = getContentResolver().update(Database.RECEIVEDCLONE, v, where, selectionArgs);
        if (update <= 0) {
            //update fail ยังไม่ได้ตรวจรับ
            Log.d("Tag Update", "update fail " + v.toString());

        } else {
            //update complete
            Log.d("Tag Update","update complete "+ v.toString());
        }
        refreshListData();
    }

    public void deleteDataFromDatabase() {

    }

    public void addTooList() {

    }

    public void deleteDataFromList() {

    }

    public void editPlantedClone(ReceiveFamilyModel m){
        ContentValues v = new ContentValues();
        v.put(ColumnName.ReceivedClone.FamilyCode,m.getFamilyCode());
        v.put(ColumnName.ReceivedClone.PlantedAmount, m.getPlantedAmount());
        v.put(ColumnName.ReceivedClone.PlantedBy, baseApplication.getUserData().getUserID());
        v.put(ColumnName.ReceivedClone.PlantedTime, baseApplication.getTimeUTC());
        v.put(ColumnName.ReceivedClone.RowNumber,mRowNumber);
        v.put(ColumnName.ReceivedClone.OrderInRow,m.getOrderinRow());
        v.put(ColumnName.ReceivedClone.LandID, mLandID);
        v.put(ColumnName.ReceivedClone.isPlanted, PlantStatus.Planted);

        String where = ColumnName.ReceivedClone.FamilyCode + " = ?";
        String[] selectionArgs = {m.getFamilyCode()};
        int update = getContentResolver().update(Database.RECEIVEDCLONE, v, where, selectionArgs);
        if (update <= 0) {
            //update fail ยังไม่ได้ตรวจรับ
            Log.d("Tag Update", "update fail " + v.toString());
        } else {
            //update complete
            Log.d("Tag Update","update complete "+ v.toString());
        }
        refreshListData();
    }


    public void refreshListData() {
        mAdapter.setDataSet(getPlantedList());
        mAdapter.notifyDataSetChanged();
    }

    public void onFragmentInteraction(String TAG, Object object) {

        if (TAG.equals(QRMode.MODE_PLANT_FAMILY)) {
            //Add TO Database
            Log.d("Tag ",((ReceiveFamilyModel)object).getPlantedAmount()+"");
            updateDataToDatabase((ReceiveFamilyModel) object);
        }
        if (TAG.equals(SelectionMode.MODE_EDIT_PLANT_CLONE)){
            //update data
            editPlantedClone((ReceiveFamilyModel) object);
            //update list

        }
    }

}