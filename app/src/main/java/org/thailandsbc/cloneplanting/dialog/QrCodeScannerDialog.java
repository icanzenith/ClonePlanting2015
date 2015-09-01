package org.thailandsbc.cloneplanting.dialog;

import android.app.Activity;
import android.content.Context;
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
import org.thailandsbc.cloneplanting.model.ScannerResult;
import org.thailandsbc.cloneplanting.utils.QRMode;
import org.thailandsbc.cloneplanting.utils.onFragmentInteractionListener;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by Icanzenith on 8/31/15 AD.
 */
public class QrCodeScannerDialog extends DialogFragment implements ZXingScannerView.ResultHandler ,View.OnClickListener{

    private String mFamilyCode;
    private int mAmount;

    private ZXingScannerView mScannerView;
    private EditText editTextAmount;
    private TextView textViewQRCodeResult;
    private TextView textViewScannerTitle;

    private Button buttonFinish;
    private Button buttonCancle;

    private onFragmentInteractionListener mListener;

    private static String ARG_SCANNER_Mode = "mode";
    private int mScannerMode;

    public QrCodeScannerDialog() {
    }

    public static QrCodeScannerDialog newInstance(int ModeMenu) {
        QrCodeScannerDialog dialog = new QrCodeScannerDialog();
        Bundle bundle = new Bundle();
        bundle.putInt("ARG_SCANNER_Mode", ModeMenu);
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
            mScannerMode = getArguments().getInt(ARG_SCANNER_Mode);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_qrcode_dialog, container, false);

        InitializationViews(v);
        setModeTitle(mScannerMode);

        return v;
    }

    private void InitializationViews(View v){
        editTextAmount = (EditText) v.findViewById(R.id.editTextAmount);
        textViewQRCodeResult = (TextView) v.findViewById(R.id.textViewQRCodeResult);
        mScannerView = (ZXingScannerView) v.findViewById(R.id.scannerView);
        textViewScannerTitle = (TextView) v.findViewById(R.id.textViewScannerTitle);

        buttonFinish = (Button) v.findViewById(R.id.buttonFinish);
        buttonCancle = (Button) v.findViewById(R.id.buttonCancle);

        buttonFinish.setOnClickListener(this);
        buttonCancle.setOnClickListener(this);
    }

    private void setModeTitle(int ModeMenu) {

        switch (ModeMenu) {
            case 1:
                textViewScannerTitle.setText(getResources().getString(R.string.qr_scanner_dialog_title_send));
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }
    @Override
    public void handleResult(Result result) {
        mFamilyCode = result.getText().toString();

        textViewQRCodeResult.setText(mFamilyCode);
        textViewQRCodeResult.setVisibility(View.VISIBLE);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonFinish:
                onFinishScan();
                break;
            case R.id.buttonCancle:
                getDialog().dismiss();
                break;
        }
    }

    private void onFinishScan(){
        ScannerResult result = new ScannerResult();
        mAmount = Integer.parseInt(editTextAmount.getText().toString());
        result.setFamilyCode(mFamilyCode);
        result.setAmount(mAmount);
        mListener.onFragmentInteraction(QRMode.MODE_SEND_FAMILY,result);
        dismiss();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mScannerView.stopCamera();
    }
}
