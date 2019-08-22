package com.lawerance.bakingapp.Adapters;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.lawerance.bakingapp.CakeResponse.cake;
import com.lawerance.bakingapp.R;

import java.util.ArrayList;
import java.util.List;


public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder>  {

    private ArrayList<cake> mItems;
    private OnItemClickListener onItemClickListener;
    private Context mContext;
    public RecycleViewAdapter(ArrayList<cake> items) {
        this.mItems = items;
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.food_item_layout, parent, false);
        mContext=parent.getContext();
        return new ViewHolder(v);

    }

    public void updateData(List<cake> viewModels) {
        mItems.clear();
        mItems.addAll(viewModels);
        notifyDataSetChanged();
    }
    public void addItem(int position, cake viewModel) {
        mItems.add(position, viewModel);
        notifyItemInserted(position);
    }

    public void removeItem(int position) {
        mItems.remove(position);
        notifyItemRemoved(position);
    }



    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        cake cake = mItems.get(position);
        holder.mName.setText(cake.getName());
        if(position==0)
        holder.mCakemage.setImageResource(R.drawable.nutellapie);
        else if(position==1)
            holder.mCakemage.setImageResource(R.drawable.brownies);
        else if(position==2)
            holder.mCakemage.setImageResource(R.drawable.yellowcake);
        else if(position==3)
            holder.mCakemage.setImageResource(R.drawable.cheesecake);





    }
    public cake getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


    protected static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mCakemage;
        public TextView mName;

        public ViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.tv_food_name);
            mCakemage=itemView.findViewById(R.id.iv_food_image);


        }
    }
public ArrayList<cake> getCakes(){
        return this.mItems;
}

    public interface OnItemClickListener {

        void onItemClick(View view, ViewModel viewModel);

    }
}