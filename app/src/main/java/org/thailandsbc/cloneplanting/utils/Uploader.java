package org.thailandsbc.cloneplanting.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;
import org.json.JSONObject;
import org.thailandsbc.cloneplanting.database.Database;
import org.thailandsbc.cloneplanting.model.ActivityData;
import org.thailandsbc.cloneplanting.model.ColumnName;
import org.thailandsbc.cloneplanting.model.LandDetailModel;
import org.thailandsbc.cloneplanting.model.SendFamilyModel;
import org.thailandsbc.cloneplanting.planting.PlantedCloneModel;
import org.thailandsbc.cloneplanting.receive.ReceiveFamilyModel;
import org.thailandsbc.cloneplanting.test.TestActivity;

import java.util.HashMap;
import java.util.Objects;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Icanzenith on 1/17/2016 AD.
 */
public class Uploader {

    private static final String TAG ="Uploader";

    public static final int UPLOADED = 1;
    public static final int NOT_UPLOADED = 0;

    private Context context;
    private AQuery aq;
    private GsonTransformer gsonTransformer = new GsonTransformer();
    private RecyclerView.Adapter adapter;

    public Uploader(Context context) {
        this.context = context;
        aq = new AQuery(context);
    }

    public void
    uploadReceiveClone(final ReceiveFamilyModel data){
        //TODO Change URL Name
        String uploadURL = "";
        HashMap<String,Object> params = new HashMap();
        params.put(ColumnName.ReceivedClone.ObjectID,       data.ObjectID      );
        params.put(ColumnName.ReceivedClone.NameTent,       data.NameTent      );
        params.put(ColumnName.ReceivedClone.SentBy,         data.SentBy        );
        params.put(ColumnName.ReceivedClone.ReceivedBy,     data.ReceivedBy    );
        params.put(ColumnName.ReceivedClone.UserReceiver,   data.UserReceiver  );
        params.put(ColumnName.ReceivedClone.ReceivedAmount, data.ReceivedAmount);
        params.put(ColumnName.ReceivedClone.createdTime,    data.createdTime   );
        params.put(ColumnName.ReceivedClone.updatedTime,    data.updatedTime   );
        params.put(ColumnName.ReceivedClone.MotherCode,     data.MotherCode    );
        params.put(ColumnName.ReceivedClone.FatherCode,     data.FatherCode    );
        params.put(ColumnName.ReceivedClone.isPlanted,      data.isPlanted     );
       //TODO Add Left Amount



        aq.transformer(gsonTransformer).ajax(uploadURL,params, JSONObject.class,new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                //TODO CHANGE UPDATE STATUS IF UPDATE COMPLETE
                if (status.getCode() == 200){
                    //network OK
                    ContentResolver cr = context.getContentResolver();
                    ContentValues v = new ContentValues();
                    v.put(ColumnName.ReceivedClone.isUploaded,1);
                    //Put Upload Status
                    String where = ColumnName.ReceivedClone.ObjectID+" = "+data.ObjectID;
                    cr.update(Database.RECEIVEDCLONE,v,where,null);
                }


            }
        });
    }

    public void
    uploadPlantedClone(PlantedCloneModel data){
        String uploadURL = "";
        HashMap<String,String> params = new HashMap();
        aq.transformer(gsonTransformer).ajax(uploadURL,params, JSONObject.class,new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);

            }
        });
    }

    public void
    uploadSentCloneData(SendFamilyModel data, final int number){
        //TODO Change URL
        Log.d(TAG, "uploadSentCloneData: startAQ"+number);
        String uploadURL = "https://raw.githubusercontent.com/icanzenith/jsonrawtest/master/sentcloneuploadreturn";
        HashMap<String,String> params = new HashMap();
        aq.transformer(gsonTransformer).ajax(uploadURL,null, JSONObject.class,new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                Log.d(TAG, "uploadData Callback: "+status.getMessage());
                try {
                    Log.d(TAG, "uploadData Callback: "+object.getInt("id"));
                    Log.d(TAG, "uploadSentCloneData: stopAQ"+number);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void
    uploadAllSentCloneData(){

        Thread uploadThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int i =0;
                //TODO Change URL
                String url = "https://raw.githubusercontent.com/icanzenith/jsonrawtest/master/sentcloneuploadreturn";
                ContentResolver c = context.getContentResolver();
                //TODO Select Selection unupdate status;
                String selection = null;
                String[] selectionArgs = null;
                final int finalI = i;

                AjaxCallback<JSONObject> callback = new AjaxCallback<JSONObject>(){


                    @Override
                    public void callback(String url, JSONObject object, AjaxStatus status) {
                        super.callback(url, object, status);
                        Log.d(TAG, "callback: "+ finalI +status.getMessage());
                        Log.d(TAG, "callback: "+object.toString());

                    }
                };

                //TODO Fix Params
                callback.url(url).type(JSONObject.class);
//                .progress(ProgressDialog.show(context,"โปรดรอ","กำลัง Upload"));

                c.query(Database.SENTCLONE,null,selection,selectionArgs,null);

                //TODO Change to Change = c!=null
                if (true){
                    //TODO Count C
                    for(i = 0 ; i< 10;i++){

                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        callback.params(null);
                        aq.progress(ProgressDialog.show(context,"โปรดรอ","กำลังอัพโหลด")).sync(callback);
                        //TODO Change to Column Count
                        if (i==9){
                            Log.d(TAG, "uploadAllSentCloneData: Finish Uploading");
                        }
                    }

                }
            }
        });

        uploadThread.start();

       
    }

    public void
    uploadPost(ActivityData.PostData data){
        //TODO Change URL
        String uploadURL = "";
        HashMap<String,String> params = new HashMap();

        aq.transformer(gsonTransformer).ajax(uploadURL,params, JSONObject.class,new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);

            }
        });
    }

    public void
    uploadLandData(final LandDetailModel data){
        final ProgressDialog dialog = ProgressDialog.show(context,"กำลังสร้างแปลง","กำลงส่งข้อมูล");
        //TODO Change URL
        String uploadURL = "https://raw.githubusercontent.com/icanzenith/jsonrawtest/master/returnlandid.json";
        HashMap<String,Object> s = new HashMap();
        //TODO FixObject ID
        //LandID ควรสร้าง ได้ก่อน
//        s.put(ColumnName.Land.LandID                ,data.LandID                );
        s.put(ColumnName.Land.LandName              ,data.LandName              );
        s.put(ColumnName.Land.LandLength            ,data.LandLength            );
        s.put(ColumnName.Land.LandWidth             ,data.LandWidth             );
        s.put(ColumnName.Land.LandArea              ,data.LandArea              );
        s.put(ColumnName.Land.UserCreate            ,data.UserCreate            );
        s.put(ColumnName.Land.Address               ,data.Address               );
        s.put(ColumnName.Land.Sector                ,data.Sector                );
        s.put(ColumnName.Land.Latitude              ,data.Latitude              );
        s.put(ColumnName.Land.Longitude             ,data.Longitude             );
        s.put(ColumnName.Land.YearCrossing          ,data.YearCrossing          );
        s.put(ColumnName.Land.SugarcaneSelectionType,data.SugarcaneSelectionType);
        s.put(ColumnName.Land.createdTime           ,data.createdTime           );
        s.put(ColumnName.Land.updatedTime           ,data.updatedTime           );
        //TODO add params in URL
        aq.transformer(gsonTransformer).ajax(uploadURL,null, JSONObject.class,new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                if (status.getCode()==200){
                    try {
                        if (object.getInt("status")==200){
                            ContentResolver cr = context.getContentResolver();
                            ContentValues v = new ContentValues();
                            v.put(ColumnName.Land.LandID                ,object.getInt("id")               );
                            v.put(ColumnName.Land.LandName              ,data.LandName              );
                            v.put(ColumnName.Land.LandLength            ,data.LandLength            );
                            v.put(ColumnName.Land.LandWidth             ,data.LandWidth             );
                            v.put(ColumnName.Land.LandArea              ,data.LandArea              );
                            v.put(ColumnName.Land.UserCreate            ,data.UserCreate            );
                            v.put(ColumnName.Land.Address               ,data.Address               );
                            v.put(ColumnName.Land.Sector                ,data.Sector                );
                            v.put(ColumnName.Land.Latitude              ,data.Latitude              );
                            v.put(ColumnName.Land.Longitude             ,data.Longitude             );
                            v.put(ColumnName.Land.YearCrossing          ,data.YearCrossing          );
                            v.put(ColumnName.Land.SugarcaneSelectionType,data.SugarcaneSelectionType);
                            v.put(ColumnName.Land.createdTime           ,data.createdTime           );
                            v.put(ColumnName.Land.updatedTime           ,data.updatedTime           );
                            v.put(ColumnName.Land.MaximumRow            ,data.MaximumRow            );
                            v.put(ColumnName.Land.MaximumClonePerFamily ,data.MaximumClonePerFamily );
                            v.put(ColumnName.Land.MaximumFamilyPerRow   ,data.MaximumFamilyPerRow   );
                            cr.insert(Database.LAND,v);
                            Log.d(TAG, "callback: "+v.toString());
                            dialog.dismiss();
                        }else{
                            Toast.makeText(context,"Cannot Create Land Please Check you Internet",Toast.LENGTH_LONG).show();

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(context,"มีบางอย่างผิดปกติเกิดขึ้น",Toast.LENGTH_LONG).show();
                    }

                }
            }
        });
    }


    ProgressDialog progressDialog ;

    public void
    uploadAllReceiveCloneData(){
        progressDialog = new ProgressDialog(context,ProgressDialog.THEME_DEVICE_DEFAULT_DARK);
        Thread uploadThread = new Thread(new Runnable() {
            @Override
            public void run() {
                Activity a = (Activity)context;
                a.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.show();
                    }
                });
                final int[] i = {1};
                int count = 0;
                final int[] current = {0};
                //TODO Change URL
                String url = "https://raw.githubusercontent.com/icanzenith/jsonrawtest/master/sentcloneuploadreturn";
                ContentResolver cr = context.getContentResolver();
                //TODO Select Selection unupdate status;
                String selection = null;
                String[] selectionArgs = null;
                final int finalI = i[0];
                final int finalCount;

                Cursor c = cr.query(Database.RECEIVEDCLONE,null,selection,selectionArgs,null);
                ContentValues v = new ContentValues();
                count = c.getCount();
                finalCount = count;

                AjaxCallback<JSONObject> callback = new AjaxCallback<JSONObject>(){


                    @Override
                    public void callback(String url, JSONObject object, AjaxStatus status) {
                        super.callback(url, object, status);
                        current[0]++;
                        Log.d(TAG, "callback: "+ finalI +status.getMessage());
                        Log.d(TAG, "callback: "+object.toString());
                        progressDialog.setMessage("Uploading "+current[0]+"/"+finalCount);
                        if (current[0] == finalCount){
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public AjaxCallback<JSONObject> param(String name, Object value) {
                        return super.param(name, value);
                    }
                };

                //TODO Fix Params
                callback.url(url).type(JSONObject.class);
//                .progress(ProgressDialog.show(context,"โปรดรอ","กำลัง Upload"));

                //TODO Change to Change = c!=null
                if (c!=null){

                    //TODO Count C
                    while(c.moveToNext()){
                        DatabaseUtils.cursorRowToContentValues(c,v);
                        HashMap<String,Object> p = new HashMap<>();
                        for (String key : v.keySet()){
                            p.put(key,v.get(key));
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        callback.params(null);
                        Log.d(TAG, "Upload All Receive: { Position :"+c.getPosition()+" "+p.get(ColumnName.ReceivedClone.NameTent)+"}");
                        aq.sync(callback);
                    }
//                    for(i = 0 ; i< 10;i++){
//
//                        try {
//                            Thread.sleep(500);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        callback.params(null);
//                        aq.progress(ProgressDialog.show(context,"โปรดรอ","กำลังอัพโหลด")).sync(callback);
//                        //TODO Change to Column Count
//                        if (i==9){
//                            Log.d(TAG, "uploadAllSentCloneData: Finish Uploading");
//                        }
//                    }

                }
            }
        });

        uploadThread.start();
    }

    public void
    DiaglogShow(Thread thread){

    }
}
