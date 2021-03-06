package com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.R;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.activity.FoodDetailActivity;
import com.example.mac.finalproject_healthyfoodhelper_yupzhijingl.data.Food;

import java.util.List;



public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    public List<Food> foodList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView foodImg;

        private TextView nameTxt;

        public ViewHolder(View view) {
            super(view);
            foodImg = (ImageView) (view.findViewById(R.id.food_img));
            nameTxt = (TextView) (view.findViewById(R.id.food_name));
        }
    }

    public FoodAdapter(List<Food> foodList) {
        this.foodList = foodList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.nameTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startFoodActivity(parent.getContext(), foodList.get(viewHolder.getAdapterPosition()));
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.foodImg.setBackgroundResource(food.getImgId());
        holder.nameTxt.setText(food.getName());
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }


    // start food detail activity
    private void startFoodActivity(Context context, Food food) {
        Intent intent = new Intent(context, FoodDetailActivity.class);
        intent.putExtra("food", food);
        context.startActivity(intent);
    }
}
