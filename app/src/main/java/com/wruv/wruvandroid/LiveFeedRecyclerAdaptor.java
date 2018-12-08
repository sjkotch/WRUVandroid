package com.wruv.wruvandroid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseObject;

import java.util.Date;
import java.util.List;

public class LiveFeedRecyclerAdaptor extends RecyclerView.Adapter<LiveFeedRecyclerAdaptor.ViewHolder> {
    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    private List<ParseObject> scheduleObjs;


    // data is passed into the constructor
    LiveFeedRecyclerAdaptor(Context context, List<ParseObject> data) { //List<ParseObject>
        this.mInflater = LayoutInflater.from(context);
        //this.mData = data;
        this.scheduleObjs = data;

    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cell_live_feed_song, parent, false);
        return new ViewHolder(view);
    }


    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //String animal = mData.get(position);
        String songText = scheduleObjs.get(position).getString("song");
        String artistText = scheduleObjs.get(position).getString("artist");
        String albumText = scheduleObjs.get(position).getString("album");
        Date timeText = scheduleObjs.get(position).getCreatedAt();
        long currentDateLong = timeText.getTime();

        holder.songTextView.setText(songText);
        holder.artistTextView.setText(artistText);
        holder.albumTextView.setText(albumText);
        holder.timeTextView.setText(DateUtils.getRelativeTimeSpanString(currentDateLong));
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return scheduleObjs.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        public TextView songTextView;
        public TextView artistTextView;
        public TextView albumTextView;
        public TextView timeTextView;



        ViewHolder(View itemView) {
            super(itemView);
            songTextView = itemView.findViewById(R.id.txtSong);
            artistTextView = itemView.findViewById(R.id.txtArtist);
            albumTextView = itemView.findViewById(R.id.txtAlbum);
            timeTextView = itemView.findViewById(R.id.txtTime);

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

