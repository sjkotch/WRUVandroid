package com.wruv.wruvandroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.List;

public class ScheduleRecyclerAdaptor extends RecyclerView.Adapter<ScheduleRecyclerAdaptor.ViewHolder> {
    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private List<ParseObject> scheduleObjs;


    // data is passed into the constructor
    ScheduleRecyclerAdaptor(Context context, List<ParseObject> data) {
        this.mInflater = LayoutInflater.from(context);
        //this.mData = data;
        this.scheduleObjs = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cell_schedule_day, parent, false);
        return new ViewHolder(view);
    }


    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //String animal = mData.get(position);
        String djText = scheduleObjs.get(position).getString("dj");
        String showText = scheduleObjs.get(position).getString("show_name");
        String descriptionText = scheduleObjs.get(position).getString("description");
        String startTimeText = scheduleObjs.get(position).getString("start_time");
        String endTimeText = scheduleObjs.get(position).getString("end_time");

        holder.djTextView.setText("with "+ djText);
        holder.showTextView.setText(showText);
        holder.descriptionTextView.setText(descriptionText);
        holder.timeView.setText(startTimeText + " - " + endTimeText);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return scheduleObjs.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        public TextView djTextView;
        public TextView showTextView;
        public TextView descriptionTextView;
        public TextView timeView;


        ViewHolder(View itemView) {
            super(itemView);
            djTextView = itemView.findViewById(R.id.DjName);
            showTextView = itemView.findViewById(R.id.show);
            descriptionTextView = itemView.findViewById(R.id.description);
            timeView = itemView.findViewById(R.id.startTime);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }


}
