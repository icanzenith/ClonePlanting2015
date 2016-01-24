package org.thailandsbc.cloneplanting.planting;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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
import org.thailandsbc.cloneplanting.model.LandDetailModel;
import org.thailandsbc.cloneplanting.receive.ReceiveFamilyModel;
import org.thailandsbc.cloneplanting.utils.PlantStatus;
import org.thailandsbc.cloneplanting.utils.QRMode;
import org.thailandsbc.cloneplanting.utils.Request;
import org.thailandsbc.cloneplanting.utils.SelectionMode;
import org.thailandsbc.cloneplanting.utils.Uploader;
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

        textViewLandName.setText(mLandDetail.getLandName());
        textViewRowNumber.setText("แถวที่ " + mRowNumber);

    }

    private List<ReceiveFamilyModel> getPlantedList() {

        ArrayList<ReceiveFamilyModel> data = new ArrayList<>();

        String[] projection = null;
        String selection = ColumnName.PlantedFamily.LandID + " = " + mLandID + " AND " + ColumnName.PlantedFamily.RowNumber + " = " + mRowNumber;
        String[] selectionArgs = null;
        String sortOrder = ColumnName.PlantedFamily.OrderInRow + " ASC";
        Cursor c = getContentResolver().query(Database.RECEIVEDCLONE, projection, selection, selectionArgs, sortOrder);
        Log.d("Tag", "Count " + c.getCount());
        if (c != null) {
            while (c.moveToNext()) {
                ReceiveFamilyModel m = new ReceiveFamilyModel();
                m.setNameTent(c.getString(c.getColumnIndex(ColumnName.ReceivedClone.NameTent)));
                m.setLandID(c.getInt(c.getColumnIndex(ColumnName.PlantedFamily.LandID)));
                m.setRowNumber(c.getInt(c.getColumnIndex(ColumnName.PlantedFamily.RowNumber)));
                m.setOrderInRow(c.getInt(c.getColumnIndex(ColumnName.PlantedFamily.OrderInRow)));
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

    public void generateCloneData(ReceiveFamilyModel m){
        for (int i  = 0 ; i < m.getPlantedAmount();i++ ){
            String CurrentTime = baseApplication.getTimeUTC();
            ContentValues v = new ContentValues();
            v.put(ColumnName.PlantedClone.CloneCode,String.format(m.getNameTent() + "-%03d", (i + 1)));
            Log.d("CloneCode", String.format(m.getNameTent() + "-%03d", (i + 1)));
            v.put(ColumnName.PlantedClone.NameTent, m.getNameTent());
            v.put(ColumnName.PlantedClone.LandID, mLandID);
            v.put(ColumnName.PlantedClone.isDead,0);
            v.put(ColumnName.PlantedClone.updatedTime, CurrentTime);
            v.put(ColumnName.PlantedClone.createdTime, CurrentTime);
            v.put(ColumnName.PlantedClone.isUploaded , Uploader.NOT_UPLOADED);
            Uri uri = getContentResolver().insert(Database.PLANTEDCLONE,v);
        }
    }

    public void deleteCloneData(ReceiveFamilyModel m){

        String selection = ColumnName.PlantedClone.NameTent +" = ? AND "+ColumnName.PlantedClone.LandID+" = "+mLandID;
        long delete = getContentResolver().delete(Database.PLANTEDCLONE,selection,null);
        Log.d("TAG Delete data"," "+delete);
    }


    public void updateDataToDatabase(ReceiveFamilyModel m) {

        ContentValues v = new ContentValues();
        v.put(ColumnName.PlantedFamily.NameTent,m.getNameTent());
        v.put(ColumnName.PlantedFamily.PlantedAmount, m.getPlantedAmount());
        v.put(ColumnName.PlantedFamily.PlantedBy, baseApplication.getUserData().getUserID());
        v.put(ColumnName.PlantedFamily.createdTime, baseApplication.getTimeUTC());
        v.put(ColumnName.PlantedFamily.isUploaded , Uploader.NOT_UPLOADED);
        v.put(ColumnName.PlantedFamily.RowNumber,mRowNumber);
        v.put(ColumnName.PlantedFamily.OrderInRow,  (getPlantedList().size()+1));
        v.put(ColumnName.PlantedFamily.LandID, mLandID);
        //TODO REFER TO Status
//        v.put(ColumnName.PlantedFamily.isPlanted, PlantStatus.Planted);

        String where = ColumnName.ReceivedClone.NameTent + " = ?";
        String[] selectionArgs = {m.getNameTent()};
        int update = getContentResolver().update(Database.RECEIVEDCLONE, v, where, selectionArgs);
        if (update <= 0) {
            //update fail ยังไม่ได้ตรวจรับ
            Log.d("Tag Update", "update fail " + v.toString());
            // 1. Instantiate an AlertDialog.Builder with its constructor
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // 2. Chain together various setter methods to set the dialog characteristics
            builder.setMessage("ไม่มีเบอร์นี้อยู่ในระบบ")
                    .setTitle("การแจ้งเตือน").setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            // 3. Get the AlertDialog from create()
            AlertDialog dialog = builder.create();
            dialog.show();
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
        v.put(ColumnName.PlantedFamily.NameTent,m.getNameTent());
        v.put(ColumnName.PlantedFamily.PlantedAmount, m.getPlantedAmount());
        v.put(ColumnName.PlantedFamily.PlantedBy, baseApplication.getUserData().getUserID());
        v.put(ColumnName.PlantedFamily.createdTime, baseApplication.getTimeUTC());
        v.put(ColumnName.PlantedFamily.isUploaded , Uploader.NOT_UPLOADED);
        v.put(ColumnName.PlantedFamily.RowNumber,mRowNumber);
        v.put(ColumnName.PlantedFamily.OrderInRow,m.getOrderInRow());
        v.put(ColumnName.PlantedFamily.LandID, mLandID);
        String where = ColumnName.PlantedFamily.NameTent + " = ?";
        String[] selectionArgs = {m.getNameTent()};
        //TODO
        int update = getContentResolver().update(Database.PLANTEDFAMILY, v, where, selectionArgs);
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
            generateCloneData(((ReceiveFamilyModel) object));
            updateDataToDatabase((ReceiveFamilyModel) object);
        }
        if (TAG.equals(SelectionMode.MODE_EDIT_PLANT_CLONE)){
            //update data
            editPlantedClone((ReceiveFamilyModel) object);
            //update list
        }
        if (TAG.equals(SelectionMode.MODE_SELECT_FORMLIST)){
            Intent intent = new Intent(
                    PlantingCloneActivity.this,
                    NameTentListActivity.class );
            startActivityForResult(
                    intent,
                    Request.SELECT_FROM_LIST
            );

        }
    }

}
