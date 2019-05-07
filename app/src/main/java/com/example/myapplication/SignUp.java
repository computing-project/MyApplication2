package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EditText fname=(EditText)findViewById(R.id.fName);
        EditText mname=(EditText)findViewById(R.id.mName);
        EditText lname=(EditText)findViewById(R.id.lName);
        EditText email=(EditText)findViewById(R.id.userEmail);
        EditText password=(EditText)findViewById(R.id.userPassword);
        EditText cpassword=(EditText)findViewById(R.id.CPassword);
        String fName=fname.getText().toString();
        String mName=mname.getText().toString();
        String lName=lname.getText().toString();
        String Email=email.getText().toString();
        String Password=password.getText().toString();
        String cPassword=cpassword.getText().toString();
        AllFunctions af=AllFunctions.getObject();
        Button submit=(Button)findViewById(R.id.button);
        CommunicationForClient cfc=new CommunicationForClient(af);
        Boolean ack=false;
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* if(Password!=cPassword){
                    Toast.makeText(getApplicationContext(),"Passwords Don't match!!!",Toast.LENGTH_LONG).show();
                }*/
               af.register(fName,mName,lName,Email,Password);
                //ack= cfc.register_ACK


            }
        });
    }
}
