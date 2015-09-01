package org.thailandsbc.cloneplanting.dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.SendActivity;

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

        // Pick a style based on the num.
        int style = DialogFragment.STYLE_NORMAL, theme = 0;
        switch ((mNum - 1) % 6) {
            case 1:
                style = DialogFragment.STYLE_NO_TITLE;
                break;
            case 2:
                style = DialogFragment.STYLE_NO_FRAME;
                break;
            case 3:
                style = DialogFragment.STYLE_NO_INPUT;
                break;
            case 4:
                style = DialogFragment.STYLE_NORMAL;
                break;
            case 5:
                style = DialogFragment.STYLE_NORMAL;
                break;
            case 6:
                style = DialogFragment.STYLE_NO_TITLE;
                break;
            case 7:
                style = DialogFragment.STYLE_NO_FRAME;
                break;
            case 8:
                style = DialogFragment.STYLE_NORMAL;
                break;
        }
        switch ((mNum - 1) % 6) {
            case 4:
                theme = android.R.style.Theme_Holo;
                break;
            case 5:
                theme = android.R.style.Theme_Holo_Light_Dialog;
                break;
            case 6:
                theme = android.R.style.Theme_Holo_Light;
                break;
            case 7:
                theme = android.R.style.Theme_Holo_Light_Panel;
                break;
            case 8:
                theme = android.R.style.Theme_Holo_Light;
                break;
        }
        setStyle(style, theme);
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

        }
    }
}
