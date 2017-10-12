package com.fithsemproject.cs.teachersassistant;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dell on 7/21/2017.
 */

public class student_adapter extends BaseAdapter{

    private Context context;
    //private final String[] videosList;
    private ArrayList<student_items> list;
    ImageView imageView;

    public student_adapter(Context context, ArrayList<student_items> list) {
        this.context = context;
        this.list=list;
    }



    public View getView(int position, View convertView, ViewGroup parent) {
        student_items Student_items;
        Student_items=list.get(position);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;

        ViewHolder holder;

            gridView = new View(context);
     convertView = inflater.inflate(R.layout.grid_design, null);
            holder=new ViewHolder();
            holder.img=(ImageView) convertView.findViewById(R.id.grid_item_image);
            holder.txt=(TextView) convertView
                    .findViewById(R.id.grid_item_label);
            holder.txt.setText(Student_items.studentName);
            imageView=(ImageView) holder.img.findViewById(R.id.grid_item_image);
            if(Student_items.getIsSelected()) {
                imageView.setImageResource(R.mipmap.ic_launcher );
                Log.i("Toggle","Now change to slideshow");
            }
            else {
                imageView.setImageResource(R.mipmap.ic_face );
                Log.i("Toggle","Now change to camera");
            }

                convertView.setTag(holder);


        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ImageView getItem(int position) {
        return imageView;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {
        ImageView img;
        TextView txt;
    }

    public void updateContent(ArrayList<student_items> updates) {
        this.list=updates;
        this.notifyDataSetChanged();
    }
}
