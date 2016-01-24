package com.codevictory.testserver;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.callback.Transformer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    AQuery aQuery;
    GsonTransformer gson;
    AjaxCallback<JSONObject> ajaxCallback;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.textViewCallback);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        aQuery = new AQuery(getApplicationContext());
        gson = new GsonTransformer();
        ajaxCallback  = new AjaxCallback<JSONObject>(){
            @Override
            public void callback(String url, JSONObject object, AjaxStatus status) {
                super.callback(url, object, status);
                textView.setText(object.toString());
            }
        };
    }

    @Override
    public void onClick(View v) {
        HashMap<String,Object> params;
        String Url;
            switch (v.getId()){
                case R.id.buttonUploadLand:
                    params = new HashMap<>();
                    //ถ้า id = null ให้ insert ใน Database
                    params.put("id",null);
                    params.put("LandName","ทดสอบ");
                    params.put("LandLength",45);
                    params.put("LandWidth",45);
                    params.put("LandArea",1.2);
                    params.put("UserCreate",2);
                    params.put("Address","แปลงทดสอบ ภาควิชาเกษตรกลวิธาน มหาวทิยาลัยเกษตรศาสตร์ วิทยาเขตกำแพงแสน");
                    params.put("Sector","A");
                    params.put("Latitude",14.015881);
                    params.put("Longitude",99.982216);
                    params.put("YearCrossing",2015);
                    params.put("SugarcaneSelectionType",1);
                    params.put("createdTime",getTimeUTC());
                    params.put("updatedTime",getTimeUTC());
                    //
                    aQuery.transformer(gson);
                    //TODO Chagne URL HERE

                    Url = "https://raw.githubusercontent.com/icanzenith/jsonrawtest/982d4e3abe17598543e4765d2e532244e9794747/sentcloneuploadreturn";
                    ajaxCallback.url(Url).
                            //TODO After Service Finish dont forger to Add Parameter from null to params
                            params(null).
                            type(JSONObject.class);
                    aQuery.ajax(ajaxCallback);
                    break;



                case R.id.buttonUploadSentClone:

                    params = new HashMap<>();
                    //ถ้า id = null ให้ insert ใน Database
                    params.put("id",null);
                    params.put("NameTent","A14002");
                    params.put("SentBy","A");
                    params.put("SentTo","C");
                    params.put("SentAmount","60");
                    params.put("UserSender",2);
                    params.put("createdTime",getTimeUTC());
                    params.put("updatedTime",getTimeUTC());
                    params.put("MotherCode","OP");
                    params.put("FatherCode","KK3");
                    //
                    aQuery.transformer(gson);
                    //TODO Chagne URL HERE
                    Url = "https://raw.githubusercontent.com/icanzenith/jsonrawtest/982d4e3abe17598543e4765d2e532244e9794747/sentcloneuploadreturn";
                    ajaxCallback.url(Url).
                            //TODO After Service Finish dont forger to Add Parameter from null to params
                                    params(null).
                            type(JSONObject.class);
                    aQuery.ajax(ajaxCallback);
                    break;

                case R.id.buttonUploadReceiveClone:

                    params = new HashMap<>();
                    //ถ้า id = null ให้ insert ใน Database
                    params.put("id",null);
                    params.put("NameTent","A14001");
                    params.put("SentBy","A");
                    params.put("ReceivedBy","C");
                    params.put("UserReceiver",13);
                    params.put("ReceivedAmount",60);
                    params.put("createdTime",getTimeUTC());
                    params.put("updatedTime",getTimeUTC());
                    params.put("MotherCode","OP");
                    params.put("FatherCode","LK21-012");
                    params.put("PlantedAmount",20);
                    params.put("LeftAmount",40);

                    //
                    aQuery.transformer(gson);
                    //TODO Chagne URL HERE
                    Url = "https://raw.githubusercontent.com/icanzenith/jsonrawtest/982d4e3abe17598543e4765d2e532244e9794747/sentcloneuploadreturn";
                    ajaxCallback.url(Url).
                            //TODO After Service Finish dont forger to Add Parameter from null to params
                                    params(null).
                            type(JSONObject.class);
                    aQuery.ajax(ajaxCallback);

                    break;
                case R.id.buttonUploadPlantedFamily:
                    params = new HashMap<>();
                    //ถ้า id = null ให้ insert ใน Database
                    params.put("id",13);
                    params.put("ReceivedCloneID",12);
                    params.put("NameTent","A14001");
                    params.put("LandID",1);
                    params.put("RowNumber",2);
                    params.put("OrderInRow",2);
                    params.put("PlantedBy",13);
                    params.put("PlantedAmount",20);
                    params.put("SurviveAmount",20);
                    params.put("updatedTime",getTimeUTC());
                    params.put("createdTime",getTimeUTC());
                    //
                    aQuery.transformer(gson);
                    //TODO Chagne URL HERE
                    Url = "https://raw.githubusercontent.com/icanzenith/jsonrawtest/982d4e3abe17598543e4765d2e532244e9794747/sentcloneuploadreturn";
                    ajaxCallback.url(Url).
                            //TODO After Service Finish dont forger to Add Parameter from null to params
                                    params(null).
                            type(JSONObject.class);
                    aQuery.ajax(ajaxCallback);



                    break;
                case R.id.buttonUploadPlantedClone:
                    params = new HashMap<>();
                    //ถ้า id = null ให้ insert ใน Database
                    params.put("id",13);
                    params.put("ReceivedCloneID",12);
                    params.put("NameTent","A14001");
                    params.put("LandID",1);
                    params.put("RowNumber",2);
                    params.put("OrderInRow",2);
                    params.put("PlantedBy",13);
                    params.put("PlantedAmount",20);
                    params.put("SurviveAmount",20);
                    params.put("updatedTime",getTimeUTC());
                    params.put("createdTime",getTimeUTC());
                    //
                    aQuery.transformer(gson);
                    //TODO Chagne URL HERE
                    Url = "https://raw.githubusercontent.com/icanzenith/jsonrawtest/982d4e3abe17598543e4765d2e532244e9794747/sentcloneuploadreturn";
                    ajaxCallback.url(Url).
                            //TODO After Service Finish dont forger to Add Parameter from null to params
                                    params(null).
                            type(JSONObject.class);
                    aQuery.ajax(ajaxCallback);



                    break;
                case R.id.buttonUploadPost:
                    ArrayList<File> picture = new ArrayList<>();
                    //อันนี้ถ้าไม่ได้ เก็บไว้ทีหลังก็ได้
                    params = new HashMap<>();
                    params.put("message","วันนี้มาคัดเลือกพันธุ์อ้อยที่กำแพงแสน อากาศดีมากครับ");
                    params.put("from",1);
                    params.put("pictures",picture);
                    params.put("createdTime",getTimeUTC());
                    params.put("upldatedTime",getTimeUTC());
                    aQuery.transformer(gson);
                    //TODO Chagne URL HERE
                    Url = "https://raw.githubusercontent.com/icanzenith/jsonrawtest/982d4e3abe17598543e4765d2e532244e9794747/sentcloneuploadreturn";
                    ajaxCallback.url(Url).
                            //TODO After Service Finish dont forger to Add Parameter from null to params
                                    params(null).
                            type(JSONObject.class);
                    aQuery.ajax(ajaxCallback);
                    break;

                case R.id.buttonComment:
                    params = new HashMap<>();
                    params.put("postID",122);
                    params.put("author",13);
                    params.put("message","คราวหน้าผมไปด้วยนะครับ");
                    params.put("createdTime",getTimeUTC());
                    params.put("updatedTime",getTimeUTC());
                    aQuery.transformer(gson);
                    //TODO Chagne URL HERE
                    Url = "https://raw.githubusercontent.com/icanzenith/jsonrawtest/982d4e3abe17598543e4765d2e532244e9794747/sentcloneuploadreturn";
                    ajaxCallback.url(Url).
                            //TODO After Service Finish dont forger to Add Parameter from null to params
                                    params(null).
                            type(JSONObject.class);
                    aQuery.ajax(ajaxCallback);
                    break;

                case R.id.buttonLike:
                    params = new HashMap<>();
                    params.put("postID",122);
                    params.put("liker",13);
                    params.put("createdTime",getTimeUTC());
                    params.put("updatedTime",getTimeUTC());
                    aQuery.transformer(gson);
                    //TODO Chagne URL HERE
                    Url = "https://raw.githubusercontent.com/icanzenith/jsonrawtest/982d4e3abe17598543e4765d2e532244e9794747/sentcloneuploadreturn";
                    ajaxCallback.url(Url).
                            //TODO After Service Finish dont forger to Add Parameter from null to params
                                    params(null).
                            type(JSONObject.class);
                    aQuery.ajax(ajaxCallback);
                    break;



            }
    }

    public String getTimeUTC(){
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        //TODO Check this is Formal Format of Time UTC
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-DD\'T\'hh:mm:ssZ");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        String dateUTC = sdf.format(new Date(calendar.getTimeInMillis()));
        return dateUTC;
    }

    class GsonTransformer implements Transformer{

        @Override
        public <T> T transform(String s, Class<T> aClass, String s1, byte[] bytes, AjaxStatus ajaxStatus) {
            T t =  null;
            Gson g = new Gson();
            try {
                t = g.fromJson(new String(bytes), aClass);

            }catch (Exception e){
                e.printStackTrace();

            }
            return t;
        }
    }
}
