package com.aillience.enginee.ui.splash;

import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;

import com.aillience.enginee.ui.main.MainActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
        TaskStackBuilder.create(this)
                .addNextIntentWithParentStack(new Intent(this, MainActivity.class))
                .addNextIntent(new Intent(this, IntroActivity.class))
                .startActivities();
    }
}