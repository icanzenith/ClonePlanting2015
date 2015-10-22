package org.thailandsbc.cloneplanting;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_send);
        InitializationViews();

        //get Userdata model here
        MySharedPreference d = new MySharedPreference(this);
        userData = d.getUserData();

    }

    private void InitializationViews() {
        mFab = (FloatingActionButton) findViewById(R.id.fabSend);
        recyclerView = (RecyclerView) findViewById(R.id.sendRecyclerView);

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

        //TODO Query Data from Database or Server
        ArrayList<SendFamilyModel> dataSet = new ArrayList<>();
        String[] projection = null;
        String selection = ColumnName.FamilyTable.SendTo + "= ?";
        String[] selectionArgs = {mWorkPlace.getWorkPlaceCode()};
        String sortOrder = null;

        Cursor c = getContentResolver().query(Database.Family, projection, selection, selectionArgs, sortOrder);

        if (c.getCount() != 0) {
            Log.d("Cursor Count",""+c.getCount());
            while (c.moveToNext()) {
                SendFamilyModel m = new SendFamilyModel();
                m.setFamilyCode(c.getString(c.getColumnIndex(ColumnName.FamilyTable.FamilyCode)));
                m.setSendAmount(c.getInt(c.getColumnIndex(ColumnName.FamilyTable.SendAmount)));
                dataSet.add(m);
            }
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
    }


    private void addNewDataToList(ScannerResultModel result) {
        SendFamilyModel item = castScannerResultToSendModel(result);
        updateToDatabases(item);
        mAdapter.addNewDataItem(item);

    }

    private void updateToDatabases(SendFamilyModel item) {
        String where = "";
        String[] selectionArgs = {};

        ContentValues values = new ContentValues();
        values.put(ColumnName.FamilyTable.FamilyCode, item.getFamilyCode());
        values.put(ColumnName.FamilyTable.SendAmount, item.getSendAmount());
        values.put(ColumnName.FamilyTable.SenderID, userData.getUserID());
        values.put(ColumnName.FamilyTable.SendTo, mWorkPlace.getWorkPlaceCode());

        Uri newUri = getContentResolver().insert(Database.Family, values);
        Log.d("Content Values newUri", newUri.toString());

    }

    private void deleteDataFromList(SendFamilyModel data, int listPosition) {
        mAdapter.deleteDataItem(data, listPosition);
    }

    private SendFamilyModel castScannerResultToSendModel(ScannerResultModel result) {
        SendFamilyModel m = new SendFamilyModel();
        m.setFamilyCode(result.getFamilyCode());
        m.setSendAmount(result.getAmount());
        return m;
    }
}
