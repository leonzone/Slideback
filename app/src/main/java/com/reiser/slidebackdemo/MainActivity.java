package com.reiser.slidebackdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.reiser.slideback.SlideBack;
import com.reiser.slideback.interfaces.SlideBackCallBack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SlideBack.register(this, new SlideBackCallBack() {
            @Override
            public void onSlideBack() {
                onBackPressed();
            }
        });

        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        SlideBack.unRegister(this);
        super.onDestroy();
    }
}
