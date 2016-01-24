package org.thailandsbc.cloneplanting.adapter;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.thailandsbc.cloneplanting.BaseApplication;
import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.model.ActivityData;
import org.thailandsbc.cloneplanting.utils.DataLoader;
import org.thailandsbc.cloneplanting.utils.WorkPlaceData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Icanzenith on 9/1/15 AD.
 */
public class NewsFeedRecyclerAdapter extends RecyclerView.Adapter<NewsFeedRecyclerAdapter.ViewHolder> {

    private ArrayList<ActivityData.PostData> data;
    private BaseApplication baseApplication;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DataLoader d;
    private RecyclerView.LayoutManager manager;

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

    public void setSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
    }

    public NewsFeedRecyclerAdapter(BaseApplication application, RecyclerView.LayoutManager manager) {

        data = new ArrayList<>();
        d = new DataLoader(application);
        d.setAdapter(this);
        d.getActivityData();
        baseApplication= application;
        this.manager = manager;
    }

    public void setToToplist(){
        manager.scrollToPosition(0);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_newsfeed_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        v.setTag(viewHolder);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mIem = data.get(position);
        holder.textViewUserFullName.setText(data.get(position).from.name);
        Picasso.with(baseApplication).load(holder.mIem.from.pictureURL).fit().into(holder.imageViewProfile);
        holder.textViewWorkPlaceCode.setText(data.get(position).from.workplaceCode);
        if (data.get(position).picture.get(0).url!=null){
            Picasso.with(baseApplication).
                    load(data.get(position).picture.get(0).url).
                    fit().
                    into(
                    holder.imageViewPicPost
            );
        }
        holder.textViewWorkPlaceFullName.setText(
                WorkPlaceData.PLACE_CODE.get(
                        data.get(position).from.workplaceCode));
        holder.textViewMessage.setText(data.get(position).from.message);
        holder.textViewDate.setText(baseApplication.getTimeUTC());

        for (int i = 0;i <  data.get(position).liker.size();i++){
            //TODO get MyID
            int myId = baseApplication.getUserData().getUserID();
            if (myId == data.get(position).liker.get(i).id){
                //I am like this post
                //TODO Change like Color
            }
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void refreshList() {
        d.getActivityData();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textViewWorkPlaceCode;
        public final TextView textViewMessage;
        public final TextView textViewUserFullName;
        public final TextView textViewWorkPlaceFullName;
        public final ImageView likeStatus;
        public final CircleImageView imageViewProfile;
        public final ImageView location;
        public final TextView textViewTime;
        public final TextView textViewDate;
        public final ImageView imageViewPicPost;
        public ActivityData.PostData mIem;

        public ViewHolder(View v) {
            super(v);

            imageViewProfile = (CircleImageView) v.findViewById(R.id.imageViewProfile);
            textViewWorkPlaceCode = (TextView) v.findViewById(R.id.textViewWorkPlaceCode);
            likeStatus = (ImageView) v.findViewById(R.id.likeStatus);
            location = (ImageView) v.findViewById(R.id.location);
            textViewTime = (TextView) v.findViewById(R.id.textViewTime);
            textViewDate = (TextView) v.findViewById(R.id.textViewDate);
            imageViewPicPost = (ImageView) v.findViewById(R.id.imageViewPicPost);
            textViewMessage = (TextView) v.findViewById(R.id.textViewMessage);
            textViewWorkPlaceFullName = (TextView) v.findViewById(R.id.textViewWorkPlaceFullName);
            textViewUserFullName = (TextView) v.findViewById(R.id.textViewUserFullName);

        }


    }

    public ArrayList<ActivityData.PostData> getData() {
        return data;
    }

    public void setData(ArrayList<ActivityData.PostData> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public String createTimeFormat(String dateUTC){
        String time = "noTime";
        //Format Jan 10,2016
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM dd,yyyy");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        try {
            Date myDate = simpleDateFormat.parse(dateUTC);
            time = myDate.toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return time;
    }


}
