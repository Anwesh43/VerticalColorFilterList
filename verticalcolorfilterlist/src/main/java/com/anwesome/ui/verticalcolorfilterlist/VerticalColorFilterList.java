package com.anwesome.ui.verticalcolorfilterlist;

import android.app.Activity;
import android.graphics.Bitmap;

/**
 * Created by anweshmishra on 08/05/17.
 */
public class VerticalColorFilterList {
    private Activity activity;
    private boolean isShown = false;
    public VerticalColorFilterList(Activity activity, Bitmap bitmap) {
        this.activity = activity;
    }
    public void addColor(int color) {
        if(isShown) {

        }
    }
    public void show() {
        if(!isShown) {
            isShown = true;
        }
    }
}
