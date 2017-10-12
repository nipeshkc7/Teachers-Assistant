package com.fithsemproject.cs.teachersassistant;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import com.fithsemproject.cs.teachersassistant.dummy.DatabaseHelper;

import java.util.ArrayList;

import static java.security.AccessController.getContext;


//This is an activity for displaying students in a grid view
public class Students extends AppCompatActivity {
    DatabaseHelper myDb;
    GridView gridView;
    ArrayList<student_items> list;
    int classId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Intent intent=getIntent();
        Bundle b = this.getIntent().getExtras();
        classId= b.getInt("class");
        gridView = (GridView) findViewById(R.id.student_gridview);

        //Retreive real data from database and add to the list

        refreshList();

        Toast.makeText(getApplicationContext(),"Class id is"+classId,Toast.LENGTH_LONG).show();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                //Link to input students
                Intent i=new Intent(getApplicationContext(),InputStudents.class);
                Bundle b = new Bundle();
                b.putInt("class",classId);
                i.putExtras(b);
                startActivity(i);
            }
        });
    }

    @Override

    public void onResume(){
        super.onResume();
        refreshList();
    }

    public void refreshList(){
        list=new ArrayList<>();
        myDb=new DatabaseHelper(getApplicationContext());
        Cursor res= myDb.getStudentdata(classId);
        if(res.getCount()==0){
            Toast.makeText(getApplicationContext(),"Unable to link database",Toast.LENGTH_LONG).show();
        }else{
            StringBuffer buffer=new StringBuffer();
            while(res.moveToNext()) {
                  student_items StudentItem=new student_items();
                  StudentItem.setRollNo(Integer.parseInt(res.getString(3)));
                  StudentItem.setStudentName(res.getString(2));
                    //Can also set class id for each student

                list.add(StudentItem);
            }
        }

        gridView.setAdapter(new student_adapter(getApplicationContext(),list));

    }


}
