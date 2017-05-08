package com.anwesome.ui.verticalcolorfilterlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 08/05/17.
 */
public class VerticalColorFilterListView extends View {
    private Bitmap bitmap;
    private int time = 0;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public VerticalColorFilterListView(Context context, Bitmap bitmap) {
        super(context);
        this.bitmap = bitmap;
    }
    public void addColor(int color) {

    }
    public void onDraw(Canvas canvas) {
        int w = canvas.getWidth(),h = canvas.getHeight();
        if(time == 0) {
            bitmap = Bitmap.createScaledBitmap(bitmap,w,h,true);
        }
        canvas.drawBitmap(bitmap,0,0,paint);
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
}
