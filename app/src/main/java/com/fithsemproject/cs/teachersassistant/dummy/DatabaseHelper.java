package com.fithsemproject.cs.teachersassistant.dummy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Dell on 7/11/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="class.db";
    public static final String TABLE_NAME="class_table";
    public static final String COL_1="ID";
    public static final String COL_2="TITLE";
    public static final String COL_3="DEPARTMENT";

    public static final String TABLE2="student_table";
    public static final String TAB2COL1="STUDENT_ID";
    public static final String TAB2COL2="CLASS_ID";
    public static final String TAB2COL3="STUDENT_NAME";
    public static final String TAB2COL4="ROLL_NO";


    public static final String TABLE3="attendance_table";
    public static final String TAB3COL1="ATTENDANCE_ID";
    public static final String TAB3COL2="CLASS_ID";
    public static final String TAB3COL3="DAY";

    public static final String TABLE4="attendees_table";
    public static final String TAB4COL1="ATI_ID";
    public static final String TAB4COL2="ATTENDANCE_ID";
    public static final String TAB4COL3="STUDENT_ID";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db =this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creating required tables for the database
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, TITLE TEXT, DEPARTMENT TEXT)");
        db.execSQL("create table " + TABLE2 + "(STUDENT_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, CLASS_ID INTEGER NOT NULL, STUDENT_NAME TEXT, ROLL_NO INTEGER NOT NULL, FOREIGN KEY(CLASS_ID) REFERENCES class_table(ID) )");
        db.execSQL("create table " + TABLE3 + "(ATTENDANCE_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, CLASS_ID INTEGER NOT NULL, DAY DATETIME DEFAULT CURRENT_TIMESTAMP, FOREIGN KEY(CLASS_ID) REFERENCES class_table(ID))");
//        db.execSQL("create table " + TABLE4 + "(ATI_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ATTENDANCE_ID INTEGER NOT NULL, STUDENT_ID INTEGER NOT NULL, FOREIGN KEY(ATTENDANCE_ID) REFERENCES attendance_table(ATTENDANCE_ID), FOREIGN KEY(STUDENT_ID) REFERENCES student_table(STUDENT_ID) )");
        db.execSQL("create table " + TABLE4 + "(ATTENDANCE_ID INTEGER NOT NULL, STUDENT_ID INTEGER NOT NULL, FOREIGN KEY(ATTENDANCE_ID) REFERENCES attendance_table(ATTENDANCE_ID), FOREIGN KEY(STUDENT_ID) REFERENCES student_table(STUDENT_ID),PRIMARY KEY(ATTENDANCE_ID,STUDENT_ID) )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE2);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE3);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE4);
        onCreate(db);
    }

    public boolean insertData(String classTitle,String department){
        //This function is called when adding a new class

        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,classTitle);
        contentValues.put(COL_3,department);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if( result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }

    public Cursor getStudentdata(int classId){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE2+" where CLASS_ID="+classId,null);
        return res;
    }

    public Cursor getStudentName(int classId){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res= db.rawQuery("select STUDENT_NAME, STUDENT_ID from "+TABLE2+" where CLASS_ID="+classId,null);
        return res;
    }

    public void clearAll(){
        //Clear all data from the database
        //Never actually called
        //Created this function just in case
        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
    }

    public void deleteSelected(int id){
        //Delete the selected entry from the class table
        // TO-DO :There might be error with foreign key constraints

        SQLiteDatabase db=this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME + " where ID="  +id);
    }

    public boolean addStudents(String name,int rollNo, int classId){
        //Adding new students

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(TAB2COL2,classId);
        contentValues.put(TAB2COL3,name);
        contentValues.put(TAB2COL4,rollNo);
        long result=db.insert(TABLE2,null,contentValues);
        return result != -1;
    }



    public boolean commenceAttendance(int classId){
        //New entry for attendance table
        //Add new date

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(TAB3COL2,classId);
        long result=db.insert(TABLE3,null,contentValues);
        //A unique attendance id has been associated
        //use and retreive it to take attendance

        return result != -1;

    }


    public int deleteLatestAttendance(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("SELECT MAX(ATTENDANCE_ID) FROM "+TABLE3,null);
        //Cursor res=db.rawQuery("select ATTENDANCE_ID from "+ TABLE3 +" ORDER BY ATTENDANCE_ID DESC LIMIT 1",null);
        int attendance_id=0;
        while(res.moveToNext()) {
            Log.i("toBeDeleted", res.getString(0));
            attendance_id=Integer.parseInt(res.getString(0));
            db.execSQL("delete from "+ TABLE3 + " where ATTENDANCE_ID="  + Integer.parseInt(res.getString(0)));
        }

        return attendance_id;
    }

    public int getAttendanceid(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("SELECT MAX(ATTENDANCE_ID) FROM "+TABLE3,null);
        int attendance_id=0;
        while(res.moveToNext()) {
            Log.i("Present", res.getString(0));
            attendance_id=Integer.parseInt(res.getString(0));
        }

        return attendance_id;
    }

    public boolean takeAttendance(int attendanceId,int studentId){
        //Function to take attendance

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(TAB4COL2,attendanceId);
        contentValues.put(TAB4COL3,studentId);
        long result=db.insert(TABLE4,null,contentValues);
        return result != -1;

    }

    public Cursor getAllAttendanceRecords(){
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from "+TABLE4,null);

        return res;

    }

    public Cursor getClassIds(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res= db.rawQuery("select ID FROM "+ TABLE_NAME ,null);
        return res;
    }

    public Cursor getAttInfo(int attendanceId){
        SQLiteDatabase db=this.getWritableDatabase();

//        Cursor res= db.rawQuery("select "+TABLE3+".ATTENDANCE_ID,"+TABLE3+".DAY,"+TABLE3+".CLASS_ID,"+TABLE4+".STUDENT_ID FROM " +TABLE3+
//                " INNER JOIN "+TABLE4+ " ON "+TABLE3+".ATTENDANCE_ID="+TABLE4+".ATTENDANCE_ID WHERE "+TABLE3+".CLASS_ID="+classId,null);
        Cursor res= db.rawQuery("select "+TABLE3+".ATTENDANCE_ID,"+TABLE3+".DAY,"+TABLE3+".CLASS_ID,"+TABLE4+".STUDENT_ID FROM " +TABLE3+
                " INNER JOIN "+TABLE4+ " ON "+TABLE3+".ATTENDANCE_ID="+TABLE4+".ATTENDANCE_ID WHERE "+TABLE3+".ATTENDANCE_ID="+attendanceId,null);
        return res;
    }

    public Cursor getAttId(int classId){
        SQLiteDatabase db=this.getWritableDatabase();

        Cursor res=db.rawQuery("select ATTENDANCE_ID FROM "+TABLE3+ " WHERE CLASS_ID="+classId,null);
        return res;
    }

    public int getTotalStudents(int classId){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("select * FROM "+TABLE2+ " WHERE CLASS_ID="+classId,null);
        return res.getCount();
    }

    public String getClassTitle(int classId){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("SELECT TITLE FROM "+ TABLE_NAME + " where ID="  + classId,null);
        String classTitle="";
        while(res.moveToNext()) {

            classTitle=res.getString(0);

        }
        return classTitle;
    }

}
