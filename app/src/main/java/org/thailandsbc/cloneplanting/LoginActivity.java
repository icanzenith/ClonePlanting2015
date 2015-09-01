package org.thailandsbc.cloneplanting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.thailandsbc.cloneplanting.baseactivity.BaseActivity;
import org.thailandsbc.cloneplanting.model.UserDataModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG_USERDATA = "UserData";
    private Button buttonLogin;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private UserDataModel userDataModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

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
        //This is Sample User Data
        //TODO Delete When the test passed
        userDataModel = new UserDataModel();

        Intent loginIntent = new Intent(LoginActivity.this, BaseActivity.class);
        loginIntent.putExtra(TAG_USERDATA, userDataModel.createSample());
        startActivity(loginIntent);
    }
}
