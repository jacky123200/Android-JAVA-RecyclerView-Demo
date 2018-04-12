package com.example.jacky.android_java_recyclerview_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyItemTouchListener.ItemTouchHelperCallback {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initAdapter();
        initItemTouchHelper();
    }

    private void initItemTouchHelper(){
        MyItemTouchListener itemTouchListener = new MyItemTouchListener(ItemTouchHelper.UP|ItemTouchHelper.DOWN, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT,this);
        ItemTouchHelper ith = new ItemTouchHelper(itemTouchListener);
        ith.attachToRecyclerView(mRecyclerView);
    }

    private void initView(){
        mRecyclerView =(RecyclerView)findViewById(R.id.myRecyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        //mLayoutManager = new GridLayoutManager(this,defaultSpanCount);
        //mLayoutManager = new StaggeredGridLayoutManager(defaultSpanCount,StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
    }

    private void initAdapter(){
        mAdapter = new MyAdapter(MainActivity.this);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.removeItem(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        int fromPosition = viewHolder.getAdapterPosition();
        int toPosition = target.getAdapterPosition();
        mAdapter.notifyItemMoved(fromPosition, toPosition);
        return true;
    }
}
