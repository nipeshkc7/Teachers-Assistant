package com.fithsemproject.cs.teachersassistant;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class Classes extends Fragment {

    private RecyclerView rvItem;
    private ArrayList<sItem> list= new ArrayList<>();
    public Classes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.w("A:","Classes fragment created");
        View rootview= inflater.inflate(R.layout.fragment_classes, container, false);
        rvItem=(RecyclerView) rootview.findViewById(R.id.rvItem);
        rvItem.setHasFixedSize(true);
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvItem.setLayoutManager(manager);
        FloatingActionButton myFab = (FloatingActionButton)  rootview.findViewById(R.id.fabAdd);

        myFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),input_data.class);
                startActivity(intent);

            }
        });
        //Create a test list
        for(int i=0;i<5;i++) {
            sItem sitem = new sItem();
            sitem.setId("Class "+ i);
            list.add(sitem);
        }
        ItemAdapter adapter = new ItemAdapter(getContext(), list);
        rvItem.setAdapter(adapter);

        return rootview;
    }

}
