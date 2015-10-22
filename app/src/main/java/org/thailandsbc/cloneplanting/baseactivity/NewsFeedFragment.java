package org.thailandsbc.cloneplanting.baseactivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.thailandsbc.cloneplanting.LoginActivity;
import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.adapter.NewsFeedRecyclerAdapter;
import org.thailandsbc.cloneplanting.adapter.ReceiveRecyclerListAdapter;
import org.thailandsbc.cloneplanting.database.MySharedPreference;
import org.thailandsbc.cloneplanting.dialog.HomeMenuDialog;
import org.thailandsbc.cloneplanting.dialog.QrCodeScannerDialog;
import org.thailandsbc.cloneplanting.model.UserDataModel;
import org.thailandsbc.cloneplanting.receive.ReceiveFamilyModel;
import org.thailandsbc.cloneplanting.utils.QRMode;
import org.thailandsbc.cloneplanting.utils.onFragmentInteractionListener;

import java.util.List;

public class NewsFeedFragment extends Fragment implements View.OnClickListener{
    private static final String ARG_USERDATA = "UserData";

    private UserDataModel mUserData;

    private onFragmentInteractionListener mListener;

    private FloatingActionButton fabHome;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private NewsFeedRecyclerAdapter mAdapter;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param userDataModel UserDataModel.
     * @return A new instance of fragment NewsFeedFragment.
     */
    public static NewsFeedFragment newInstance(UserDataModel userDataModel) {
        NewsFeedFragment fragment = new NewsFeedFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_USERDATA, userDataModel);
        fragment.setArguments(args);
        return fragment;
    }

    public NewsFeedFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news_feed, container, false);

        InitializationViews(v);


        return v;

    }

    private void InitializationViews(View v) {
        fabHome = (FloatingActionButton) v.findViewById(R.id.fabHome);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        fabHome.setOnClickListener(this);

        createRecyclerList();

    }
    private void createRecyclerList() {

        //Create Sample DataSet
//        List<ReceiveFamilyModel> dataSet = createDataSet();
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new NewsFeedRecyclerAdapter();
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

    }


    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            mListener = (onFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==fabHome.getId()){
            HomeMenuDialog dialog = HomeMenuDialog.newInstance(2);
            dialog.show(getChildFragmentManager(),"Home Menu");
        }
    }
}
