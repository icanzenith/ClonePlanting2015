package org.thailandsbc.cloneplanting.utils;

import android.content.Context;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.thailandsbc.cloneplanting.model.LandDetailModel;

import java.util.ArrayList;

public class DataLoader {
    private static final String TAG = "DataLoader";
    AQuery aq;
    GsonTransformer gsonTransformer = new GsonTransformer();
    public DataLoader(Context context) {
        aq = new AQuery(context);
    }

    public void getLandData(){
        String getLandURL = "https://api.myjson.com/bins/4dw7t";
        aq.transformer(gsonTransformer).ajax(getLandURL,null,LandData.class,new AjaxCallback<LandData>(){
            @Override
            public void callback(String url, LandData object, AjaxStatus status) {
                super.callback(url, object, status);
                Log.d(TAG, "callback: Callback Status : "+status.getMessage());
                Log.d(TAG, "callback: LandData : ");
                for (LandDetailModel model :object.LandListData){
                    Log.d(TAG, "callback:"+model.getLandID());
                    Log.d(TAG, "callback:"+model.getLandName());
                }
            }
        });
    }

    class LandData{
        public ArrayList<LandDetailModel> LandListData = new ArrayList<>();
    }
}
