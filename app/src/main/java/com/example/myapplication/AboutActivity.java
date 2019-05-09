package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AboutActivity extends AppCompatActivity {

    String index;
    ProjectInfo project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Intent intent =getIntent();
        index = intent.getStringExtra("index");
        if(index.equals("-999"))
            ;
        else
            init(Integer.parseInt(index));
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
    private void init(int i)
    {
        project = AllFunctions.getObject().getProjectList().get(i);
        EditText editText_projectName = findViewById(R.id.pname);
        editText_projectName.setText(project.getProjectName());
        editText_projectName.setEnabled(false);
        EditText editText_subjectName = findViewById(R.id.sname);
        editText_subjectName.setText(project.getSubjectName());
        EditText editText_subjectCode = findViewById(R.id.scode);
        editText_subjectCode.setText(project.getSubjectCode());
        EditText editText_projectDes = findViewById(R.id.sname2);
        editText_projectDes.setText(project.getDescription());
    }

    //save button click
    public void save(View view)
    {
        EditText editText_projectName = findViewById(R.id.pname);
        EditText editText_subjectName = findViewById(R.id.sname);
        EditText editText_subjectCode = findViewById(R.id.scode);
        EditText editText_projectDes = findViewById(R.id.sname2);
        String projectName = editText_projectName.getText().toString();
        String subjectName = editText_subjectName.getText().toString();
        String subjectCode = editText_subjectCode.getText().toString();
        String projectDes = editText_projectDes.getText().toString();
        if(index.equals("-999"))
            AllFunctions.getObject().createProject(projectName,subjectName,subjectCode,projectDes);
        else
            AllFunctions.getObject().updateProject(project,projectName,subjectName,subjectCode,projectDes);
        Intent intent = new Intent(this, Assessment_Preparation_Activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void back(View view)
    {
        Intent intent = new Intent(this, Assessment_Preparation_Activity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
