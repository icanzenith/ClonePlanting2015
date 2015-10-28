package org.thailandsbc.cloneplanting;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.thailandsbc.cloneplanting.adapter.SendRecyclerListAdapter;
import org.thailandsbc.cloneplanting.database.Database;
import org.thailandsbc.cloneplanting.database.MySharedPreference;
import org.thailandsbc.cloneplanting.dialog.QrCodeScannerDialog;
import org.thailandsbc.cloneplanting.dialog.SelectPlaceToSendDialog;
import org.thailandsbc.cloneplanting.model.ColumnName;
import org.thailandsbc.cloneplanting.model.ScannerResultModel;
import org.thailandsbc.cloneplanting.model.SendFamilyModel;
import org.thailandsbc.cloneplanting.model.UserDataModel;
import org.thailandsbc.cloneplanting.model.WorkPlaceModel;
import org.thailandsbc.cloneplanting.utils.QRMode;
import org.thailandsbc.cloneplanting.utils.SelectionMode;
import org.thailandsbc.cloneplanting.utils.onFragmentInteractionListener;

import java.util.ArrayList;
import java.util.List;

public class SendActivity extends AppCompatActivity implements onFragmentInteractionListener {

    private WorkPlaceModel mWorkPlace;

    private FloatingActionButton mFab;

    private RecyclerView recyclerView;

    private SendRecyclerListAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    private UserDataModel userData;

    private SearchView searchView;

    private BaseApplication baseApplication;

