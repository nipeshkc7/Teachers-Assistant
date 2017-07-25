package com.fithsemproject.cs.teachersassistant;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.fithsemproject.cs.teachersassistant.dummy.DatabaseHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Dell on 6/15/2017.
 */

public class attendanceViewAdapter extends RecyclerView.Adapter<attendanceViewAdapter.ItemViewHolder> {

    private Context context;
    private ArrayList<attendance_records> ItemList;

    public attendanceViewAdapter(Context context, ArrayList<attendance_records> ItemList) {
        this.context = context;
        this.ItemList = ItemList;

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.attendance_view_card, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);

        return itemViewHolder;
    }


    @SuppressWarnings("deprecation")

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        attendance_records item = ItemList.get(position);
//        Picasso.with(context)
//                .load(item.img)
//                .placeholder(R.drawable.ggg)
//                .error(android.R.drawable.stat_notify_error)
//                .into(holder.ivImg);

        int total_attendance_days=0;
        int no_of_attendees=0;
        int no_of_students_in_class=item.total_students;
        float avg_attendance;
        holder.tvText.setText(item.classTitle);
        float total_average=0;
        Iterator it = item.map.entrySet().iterator();
        String att="";
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            //Log.i("keyzz ",""+pair.getKey());
            att=att+pair.getKey().toString()+',';
            //Log.i("valuezz",""+pair.getValue());
            total_attendance_days++;
            no_of_attendees=((ArrayList<Integer>)pair.getValue()).size();
            //Log.i("noOfAttendees",""+no_of_attendees);
            //Log.i("totalStudents",""+no_of_students_in_class);
            //Log.i("attendancePercentage",""+((float)no_of_attendees/(float)no_of_students_in_class)*100);
            total_average=total_average+((float)no_of_attendees/(float)no_of_students_in_class*100);

          //  it.remove(); // avoids a ConcurrentModificationException
        }
        //Log.i("totalStudents",""+no_of_students_in_class);
        avg_attendance=total_average/(float)total_attendance_days;
       // Log.i("averageattendance",""+avg_attendance +"%");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            holder.progressBar.setProgress((int)avg_attendance,true);
        }


//        holder.dateText.setText("No of attendance taken"+item.attendanceId);
        holder.dateText.setText("Average attendance: "+ (int)avg_attendance +"%");

    }


    @Override
    public int getItemCount() {

        if (ItemList != null) {
            return ItemList.size();
        }
        return 0;
    }

    //Viewholder Class
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        CardView cvItem;
        ImageView ivImg;
        TextView tvText;
        TextView dateText;
        ProgressBar progressBar;

        public ItemViewHolder(View itemView) {
            super(itemView);
            cvItem = (CardView) itemView.findViewById(R.id.cvItem);
            //ivImg = (ImageView) itemView.findViewById(R.id.ivMainImage);
            tvText = (TextView) itemView.findViewById(R.id.tvTitle);
            dateText = (TextView) itemView.findViewById(R.id.dateText);
            progressBar=(ProgressBar) itemView.findViewById(R.id.progressBar);
        }

    }

}
