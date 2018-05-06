package com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.R;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.activity.HistoryDetailActivity;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.data.RecordData;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.util.ImageUtil;

import java.util.List;



public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private static final String TAG = "HistoryAdapter";

    public List<RecordData> historyList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView historyImg;

        private LinearLayout linearLayout;
        
        private TextView stapleTxt;
        
        private TextView dateTxt;

        public ViewHolder(View view) {
            super(view);
            
            historyImg	= (ImageView) (view.findViewById(R.id.history_item_img));
            linearLayout= (LinearLayout) (view.findViewById(R.id.history_item_layout));
            stapleTxt 	= (TextView) (view.findViewById(R.id.history_item_staple));
            dateTxt		= (TextView) (view.findViewById(R.id.history_item_date));
        }
    }

    public HistoryAdapter(List<RecordData> historyList) {
        this.historyList = historyList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startHistoryActivity(parent.getContext(), historyList.get(
            		   viewHolder.getAdapterPosition()));
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecordData history = historyList.get(position);
        ImageUtil.setImageBg(holder.historyImg, history.getImg(), R.mipmap.healthy_food);
        holder.stapleTxt.setText(history.getStaple());
        holder.dateTxt.setText(history.getDate());
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }


    // start food detail activity
    private void startHistoryActivity(Context context, RecordData history) {
        Intent intent = new Intent(context, HistoryDetailActivity.class);
        intent.putExtra("history", history);
        context.startActivity(intent);
    }
}
