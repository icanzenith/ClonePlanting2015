package org.thailandsbc.cloneplanting.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.thailandsbc.cloneplanting.adapter.NewsFeedRecyclerAdapter;
import org.thailandsbc.cloneplanting.breedernetwork.content.BreederContent;
import org.thailandsbc.cloneplanting.breedernetwork.content.SectorContent;
import org.thailandsbc.cloneplanting.database.Database;
import org.thailandsbc.cloneplanting.model.ActivityData;
import org.thailandsbc.cloneplanting.model.ColumnName;
import org.thailandsbc.cloneplanting.model.LandDetailModel;
import org.thailandsbc.cloneplanting.model.SendFamilyModel;
import org.thailandsbc.cloneplanting.planting.PlantedCloneModel;
import org.thailandsbc.cloneplanting.receive.ReceiveFamilyModel;

import java.util.ArrayList;

public class DataLoader {
    private Context context;
    private static final String TAG = "DataLoader";
    private AQuery aq;
    private GsonTransformer gsonTransformer = new GsonTransformer();
    private RecyclerView.Adapter adapter;

    public DataLoader(Context context) {
        this.context = context;
        aq = new AQuery(context);
    }


    public void
    getLandData(){
        String getLandURL = "https://api.myjson.com/bins/4dw7t";
        aq.transformer(gsonTransformer).ajax(getLandURL,null,LandData.class,new AjaxCallback<LandData>(){
            @Override
            public void callback(String url, LandData object, AjaxStatus status) {
                super.callback(url, object, status);
                Log.d(TAG, "callback: Callback Status : "+status.getMessage());
                Log.d(TAG, "callback: LandData : ");
                for (LandDetailModel model :object.LandListData){
                    ContentValues v = new ContentValues();
                    v.put(ColumnName.Land.ObjectID              ,model.getLandID());
                    v.put(ColumnName.Land.LandID                ,model.getLandID());
                    v.put(ColumnName.Land.LandName              ,model.getLandName());
                    v.put(ColumnName.Land.LandLength            ,model.getLandLength());
                    v.put(ColumnName.Land.LandWidth             ,model.getLandWidth());
                    v.put(ColumnName.Land.LandArea              ,model.getLandArea());
                    v.put(ColumnName.Land.UserCreate            ,model.getUserCreate());
                    v.put(ColumnName.Land.Address               ,model.getAddress());
                    v.put(ColumnName.Land.Sector                ,model.getSector());
                    v.put(ColumnName.Land.Latitude              ,model.getLatitude());
                    v.put(ColumnName.Land.Longitude             ,model.getLongitude());
                    v.put(ColumnName.Land.YearCrossing          ,model.getYearCrossing());
                    v.put(ColumnName.Land.SugarcaneSelectionType,model.getSugarcaneSelectionType());
                    v.put(ColumnName.Land.createdTime           ,model.getCreatedTime());
                    v.put(ColumnName.Land.updatedTime           ,model.getUpdatedTime());
                    v.put(ColumnName.Land.MaximumRow            ,model.getMaximumRow());
                    v.put(ColumnName.Land.MaximumFamilyPerRow   ,model.getMaximumFamilyPerRow());
                    v.put(ColumnName.Land.MaximumClonePerFamily ,model.getMaximumClonePerFamily());

                    ContentResolver cr = context.getContentResolver();
                    cr.insert(Database.LAND,v);

                    Log.d(TAG, "callback:"+model.getLandID());
                    Log.d(TAG, "callback:"+model.getLandName());
                }
            }
        });
    }
    class LandData{
        public ArrayList<LandDetailModel> LandListData = new ArrayList<>();
    }

    public void
    setAdapter(RecyclerView.Adapter adapter) {
        this.adapter = adapter;
    }

