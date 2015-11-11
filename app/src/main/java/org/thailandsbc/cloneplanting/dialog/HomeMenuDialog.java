package org.thailandsbc.cloneplanting.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.thailandsbc.cloneplanting.createland.LandSelectionActivity;
import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.SendActivity;
import org.thailandsbc.cloneplanting.receive.ReceiveActivity;
import org.thailandsbc.cloneplanting.test.testAddData;
import org.thailandsbc.cloneplanting.utils.SelectionMode;

/**
 * Created by Icanzenith on 8/31/15 AD.
 */

public class HomeMenuDialog extends DialogFragment implements View.OnClickListener {

    int mNum;

    public static HomeMenuDialog newInstance(int num) {
        HomeMenuDialog f = new HomeMenuDialog();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("num", num);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNum = getArguments().getInt("num");

        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    private Button buttonText;
    private Button buttonPic;
    private Button buttonCheck;
    private Button buttonPlant;
    private Button buttonReceive;
    private Button buttonSend;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_menu_dialog, container, false);
        buttonText      = (Button) v.findViewById(R.id.buttonText);
        buttonPic       = (Button) v.findViewById(R.id.buttonPic);
        buttonCheck     = (Button) v.findViewById(R.id.buttonCheck);
        buttonPlant     = (Button) v.findViewById(R.id.buttonPlant);
        buttonReceive   = (Button) v.findViewById(R.id.buttonReceive);
        buttonSend      = (Button) v.findViewById(R.id.buttonSend);

        buttonText.setOnClickListener(this);
        buttonPic.setOnClickListener(this);
        buttonCheck.setOnClickListener(this);
        buttonPlant.setOnClickListener(this);
        buttonReceive.setOnClickListener(this);
        buttonSend.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.buttonSend:
                intent = new Intent(getActivity(), SendActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonReceive:
                intent = new Intent(getActivity(), ReceiveActivity.class);
                startActivity(intent);
                break;
            case R.id.buttonPlant:
                intent = new Intent(getActivity(), LandSelectionActivity.class);
                intent.putExtra(SelectionMode.MODE, SelectionMode.MODE_PLANT_CLONE);
                startActivity(intent);
                break;
            case R.id.buttonText:
                intent = new Intent(getActivity(),testAddData.class);
                startActivity(intent);
                break;
        }
    }
}
