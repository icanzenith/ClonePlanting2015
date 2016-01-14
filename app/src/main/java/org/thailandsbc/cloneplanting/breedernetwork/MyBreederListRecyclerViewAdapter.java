package org.thailandsbc.cloneplanting.breedernetwork;

import android.content.Context;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.breedernetwork.content.BreederContent.BreederItem;
import org.thailandsbc.cloneplanting.utils.DataLoader;

import java.util.List;

public class MyBreederListRecyclerViewAdapter extends RecyclerView.Adapter<MyBreederListRecyclerViewAdapter.ViewHolder> {

    private final List<BreederItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final Context mContext;
    public MyBreederListRecyclerViewAdapter(Context context, OnListFragmentInteractionListener listener) {
        mListener = listener;
        mContext = context;
        DataLoader dataLoader = new DataLoader(context);
        dataLoader.setAdapter(MyBreederListRecyclerViewAdapter.this);
        mValues = dataLoader.getBreederListData();
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_breeder_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mName.setText(mValues.get(position).Name);
        Picasso.with(mContext).load(mValues.get(position).PictureURL).fit().centerCrop().into(holder.mImageView);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mName;
        public final ImageView mImageView;
        public BreederItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mName = (TextView) view.findViewById(R.id.textViewBreederName);
            mImageView = (ImageView) view.findViewById(R.id.imageViewBreederProfilePicture);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mName.getText() + "'";
        }
    }
}
