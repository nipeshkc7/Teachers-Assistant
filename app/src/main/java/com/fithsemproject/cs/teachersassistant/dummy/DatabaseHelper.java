package com.fithsemproject.cs.teachersassistant.dummy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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
    public static final String TAB3COL3="DATE";

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
        db.execSQL("create table " + TABLE4 + "(ATI_ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ATTENDANCE_ID INTEGER NOT NULL, STUDENT_ID INTEGER NOT NULL, FOREIGN KEY(ATTENDANCE_ID) REFERENCES attendance_table(ATTENDANCE_ID), FOREIGN KEY(STUDENT_ID) REFERENCES student_table(STUDENT_ID) )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
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
    public boolean takeAttendance(int attendanceId,int studentId){
        //Function to take attendance

        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(TAB4COL2,attendanceId);
        contentValues.put(TAB4COL3,studentId);
        long result=db.insert(TABLE4,null,contentValues);
        return result != -1;

    }

}
