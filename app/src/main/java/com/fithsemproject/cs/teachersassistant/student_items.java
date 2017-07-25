package com.fithsemproject.cs.teachersassistant;

/**
 * Created by Dell on 7/21/2017.
 */

public class student_items {
    public String studentName;
    public int studentId;
    public int rollNo;
    boolean isSelected=false;


    public void toggleSelected(){
       if(isSelected){
           isSelected=false;
       }else{
           isSelected=true;
       }
    }
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

    public void setStudentId(int studentId){
        this.studentId=studentId;
    }

    public boolean getIsSelected(){
        return isSelected;
    }


    //No setter for student id

}
