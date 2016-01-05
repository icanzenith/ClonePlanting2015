package org.thailandsbc.cloneplanting.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.thailandsbc.cloneplanting.R;

/**
 * Created by Icanzenith on 12/23/2015 AD.
 */
public class PlantedLayout extends ViewGroup {

    boolean mShowText = false ;

    private Paint mTextPaint;
    private float mTextWidth = 0.0f;
    private float mTextHeight = 0.0f;

    private int mTextColor;


    public PlantedLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PlantedLayout,0,0);

        try{
            a.getBoolean(R.styleable.PlantedLayout_showText,false);
            a.getInteger(R.styleable.PlantedLayout_rowNumber,0);
            a.getInteger(R.styleable.PlantedLayout_familyPerRow,0);
            a.getInteger(R.styleable.PlantedLayout_labelPosition,0);
        }
        finally {
            a.recycle();
        }
        init();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    public boolean isShowText(){
        return mShowText;
    }

    public void setShowText(boolean ShowText){
        mShowText = ShowText;
        invalidate();
        requestLayout();
    }


    private void init(){
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mTextPaint.setColor(mTextColor);
        if (mTextHeight == 0){
            mTextHeight = mTextPaint.getTextSize();
        }else{
            mTextPaint.setTextSize(mTextHeight);
        }

    }

    class buttonLayout extends Button{

        public buttonLayout(Context context, AttributeSet attrs) {
            super(context, attrs);
        }
        

    }
}
