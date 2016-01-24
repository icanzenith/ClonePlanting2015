package org.thailandsbc.cloneplanting.planting;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.database.Database;
import org.thailandsbc.cloneplanting.model.ColumnName;

import java.util.ArrayList;
import java.util.HashMap;

public class NameTentListActivity extends AppCompatActivity {

    private static final String TAG = "NameTentListActivity";
    private SimpleAdapter adapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_name_tent_list_fragment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        InitializeView();
    }
    private void InitializeView(){
        mListView = (ListView) findViewById(R.id.listView);
        openNameTentList();
    }

    public void openNameTentList(){

        ContentResolver cr  = NameTentListActivity.this.getContentResolver();
        String selection = null;
        String[] selectionArgs = null;

        Cursor c = cr.query(Database.RECEIVEDCLONE,null,selection,selectionArgs,null);
        ArrayList<HashMap<String,String>> data = new ArrayList<>();
        if (c!=null){
            while (c.moveToNext()){
                HashMap<String,String> map = new HashMap<>();
                map.put(ColumnName.ReceivedClone.NameTent,c.getString(c.getColumnIndex(ColumnName.ReceivedClone.NameTent)));
                data.add(map);
            }
        }
        adapter = new SimpleAdapter(this,data,android.R.layout.simple_list_item_1,new String[]{ColumnName.ReceivedClone.NameTent},new  int[] {android.R.id.text1});
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                HashMap<String,String> map = (HashMap<String, String>) parent.getItemAtPosition(position);
                String nameTent = map.get(ColumnName.ReceivedClone.NameTent);
                setResult(RESULT_OK,getIntent().putExtra(ColumnName.ReceivedClone.NameTent,nameTent));
                Log.d(TAG, "onItemClick() called with: " + "parent = [" + nameTent + "], view = [" + view + "], position = [" + position + "], id = [" + id + "]");
                finish();

            }
        });

    }


}
