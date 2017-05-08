package com.anwesome.ui.verticalcolorfilterlistdemo;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.anwesome.ui.verticalcolorfilterlist.VerticalColorFilterList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        VerticalColorFilterList verticalColorFilterList = new VerticalColorFilterList(this, BitmapFactory.decodeResource(getResources(),R.drawable.back3));
        int colors[] = {Color.CYAN,Color.GREEN,Color.BLUE,Color.RED,Color.BLACK};
        for(int color:colors) {
            verticalColorFilterList.addColor(color);
        }
        verticalColorFilterList.show();
    }
}