    private TextView ToolsBarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_send);
        InitializationViews();

        //get Userdata model here
        MySharedPreference d = new MySharedPreference(this);
        userData = d.getUserData();
        baseApplication = (BaseApplication) getApplication();

    }

    private void InitializationViews() {
        mFab = (FloatingActionButton) findViewById(R.id.fabSend);
        recyclerView = (RecyclerView) findViewById(R.id.sendRecyclerView);
        ToolsBarTitle = (TextView) findViewById(R.id.toolbarTitle);

        searchView = (SearchView) findViewById(R.id.action_search);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QrCodeScannerDialog dialog = QrCodeScannerDialog.newInstance(QRMode.MODE_SEND_FAMILY);
                dialog.show(getSupportFragmentManager(), "QR code Scanner");
            }
        });


    }

    private void createRecyclerList() {

        //Create DataSet here
        List<SendFamilyModel> dataSet = createDataSet(mWorkPlace);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new SendRecyclerListAdapter(this, dataSet);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

    }

    private List<SendFamilyModel> createDataSet(WorkPlaceModel mWorkPlace) {

        ArrayList<SendFamilyModel> dataSet = new ArrayList<>();
        String[] projection = null;
        String selection = ColumnName.SentClone.SentTo + "= ?";
        String[] selectionArgs = {mWorkPlace.getWorkPlaceCode()};
        String sortOrder = null;

        Cursor c = getContentResolver().query(Database.SENTCLONE, projection, selection, selectionArgs, sortOrder);

        if (c.getCount() != 0) {
            Log.d("Cursor Count",""+c.getCount());
            while (c.moveToNext()) {
                SendFamilyModel m = new SendFamilyModel();
                m.setFamilyCode(c.getString(c.getColumnIndex(ColumnName.SentClone.FamilyCode)));
                m.setSentBy(userData.getWorkPlaceCode());
                m.setSentTo(mWorkPlace.getWorkPlaceCode());
                m.setSendAmount(c.getInt(c.getColumnIndex(ColumnName.SentClone.SentAmount)));
                m.setCreatedTime(c.getString(c.getColumnIndex(ColumnName.SentClone.createdTime)));
                m.setUpdatedTime(c.getString(c.getColumnIndex(ColumnName.SentClone.updatedTime)));
                dataSet.add(m);
            }
        }else{
            Log.d("Cursor Count", "" + c.getCount());
        }
        return dataSet;


    }


    @Override
    protected void onResume() {
        super.onResume();
        CreateListWorkPlaceDialog();

    }

    private void CreateListWorkPlaceDialog() {
        Thread show = new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    Thread.sleep(100);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            SelectPlaceToSendDialog dialog = SelectPlaceToSendDialog.newInstance();
                            dialog.show(getSupportFragmentManager(), "dialog");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        show.start();
    }

    @Override
    public void onFragmentInteraction(String TAG, Object object) {
        if (TAG.equals(SelectionMode.MODE_SELECT_PLACE)) {
            WorkPlaceModel model = (WorkPlaceModel) object;
            mWorkPlace = model;
            ToolsBarTitle.setText("Sent From "+userData.getWorkPlaceCode()+" to "+model.getWorkPlaceCode());
            Log.d(TAG, model.getWorkPlaceCode().toString());
            createRecyclerList();

        }

        if (TAG.equals(QRMode.MODE_SEND_FAMILY)) {
            ScannerResultModel result = null;
            try {
                result = (ScannerResultModel) object;
                addNewDataToList(result);
                //
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("Throw Exception", "Error Casting Object");
            }
        }

        if (TAG.equals(SelectionMode.MODE_DELETE_SEND_CLONE)) {
            SendFamilyModel result = null;
            try {
                result = (SendFamilyModel) object;
                deleteDataFromList(result, ((SendFamilyModel) object).getPositionInList());
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("Throw Exception", "Error Casting Object");
            }
        }

        if (TAG.equals(SelectionMode.MODE_EDIT_SENT_CLONE)){
            SendFamilyModel result = null;
            try{
                result = (SendFamilyModel) object;
                updateToDatabases(result);
                }catch(Exception e){
                    e.printStackTrace();
                    Log.d("Throw Exception", "Error Casting Object");
                }


        }
    }


    private void addNewDataToList(ScannerResultModel result) {
        SendFamilyModel item = castScannerResultToSendModel(result);
        updateToDatabases(item);
    }

    private void updateToDatabases(SendFamilyModel item) {

        String where = ColumnName.SentClone.FamilyCode+" = ? AND "+ColumnName.SentClone.SentBy+" = ? AND "+ColumnName.SentClone.SentTo +" = ?";
        String[] selectionArgs = {item.getFamilyCode(),userData.getWorkPlaceCode(),mWorkPlace.getWorkPlaceCode()};

        ContentValues values = new ContentValues();
        values.put(ColumnName.SentClone.FamilyCode, item.getFamilyCode());
        values.put(ColumnName.SentClone.SentAmount, item.getSendAmount());
        values.put(ColumnName.SentClone.UserSender, userData.getUserID());
        values.put(ColumnName.SentClone.SentTo, mWorkPlace.getWorkPlaceCode());
        values.put(ColumnName.SentClone.SentBy, userData.getWorkPlaceCode());
        values.put(ColumnName.SentClone.createdTime, baseApplication.getTimeUTC());
        values.put(ColumnName.SentClone.updatedTime, baseApplication.getTimeUTC());

        int update = getContentResolver().update(Database.SENTCLONE, values, where, selectionArgs);
        if (update <= 0){
            Log.d("Update", "Update Finish");
            insertToDatabases(item);

        }else{
            //Update Complete
            //TODO Test
            Toast.makeText(SendActivity.this,"อัพเดทข้อมูลพันธุ์เรียบร้อย",Toast.LENGTH_LONG).show();
            refreshListData();
        }
    }

    private void insertToDatabases(SendFamilyModel item){

        ContentValues values = new ContentValues();
        values.put(ColumnName.SentClone.FamilyCode, item.getFamilyCode());
        values.put(ColumnName.SentClone.SentAmount, item.getSendAmount());
        values.put(ColumnName.SentClone.UserSender, userData.getUserID());
        values.put(ColumnName.SentClone.SentTo, mWorkPlace.getWorkPlaceCode());
        values.put(ColumnName.SentClone.SentBy, userData.getWorkPlaceCode());
        values.put(ColumnName.SentClone.createdTime, baseApplication.getTimeUTC());
        values.put(ColumnName.SentClone.updatedTime, baseApplication.getTimeUTC());
        Uri newUri = getContentResolver().insert(Database.SENTCLONE, values);
        mAdapter.addNewDataItem(item);

        Log.d("Uri", newUri.toString());
        Log.d("Uri",newUri.getLastPathSegment());

    }

    private void deleteFromDatabase(SendFamilyModel item){

        String where = ColumnName.SentClone.FamilyCode+" = ? AND "+ColumnName.SentClone.SentBy+" = ? AND "+ColumnName.SentClone.SentTo +" = ?";
        String[] selectionArgs = {item.getFamilyCode(),userData.getWorkPlaceCode(),mWorkPlace.getWorkPlaceCode()};
        int delete = getContentResolver().delete(Database.SENTCLONE, where, selectionArgs);
        if (delete>0){
            //Delete complete
            Log.d("Delete","Delete Complete : "+delete);
        }

    }

    private void deleteDataFromList(SendFamilyModel data, int listPosition) {
        mAdapter.deleteDataItem(data, listPosition);
        deleteFromDatabase(data);
    }

    private SendFamilyModel castScannerResultToSendModel(ScannerResultModel result) {
        SendFamilyModel m = new SendFamilyModel();
        m.setFamilyCode(result.getFamilyCode());
        m.setSendAmount(result.getAmount());
        return m;
    }

    private void refreshListData(){

        ArrayList<SendFamilyModel> dataSet = new ArrayList<>();
        String[] projection = null;
        String selection = ColumnName.SentClone.SentTo + "= ?";
        String[] selectionArgs = {mWorkPlace.getWorkPlaceCode()};
        String sortOrder = null;

        Cursor c = getContentResolver().query(Database.SENTCLONE, projection, selection, selectionArgs, sortOrder);

        if (c.getCount() != 0) {
            Log.d("Cursor Count",""+c.getCount());
            while (c.moveToNext()) {
                SendFamilyModel m = new SendFamilyModel();
                m.setFamilyCode(c.getString(c.getColumnIndex(ColumnName.SentClone.FamilyCode)));
                m.setSentBy(userData.getWorkPlaceCode());
                m.setSentTo(mWorkPlace.getWorkPlaceCode());
                m.setSendAmount(c.getInt(c.getColumnIndex(ColumnName.SentClone.SentAmount)));
                m.setCreatedTime(c.getString(c.getColumnIndex(ColumnName.SentClone.createdTime)));
                m.setUpdatedTime(c.getString(c.getColumnIndex(ColumnName.SentClone.updatedTime)));
                dataSet.add(m);
            }
        }else{
            Log.d("Cursor Count", "" + c.getCount());
        }
        mAdapter.setDataSet(dataSet);
        mAdapter.notifyDataSetChanged();


    }
}
