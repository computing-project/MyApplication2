package com.example.myapplication;


import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

public class AllFunctions{

    private static AllFunctions allFunctions;
    //initiate the new object: AllFunctions all = AllFunctions.getObject();

    private CommunicationForClient communication;
    private ArrayList<ProjectInfo> projectList = new ArrayList<ProjectInfo>();

    private AllFunctions(){

        communication = new CommunicationForClient(this);

    }

    public void login(final String username, final String password){

        new Thread(new Runnable(){
            @Override
            public void run(){

                communication.login(username, password);

            }
        }).start();
    }

    public void loginSucc(ArrayList<ProjectInfo> projectList){

        this.projectList = projectList;
        // need to call ui function to update the interface
        //for test
        System.out.println("loginSucc received in AllFunc.class:"+ JSON.toJSONString(projectList));
    }

    public ArrayList<ProjectInfo> getProjectList(){

        return  projectList;

    }

    public void loginFail(){

        System.out.println("login fail has been called!");

    }

    public void logout(){



    }

    static public AllFunctions getObject(){
        if(allFunctions == null){

            allFunctions = new AllFunctions();

        }
        return allFunctions;
    }

    public void register(final String firstName, final String middleName,
                         final String lastName, final String email,
                         final String password){

        new Thread(new Runnable(){
            @Override
            public void run(){

                communication.register(firstName, middleName, lastName,
                        email, password);
                Log.d("register","success");

            }
        }).start();
    }

    public void registerACK(boolean ack){

        System.out.print("registerACK" + ack);


        //for test
        System.out.println("receive register_ACK in AllFunc: "+ack);

        if(ack){

        }else{

        }
    }

    public void communicationFail(){


    }

    public void createProject(String projectName, String subjectName,
                              String subjectCode, String description){

        ProjectInfo project = new ProjectInfo();
        projectList.add(project);
        project.setProjectName(projectName);
        project.setSubjectName(subjectName);
        project.setSubjectCode(subjectCode);
        project.setDescription(description);

        new Thread(new Runnable(){
            @Override
            public void run(){

                communication.updateProject_About(projectName, subjectName,
                        subjectCode, description);
                Log.d("createProject","success");

            }
        }).start();

    }

    public void updateProject(ProjectInfo project, String projectName, String subjectName,
                              String subjectCode, String description){

        project.setProjectName(projectName);
        project.setSubjectName(subjectName);
        project.setSubjectCode(subjectCode);
        project.setDescription(description);

        new Thread(new Runnable(){
            @Override
            public void run(){

                communication.updateProject_About(projectName, subjectName,
                        subjectCode, description);
                Log.d("createProject","success");

            }
        }).start();

    }

    public void projectTimer(ProjectInfo project,int durationMin, int durationSec,
                             int warningMin, int warningSec){

        project.setTimer(durationMin, durationMin, warningMin, warningSec);

        new Thread(new Runnable(){
            @Override
            public void run(){

                communication.updateProject_Time(project.getProjectName(), durationMin,
                        durationSec, warningMin, warningSec);
                Log.d("projectTimer","success");

            }
        }).start();

    }

    public DefaultCriteriaList defaultCriteriaList = new DefaultCriteriaList();

    public void addDefaultCriteria(ProjectInfo project, ArrayList<Criteria> criteriaList){

        project.setCriteria(criteriaList);

        //next remove the added criteria list


    }

    public void addNewCriteria(ProjectInfo project, Criteria criteria){

        project.addSingleCriteria(criteria);

    }

    public void projectCriteria(ProjectInfo project, ArrayList<Criteria> criteriaList){

        new Thread(new Runnable(){
            @Override
            public void run(){

                communication.criteriaListSend(project.getProjectName(), criteriaList);

                Log.d("readExcel","success");

            }
        }).start();


    }

    public void readExcel(ProjectInfo project, String path){

        ReadExcel read = new ReadExcel();
        read.setInputFile(path);
        project.addStudentList(read.read());
        System.out.println("student list in allFunction: "+read.read().size());

        new Thread(new Runnable(){
            @Override
            public void run(){

                communication.importStudents(project.getProjectName(),
                        project.getStudentInfo());

                Log.d("readExcel","success");

            }
        }).start();

    }

    public void addStudent(ProjectInfo project, String number, String firstName,
                           String middleName, String surname, String email){

        StudentInfo studentInfo = new StudentInfo(number, firstName, middleName, surname, email);
        project.addSingleStudent(studentInfo);

        new Thread(new Runnable(){
            @Override
            public void run(){

                communication.addStudent(project.getProjectName(),number,
                        firstName, middleName, surname, email);

                Log.d("addStudent","success");

            }
        }).start();
    }

    private int searchStudent(ProjectInfo project, String number)
    {
        ArrayList<StudentInfo> list = project.getStudentInfo();

        //test
        System.out.println("list size in search student: "+list.size());
        for(int i = 0; i < list.size(); i++){
            //test
            // System.out.println("The "+i+" student number: "+list.get(i).getNumber());
            if(number.equals(list.get(i).getNumber())){

                return i;

            }
        }
        return -999;
    }

    public void editStudent(ProjectInfo project, String number, String firstName,
                            String middleName, String surname, String email){

        int i = searchStudent(project, number);
        project.getStudentInfo().get(i).setStudentInfo(number, firstName,
                middleName, surname, email);

        new Thread(new Runnable(){
            @Override
            public void run(){

                communication.editStudent(project.getProjectName(), number, firstName,
                        middleName, surname, email);

                Log.d("editStudent","success");

            }
        }).start();

    }

    public void deleteStudent(ProjectInfo project, String number){

        int i = searchStudent(project, number);
        project.getStudentInfo().remove(i);

        new Thread(new Runnable(){
            @Override
            public void run(){

                communication.deleteStudent(project.getProjectName(), number);

                Log.d("deleteStudent","success");

            }
        }).start();
    }

    public void groupStudent(ProjectInfo project, ArrayList<StudentInfo> studentList){

        project.addStudentList(studentList);

        new Thread(new Runnable(){
            @Override
            public void run(){

                communication.importStudents(project.getProjectName(),
                        project.getStudentInfo());

                Log.d("groupStudent","success");

            }
        }).start();

    }
}