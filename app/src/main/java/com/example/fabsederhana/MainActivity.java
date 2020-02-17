package com.example.fabsederhana;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    ToggleButton toggleButton;
    ImageView imageFan;
    ObjectAnimator rotateAnimator;
    Switch switchButton;
    SeekBar seekBar;
    int SPEED[] = {0, 5000, 2000, 500};

    GradientDrawable gd = new GradientDrawable();

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = (ToggleButton) findViewById(R.id.toggleOnOff);
        imageFan = (ImageView) findViewById(R.id.imageFan);
        seekBar = (SeekBar) findViewById(R.id.seekBarSpeed);
        switchButton = (Switch) findViewById(R.id.switchLight);


        rotateAnimator = ObjectAnimator.ofFloat(imageFan, "rotation", 0, 360);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(new LinearInterpolator());

        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gd.setGradientRadius(330);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(toggleButton.isChecked()){
                    rotateAnimator.setDuration(SPEED[seekBar.getProgress()]);
                    rotateAnimator.start();
                }else{
                    rotateAnimator.end();
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(toggleButton.isChecked()){
                    rotateAnimator.setDuration(SPEED[progress]);
                    rotateAnimator.start();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(switchButton.isChecked()){
                    gd.setColors(new int[]{Color.YELLOW,Color.TRANSPARENT});
                    imageFan.setBackground(gd);
                }else{
                    imageFan.setBackgroundColor(Color.TRANSPARENT);
                }
            }
        });


    }
}
