package com.example.jacky.android_java_recyclerview_demo;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by jacky on 4/12/18.
 */

public class MyItemTouchListener extends ItemTouchHelper.SimpleCallback{
    private ItemTouchHelperCallback listener;

    public MyItemTouchListener(int dragDirs, int swipeDirs,ItemTouchHelperCallback listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return listener.onMove(recyclerView,viewHolder,target);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder,direction);
    }

    public interface ItemTouchHelperCallback{
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction);
        boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target);
    }
}
