package com.fithsemproject.cs.teachersassistant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class manualAttendance extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_attendance);
        getSupportActionBar().setTitle("Choose class");
        android.support.v4.app.FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        manualAttendancelist Manual=new manualAttendancelist();
        ft.replace(R.id.fragment_container2,Manual);
        ft.commit();

    }
}
