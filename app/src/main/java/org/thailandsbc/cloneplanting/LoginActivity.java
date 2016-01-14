package org.thailandsbc.cloneplanting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.google.gson.Gson;

import org.thailandsbc.cloneplanting.baseactivity.BaseActivity;
import org.thailandsbc.cloneplanting.database.MySharedPreference;
import org.thailandsbc.cloneplanting.model.UserDataModel;
import org.thailandsbc.cloneplanting.utils.GsonTransformer;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG_USERDATA = "UserData";
    private Button buttonLogin;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private UserDataModel userDataModel;
    private MySharedPreference m;
    private UserData userdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        m = new MySharedPreference(this);
        if (m.isLoggedIn()) {
            Intent loginIntent = new Intent(LoginActivity.this, BaseActivity.class);
            userDataModel=m.getUserData();
//            loginIntent.putExtra(TAG_USERDATA, userDataModel.createSample());
            startActivity(loginIntent);
            finish();
        }

        InitializeView();

        buttonLogin.setOnClickListener(this);

    }

    private void InitializeView() {
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        editTextUsername = (EditText) findViewById(R.id.editTextUsername);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
    }

    private ProgressDialog progressDialogLogin;
    @Override
    public void onClick(View v) {

        progressDialogLogin = new ProgressDialog(this,0);
        progressDialogLogin.setTitle("Login in...");
        progressDialogLogin.show();
        doLogin();

    }



    AQuery aq;
    static GsonTransformer gsonTransformer = new GsonTransformer();
    private void doLogin() {
//        String name = "http://210.1.60.178:6111/index.php/service/login1stcloneplanting?Username=" +
//                ""+editTextUsername.getText().toString()+
//                "&&Password="+editTextPassword.getText().toString();
        String name = "http://210.1.60.178:6111/index.php/service/login1stcloneplanting?Username=111&Password=111";

        Log.d("Call Back URL",name);

        aq = new AQuery(this);
        Map<String, String> params = new HashMap();
        params.put("Username", editTextUsername.getText().toString());
        params.put("Password", editTextPassword.getText().toString());

        aq.transformer(gsonTransformer).ajax(name, null,UserLoginModel.class, new AjaxCallback<UserLoginModel>() {
            @Override
            public void callback(String url, UserLoginModel object, AjaxStatus status) {
                super.callback(url, object, status);
                if (status.getCode() == 200) {
                    CreateSessionLogin(object.getUserData());
                    Log.d("Callback", status.getCode() + "");
                    Log.d("Callback", status.getMessage());
                    Log.d("Callback UserData", "" + object.getUserData().getName());
                    Log.d("Callback UserData", "" + object.getUserData().getSector());
//                    Log.d("Callback UserData", ""+object.getUserData().getPicURL().toString());
                    Log.d("Callback UserData", ""+object.getUserData().getTel());
                    Log.d("Callback UserData", ""+object.getUserData().getUserID());

                    if (object!=null){
//                        Log.d("Callback UserData", ""+object.getName().toString());
                        Gson gson = new Gson();
                        Log.d("Callback UserData", ""+gson.toJson(object).toString());
                    }

                } else {
                    Toast.makeText(getApplication(), "ไม่สามารถ Login ได้", Toast.LENGTH_LONG).show();
                    CreateSessionLogin(null);

                }
            }


        });


    }

    private void CreateSessionLogin(UserData object) {

        UserDataModel data = new UserDataModel();
        Log.d("object",object.getName()+"");
        data.setUserID(object.getUserID());
        data.setFullName(object.getName());
        data.setWorkPlaceCode(object.getSector());
//        data.setProfilePictureURL(object.getPicURL());
        m.CreatedSessionLogin(data);
//        m.CreatedSessionLogin(data.createSample());
        progressDialogLogin.dismiss();
        Intent loginIntent = new Intent(LoginActivity.this, BaseActivity.class);
        startActivity(loginIntent);
        finish();
    }




    private class UserLoginModel {
        private UserData UserData;

        public LoginActivity.UserData getUserData() {
            return UserData;
        }

        public void setUserData(LoginActivity.UserData userData) {
            UserData = userData;
        }
    }
    class UserData{

        public int UserID;
        public String Name;
        public String Sector;
        public String Tel;
        public String Email;
        public String PicURL;

        public String getPicURL() {
            return PicURL;
        }

        public void setPicURL(String picURL) {
            PicURL = picURL;
        }

        public int getUserID() {
            return UserID;
        }

        public void setUserID(int userID) {
            UserID = userID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getSector() {
            return Sector;
        }

        public void setSector(String sector) {
            Sector = sector;
        }

        public String getTel() {
            return Tel;
        }

        public void setTel(String tel) {
            Tel = tel;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String email) {
            Email = email;
        }


    }
}
