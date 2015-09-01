package org.thailandsbc.cloneplanting.dialog;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.model.WorkPlaceModel;
import org.thailandsbc.cloneplanting.utils.SelectionMode;
import org.thailandsbc.cloneplanting.utils.onFragmentInteractionListener;

/**
 * Created by Icanzenith on 8/31/15 AD.
 */
public class SelectPlaceToSendDialog extends DialogFragment implements AdapterView.OnItemClickListener,View.OnClickListener{

    private ListView mListView;
    private onFragmentInteractionListener mListener;

    public static SelectPlaceToSendDialog newInstance(){
        SelectPlaceToSendDialog sendDialog = new SelectPlaceToSendDialog();
        return sendDialog;
    }

    public SelectPlaceToSendDialog() {
        //Default
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (onFragmentInteractionListener) context;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("เลือกสถานที่ส่ง");
        getDialog().setCancelable(false);
        View v = inflater.inflate(R.layout.fragment_select_place_to_sent_dialog, container, false);

        InitializationViews(v);

        ModelWorkplaceListAdapter mAdapter = new ModelWorkplaceListAdapter(getActivity());
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(this);
        return v;
    }

    private void InitializationViews(View v){
        mListView = (ListView) v.findViewById(R.id.listViewPlaceName);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        WorkPlaceModel model = (WorkPlaceModel) parent.getItemAtPosition(position);
        mListener.onFragmentInteraction(SelectionMode.MODE_SELECT_PLACE,model);
        this.dismiss();
    }


    @Override
    public void onClick(View v) {
        //TODO Create QR Code Dialog
    }
}
