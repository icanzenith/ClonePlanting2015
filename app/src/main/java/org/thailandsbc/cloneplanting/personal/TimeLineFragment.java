package org.thailandsbc.cloneplanting.personal;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.thailandsbc.cloneplanting.R;

/**
 * Created by Icanzenith on 11/16/2015 AD.
 */
public class TimeLineFragment extends Fragment{


    public TimeLineFragment() {

    }

    public static Fragment newInstance(){
        Fragment timeLineFragment = new TimeLineFragment();
        Bundle bundle = new Bundle();
        bundle.putString("PageTitle","TimeLine");
        timeLineFragment.setArguments(bundle);
        return timeLineFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_timeline, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
