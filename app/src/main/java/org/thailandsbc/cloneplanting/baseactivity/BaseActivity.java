package org.thailandsbc.cloneplanting.baseactivity;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.thailandsbc.cloneplanting.LoginActivity;
import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.createland.LandSelectionActivity;
import org.thailandsbc.cloneplanting.database.MySharedPreference;
import org.thailandsbc.cloneplanting.personal.PersonalProfile;
import org.thailandsbc.cloneplanting.setting.SettingsActivity;
import org.thailandsbc.cloneplanting.test.TestActivity;
import org.thailandsbc.cloneplanting.utils.SelectionMode;
import org.thailandsbc.cloneplanting.utils.onFragmentInteractionListener;

public class BaseActivity extends AppCompatActivity implements onFragmentInteractionListener {
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mNavigationView = (NavigationView) findViewById(R.id.navigation);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Intent intent;
                switch (menuItem.getItemId()){
                    case R.id.navigation_item_logout:
                        MySharedPreference m = new MySharedPreference(BaseActivity.this);
                        m.Logout();
                        finish();
                        intent = new Intent(BaseActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        break;
                    case R.id.navigation_item_2:
                        intent = new Intent(BaseActivity.this, PersonalProfile.class);
                        startActivity(intent);
                        break;

                    case R.id.navigation_item_testmenu:
                        intent=  new Intent(BaseActivity.this, TestActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.navigation_item_land_management:
                        intent = new Intent(BaseActivity.this, LandSelectionActivity.class);
                        intent.putExtra(SelectionMode.MODE,SelectionMode.MODE_LANDMANAGEMENT);
                        startActivity(intent);
                        break;
                    case R.id.navigation_item_setting:
                        intent = new Intent(BaseActivity.this, SettingsActivity.class);
                        startActivity(intent);
                        break;
                }
                return true;
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_base, menu);
        return false;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onFragmentInteraction(String TAG, Object object) {

    }
}
