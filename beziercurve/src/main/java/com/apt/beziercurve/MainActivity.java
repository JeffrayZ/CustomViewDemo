package com.apt.beziercurve;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.waveView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WaveView waveView = (WaveView) v;
                waveView.startAnim();
            }
        });
    }
}