    public ArrayList<BreederContent.BreederItem>
    getBreederListData(){
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

    public void
    notifyAdapter(){
            adapter.notifyDataSetChanged();
    }

    public void
    notifyAdapterInsert(int count){
        adapter.notifyItemRangeInserted(0,count);

    }
    class FriendListData{
        public ArrayList<BreederContent.BreederItem> FriendListData= new ArrayList<>();
    }

    public ArrayList<SectorContent.SectorItem>
    getSectorListData(){
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

    public  void
    getSentCloneData(){
        final ContentResolver contentResolver = context.getContentResolver();
        String sentCloneURL = "https://api.myjson.com/bins/1ha0h";
        aq.transformer(gsonTransformer).ajax(sentCloneURL,null,SentCloneData.class,new AjaxCallback<SentCloneData>(){
            @Override
            public void callback(String url, SentCloneData object, AjaxStatus status) {
                super.callback(url, object, status);
                Log.d(TAG, "callback: Callback Status : "+status.getMessage());
                if (status.getCode() == 200){
                    Log.d(TAG, "callback: SectorData : ");
                    for (SendFamilyModel model :object.SentCloneData){
                        ContentValues c = new ContentValues();
//                        c.put(ColumnName.SentClone.ObjectID,          model.ObjectID);
                        c.put(ColumnName.SentClone.NameTent,          model.NameTent);
                        c.put(ColumnName.SentClone.SentBy,            model.SentBy);
                        c.put(ColumnName.SentClone.SentTo,            model.SentTo);
                        c.put(ColumnName.SentClone.SentAmount,        model.SentAmount);
                        c.put(ColumnName.SentClone.UserSender,        model.UserSender);
                        c.put(ColumnName.SentClone.createdTime  ,     model.createdTime);
                        c.put(ColumnName.SentClone.updatedTime  ,     model.updatedTime);
                        c.put(ColumnName.SentClone.MotherCode,        model.MotherCode);
                        c.put(ColumnName.SentClone.FatherCode,        model.FatherCode);
                        contentResolver.insert(Database.SENTCLONE,c);
                        Log.d(TAG, "callback: SentCloneData "+c.toString());

                    }
                }
            }
        });

    }
    class SentCloneData{
        public ArrayList<SendFamilyModel> SentCloneData = new ArrayList<>();
    }

    public void
    getReceiveCloneData(){
        final long startTime = System.nanoTime();
        String sentCloneURL = "https://api.myjson.com/bins/1ka45";
        aq.transformer(gsonTransformer).ajax(sentCloneURL,null,ReceiveCloneData.class,new AjaxCallback<ReceiveCloneData>(){
            @Override
            public void callback(String url, ReceiveCloneData object, AjaxStatus status) {
                ContentResolver contentResolver = context.getContentResolver();
                super.callback(url, object, status);
                Log.d(TAG, "callback: Callback Status : "+status.getMessage());
                if (status.getCode() == 200){
                    Log.d(TAG, "callback: SectorData : ");
                    for (ReceiveFamilyModel model :object.ReceiveFamilyList){
                        ContentValues c = new ContentValues();
//                        c.put(ColumnName.SentClone.ObjectID,          model.ObjectID);
                        c.put(ColumnName.ReceivedClone.NameTent,        model.NameTent          );
                        c.put(ColumnName.ReceivedClone.SentBy,          model.SentBy            );
                        c.put(ColumnName.ReceivedClone.ReceivedBy,      model.ReceivedBy        );
                        c.put(ColumnName.ReceivedClone.UserReceiver,    model.UserReceiver      );
                        c.put(ColumnName.ReceivedClone.ReceivedAmount,  model.ReceivedAmount    );
                        c.put(ColumnName.ReceivedClone.createdTime,     model.createdTime       );
                        c.put(ColumnName.ReceivedClone.updatedTime,     model.updatedTime       );
                        c.put(ColumnName.ReceivedClone.MotherCode,      model.MotherCode        );
                        c.put(ColumnName.ReceivedClone.FatherCode,      model.FatherCode        );
                        c.put(ColumnName.ReceivedClone.isPlanted,       model.isPlanted         );
                        c.put(ColumnName.ReceivedClone.PlantedBy,       model.PlantedBy         );
                        c.put(ColumnName.ReceivedClone.PlantedAmount,   model.PlantedAmount     );
                        c.put(ColumnName.ReceivedClone.RowNumber,       model.RowNumber         );
                        c.put(ColumnName.ReceivedClone.OrderInRow,      model.OrderInRow        );
                        c.put(ColumnName.ReceivedClone.PlantedTime,     model.PlantedTime       );
                        c.put(ColumnName.ReceivedClone.LandID,          model.LandID            );
                        c.put(ColumnName.ReceivedClone.SurviveAmount,   model.SurviveAmount     );
                        c.put(ColumnName.ReceivedClone.OrderInRow,      model.OrderInRow        );

                        contentResolver.insert(Database.RECEIVEDCLONE,c);
                        Log.d(TAG, "callback: SentCloneData "+c.toString());

                    }
                }
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                Log.d(TAG, "callback Receive Clone: timeUser "+(duration/1000000)+" ms");
            }
        });
    }

    class ReceiveCloneData{
        public ArrayList<ReceiveFamilyModel> ReceiveFamilyList = new ArrayList<>();
    }

    public void
    getPlantedCloneData(){
        final long startTime = System.nanoTime();
        String url = "https://raw.githubusercontent.com/icanzenith/ClonePlanting2015/master/app/assets/plantedclone.json";
        aq.transformer(gsonTransformer).ajax(url,null,PlantedCloneList.class,new AjaxCallback<PlantedCloneList>(){
            @Override
            public void callback(String url, PlantedCloneList object, AjaxStatus status) {
                ContentResolver contentResolver = context.getContentResolver();
                super.callback(url, object, status);
                Log.d(TAG, "callback: Callback Status : "+status.getMessage());
                if (status.getCode() == 200){
                    Log.d(TAG, "callback: SectorData : ");
                    Log.d(TAG, "callback: Object Size"+object.PlantedCloneList.size());
                    for (PlantedCloneModel model :object.PlantedCloneList){
                        ContentValues c = new ContentValues();
                        c.put(ColumnName.PlantedClone.ObjectID, model.ObjectID);
                        c.put(ColumnName.PlantedClone.CloneCode, model.CloneCode);
                        c.put(ColumnName.PlantedClone.isDead,model.isDead);
                        c.put(ColumnName.PlantedClone.createdTime,model.createdTime);
                        c.put(ColumnName.PlantedClone.updatedTime,model.updatedTime);
                        contentResolver.insert(Database.PLANTEDCLONE,c);
                        Log.d(TAG, "callback: PlantedCloneData "+c.toString());
                    }
                }
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                Log.d(TAG, "callback PlantedClone: timeUse "+(duration/1000000)+" ms");
            }
        });
    }

    class PlantedCloneList {
        public ArrayList<PlantedCloneModel> PlantedCloneList = new ArrayList<>();
    }

    public void
    getActivityData(){
        final ActivityData[] activityData = new ActivityData[1];
        final long startTime = System.nanoTime();
        String url = "https://raw.githubusercontent.com/icanzenith/ClonePlanting2015/master/app/assets/activity.json";
        aq.transformer(gsonTransformer).ajax(url,null,ActivityData.class,new AjaxCallback<ActivityData>(){
            @Override
            public void callback(String url, ActivityData object, AjaxStatus status) {
                super.callback(url, object, status);
                ContentResolver contentResolver = context.getContentResolver();
                Log.d(TAG, "callback: Callback Status : "+status.getMessage());
                if (status.getCode() == 200){
                    Log.d(TAG, "callback: SectorData : ");
                    Log.d(TAG, "callback: Object Size"+object.data.size());
                    for (int i = 0; i < object.data.size();i++){
                        ((NewsFeedRecyclerAdapter)adapter).getData().add(i,object.data.get(i));
                    }

                    SwipeRefreshLayout swipeRefreshLayout = ((NewsFeedRecyclerAdapter) adapter).getSwipeRefreshLayout();
                    swipeRefreshLayout.setRefreshing(false);
                    notifyAdapterInsert(object.data.size());
                    ((NewsFeedRecyclerAdapter) adapter).setToToplist();
                }
                long endTime = System.nanoTime();
                long duration = (endTime - startTime);
                Log.d(TAG, "callback PlantedClone: timeUse "+(duration/1000000)+" ms");
            }
        });

    }
}
