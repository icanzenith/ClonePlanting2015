package org.thailandsbc.cloneplanting.baseactivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.thailandsbc.cloneplanting.BaseApplication;
import org.thailandsbc.cloneplanting.LoginActivity;
import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.breedernetwork.BreederNetworkActivity;
import org.thailandsbc.cloneplanting.createland.LandSelectionActivity;
import org.thailandsbc.cloneplanting.database.MySharedPreference;
import org.thailandsbc.cloneplanting.model.UserDataModel;
import org.thailandsbc.cloneplanting.personal.PersonalProfile;
import org.thailandsbc.cloneplanting.setting.SettingsActivity;
import org.thailandsbc.cloneplanting.test.TestActivity;
import org.thailandsbc.cloneplanting.utils.SelectionMode;
import org.thailandsbc.cloneplanting.utils.onFragmentInteractionListener;

public class BaseActivity extends AppCompatActivity implements onFragmentInteractionListener {

    private static final String TAG = "BaseActivity";
    private NavigationView mNavigationView;
    private DrawerLayout mDrawerLayout;
    private BaseApplication baseApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        baseApplication = (BaseApplication) getApplication();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation);
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                final Intent[] intent = new Intent[1];
                switch (menuItem.getItemId()){
                    case R.id.navigation_item_logout:

                        AlertDialog.Builder dialog = new AlertDialog.Builder(BaseActivity.this);
                        dialog.setMessage(getResources().getString(R.string.message_logout));
                        dialog.setPositiveButton(getResources().getString(R.string.logout), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                                MySharedPreference m = new MySharedPreference(BaseActivity.this);
                                m.Logout();
                                finish();
                                intent[0] = new Intent(BaseActivity.this, LoginActivity.class);
                                intent[0].setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent[0]);
                            }
                        });
                        dialog.setNegativeButton(getResources().getString(R.string.Cancel), new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                            }
                        });
                        dialog.show();
                        break;
                    case R.id.navigation_item_2:
                        intent[0] = new Intent(BaseActivity.this, PersonalProfile.class);
                        startActivity(intent[0]);
                        break;

                    case R.id.navigation_item_testmenu:
                        intent[0] =  new Intent(BaseActivity.this, TestActivity.class);
                        startActivity(intent[0]);
                        break;
                    case R.id.navigation_item_land_management:
                        intent[0] = new Intent(BaseActivity.this, LandSelectionActivity.class);
                        intent[0].putExtra(SelectionMode.MODE,SelectionMode.MODE_LANDMANAGEMENT);
                        startActivity(intent[0]);
                        break;
                    case R.id.navigation_item_setting:
                        intent[0] = new Intent(BaseActivity.this, SettingsActivity.class);
                        startActivity(intent[0]);
                        break;
                    case R.id.navigation_item_network:
                        intent[0] = new Intent(BaseActivity.this, BreederNetworkActivity.class);
                        startActivity(intent[0]);
                        break;
                    case R.id.navigation_home:
                        mDrawerLayout.closeDrawers();
                        break;
                }
                return true;


            }
        });

        setNavHeader();
    }

    private void setNavHeader(){
        View v = mNavigationView.getHeaderView(0);
        if (v != null){
            Log.d(TAG, "setNavHeader: V!=null");
        }
        UserDataModel d ;
        ImageView imageViewProfile = (ImageView) v.findViewById(R.id.imageViewProfile);
        TextView textViewName = (TextView) v.findViewById(R.id.textViewName);
        TextView textViewWorkPlaceName = (TextView) v.findViewById(R.id.textViewWorkPlaceFullName);

        d = baseApplication.getUserData();

        try {
            Log.d(TAG, "setNavHeader: "+d.getProfilePictureURL().toString());
            Picasso.with(getApplicationContext()).load(d.getProfilePictureURL()).fit().into(imageViewProfile);
            textViewName.setText(d.getFullName());
            textViewWorkPlaceName.setText(d.getWorkPlaceFullName());
        } catch (Exception e) {
            e.printStackTrace();
        }


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
