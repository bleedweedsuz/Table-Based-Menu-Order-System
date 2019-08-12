package com.example.sophiya.tbmosystem;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends Activity {
    ImageView image_Splash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_layout);

        image_Splash=(ImageView)findViewById(R.id.imageView6);
        final Animation splash_first= AnimationUtils.loadAnimation(getBaseContext(), R.anim.splash_fade_in);
        image_Splash.startAnimation(splash_first);


        splash_first.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                Intent i=new Intent(getApplicationContext(),All_User_Login.class);
                startActivity(i);
                overridePendingTransition(R.anim.splash_transition_fade_in,R.anim.splash_transition_fade_out);

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });



    }
}
