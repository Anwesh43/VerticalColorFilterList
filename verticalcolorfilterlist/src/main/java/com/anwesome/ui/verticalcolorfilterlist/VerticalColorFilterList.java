package com.anwesome.ui.verticalcolorfilterlist;

import android.app.Activity;
import android.graphics.Bitmap;

/**
 * Created by anweshmishra on 08/05/17.
 */
public class VerticalColorFilterList {
    private Activity activity;
    private VerticalColorFilterListView view;
    private boolean isShown = false;
    int colorAdded = 0;
    public VerticalColorFilterList(Activity activity, Bitmap bitmap) {
        this.activity = activity;
        this.view = new VerticalColorFilterListView(activity,bitmap);
    }
    public void addColor(int color) {
        if(!isShown) {
            view.addColor(color);
        }
    }
    public void show() {
        if(!isShown) {
            activity.setContentView(view);
            isShown = true;
        }
    }
}
