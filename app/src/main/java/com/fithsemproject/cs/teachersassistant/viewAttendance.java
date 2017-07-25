package com.fithsemproject.cs.teachersassistant;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fithsemproject.cs.teachersassistant.dummy.DatabaseHelper;
import com.fithsemproject.cs.teachersassistant.dummy.Table_view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class viewAttendance extends Fragment {

    private RecyclerView recyclerView;
    DatabaseHelper myDb;
    ArrayList<attendance_records> list;
    public viewAttendance() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView= inflater.inflate(R.layout.fragment_view_attendance, container, false);

        recyclerView=(RecyclerView) rootView.findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);



        refreshList();
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent=new Intent(getContext(),Table_view.class);
                        //Get class id and send it to Student Activity
                        Bundle b = new Bundle();
                        b.putSerializable("map", list.get(position).map);
                        b.putInt("classId", list.get(position).classId);

                        intent.putExtras(b);
                        startActivity(intent);

                    }

                    @Override
                    public void onLongItemClick(View view, final int position) {
                        // do whatever

                    }
                })
        );

        return rootView;
    }

    public void refreshList(){
        list=new ArrayList<>();


        myDb=new DatabaseHelper(getContext());
        int classid;

        Cursor res= myDb.getClassIds();
        if(res.getCount()==0){
            Toast.makeText(getContext(),"Unable to link database",Toast.LENGTH_LONG).show();
        }else{
            StringBuffer buffer=new StringBuffer();
            while(res.moveToNext()) {
                classid = Integer.parseInt((res.getString(0)));
                Cursor interres = myDb.getAttId(classid);
                int total_students=myDb.getTotalStudents(classid);
                String class_title=myDb.getClassTitle(classid);
                attendance_records attendanceRecords = new attendance_records();

                while(interres.moveToNext()){

                Cursor newres = myDb.getAttInfo(Integer.parseInt(interres.getString(0)));
                //attendance_records attendanceRecords = new attendance_records();
                    attendanceRecords.studentId=new ArrayList<Integer>();
                int x = 0;
                while (newres.moveToNext()) {
//                    Log.i("attrecords", newres.getString(0));
//                    Log.i("attrecords", newres.getString(1));
//                    Log.i("attrecords", newres.getString(2));
//                    Log.i("attrecords", newres.getString(3));
//                    Log.d("attendanceinfo", "ATTENDANCE ID IS"+ interres.getString(0));
//                    Log.d("attendanceinfo", "Student_id IS"+ newres.getString(3));
                    attendanceRecords.attendanceId=Integer.parseInt(interres.getString(0));
                    attendanceRecords.date=newres.getString(1);
                    attendanceRecords.classId=Integer.parseInt(newres.getString(2));
                    attendanceRecords.studentId.add(Integer.parseInt(newres.getString(3)));
                    attendanceRecords.map.put(Integer.parseInt(interres.getString(0)),attendanceRecords.studentId);
                    attendanceRecords.total_students=total_students;
                    attendanceRecords.classTitle=class_title;
                }
                    // list.add(attendanceRecords);
            }
                list.add(attendanceRecords);
            }
        }

        //Get database instance
        //ItemAdapter adapter = new ItemAdapter(getContext(), list);
        attendanceViewAdapter adapter=new attendanceViewAdapter(getContext(),list);
        recyclerView.setAdapter(adapter);
        myDb.close();
    }
}
