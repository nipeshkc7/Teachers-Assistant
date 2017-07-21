package com.fithsemproject.cs.teachersassistant;

/**
 * Created by Dell on 7/21/2017.
 */

public class student_items {
    public String studentName;
    public int studentId;
    public int rollNo;

    public String getStudentName(){
        return studentName;
    }

    public void setStudentName(String studentName){
        this.studentName=studentName;
    }

    public int getStudentId(){
        return studentId;
    }

    public void setRollNo(int rollNo){
        this.rollNo=rollNo;
    }

    public int getRollNo(){
        return rollNo;
    }


    //No setter for student id

}
