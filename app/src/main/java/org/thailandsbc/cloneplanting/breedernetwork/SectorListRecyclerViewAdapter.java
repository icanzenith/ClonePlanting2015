package org.thailandsbc.cloneplanting.breedernetwork;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.breedernetwork.content.SectorContent;

import java.util.List;

public class SectorListRecyclerViewAdapter extends RecyclerView.Adapter<SectorListRecyclerViewAdapter.ViewHolder> {

    private final List<SectorContent.SectorItem> mValues;
    private final OnListFragmentInteractionListener mListener;
    private final Context mContext;
    public SectorListRecyclerViewAdapter(Context context, List<SectorContent.SectorItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
        mContext = context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.model_workplace_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mTextViewFullName.setText(mValues.get(position).name);
        holder.mTextViewSymbol.setText(mValues.get(position).sectorCode);

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
        public final TextView mTextViewFullName;
        public final TextView mTextViewSymbol;
        public SectorContent.SectorItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTextViewFullName = (TextView) view.findViewById(R.id.textViewFullName);
            mTextViewSymbol = (TextView) view.findViewById(R.id.textViewSymbol);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mTextViewSymbol.getText() + "'";
        }
    }
}
