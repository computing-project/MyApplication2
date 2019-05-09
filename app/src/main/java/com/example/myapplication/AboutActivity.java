package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Button next = (Button)findViewById(R.id.button);
        EditText pname=(EditText)findViewById(R.id.pname);
        EditText sname=(EditText)findViewById(R.id.sname);
        EditText scode=(EditText)findViewById(R.id.scode);
        EditText desc=(EditText)findViewById(R.id.sname2);
        String ProjName=pname.getText().toString();
        String SubName=sname.getText().toString();
        String SubCode=scode.getText().toString();
        String Desc=desc.getText().toString();
        
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutActivity.this, AssesmentTime.class);
                startActivity(intent);

            }
        });
    }
}
