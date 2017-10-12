package com.fithsemproject.cs.teachersassistant;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Dell on 6/15/2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private Context context;
    private ArrayList<sItem> ItemList;

    public ItemAdapter(Context context, ArrayList<sItem> ItemList) {
        this.context = context;
        this.ItemList = ItemList;

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.from(parent.getContext()).inflate(R.layout.final_card, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);

        return itemViewHolder;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        sItem item = ItemList.get(position);
//        Picasso.with(context)
//                .load(item.img)
//                .placeholder(R.drawable.ggg)
//                .error(android.R.drawable.stat_notify_error)
//                .into(holder.ivImg);


        holder.tvText.setText(item.title);
        holder.dateText.setText(item.Department);
        //holder.tvText.setText(item.id);

        // holder.dateText.setText(dateCalculator(item.date));
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

        public ItemViewHolder(View itemView) {
            super(itemView);
            cvItem = (CardView) itemView.findViewById(R.id.cvItem);
            //ivImg = (ImageView) itemView.findViewById(R.id.ivMainImage);
            tvText = (TextView) itemView.findViewById(R.id.tvTitle);
            dateText = (TextView) itemView.findViewById(R.id.dateText);
        }

    }

}
