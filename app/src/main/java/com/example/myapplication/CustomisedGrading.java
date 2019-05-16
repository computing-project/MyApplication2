package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class CustomisedGrading extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customised_grading);
        ImageView plus1=(ImageView)findViewById(R.id.imageView934);
        ImageView minus1=(ImageView)findViewById(R.id.imageView913);
        ImageView plus2=(ImageView)findViewById(R.id.imageView95);
        ImageView minus2=(ImageView)findViewById(R.id.imageView916);
        ImageView plus3=(ImageView)findViewById(R.id.imageView19);
        ImageView minus3=(ImageView)findViewById(R.id.imageView191);
        ImageView plus4=(ImageView)findViewById(R.id.imageView9);
        ImageView minus4=(ImageView)findViewById(R.id.imageView91);
        ImageView comment1=(ImageView)findViewById(R.id.imageView185);
        ImageView comment2=(ImageView)findViewById(R.id.imageView181);
        ImageView comment3=(ImageView)findViewById(R.id.imageView12);
        ImageView comment4=(ImageView)findViewById(R.id.imageView18);
        ImageView comment5=(ImageView)findViewById(R.id.imageView14);

    }
}
