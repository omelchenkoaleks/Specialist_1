package com.omelchenkoaleks.specialist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class AnimSample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_sample);

        ImageView lander = findViewById(R.id.lander);
        Animation landerAnim = AnimationUtils.loadAnimation(
                this,
                R.anim.ship_anim);
        lander.startAnimation(landerAnim);
    }
}
