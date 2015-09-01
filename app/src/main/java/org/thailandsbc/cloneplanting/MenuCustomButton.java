package org.thailandsbc.cloneplanting;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by Icanzenith on 8/31/15 AD.
 */
public class MenuCustomButton extends ViewGroup {


    public MenuCustomButton(Context context) {
        super(context);
    }

    public MenuCustomButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MenuCustomButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MenuCustomButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }


}
