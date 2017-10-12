package com.fithsemproject.cs.teachersassistant;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Dell on 7/23/2017.
 */

public class attendance_records {
    int attendanceId;
    String date;
    int classId;
    String classTitle;
    ArrayList<Integer> studentId =new ArrayList<>();
    int total_students;

    HashMap<Integer,ArrayList<Integer>> map =new HashMap<>();

    //int[] studentId= new int[100];
    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public ArrayList<Integer> getStudentIds() {
        return studentId;
    }

    public void setStudentIds(ArrayList<Integer> studentIds) {
        this.studentId = studentIds;
    }
}
