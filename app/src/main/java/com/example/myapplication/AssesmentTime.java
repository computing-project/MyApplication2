package com.example.myapplication;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class AssesmentTime extends AppCompatActivity {
    public int t1,t2,t3,t4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assesment_time);
        ImageView ig1=(ImageView)findViewById(R.id.imageView2);
        ImageView ig2=(ImageView)findViewById(R.id.imageView11);
        ImageView ig3=(ImageView)findViewById(R.id.imageView14);
        ImageView ig4=(ImageView)findViewById(R.id.imageView15);
        ImageView ig5=(ImageView)findViewById(R.id.imageView9);
        ImageView ig6=(ImageView)findViewById(R.id.imageView12);
        ImageView ig7=(ImageView)findViewById(R.id.imageView13);
        ImageView ig8=(ImageView)findViewById(R.id.imageView16);
        EditText time1=(EditText)findViewById(R.id.time1);
        EditText time2=(EditText)findViewById(R.id.time2);
        EditText time3=(EditText)findViewById(R.id.time3);
        EditText time4=(EditText)findViewById(R.id.time4);
        Button back=(Button)findViewById(R.id.button6);
        Button next=(Button)findViewById(R.id.button);
        t1=Integer.parseInt(time1.getText().toString());
        t2=Integer.parseInt(time2.getText().toString());
        t3=Integer.parseInt(time3.getText().toString());
        t4=Integer.parseInt(time4.getText().toString());
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssesmentTime.this, Criteria.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AssesmentTime.this, AboutActivity.class);
                startActivity(intent);
            }
        });
        ig1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1++;
                time1.setText(t1);
            }
        });
        ig2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t1--;
                time1.setText(t1);
            }
        });
        ig3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t2++;
                time1.setText(t2);
            }
        });
        ig4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t2--;
                time1.setText(t2);
            }
        });
        ig5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t3++;
                time1.setText(t3);
            }
        });
        ig6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t3--;
                time1.setText(t3);
            }
        });
        ig7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t4++;
                time1.setText(t4);
            }
        });
        ig8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                t4--;
                time1.setText(t4);
            }
        });


    }
}
