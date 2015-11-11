package org.thailandsbc.cloneplanting;

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

import org.json.JSONObject;
import org.thailandsbc.cloneplanting.baseactivity.BaseActivity;
import org.thailandsbc.cloneplanting.database.MySharedPreference;
import org.thailandsbc.cloneplanting.model.UserDataModel;
import org.thailandsbc.cloneplanting.model.userLoginModel;
import org.thailandsbc.cloneplanting.utils.GsonTransformer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG_USERDATA = "UserData";
    private Button buttonLogin;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private UserDataModel userDataModel;
    private MySharedPreference m;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        m = new MySharedPreference(this);
        if (m.isLoggedIn()) {
            Intent loginIntent = new Intent(LoginActivity.this, BaseActivity.class);
            userDataModel=m.getUserData();
            loginIntent.putExtra(TAG_USERDATA, userDataModel.createSample());
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


    @Override
    public void onClick(View v) {

        doLogin();
    }


    private AQuery aq;

    private void doLogin() {
        String name = "http://210.1.60.178:6111/index.php/service/login1stcloneplanting";
        aq = new AQuery(getApplication());
        Map<String, String> params = new HashMap();
        params.put("Username", editTextUsername.getText().toString());
        params.put("Password", editTextPassword.getText().toString());
        aq.transformer(new GsonTransformer()).ajax(name, params, userLoginModel.class, new AjaxCallback<userLoginModel>() {
            @Override
            public void callback(String url, userLoginModel object, AjaxStatus status) {
                super.callback(url, object, status);

                if (status.getCode() == 200) {
                    CreateSessionLogin(object);
                    Log.d("Callback", status.getCode() + "");
                    Log.d("Callback", status.getMessage().toString());
                    Log.d("Callback", object.toString());
                } else {
                    Toast.makeText(getApplication(), "ไม่สามารถ Login ได้", Toast.LENGTH_LONG).show();
                    //TODO
                    CreateSessionLogin(null);
                }
            }


        });

    }

    private void CreateSessionLogin(userLoginModel object) {


        UserDataModel data = new UserDataModel();
//        Log.d("object",object.getName()+"");
//        data.setUserCreate(object.getUserCreate());
//        data.setFullName(object.getName());
//        data.setWorkPlaceCode(object.getSector());
//        data.setProfilePictureURL(object.getPicURL());
        //TODO FIX THIS after Login URL Complete
//        m.CreatedSessionLogin(data);
        m.CreatedSessionLogin(data.createSample());
        Intent loginIntent = new Intent(LoginActivity.this, BaseActivity.class);
        loginIntent.putExtra(TAG_USERDATA, object);
        startActivity(loginIntent);
        finish();

    }
}
