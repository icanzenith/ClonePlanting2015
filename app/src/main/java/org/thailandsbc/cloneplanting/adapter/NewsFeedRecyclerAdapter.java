package org.thailandsbc.cloneplanting.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.thailandsbc.cloneplanting.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Icanzenith on 9/1/15 AD.
 */
public class NewsFeedRecyclerAdapter extends RecyclerView.Adapter<NewsFeedRecyclerAdapter.ViewHolder> {

    public NewsFeedRecyclerAdapter() {

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

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewWorkPlaceCode;
        TextView textViewMessage;
        TextView textViewUserFullName;
        TextView textViewWorkPlaceFullName;
        ImageView likeStatus;
        CircleImageView imageViewProfile;
        ImageView location;
        TextView textViewTime;
        TextView textViewDate;
        ImageView imageViewPicPost;

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
}
