package org.thailandsbc.cloneplanting.utils;

import android.content.Context;
import android.content.Loader;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Adapter;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.thailandsbc.cloneplanting.breedernetwork.MyBreederListRecyclerViewAdapter;
import org.thailandsbc.cloneplanting.breedernetwork.content.BreederContent;
import org.thailandsbc.cloneplanting.breedernetwork.content.SectorContent;
import org.thailandsbc.cloneplanting.model.LandDetailModel;

import java.util.ArrayList;

public class DataLoader {
    private static final String TAG = "DataLoader";
    AQuery aq;
    GsonTransformer gsonTransformer = new GsonTransformer();
    private RecyclerView.Adapter adapter;

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

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    class LandData{
        public ArrayList<LandDetailModel> LandListData = new ArrayList<>();
    }


    public ArrayList<BreederContent.BreederItem> getBreederListData(){
        final ArrayList<BreederContent.BreederItem> arrayListBreederItems = new ArrayList<>();

        String getLandURL = "https://api.myjson.com/bins/23syx";
        aq.transformer(gsonTransformer).ajax(getLandURL,null,FriendListData.class,new AjaxCallback<FriendListData>(){
            @Override
            public void callback(String url, FriendListData object, AjaxStatus status) {
                super.callback(url, object, status);
                Log.d(TAG, "callback: Callback Status : "+status.getMessage());
                if (status.getCode() == 200){
                    Log.d(TAG, "callback: UserData : ");
                    for (BreederContent.BreederItem model :object.FriendListData){
                        Log.d(TAG, "callback: UserID"+model.UserID);
                        Log.d(TAG, "callback: Name"+model.Name);
                        Log.d(TAG, "callback: URL"+model.PictureURL);
                        arrayListBreederItems.add(model);
                        notifyAdapter();
                    }
                }
            }
        });
        return arrayListBreederItems;
    }

    public void notifyAdapter(){
            adapter.notifyDataSetChanged();
    }

    class FriendListData{
        public ArrayList<BreederContent.BreederItem> FriendListData= new ArrayList<>();
    }

    public ArrayList<SectorContent.SectorItem> getSectorListData(){
        final ArrayList<SectorContent.SectorItem> arrayListSectItems = new ArrayList<>();

        String getLandURL = "https://api.myjson.com/bins/1gnmx";
        aq.transformer(gsonTransformer).ajax(getLandURL,null,SectorList.class,new AjaxCallback<SectorList>(){
            @Override
            public void callback(String url, SectorList object, AjaxStatus status) {
                super.callback(url, object, status);
                Log.d(TAG, "callback: Callback Status : "+status.getMessage());
                if (status.getCode() == 200){
                    Log.d(TAG, "callback: SectorData : ");
                    for (SectorContent.SectorItem model :object.SectorList){
                        Log.d(TAG, "callback: SectorCode"+model.SectorCode);
                        Log.d(TAG, "callback: SectorName"+model.FullName);
                        arrayListSectItems.add(model);
                        notifyAdapter();
                    }
                }
            }
        });
        return arrayListSectItems;
    }
     class SectorList{
        public ArrayList<SectorContent.SectorItem> SectorList = new ArrayList<>();
    }
}
