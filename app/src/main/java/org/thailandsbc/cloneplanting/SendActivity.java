package org.thailandsbc.cloneplanting;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.zxing.qrcode.encoder.QRCode;

import org.thailandsbc.cloneplanting.adapter.SendRecyclerListAdapter;
import org.thailandsbc.cloneplanting.dialog.QrCodeScannerDialog;
import org.thailandsbc.cloneplanting.dialog.SelectPlaceToSendDialog;
import org.thailandsbc.cloneplanting.model.ScannerResult;
import org.thailandsbc.cloneplanting.model.SendFamilyModel;
import org.thailandsbc.cloneplanting.model.WorkPlaceModel;
import org.thailandsbc.cloneplanting.utils.QRMode;
import org.thailandsbc.cloneplanting.utils.SelectionMode;
import org.thailandsbc.cloneplanting.utils.onFragmentInteractionListener;

import java.util.ArrayList;
import java.util.List;

public class SendActivity extends AppCompatActivity implements onFragmentInteractionListener {

    WorkPlaceModel mWorkPlace;

    private FloatingActionButton mFab;

    private RecyclerView recyclerView;

    private SendRecyclerListAdapter mAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_send);
        InitializationViews();

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

        //Create Sample DataSet

        List<SendFamilyModel> dataSet = createDataSet(mWorkPlace);
        mLayoutManager = new LinearLayoutManager(this);


        mAdapter = new SendRecyclerListAdapter(this,dataSet);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

    }

    private List<SendFamilyModel> createDataSet(WorkPlaceModel mWorkPlace) {
        //TODO Query Data from Database or Server
        List<SendFamilyModel> dataSet = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            SendFamilyModel m = new SendFamilyModel();
            m.setOrder(i++);
            m.setFamilyCode("A150" + 10 + i);
            m.setSendAmount(35);
            dataSet.add(m);

        }

        //Layout Manager
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
            Log.d(TAG, model.getWorkPlace().toString());
            //TODO ใช้ Code ของ WorkPlace หา
            createRecyclerList();

        }

        if (TAG.equals(QRMode.MODE_SEND_FAMILY)) {
            ScannerResult result = (ScannerResult) object;
            addNewDataToList(result);
        }

        if (TAG.equals(SelectionMode.MODE_DELETE_SEND_CLONE)){
            SendFamilyModel result = (SendFamilyModel) object;
            deleteDataFromList(result,((SendFamilyModel) object).getPositionInList());
        }
    }



    private void addNewDataToList(ScannerResult result) {

        SendFamilyModel item = castScannerResultToSendModel(result);
        mAdapter.addNewDataItem(item);

    }

    private void deleteDataFromList(SendFamilyModel data,int listPosition) {
        mAdapter.deleteDataItem(data,listPosition);
    }

    private SendFamilyModel castScannerResultToSendModel(ScannerResult result) {
        SendFamilyModel m = new SendFamilyModel();
        //TODO set Latest order
        m.setOrder(0);
        m.setFamilyCode(result.getFamilyCode());
        m.setSendAmount(result.getAmount());
        return m;
    }
}
