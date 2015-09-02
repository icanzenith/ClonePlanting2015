package org.thailandsbc.cloneplanting.receive;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.adapter.ReceiveRecyclerListAdapter;
import org.thailandsbc.cloneplanting.dialog.QrCodeScannerDialog;
import org.thailandsbc.cloneplanting.model.ScannerResultModel;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_receive);
        InitializationViews();
    }

    private void InitializationViews() {
        mFab = (FloatingActionButton) findViewById(R.id.fabReceive);
        recyclerView = (RecyclerView) findViewById(R.id.sendRecyclerView);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QrCodeScannerDialog dialog = QrCodeScannerDialog.newInstance(QRMode.MODE_RECEIVE_FAMILY);
                dialog.show(getSupportFragmentManager(), "QR code Scanner");
            }
        });

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
    }

    private void addNewDataToList(ScannerResultModel result) {
        ReceiveFamilyModel item = castScannerResultToReceiveModel(result);
        mAdapter.addNewDataItem(item);
    }

    private void deleteDataFromList(ReceiveFamilyModel data,int listPosition) {
        mAdapter.deleteDataItem(data, listPosition);
    }

    private List<ReceiveFamilyModel> createDataSet() {
        //TODO Query Data from Database or Server

        List<ReceiveFamilyModel> dataSet = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            ReceiveFamilyModel m = new ReceiveFamilyModel();
            m.setOrder(i++);
            m.setFamilyCode("A150" + 10 + i);
            m.setSendAmount(35);
            dataSet.add(m);

        }
        return dataSet;
        
    }

    private ReceiveFamilyModel castScannerResultToReceiveModel(ScannerResultModel result) {
        ReceiveFamilyModel m = new ReceiveFamilyModel();
        //TODO set Latest order
        m.setOrder(0);
        m.setFamilyCode(result.getFamilyCode());
        m.setSendAmount(result.getAmount());
        return m;
    }
}
