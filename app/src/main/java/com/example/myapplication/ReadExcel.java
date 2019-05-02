package com.example.myapplication;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class ReadExcel {

    private String fileAddress;

    public void setInputFile(String fileAddress) {

        this.fileAddress = fileAddress;

    }

    public ArrayList<StudentInfo> read(){

        ArrayList<StudentInfo> studentList = new ArrayList<StudentInfo>();
        File inputFile = new File(fileAddress);
        FileInputStream fileInputStream;
        Scanner in;

        try {

            fileInputStream = new FileInputStream(inputFile);
            in = new Scanner(fileInputStream, "UTF-8");
            in.nextLine();

            while (in.hasNextLine()) {
                String[] lines = in.nextLine().split(",");
                //StudentInfo studentInfo = new StudentInfo();
                //studentInfo.setStuInfo(lines[0], lines[1], lines[2], lines[3], lines[4]);
               // studentList.add(studentInfo);

               // Log.d("ReadExcel", "the content is " + studentInfo.getStuInfo());

            }

        } catch (Exception e) {

            Log.e("ReadExcel", "read error=" + e, e);

        }

        return studentList;
    }

}