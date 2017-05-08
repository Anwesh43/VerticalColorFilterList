package com.anwesome.ui.verticalcolorfilterlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import java.util.*;
/**
 * Created by anweshmishra on 08/05/17.
 */
public class VerticalColorFilterListView extends View {
    private Bitmap bitmap;
    private int time = 0,w,h;
    private Screen screen;
    private List<ColorFilterRect> colorFilterRects = new ArrayList<>();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public VerticalColorFilterListView(Context context, Bitmap bitmap) {
        super(context);
        this.bitmap = bitmap;
    }
    public void addColor(int color) {
        colorFilterRects.add(new ColorFilterRect(colorFilterRects.size()*h,color));
    }
    public void onDraw(Canvas canvas) {
        w = canvas.getWidth();
        h = canvas.getHeight();
        if(time == 0) {
            bitmap = Bitmap.createScaledBitmap(bitmap,w,h,true);
            screen = new Screen();
        }
        canvas.drawBitmap(bitmap,0,0,paint);
        canvas.save();
        canvas.translate(0,screen.getY());
        for(ColorFilterRect rect:colorFilterRects) {
            rect.draw(canvas,paint);
        }
        canvas.restore();
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
    private class Screen {
        private float y = 0,dir=0;
        public void updateY(float factor) {
            y+=h*factor*dir;
        }
        public void setDir(float dir) {
            this.dir = dir;
        }
        public float getY() {
            return y;
        }
    }
    private class ColorFilterRect {
        private float y;
        private int color;
        public ColorFilterRect(float y,int color) {
            this.y = y;
            this.color = color;
        }
        public void draw(Canvas canvas,Paint paint) {
            paint.setColor(Color.argb(150,Color.red(color),Color.green(color),Color.blue(color)));
            canvas.save();
            canvas.translate(0,y);
            canvas.drawRect(new RectF(0,0,w,h),paint);
            canvas.restore();
        }
        public int hashCode() {
            return color+(int)y;
        }
    }
}
