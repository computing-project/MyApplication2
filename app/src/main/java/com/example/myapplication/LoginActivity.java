package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    String userName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        EditText email=(EditText)findViewById(R.id.userEmail);
        EditText password=(EditText)findViewById(R.id.userPassword);
        Button button = (Button)findViewById(R.id.button);
        Button reset=(Button)findViewById(R.id.btn_reset);
        String email1=email.getText().toString();
        String password1=password.getText().toString();

        final AllFunctions af=AllFunctions.getObject();
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, AboutActivity.class);
                startActivity(intent);

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                    EditText email=(EditText)findViewById(R.id.userEmail);
                    EditText password=(EditText)findViewById(R.id.userPassword);
                    String email1=email.getText().toString();
                    String password1=password.getText().toString();
               af.login(email1, password1);
                if(email1.equals("")) {
                    Toast.makeText(getApplicationContext(),userName,Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Incorrect Password!!!",Toast.LENGTH_LONG).show();
                }
            }
        });
        ImageView IG=(ImageView)findViewById(R.id.textView4);
        IG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,SignUp.class);
                startActivity(intent);
            }
        });

    }
}
