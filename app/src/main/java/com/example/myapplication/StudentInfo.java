package com.example.myapplication;

public class StudentInfo {

    private String number;
    private String firstName;
    private String middleName;
    private String surname;
    private String email;
    private Double toatalMark;
    private Mark mark;
    private int group;


    public StudentInfo(){}
    public StudentInfo(String number, String firstName, String middleName,
                       String surname, String email){

        this.number = number;
        this.firstName = firstName;
        this.middleName = middleName;
        this.surname = surname;
        this.email = email;

    }

    public void setStudentInfo(String number, String firstName, String middleName,
                               String surname, String email){

        this.number = number;
        this.firstName = firstName;
        this.middleName = middleName;
        this.surname = surname;
        this.email = email;

    }

    public void setFirstName(String firstName){

        this.firstName = firstName;

    }

    public void setMiddleName(String middleName){

        this.middleName = middleName;

    }

    public void setEmail(String email){

        this.email = email;

    }

    public void setSurname(String surname){

        this.surname = surname;

    }

    public void setMark(Double totalMark){

        this.toatalMark = totalMark;

    }


    public void setGroup(int group){

        this.group = group;

    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getNumber(){

        return number;

    }

    public String getFirstName(){

        return firstName;

    }

    public String getMiddleName(){

        return middleName;

    }

    public String getSurname(){

        return surname;

    }

    public String getEmail(){

        return email;

    }

    public Double getMark(){

        return toatalMark;

    }

    public int getGroup(){

        return group;

    }
}