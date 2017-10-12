package com.fithsemproject.cs.teachersassistant;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.fithsemproject.cs.teachersassistant.dummy.DatabaseHelper;

import java.util.ArrayList;

public class TickBoxAttendance extends AppCompatActivity {

    Boolean attendance_commenced=false;
    int classId;
    GridView gridView;
    DatabaseHelper myDb;
    ArrayList<student_items> list;
    student_adapter SAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tick_box_attendance);

        Intent intent=getIntent();
        Bundle b = this.getIntent().getExtras();
        classId= b.getInt("class");
        gridView = (GridView) findViewById(R.id.student_gridview2);
    //Onclick listener for gridview
        refreshList();
        //Retreive real data from database and add to the list



        Toast.makeText(getApplicationContext(),"Class id is"+classId,Toast.LENGTH_LONG).show();
        final FloatingActionButton begin_attendance = (FloatingActionButton) findViewById(R.id.begin_attendance);
        final FloatingActionButton finish_attendance = (FloatingActionButton) findViewById(R.id.finish_attendance);
        begin_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                finish_attendance.setVisibility(View.VISIBLE);
                begin_attendance.setVisibility(View.GONE);
                //commence attendance
                boolean res=myDb.commenceAttendance(classId);
                    if(res){
                        Toast.makeText(getApplicationContext(),"Begin Attendance",Toast.LENGTH_LONG).show();
                        attendance_commenced=true;
                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            public void onItemClick(AdapterView<?> parent, View v,
                                                    int position, long id) {
  //

                                //SAdapter.notifyDataSetChanged();
                                //refreshList();
                                //                              ImageView imageView = (ImageView) v;
//                                imageView.setImageResource(R.drawable.ic_menu_camera);
                                //parent.getItemAtPosition(position).setImageResource(R.drawable.ic_menu_camera)

                                boolean reslt=myDb.takeAttendance(myDb.getAttendanceid(),list.get(position).studentId);
                                if (reslt){
                                    Toast.makeText(getApplicationContext(), list.get(position).studentName + ": Present",Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), list.get(position).studentName + ": Already present",Toast.LENGTH_SHORT).show();

                                }

                                list.get(position).toggleSelected();
                                Log.i("Toggle",""+list.get(position).isSelected);
                                //SAdapter.notifyDataSetChanged();

                                SAdapter.updateContent(list);
                            }



                        });
                    }else{
                        Toast.makeText(getApplicationContext(),"Cannot start taking attendance, internal error",Toast.LENGTH_LONG).show();
                    }

                 //Handle on back pressed condition
                //set onclick listener for gridview




            }
        });


        finish_attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                //Toast.makeText(getApplicationContext(),"Finish Attendance",Toast.LENGTH_LONG).show();


            }
        });
    }

    @Override

    public void onResume(){
        super.onResume();
        refreshList();
    }

    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        if(attendance_commenced) {
            int x = myDb.deleteLatestAttendance();
        }
            //Toast.makeText(getApplicationContext(),"onBackPressed deleted record with attendance id: "+x,Toast.LENGTH_LONG).show();

        super.onBackPressed();  // optional depending on your needs
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
                StudentItem.setStudentId(Integer.parseInt(res.getString(0)));
                Log.i("RefreshList",""+Integer.parseInt(res.getString(0)));
                StudentItem.setRollNo(Integer.parseInt(res.getString(3)));
                StudentItem.setStudentName(res.getString(2));
                //Can also set class id for each student

                list.add(StudentItem);
            }
        }
        SAdapter= new student_adapter(getApplicationContext(),list);
        gridView.setAdapter(SAdapter);

    }
}
