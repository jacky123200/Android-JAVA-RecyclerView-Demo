package com.example.jacky.android_java_recyclerview_demo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jacky on 4/12/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private List<String> mDataset = new ArrayList<String>();  //資料集
    private Context context;

    public MyAdapter(Context context){
        this.context = context;
        initDataSet();
    }

    private void initDataSet(){
        for(int i = 0; i < 100; i++){
            mDataset.add(Integer.toString(i));
        }
    }

    public void removeItem(int position){
        mDataset.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,mDataset.size());
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //return evey item view holder by inflate layout R.layout.item
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //當物件顯示於畫面時被調用，可利用此方法更新該物件之內容。
        holder.mTextView.setText(mDataset.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Item click : " + mDataset.get(position),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //ViewHolder class 主要是用於連接layout (每行item)
        public TextView mTextView;
        public ImageView mImageView;
        public MyViewHolder(View v) {
            super(v);
            mTextView = (TextView)v.findViewById(R.id.textView);
            mImageView = (ImageView)v.findViewById(R.id.imageView);
        }
    }
}
