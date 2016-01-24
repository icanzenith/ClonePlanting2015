package org.thailandsbc.cloneplanting.dialog;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.zxing.Result;

import org.thailandsbc.cloneplanting.R;
import org.thailandsbc.cloneplanting.database.Database;
import org.thailandsbc.cloneplanting.model.ColumnName;
import org.thailandsbc.cloneplanting.model.ScannerResultModel;
import org.thailandsbc.cloneplanting.planting.NameTentListActivity;
import org.thailandsbc.cloneplanting.receive.ReceiveFamilyModel;
import org.thailandsbc.cloneplanting.utils.QRMode;
import org.thailandsbc.cloneplanting.utils.Request;
import org.thailandsbc.cloneplanting.utils.onFragmentInteractionListener;

import java.util.ArrayList;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Icanzenith on 8/31/15 AD.
 */
public class QrCodeScannerDialog extends DialogFragment implements ZXingScannerView.ResultHandler, View.OnClickListener {

    private String mFamilyCode;
    private int mAmount;

    private ZXingScannerView mScannerView;
    private EditText editTextAmount;
    private TextView textViewQRCodeResult;
    private TextView textViewScannerTitle;

    private Button buttonFinish;
    private Button buttonCancle;
    private Button buttonOpenList;

    private onFragmentInteractionListener mListener;

    private static String ARG_SCANNER_Mode = "mode";
    private String mScannerMode;

    public QrCodeScannerDialog() {
    }

    public static QrCodeScannerDialog newInstance(String ModeMenu) {
        QrCodeScannerDialog dialog = new QrCodeScannerDialog();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_SCANNER_Mode, ModeMenu);
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
        if (getArguments() != null) {
            mScannerMode = getArguments().getString(ARG_SCANNER_Mode);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_qrcode_dialog, container, false);

        InitializationViews(v);
        setModeTitle(mScannerMode);

        return v;
    }

    private void InitializationViews(View v) {
        editTextAmount = (EditText) v.findViewById(R.id.editTextAmount);
        textViewQRCodeResult = (TextView) v.findViewById(R.id.textViewQRCodeResult);
        mScannerView = (ZXingScannerView) v.findViewById(R.id.scannerView);
        textViewScannerTitle = (TextView) v.findViewById(R.id.textViewScannerTitle);


        buttonFinish = (Button) v.findViewById(R.id.buttonFinish);
        buttonCancle = (Button) v.findViewById(R.id.buttonCancel);
        buttonOpenList = (Button) v.findViewById(R.id.buttonOpenList);
        buttonOpenList.setVisibility(View.INVISIBLE);

        buttonFinish.setOnClickListener(this);
        buttonCancle.setOnClickListener(this);
        buttonOpenList.setOnClickListener(this);
    }

    private void setModeTitle(String ModeMenu) {

        if (ModeMenu.equals(QRMode.MODE_SEND_FAMILY)) {
            textViewScannerTitle.setText(getResources().getString(R.string.qr_scanner_dialog_title_send));
        }

        if (ModeMenu.equals(QRMode.MODE_RECEIVE_FAMILY)) {
            textViewScannerTitle.setText(getResources().getString(R.string.qr_scanner_dialog_title_receive));
        }

        if (ModeMenu.equals(QRMode.MODE_PLANT_FAMILY)) {
            textViewScannerTitle.setText(getResources().getString(R.string.qr_scanner_dialog_title_plant));
            setOpenListButtonOpen();

        }
    }

    private void setOpenListButtonOpen() {
        buttonOpenList.setVisibility(View.VISIBLE);

    }


    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void handleResult(Result result) {
        mFamilyCode = result.getText();
        textViewQRCodeResult.setText(mFamilyCode);
        textViewQRCodeResult.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonFinish:
                onFinishScan();
                break;
            case R.id.buttonCancel:
                getDialog().dismiss();
                break;
            case R.id.buttonOpenList:
                Intent intent = new Intent(getActivity(), NameTentListActivity.class);
                startActivityForResult(intent,Request.SELECT_FROM_LIST);
                break;
        }
    }



    private void onFinishScan() {
        mAmount = Integer.parseInt(editTextAmount.getText().toString());
        if (mScannerMode.equals(QRMode.MODE_PLANT_FAMILY)){
            ReceiveFamilyModel result = new ReceiveFamilyModel();
            result.setNameTent(mFamilyCode);
            result.setPlantedAmount(mAmount);
            mListener.onFragmentInteraction(mScannerMode,result);
        }else {
            ScannerResultModel result = new ScannerResultModel();
            result.setFamilyCode(mFamilyCode);
            result.setAmount(mAmount);
            mListener.onFragmentInteraction(mScannerMode, result);
        }
        dismiss();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Request.SELECT_FROM_LIST){
            mFamilyCode = data.getStringExtra(ColumnName.ReceivedClone.NameTent);
            textViewQRCodeResult.setText(mFamilyCode);
            textViewQRCodeResult.setVisibility(View.VISIBLE);
            ReceiveFamilyModel result = new ReceiveFamilyModel();
            result.setNameTent(mFamilyCode);
            result.setPlantedAmount(mAmount);

        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mScannerView.stopCamera();
    }
}
