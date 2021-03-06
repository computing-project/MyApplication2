package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Assessment_Preparation_Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    ArrayList<String> alist;
    AllFunctions allFunctions = AllFunctions.getObject();
    ArrayList<ProjectInfo> projectList;
    MyAdapter_for_listView myAdapter;
    Button button_edit;
    int index_to_send = -999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment__preparation_);
        button_edit = findViewById(R.id.button_edit_inpreparation);
        init();
        System.out.println("Preparation: onCreate has been called!");
    }

    protected void onNewIntent(Intent intent) {
        init();
        System.out.println("Preparation: onNewIntent has been called!");
    }

    private void init() {
        resetDetailView();
        alist = new ArrayList<String>();

        projectList = allFunctions.getProjectList();
        for (ProjectInfo p : projectList)
            alist.add(p.getProjectName());

        ArrayAdapter<String> adpter = new ArrayAdapter<String>
                (Assessment_Preparation_Activity.this, R.layout.list_item_string, alist);
        listView = (ListView) findViewById(R.id.listView_inpreparation);
        listView.setAdapter(adpter);
        listView.setOnItemClickListener(this);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {
        // TODO Auto-generated method stub
        String text = listView.getItemAtPosition(position).toString();
        for(int i=0; i<parent.getChildCount(); i++)
            parent.getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
        view.setBackgroundColor(Color.rgb(135,206,250));
        Toast.makeText(this, "position=" + position + "; text=" + text,
                Toast.LENGTH_SHORT).show();
        showOtherInfo(position);
    }

    public void showOtherInfo(int index) {
        index_to_send = index;
        ProjectInfo projectInfo = allFunctions.getProjectList().get(index);
        TextView textView_projectName = findViewById(R.id.project_name_inpreparation);
        textView_projectName.setText(projectInfo.getProjectName());
        TextView textView_aboutDetail = findViewById(R.id.about_detail__inpreparation);
        textView_aboutDetail.setText("Subject Name: " + projectInfo.getSubjectName() + "\n" +
                "Subject Code: " + projectInfo.getSubjectCode() + "\n" +
                "Description: " + projectInfo.getDescription()+"\n");
        TextView textView_timeDetail = findViewById(R.id.time_detail__inpreparation);
        textView_timeDetail.setText("Assessment duration: " + projectInfo.getDurationMin() + ":" + projectInfo.getDurationSec() + "\n" +
                "Warning time: " + projectInfo.getWarningMin() + ":" + projectInfo.getWarningSec()+"\n");

    }

    private void resetDetailView()
    {
        TextView textView_projectName = findViewById(R.id.project_name_inpreparation);
        textView_projectName.setText("Project Name");
        TextView textView_aboutDetail = findViewById(R.id.about_detail__inpreparation);
        textView_aboutDetail.setText("\n");
        TextView textView_timeDetail = findViewById(R.id.time_detail__inpreparation);
        textView_timeDetail.setText("\n");
        TextView textView_criteriaDetail = findViewById(R.id.criteria_detail__inpreparation);
        textView_criteriaDetail.setText("\n");
        TextView textView_asseccorDetail = findViewById(R.id.asseccor_detail__inpreparation);
        textView_asseccorDetail.setText("\n");
    }

    //plus button click function
    public void plus(View view) {
        Intent intent = new Intent(this, AboutActivity.class);
        intent.putExtra("index", "-999");
        startActivity(intent);
    }

    //edit button click function
    public void edit(View view) {
        String button_text = button_edit.getText().toString();
        if(button_text.equals(" Edit")) {
            button_edit.setText(" Done");
            listView = (ListView) findViewById(R.id.listView_inpreparation);
           projectList = allFunctions.getProjectList();
            myAdapter = new MyAdapter_for_listView(projectList, this);

            listView.setAdapter(myAdapter);
            listView.setOnItemClickListener(this);
        }
        if(button_text.equals(" Done"))
        {
            button_edit.setText(" Edit");
            init();
        }
    }


    public void about(View view)
    {
        Intent intent = new Intent(this, AboutActivity.class);
        intent.putExtra("index", String.valueOf(index_to_send));
        startActivity(intent);
    }


    public class MyAdapter_for_listView extends BaseAdapter {

        private ArrayList<ProjectInfo> mProjectList;
        private Context mContext;

        public MyAdapter_for_listView(ArrayList<ProjectInfo> projectList, Context context) {
            this.mProjectList = projectList;
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return mProjectList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_with_delete, parent, false);
            TextView textView_listItem = (TextView) convertView.findViewById(R.id.textView_inlistView);
            textView_listItem.setText(mProjectList.get(position).getProjectName());
            Button button = convertView.findViewById(R.id.Bt_delete_inlist);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mProjectList.remove(position);
                    myAdapter.notifyDataSetChanged();
                }
            });
            return convertView;
        }
    }

}
