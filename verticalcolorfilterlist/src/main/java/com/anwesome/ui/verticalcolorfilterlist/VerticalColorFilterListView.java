package com.anwesome.ui.verticalcolorfilterlist;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import java.util.*;
/**
 * Created by anweshmishra on 08/05/17.
 */
public class VerticalColorFilterListView extends View {
    private Bitmap bitmap;
    private int time = 0,w,h,barH;
    private AnimationHandler animationHandler;
    private Screen screen;
    private List<ColorFilterRect> colorFilterRects = new ArrayList<>();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private GestureDetector gestureDetector;
    public VerticalColorFilterListView(Context context, Bitmap bitmap) {
        super(context);
        this.bitmap = bitmap;
        gestureDetector = new GestureDetector(context,new ScreenGestureDetector());
    }
    public void addColor(int color) {
        colorFilterRects.add(new ColorFilterRect(color));
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = 9*canvas.getHeight()/10;
            barH = canvas.getHeight()/10;
            bitmap = Bitmap.createScaledBitmap(bitmap,w,h,true);
            screen = new Screen();
            animationHandler = new AnimationHandler();
            int i = 0;
            for(ColorFilterRect colorFilterRect:colorFilterRects) {
                colorFilterRect.setY(h*i);
                i++;
            }
        }
        canvas.save();
        canvas.translate(0,barH);
        canvas.drawBitmap(bitmap,0,0,paint);
        canvas.save();
        canvas.translate(0,screen.getY());
        for(ColorFilterRect rect:colorFilterRects) {
            rect.draw(canvas,paint);
        }
        canvas.restore();
        canvas.restore();
        paint.setColor(Color.WHITE);
        canvas.drawRect(new RectF(0,0,w,barH),paint);
        paint.setColor(Color.parseColor("#FF6F00"));
        canvas.drawArc(new RectF(w/2-h/20,0,w/2+h/20,h/10),0,Math.abs(360*((screen.y)/(h*(colorFilterRects.size()-1)))),true,paint);
        time++;
    }
    public void update(float factor) {
        screen.updateY(factor);
        postInvalidate();
    }
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
    private class Screen {
        private float y = 0,dir=0,initY;
        public Screen() {
            initY = y;
        }
        public void updateY(float factor) {
            float yDisp = h*factor*dir;
            Log.d("yDisp",""+yDisp);
            y=initY+yDisp;
        }
        public void setDir(float dir) {
            this.dir = dir;
            if(dir == 0) {
                initY = y;
            }

        }
        public float getY() {
            return y;
        }
    }
    private class ColorFilterRect {
        private float y;
        private int color;
        public void setY(float y) {
            this.y = y;
        }
        public ColorFilterRect(int color) {
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
    private class ScreenGestureDetector extends GestureDetector.SimpleOnGestureListener {
        public boolean onDown(MotionEvent event) {
            return true;
        }
        public boolean onSingleTapUp(MotionEvent event) {
            return true;
        }
//        public boolean onScroll(MotionEvent e1,MotionEvent e2,float velx,float vely) {
//            screen.y += e2.getY()-e1.getY();
//            postInvalidate();
//            return true;
//        }
        public boolean onFling(MotionEvent e1,MotionEvent e2,float velx,float vely) {
            if(Math.abs(vely) > Math.abs(velx) && e1.getY()!=e2.getY() && screen.dir == 0) {
                float dir = (e2.getY()-e1.getY())/Math.abs(e2.getY()-e1.getY());
                if((dir == -1 && screen.y > -h*(colorFilterRects.size()-1)) || (dir==1 && screen.y < 0)) {
                    screen.setDir(dir);
                    //screen.y += dir*h;
                    //postInvalidate();
                    animationHandler.start();
                }
            }
            return true;
        }
    }
    private class AnimationHandler extends AnimatorListenerAdapter implements ValueAnimator.AnimatorUpdateListener{
        private ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
        public AnimationHandler() {
            valueAnimator.setDuration(500);
            valueAnimator.addUpdateListener(this);
            valueAnimator.addListener(this);
        }
        public void onAnimationEnd(Animator animator) {
            screen.setDir(0);
        }
        public void onAnimationUpdate(ValueAnimator valueAnimator) {
            update((float)(valueAnimator.getAnimatedValue()));
        }
        public void start() {
            valueAnimator.start();
        }
    }
}
