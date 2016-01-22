package org.thailandsbc.cloneplanting.baseactivity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Downloader;

import org.thailandsbc.cloneplanting.BaseApplication;
import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.adapter.NewsFeedRecyclerAdapter;
import org.thailandsbc.cloneplanting.dialog.HomeMenuDialog;
import org.thailandsbc.cloneplanting.model.ActivityData;
import org.thailandsbc.cloneplanting.model.NewsFeedModel;
import org.thailandsbc.cloneplanting.model.UserDataModel;
import org.thailandsbc.cloneplanting.utils.DataLoader;
import org.thailandsbc.cloneplanting.utils.onFragmentInteractionListener;

import java.util.ArrayList;

public class NewsFeedFragment extends Fragment implements View.OnClickListener{
    private static final String ARG_USERDATA = "UserData";

    private ArrayList<ActivityData.PostData> mNewsFeedData;

    private SwipeRefreshLayout swipeRefreshLayout;

    private UserDataModel mUserData;

    private onFragmentInteractionListener mListener;

    private FloatingActionButton fabHome;
    private Button buttonRefresh;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private NewsFeedRecyclerAdapter mAdapter;

    private ImageView imageViewEmptyState;

    private BaseActivity baseActivity;

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

            baseActivity.getSupportActionBar().setTitle("กิจกรรมรวม");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news_feed, container, false);

        //Sample Create Size 0 mNewsFeedData
        mNewsFeedData = new ArrayList<>();
        InitializationViews(v);

        return v;

    }

    private void InitializationViews(View v) {
        fabHome = (FloatingActionButton) v.findViewById(R.id.fabHome);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        fabHome.setOnClickListener(this);

        buttonRefresh = (Button) v.findViewById(R.id.buttonRefresh);
        buttonRefresh.setOnClickListener(this);
        imageViewEmptyState = (ImageView) v.findViewById(R.id.emptyState);

        if (mNewsFeedData.size()!=0) {
            createRecyclerList();

        }else{
            createAEmptyStateList();
        }

        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.refreshList();
                swipeRefreshLayout.setRefreshing(false);
            }
        });



    }

    private void createAEmptyStateList() {
        recyclerView.setVisibility(View.GONE);
        imageViewEmptyState.setVisibility(View.VISIBLE);

    }

    private void createRecyclerList() {

        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new NewsFeedRecyclerAdapter((BaseApplication) getActivity().getApplication(),mLayoutManager);
        mAdapter.setSwipeRefreshLayout(swipeRefreshLayout);
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
        baseActivity = (BaseActivity) activity;

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

        if (v.getId() == R.id.buttonRefresh){
            recyclerView.setVisibility(View.VISIBLE);
            createRecyclerList();

            //set Empty state Gone
            imageViewEmptyState.setVisibility(View.GONE);
            buttonRefresh.setVisibility(View.INVISIBLE);

        }
    }
}
