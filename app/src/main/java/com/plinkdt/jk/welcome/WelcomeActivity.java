package com.plinkdt.jk.welcome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.plinkdt.jk.main.LoginActivity;
import com.plinkdt.jk.main.MainActivity;


public class WelcomeActivity extends AppCompatActivity implements Runnable {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().postDelayed(this, 1500);
    }

    @Override
    public void run() {
        if (isFinishing()) {
            finish();
            return;
        }
        LoginActivity.start(this);
        finish();
    }
}
