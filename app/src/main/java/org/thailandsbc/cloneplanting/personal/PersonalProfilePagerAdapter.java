package org.thailandsbc.cloneplanting.personal;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Icanzenith on 11/16/2015 AD.
 */
public class PersonalProfilePagerAdapter extends FragmentStatePagerAdapter {

    protected ArrayList<Fragment> array;
    Context context;
    public PersonalProfilePagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
        onCreateVectorFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return array.get(position).getArguments().get("PageTitle").toString();
    }


    @Override
    public Fragment getItem(int position) {
        return array.get(position);
    }



    private void onCreateVectorFragment(){
        array = new ArrayList<>();
        array.add(createTimelineFragment());
        array.add(createLandFragment());
        array.add(createProfileFragment());


    }

    private Fragment createTimelineFragment() {
        return TimeLineFragment.newInstance();
    }

    private Fragment createLandFragment(){
        return LandListFragment.newInstance();
    }

    private Fragment createProfileFragment(){
        return ProfileDataFragment.newInstance(null, null);
    }
}
