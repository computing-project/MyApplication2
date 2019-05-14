package com.example.myapplication;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Assessment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment);
        ImageButton play= (ImageButton) findViewById(R.id.imageButton2);
        TextView time=(TextView)findViewById(R.id.textView23) ;
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CountDownTimer(30000, 1000) {

                    public void onTick(long millisUntilFinished) {
                        time.setText("seconds remaining: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        time.setText("done!");
                    }
                }.start();
            }
        });
    }
}
