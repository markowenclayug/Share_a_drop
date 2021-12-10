package com.example.shareadrop;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class stockAdapter extends RecyclerView.Adapter<stockAdapter.MyViewHolder> {
    private Stocks2 activity;
    private List<stockmodel> mList;

    public stockAdapter(Stocks2 activity , List<stockmodel> mList){
        this.activity = activity;
        this.mList = mList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.itemstock , parent, false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.date.setText(mList.get(position).getDate());
        holder.bestbefore.setText(mList.get(position).getBestbefore());
        holder.ounce.setText(mList.get(position).getOunce());
        holder.bags.setText(mList.get(position).getBags());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView date, bestbefore, ounce, bags;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            date = itemView.findViewById (R.id.date_text);
            bestbefore = itemView.findViewById (R.id.best_text);
            ounce = itemView.findViewById (R.id.ounce_text);
            bags = itemView.findViewById (R.id.bags_text);
        }
    }
}
