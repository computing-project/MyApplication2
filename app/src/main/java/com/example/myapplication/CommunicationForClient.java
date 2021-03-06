package com.example.myapplication;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class CommunicationForClient {
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private String host;
    private OkHttpClient client;
    private String token;
    String userName="";
    //private String myUsername;
    AllFunctions functions;

    public CommunicationForClient(AllFunctions functions) {
        host = "http://10.13.101.237:8080/RapidFeedback/";
        client = new OkHttpClient();
        this.functions = functions;
    }


    public void register(String firstName, String middleName, String lastName,
                         String email, String password) {
        JSONObject jsonSend = new JSONObject();
        jsonSend.put("firstName", firstName);
        jsonSend.put("middleName", middleName);
        jsonSend.put("lastName", lastName);
        jsonSend.put("email", email);
        jsonSend.put("password", password);
        System.out.println("Send: " + jsonSend.toJSONString()); //just for test

        RequestBody body = RequestBody.create(JSON, jsonSend.toJSONString());
        Request request = new Request.Builder()
                .url(host + "RegisterServlet")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String receive = response.body().string();
            System.out.println("Receive: " + receive); //just for test
            JSONObject jsonReceive = JSONObject.parseObject(receive);

            boolean register_ACK = Boolean.getBoolean(jsonReceive.get("register_ACK").toString());
            functions.registerACK(register_ACK);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    public void login(String username, String password) {
        JSONObject jsonSend = new JSONObject();
        jsonSend.put("username", username);
        jsonSend.put("password", password);
        System.out.println("Send: " + jsonSend.toJSONString()); //just for test

        RequestBody body = RequestBody.create(JSON, jsonSend.toJSONString());
        Request request = new Request.Builder()
                .url(host + "LoginServlet")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String receive = response.body().string();
            System.out.println("Receive: " + receive); //just for test
            JSONObject jsonReceive = JSONObject.parseObject(receive);
            int login_ACK = Integer.parseInt(jsonReceive.get("login_ACK").toString());
            if (login_ACK > 0)
            {
                //get projectlist from jsonReceive
                userName=username;
                String projectListString = jsonReceive.get("projectList").toString();
                List<ProjectInfo> projectList = JSONObject.parseArray(projectListString, ProjectInfo.class);
                ArrayList<ProjectInfo> arrayList ;
                if (projectList instanceof ArrayList)
                {
                    arrayList = (ArrayList)projectList;
                }
                else
                {
                    arrayList = new ArrayList();
                    arrayList.addAll(projectList);
                }

                functions.loginSucc(arrayList);

                token = jsonReceive.getString("token");
            }
            else {
                functions.loginFail();
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void updateProject_About(String projectName, String subjectName,
                                    String subjectCode, String description) {
        JSONObject jsonSend = new JSONObject();
        jsonSend.put("token", token);
        jsonSend.put("projectName", projectName);
        jsonSend.put("subjectName", subjectName);
        jsonSend.put("subjectCode", subjectCode);
        jsonSend.put("description", description);
        System.out.println("Send: " + jsonSend.toJSONString()); //just for test

        RequestBody body = RequestBody.create(JSON, jsonSend.toJSONString());
        Request request = new Request.Builder()
                .url(host + "UpdateProject_About_Servlet")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String receive = response.body().string();
            System.out.println("Receive: " + receive); //just for test
            JSONObject jsonReceive = JSONObject.parseObject(receive);
            String updateProject_ACK = jsonReceive.get("updateProject_ACK").toString();
            if (updateProject_ACK.equals("true")) {
                ;
            } else {
                //失败跳出
            }
        } catch (IOException e1) {
            System.out.println("updateProjectAbout: something wrong in receiving response from server.");
        }
    }


    public void criteriaListSend(String projectName, ArrayList<Criteria> criteriaList) {
        JSONObject jsonSend = new JSONObject();
        jsonSend.put("token", token);
        jsonSend.put("projectName", projectName);

        String criteriaListString = com.alibaba.fastjson.JSON.toJSONString(criteriaList);
        jsonSend.put("criteriaList", criteriaListString);
        System.out.println("Send: " + jsonSend.toJSONString()); //just for test

        RequestBody body = RequestBody.create(JSON, jsonSend.toJSONString());
        Request request = new Request.Builder()
                .url(host + "CriteriaListServlet")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String receive = response.body().string();
            System.out.println("Receive: " + receive); //just for test
            JSONObject jsonReceive = JSONObject.parseObject(receive);
            String updateProject_ACK = jsonReceive.get("updateProject_ACK").toString();
            if (updateProject_ACK.equals("true")) {
                ;
            } else {
                //失败跳出
            }
        } catch (IOException e1) {
            System.out.println("updateProjectAbout: something wrong in receiving response from server.");
        }
    }



    public void updateProject_Time(String projectName, int durationMin, int durationSec,
                                   int warningMin, int warningSec) {
        JSONObject jsonSend = new JSONObject();
        jsonSend.put("token", token);
        jsonSend.put("projectName", projectName);
        jsonSend.put("durationMin", durationMin);
        jsonSend.put("durationSec", durationSec);
        jsonSend.put("warningMin", warningMin);
        jsonSend.put("warningSec", warningSec);
        System.out.println("Send: " + jsonSend.toJSONString()); //just for test

        RequestBody body = RequestBody.create(JSON, jsonSend.toJSONString());
        Request request = new Request.Builder()
                .url(host + "UpdateProject_Time_Servlet")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String receive = response.body().string();
            System.out.println("Receive: " + receive); //just for test
            JSONObject jsonReceive = JSONObject.parseObject(receive);
            String updateProject_ACK = jsonReceive.get("updateProject_ACK").toString();
            if (updateProject_ACK.equals("true")) {
                ;
            } else {
                //失败跳出
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void addStudent(String projectName, String studentID, String firstName,
                           String middleName, String lastName, String email)
    {
        //construct JSONObject to send
        JSONObject jsonSend = new JSONObject();
        jsonSend.put("token", token);
        jsonSend.put("projectName", projectName);
        jsonSend.put("studentID", studentID);
        jsonSend.put("firstName", firstName);
        jsonSend.put("middleName", middleName);
        jsonSend.put("lastName", lastName);
        jsonSend.put("email", email);

        System.out.println("Send: " + jsonSend.toJSONString()); //just for test

        RequestBody body = RequestBody.create(JSON, jsonSend.toJSONString());
        Request request = new Request.Builder()
                .url(host + "AddStudentServlet")
                .post(body)
                .build();

        //get the JSONObject from response
        try (Response response = client.newCall(request).execute()) {
            String receive = response.body().string();

            System.out.println("Receive: " + receive); //just for test

            JSONObject jsonReceive = JSONObject.parseObject(receive);
            String updateStudent_ACK = jsonReceive.get("updateStudent_ACK").toString();
            if (updateStudent_ACK.equals("true")) {
                ;
            } else {
                //失败跳出
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void editStudent(String projectName, String studentID, String firstName,
                            String middleName, String lastName, String email)
    {
        //construct JSONObject to send
        JSONObject jsonSend = new JSONObject();
        jsonSend.put("token", token);
        jsonSend.put("projectName", projectName);
        jsonSend.put("studentID", studentID);
        jsonSend.put("firstName", firstName);
        jsonSend.put("middleName", middleName);
        jsonSend.put("lastName", lastName);
        jsonSend.put("email", email);

        System.out.println("Send: " + jsonSend.toJSONString()); //just for test

        RequestBody body = RequestBody.create(JSON, jsonSend.toJSONString());
        Request request = new Request.Builder()
                .url(host + "EditStudentServlet")
                .post(body)
                .build();

        //get the JSONObject from response
        try (Response response = client.newCall(request).execute()) {
            String receive = response.body().string();

            System.out.println("Receive: " + receive); //just for test

            JSONObject jsonReceive = JSONObject.parseObject(receive);
            String updateStudent_ACK = jsonReceive.get("updateStudent_ACK").toString();
            if (updateStudent_ACK.equals("true")) {
                ;
            } else {
                //失败跳出
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void deleteStudent(String projectName, String studentID)
    {
        //construct JSONObject to send
        JSONObject jsonSend = new JSONObject();
        jsonSend.put("token", token);
        jsonSend.put("projectName", projectName);
        jsonSend.put("studentID", studentID);

        System.out.println("Send: " + jsonSend.toJSONString()); //just for test

        RequestBody body = RequestBody.create(JSON, jsonSend.toJSONString());
        Request request = new Request.Builder()
                .url(host + "DeleteStudentServlet")
                .post(body)
                .build();

        //get the JSONObject from response
        try (Response response = client.newCall(request).execute()) {
            String receive = response.body().string();

            System.out.println("Receive: " + receive); //just for test

            JSONObject jsonReceive = JSONObject.parseObject(receive);
            String updateStudent_ACK = jsonReceive.get("updateStudent_ACK").toString();
            if (updateStudent_ACK.equals("true")) {
                ;
            } else {
                //失败跳出
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public void importStudents(String projectName, ArrayList<StudentInfo> studentList)
    {
        //construct JSONObject to send
        JSONObject jsonSend = new JSONObject();
        jsonSend.put("token", token);
        jsonSend.put("projectName", projectName);
        String studentListString = com.alibaba.fastjson.JSON.toJSONString(studentList);
        jsonSend.put("studentList", studentListString);

        System.out.println("Send: " + jsonSend.toJSONString()); //just for test

        RequestBody body = RequestBody.create(JSON, jsonSend.toJSONString());
        Request request = new Request.Builder()
                .url(host + "ImportStudentsServlet")
                .post(body)
                .build();

        //get the JSONObject from response
        try (Response response = client.newCall(request).execute()) {
            String receive = response.body().string();

            System.out.println("Receive: " + receive); //just for test

            JSONObject jsonReceive = JSONObject.parseObject(receive);
            String updateStudent_ACK = jsonReceive.get("updateStudent_ACK").toString();
            if (updateStudent_ACK.equals("true")) {
                ;
            } else {
                //失败跳出
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}