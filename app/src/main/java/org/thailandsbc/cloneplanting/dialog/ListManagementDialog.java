package org.thailandsbc.cloneplanting.dialog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.thailandsbc.cloneplanting.FamilyDetailsActivity;
import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.utils.SelectionMode;
import org.thailandsbc.cloneplanting.utils.onFragmentInteractionListener;

/**
 * Created by Icanzenith on 9/1/15 AD.
 */
public class ListManagementDialog extends DialogFragment implements View.OnClickListener{

    private Button buttonCancle;
    private Button buttonDelete;
    private Button buttonFix;
    private Button buttonOpen;

    private static final String ARG_TAG_OBJECT = "Object";
    private Object object;
    
    private onFragmentInteractionListener mListener;

    public ListManagementDialog() {
    }

    public static ListManagementDialog newInstance(Object object) {
        ListManagementDialog dialog = new ListManagementDialog();
        Bundle bundle = new Bundle();
        try {

            bundle.putParcelable(ARG_TAG_OBJECT, (Parcelable) object);

        } catch (Exception e) {
            e.printStackTrace();
            Log.d("Throw Exeption","Object Must be extends with Parcelable");
        }

        dialog.setArguments(bundle);
        return dialog;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (onFragmentInteractionListener) context;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
        if (getArguments()!=null){
            object = getArguments().getParcelable(ARG_TAG_OBJECT);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_management_dialog, container, false);

        buttonCancle = (Button) v.findViewById(R.id.buttonCancel);
        buttonDelete = (Button) v.findViewById(R.id.buttonDelete);
        buttonFix = (Button) v.findViewById(R.id.buttonFix);
        buttonOpen = (Button) v.findViewById(R.id.buttonOpen);

        buttonDelete.setOnClickListener(this);
        buttonCancle.setOnClickListener(this);
        buttonFix.setOnClickListener(this);
        buttonOpen.setOnClickListener(this);

        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonDelete:
                deleteObject(object);
                break;
            case R.id.buttonCancel:
                cancel();
                break;
            case R.id.buttonOpen:
                openObject(object);
                break;
            case R.id.buttonFix:
                fixObject(object);
                break;
        }
    }

    private void deleteObject(Object object){
        mListener.onFragmentInteraction(SelectionMode.MODE_DELETE_SEND_CLONE,object);
        dismiss();
    }

    private void cancel(){
        dismiss();
    }

    private void openObject(Object object){
        Intent intent = new Intent(getActivity(),FamilyDetailsActivity.class);
        intent.putExtra(ARG_TAG_OBJECT, (Parcelable) object);
        startActivity(intent);

    }

    private void fixObject(Object object){
        //TODO Show Dialog Change

    }

}
