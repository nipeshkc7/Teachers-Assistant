package com.fithsemproject.cs.teachersassistant;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.fithsemproject.cs.teachersassistant.dummy.DatabaseHelper;

import java.util.ArrayList;

import static com.fithsemproject.cs.teachersassistant.R.id.view;


/**
 * A simple {@link Fragment} subclass.
 */
public class manualAttendancelist extends Fragment {

    ItemAdapter adapter;
    private RecyclerView rvItem;
    private ArrayList<sItem> list= new ArrayList<>();
    DatabaseHelper myDb;
    public manualAttendancelist() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.w("A:","Classes fragment created");
        View rootview= inflater.inflate(R.layout.fragment_manual_attendancelist, container, false);
        rvItem=(RecyclerView) rootview.findViewById(R.id.recItem);
        rvItem.setHasFixedSize(true);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvItem.setLayoutManager(manager);


        refreshList();

        rvItem.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), rvItem ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Intent intent=new Intent(getContext(),TickBoxAttendance.class);
//                        Toast.makeText(getContext(),"Current class id is  " + list.get(position).id,Toast.LENGTH_LONG).show();
                        Bundle b = new Bundle();
                        b.putInt("class", list.get(position).id);
                        intent.putExtras(b);
                        startActivity(intent);

                    }

                    @Override
                    public void onLongItemClick(View view, final int position) {
                        // do whatever
//                        Toast.makeText(getContext(),"Long press " + list.get(position).id,Toast.LENGTH_LONG).show();
//                        AlertDialog.Builder a_builder=new AlertDialog.Builder(getContext());
//                        a_builder.setMessage("Delete this class ?")
//                                .setCancelable(true)
//                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        myDb.deleteSelected(list.get(position).id);
//                                        refreshList();
//                                    }
//                                })
//                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        dialog.cancel();
//                                    }
//                                })
//                        ;
//                        AlertDialog alert=a_builder.create();
//                        alert.setTitle("Delete ?");
//
//                        alert.show();

                    }
                })
        );

        return rootview;
    }

    @Override

    public void onResume(){
        super.onResume();
        refreshList();
    }

    public void refreshList(){
        list=new ArrayList<>();
        myDb=new DatabaseHelper(getContext());
        Cursor res= myDb.getAllData();
        if(res.getCount()==0){
            Toast.makeText(getContext(),"Unable to link database",Toast.LENGTH_LONG).show();
        }else{
            StringBuffer buffer=new StringBuffer();
            while(res.moveToNext()){
                sItem sItem=new sItem();
                sItem.setId(Integer.parseInt(res.getString(0)));
                sItem.setTitle(res.getString(1));
                sItem.setDepartment(res.getString(2));
                list.add(sItem);
            }
        }
//
//        //Get database instance
        ItemAdapter adapter = new ItemAdapter(getContext(), list);
        rvItem.setAdapter(adapter);

    }


}
