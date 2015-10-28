package org.thailandsbc.cloneplanting.dialog;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.model.SendFamilyModel;
import org.thailandsbc.cloneplanting.utils.SelectionMode;
import org.thailandsbc.cloneplanting.utils.onFragmentInteractionListener;

/**
 * Created by Icanzenith on 10/27/2015 AD.
 */
public class EditDialog extends DialogFragment implements View.OnClickListener{

    private static final String ARG_OBJECT = "Object";
    private static final String ARG_TAG = "TAG";
    private static final String ARG_TITLE = "Title";

    private onFragmentInteractionListener mListener;

    private String TAG;
    private String title;
    private Object Object;

    private EditText editTextAmount;
    private TextView textViewTitle;
    private Button buttonOK;
    private Button buttonCancel;

    public EditDialog() {
    }

    public static EditDialog newInstance(String title,String TAG,Parcelable value) {
        EditDialog editDialog = new EditDialog();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_TITLE,title);
        bundle.putParcelable(ARG_OBJECT,value);
        bundle.putString(ARG_TAG,TAG);
        editDialog.setArguments(bundle);
        return editDialog;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mListener = (onFragmentInteractionListener) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(DialogFragment.STYLE_NO_TITLE, 0);

        if (getArguments()!=null){
            TAG = getArguments().getString(ARG_TAG);
            Object = getArguments().getParcelable(ARG_OBJECT);
            title = getArguments().getString(ARG_TITLE);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_edit_dialog, container, false);
        InitializationViews(v);

        return v;
    }

    private void InitializationViews(View v) {

        textViewTitle = (TextView) v.findViewById(R.id.title);
        editTextAmount = (EditText) v.findViewById(R.id.editTextAmount);
        buttonCancel = (Button) v.findViewById(R.id.buttonCancel);
        buttonOK = (Button) v.findViewById(R.id.buttonOK);

        textViewTitle.setText(title);
        buttonOK.setOnClickListener(this);
        buttonCancel.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        if (v.getId()==R.id.buttonOK){
            if (TAG.equals(SelectionMode.MODE_EDIT_SENT_CLONE)){
                SendFamilyModel item = (SendFamilyModel) Object;
                item.setSendAmount(Integer.parseInt(editTextAmount.getText().toString()));
                mListener.onFragmentInteraction(TAG, Object);
                dismiss();
            }
        }

        if (v.getId() == R.id.buttonCancel){
                dismiss();
        }

    }
}

