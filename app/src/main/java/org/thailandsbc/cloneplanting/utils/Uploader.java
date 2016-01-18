package org.thailandsbc.cloneplanting.utils;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

import java.util.HashMap;

/**
 * Created by Icanzenith on 1/17/2016 AD.
 */
public class Uploader {

    private static final String TAG ="Uploader";

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
        params.put(ColumnName.ReceivedClone.PlantedBy,      data.PlantedBy     );
        params.put(ColumnName.ReceivedClone.RowNumber,      data.RowNumber     );
        params.put(ColumnName.ReceivedClone.OrderInRow,     data.OrderInRow    );
        params.put(ColumnName.ReceivedClone.PlantedTime,    data.PlantedTime   );
        params.put(ColumnName.ReceivedClone.LandID,         data.LandID        );
        params.put(ColumnName.ReceivedClone.SurviveAmount,  data.SurviveAmount );


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
    uploadSentCloneData(SendFamilyModel data){
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
        //TODO Change URL
        String uploadURL = "";
        HashMap<String,Object> s = new HashMap();
        //TODO FixObject ID
        //LandID ควรสร้าง ได้ก่อน
        s.put(ColumnName.Land.LandID                ,data.LandID                );
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

        aq.transformer(gsonTransformer).ajax(uploadURL,s, JSONObject.class,new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                if (status.getCode()==200){
                    try {
                        if (object.getBoolean("status")){
                            ContentResolver cr = context.getContentResolver();
                            ContentValues v = new ContentValues();
                            v.put(ColumnName.Land.LandID                ,object.getInt("landid")               );
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
                            cr.insert(Database.LAND,v);
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



}
