package org.thailandsbc.cloneplanting.checkclone;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.database.Database;
import org.thailandsbc.cloneplanting.model.CloneData;
import org.thailandsbc.cloneplanting.model.ColumnName;
import org.thailandsbc.cloneplanting.receive.ReceiveFamilyModel;

import java.util.ArrayList;

public class CheckCloneActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    protected RecyclerView.LayoutManager layoutManager;
    private CloneListAdapter mAdapter;
    private ReceiveFamilyModel receiveFamilyModel;

    private TextView textViewOrderInRow;
    private TextView textViewRowNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_clone);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        receiveFamilyModel = getIntent().getParcelableExtra("Item");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        InitializeViews();
        InitializeData();
        setRecyclerView();
    }

    private void InitializeViews() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        textViewOrderInRow = (TextView) findViewById(R.id.textViewOrder);
        textViewRowNumber = (TextView) findViewById(R.id.textViewRowNumber);

    }

    private void InitializeData() {
        layoutManager = new LinearLayoutManager(this);
        mAdapter = new CloneListAdapter(getCloneCodeList(receiveFamilyModel), this);

        textViewRowNumber.setText("แถวที่ "+receiveFamilyModel.getRowNumber());
        textViewOrderInRow.setText("ลำดับที่ "+receiveFamilyModel.getOrderinRow());
    }

    private void setRecyclerView() {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    }

    private ArrayList<CloneData> getCloneCodeList(ReceiveFamilyModel m) {
        ArrayList<CloneData> cloneListData = new ArrayList<>();
        String selection = ColumnName.PlantedClone.FamilyCode + " = ? AND " + ColumnName.PlantedClone.LandID + " = " + m.getLandID();
        String[] selectionArgs = {m.getFamilyCode()};
        String sortOrder = ColumnName.PlantedClone.CloneCode + " ASC";
        Cursor c = getContentResolver().query(Database.PLANTEDCLONE, null, selection, selectionArgs, sortOrder);
        if (c != null) {
            while (c.moveToNext()) {
                CloneData clone = new CloneData();
                clone.setCloneCode(c.getString(c.getColumnIndex(ColumnName.PlantedClone.CloneCode)));
                clone.setFamilyCode(c.getString(c.getColumnIndex(ColumnName.PlantedClone.FamilyCode)));
                clone.setCreateTime(c.getString(c.getColumnIndex(ColumnName.PlantedClone.createdTime)));
                if (c.getInt(c.getColumnIndex(ColumnName.PlantedClone.isDead)) == 1) {
                    clone.setIsDead(true);
                } else {
                    clone.setIsDead(false);
                }
                clone.setLandID(c.getInt(c.getColumnIndex(ColumnName.PlantedClone.LandID)));
                cloneListData.add(clone);
            }
        }


        return cloneListData;
    }

}
