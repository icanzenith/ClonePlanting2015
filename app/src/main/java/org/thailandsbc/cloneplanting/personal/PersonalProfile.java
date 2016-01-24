package org.thailandsbc.cloneplanting.personal;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.thailandsbc.cloneplanting.BaseApplication;
import org.thailandsbc.cloneplanting.R;

public class PersonalProfile extends AppCompatActivity {

    private BaseApplication baseApplication;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private ImageView imageViewProfile;
    private TextView textViewName;
    private ImageView imageViewCover;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        baseApplication = (BaseApplication) getApplication();

        setContentView(R.layout.activity_personal_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
        initialize();
        setProfile();
    }

    private void initialize(){
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        PersonalProfilePagerAdapter mAdapter = new PersonalProfilePagerAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);

        imageViewCover = (ImageView) findViewById(R.id.imageCover);
        imageViewProfile = (ImageView) findViewById(R.id.imageViewProfile);
        textViewName = (TextView) findViewById(R.id.textViewName);

    }

    private void setProfile(){
        Picasso.with(this).load(baseApplication.getUserData().getProfilePictureURL()).into(imageViewProfile);
        //TODO Change URL inter Future
        Picasso.with(this).load("http://lorempixel.com/400/200/").fit().centerCrop().into(imageViewCover);
        textViewName.setText(baseApplication.getUserData().getFullName());
    }


}
