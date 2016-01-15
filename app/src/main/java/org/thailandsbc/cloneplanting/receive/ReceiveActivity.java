package org.thailandsbc.cloneplanting.receive;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.thailandsbc.cloneplanting.BaseApplication;
import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.adapter.ReceiveRecyclerListAdapter;
import org.thailandsbc.cloneplanting.database.Database;
import org.thailandsbc.cloneplanting.dialog.QrCodeScannerDialog;
import org.thailandsbc.cloneplanting.model.ColumnName;
import org.thailandsbc.cloneplanting.model.ScannerResultModel;
import org.thailandsbc.cloneplanting.model.UserDataModel;
import org.thailandsbc.cloneplanting.model.WorkPlaceModel;
import org.thailandsbc.cloneplanting.utils.QRMode;
import org.thailandsbc.cloneplanting.utils.SelectionMode;
import org.thailandsbc.cloneplanting.utils.onFragmentInteractionListener;

import java.util.ArrayList;
import java.util.List;

public class ReceiveActivity extends AppCompatActivity implements onFragmentInteractionListener{

    private FloatingActionButton mFab;
    private RecyclerView recyclerView;
    private ReceiveRecyclerListAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    private Toolbar toolbar;
    private WorkPlaceModel sentToWorkPlace;
    private BaseApplication baseApplication;
    private UserDataModel userData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        baseApplication = (BaseApplication) getApplication();
        userData = baseApplication.getUserData();
        sentToWorkPlace = new WorkPlaceModel();
        sentToWorkPlace.setWorkPlaceCode("ALL");
        InitializationViews();
    }

    private void InitializationViews() {

        mFab = (FloatingActionButton) findViewById(R.id.fabReceive);
        recyclerView = (RecyclerView) findViewById(R.id.sendRecyclerView);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QrCodeScannerDialog dialog = QrCodeScannerDialog.newInstance(QRMode.MODE_RECEIVE_FAMILY);
                dialog.show(getSupportFragmentManager(), "QR code Scanner");
            }
        });
        InitializeSpinner();
        createRecyclerList();


    }

    private void createRecyclerList() {

        //Create Sample DataSet

        List<ReceiveFamilyModel> dataSet = createDataSet();
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ReceiveRecyclerListAdapter(this,dataSet);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

    }

    @Override
    public void onFragmentInteraction(String TAG, Object object) {
        if (TAG.equals(QRMode.MODE_RECEIVE_FAMILY)) {
            ScannerResultModel result = (ScannerResultModel) object;
            addNewDataToList(result);
        }
        if (TAG.equals(SelectionMode.MODE_DELETE_RECEIVE_CLONE)){
            ReceiveFamilyModel result = (ReceiveFamilyModel) object;
            deleteDataFromList(result, ((ReceiveFamilyModel) object).getPositionInList());
        }
        if (TAG.equals(SelectionMode.MODE_EDIT_RECEIVED_CLONE)){
            ReceiveFamilyModel result = (ReceiveFamilyModel) object;
            updateToDatabases(result);
        }
    }

    private void addNewDataToList(ScannerResultModel result) {
        ReceiveFamilyModel item = castScannerResultToReceiveModel(result);
        updateToDatabases(item);

    }

    private List<ReceiveFamilyModel> createDataSet() {
        ArrayList<ReceiveFamilyModel> dataSet = new ArrayList<>();
        String[] projection = null;
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        Cursor c = getContentResolver().query(Database.RECEIVEDCLONE, projection, selection, selectionArgs, sortOrder);

        if (c.getCount() != 0) {
            Log.d("Cursor Count 1", "" + c.getCount());
            while (c.moveToNext()) {
                ReceiveFamilyModel m = new ReceiveFamilyModel();
                m.setNameTent(c.getString(c.getColumnIndex(ColumnName.ReceivedClone.NameTent)));
                m.setSentBy(sentToWorkPlace.getWorkPlaceCode());
                m.setReceivedBy(sentToWorkPlace.getWorkPlaceCode());
                m.setReceivedAmount(c.getInt(c.getColumnIndex(ColumnName.ReceivedClone.ReceivedAmount)));
                m.setCreatedTime(c.getString(c.getColumnIndex(ColumnName.ReceivedClone.createdTime)));
                m.setUpdatedTime(c.getString(c.getColumnIndex(ColumnName.ReceivedClone.updatedTime)));
                dataSet.add(m);
            }
        }else{
            Log.d("Cursor Count", "" + c.getCount());
        }
        return dataSet;
    }

    private ReceiveFamilyModel castScannerResultToReceiveModel(ScannerResultModel result) {
        ReceiveFamilyModel m = new ReceiveFamilyModel();
        m.setNameTent(result.getFamilyCode());
        m.setReceivedAmount(result.getAmount());
        return m;
    }

    private void refreshListData(){
        ArrayList<ReceiveFamilyModel> dataSet = new ArrayList<>();
        String[] projection = null;
        String selection = null;
        String[] selectionArgs =null;
        String sortOrder = null;

        Cursor c = getContentResolver().query(Database.RECEIVEDCLONE, projection, selection, selectionArgs, sortOrder);

        if (c.getCount() != 0) {
            Log.d("Cursor Count", "" + c.getCount());
            while (c.moveToNext()) {
                ReceiveFamilyModel m = new ReceiveFamilyModel();
                m.setNameTent(c.getString(c.getColumnIndex(ColumnName.ReceivedClone.NameTent)));
                m.setSentBy(sentToWorkPlace.getWorkPlaceCode());
                m.setReceivedBy(sentToWorkPlace.getWorkPlaceCode());
                m.setReceivedAmount(c.getInt(c.getColumnIndex(ColumnName.ReceivedClone.ReceivedAmount)));
                m.setCreatedTime(c.getString(c.getColumnIndex(ColumnName.ReceivedClone.createdTime)));
                m.setUpdatedTime(c.getString(c.getColumnIndex(ColumnName.ReceivedClone.updatedTime)));
                dataSet.add(m);
            }
        }else{
            Log.d("Cursor Count", "" + c.getCount());
        }
        mAdapter.setDataSet(dataSet);
        mAdapter.notifyDataSetChanged();
    }

    private void refreshListData(CharSequence placeCode){
        ArrayList<ReceiveFamilyModel> dataSet = new ArrayList<>();
        String[] projection = null;
        String selection = ColumnName.ReceivedClone.SentBy + "= ?";
        String[] selectionArgs = {placeCode.toString()};
        String sortOrder = null;

        Cursor c = getContentResolver().query(Database.RECEIVEDCLONE, projection, selection, selectionArgs, sortOrder);

        if (c.getCount() != 0) {
            Log.d("Cursor Count", "" + c.getCount());
            while (c.moveToNext()) {
                ReceiveFamilyModel m = new ReceiveFamilyModel();
                m.setNameTent(c.getString(c.getColumnIndex(ColumnName.ReceivedClone.NameTent)));
                m.setSentBy(sentToWorkPlace.getWorkPlaceCode());
                m.setReceivedBy(sentToWorkPlace.getWorkPlaceCode());
                m.setReceivedAmount(c.getInt(c.getColumnIndex(ColumnName.ReceivedClone.ReceivedAmount)));
                m.setCreatedTime(c.getString(c.getColumnIndex(ColumnName.ReceivedClone.createdTime)));
                m.setUpdatedTime(c.getString(c.getColumnIndex(ColumnName.ReceivedClone.updatedTime)));
                dataSet.add(m);
            }
        }else{
            Log.d("Cursor Count", "" + c.getCount());
        }
        mAdapter.setDataSet(dataSet);
        mAdapter.notifyDataSetChanged();
    }
    private void updateToDatabases(ReceiveFamilyModel item){
        String where = ColumnName.SentClone.NameTent+" = ? AND "+ColumnName.ReceivedClone.SentBy+" = ? AND "+ColumnName.ReceivedClone.ReceivedBy +" = ?";
        String[] selectionArgs = {item.getNameTent(),getPlaceCodeFormFamilyCode(item.getNameTent()),userData.getWorkPlaceCode()};
        ContentValues values = new ContentValues();
        values.put(ColumnName.ReceivedClone.NameTent           , item.getNameTent());
        values.put(ColumnName.ReceivedClone.ReceivedAmount       , item.getReceivedAmount());
        values.put(ColumnName.ReceivedClone.UserReceiver         , userData.getUserID());
        values.put(ColumnName.ReceivedClone.ReceivedBy           , userData.getWorkPlaceCode() );
        values.put(ColumnName.ReceivedClone.SentBy               , getPlaceCodeFormFamilyCode(item.getNameTent()));
//        values.put(ColumnName.ReceivedClone.createdTime          , baseApplication.getTimeUTC());
        values.put(ColumnName.ReceivedClone.updatedTime, baseApplication.getTimeUTC());
        int update = getContentResolver().update(Database.RECEIVEDCLONE, values, where, selectionArgs);
        if (update <= 0){
            Log.d("Update", "Update Finish not finish");
            insertToDatabases(item);

        }else{
            //Update Complete
            //TODO Test
            Toast.makeText(ReceiveActivity.this, "อัพเดทข้อมูลพันธุ์เรียบร้อย", Toast.LENGTH_LONG).show();
            refreshListData();
        }
    }

    private void insertToDatabases(ReceiveFamilyModel item) {

        ContentValues values = new ContentValues();
        values.put(ColumnName.ReceivedClone.NameTent           , item.getNameTent());
        values.put(ColumnName.ReceivedClone.ReceivedAmount       , item.getReceivedAmount());
        values.put(ColumnName.ReceivedClone.UserReceiver         , userData.getUserID());
        values.put(ColumnName.ReceivedClone.ReceivedBy           , userData.getWorkPlaceCode() );
        values.put(ColumnName.ReceivedClone.SentBy               , getPlaceCodeFormFamilyCode(item.getNameTent()));
        values.put(ColumnName.ReceivedClone.createdTime          , baseApplication.getTimeUTC());
        values.put(ColumnName.ReceivedClone.updatedTime          , baseApplication.getTimeUTC());
        Uri newUri = getContentResolver().insert(Database.RECEIVEDCLONE, values);
        mAdapter.addNewDataItem(item);
        Log.d("Uri", newUri.toString());
        Log.d("Uri",newUri.getLastPathSegment());

        //TODO GENERATE Clone
    }

    private void InitializeSpinner(){
        Spinner spinner = (Spinner) findViewById(R.id.listPlace);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.place, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CharSequence placeCode = (CharSequence) parent.getItemAtPosition(position);
                if (placeCode.equals("ALL")) {
                    Log.d("Debug", "OnItemSelect ALL");
                    sentToWorkPlace.setWorkPlaceCode("ALL");
                    refreshListData();
                } else {
                    Log.d("Debug", "OnItemSelect PlaceCode");
                    sentToWorkPlace.setWorkPlaceCode(placeCode.toString());
                    refreshListData(placeCode);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sentToWorkPlace.setWorkPlaceCode("ALL");
            }
        });
    }


    private void deleteDataFromList(ReceiveFamilyModel data,int listPosition) {
        mAdapter.deleteDataItem(data, listPosition);
        deleteFromDatabase(data);
    }
    private void deleteFromDatabase(ReceiveFamilyModel item){

        String where = ColumnName.ReceivedClone.NameTent+" = ? AND "+ColumnName.ReceivedClone.SentBy+" = ? AND "+ColumnName.ReceivedClone.ReceivedBy +" = ?";
        String[] selectionArgs = {item.getNameTent(),getPlaceCodeFormFamilyCode(item.getNameTent()),userData.getWorkPlaceCode()};
        int delete = getContentResolver().delete(Database.RECEIVEDCLONE, where, selectionArgs);
        if (delete>0){
            //Delete complete
            Log.d("Delete","Delete Complete : "+delete);
        }

        //TODO DELETE PLANTED CLONE

    }

    private String getPlaceCodeFormFamilyCode(String familyCode){
        String placeCode = null;
        String[] array = familyCode.split("");
        placeCode = array[1];
        Log.d("Split PlaceCode", "  " + placeCode);
        return placeCode;
    }

}
