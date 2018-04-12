package com.example.jacky.android_java_recyclerview_demo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

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

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        //我們可以在這個方法內實現我們自定義的交互規則或者自定義的動畫效果。
        getDefaultUIUtil().onDraw(c, recyclerView, ((MyAdapter.MyViewHolder)viewHolder).item, dX, dY, actionState, isCurrentlyActive);
        if (dX > 0) { // 向左滑动是的提示
            ((MyAdapter.MyViewHolder) viewHolder).background.setBackgroundResource(R.color.colorDone);
            ((MyAdapter.MyViewHolder) viewHolder).done.setVisibility(View.VISIBLE);
            ((MyAdapter.MyViewHolder) viewHolder).schedule.setVisibility(View.GONE);
        }
        if (dX < 0) { // 向右滑动时的提示
            ((MyAdapter.MyViewHolder) viewHolder).background.setBackgroundResource(R.color.colorSchedule);
            ((MyAdapter.MyViewHolder) viewHolder).schedule.setVisibility(View.VISIBLE);
            ((MyAdapter.MyViewHolder) viewHolder).done.setVisibility(View.GONE);
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        //用戶操作完畢某個item並且其動畫也結束後會調用該方法，一般我們在該方法內恢復ItemView的初始狀態，防止由於復用而產生的顯示錯亂問題。
        getDefaultUIUtil().clearView(((MyAdapter.MyViewHolder) viewHolder).item);
        ((MyAdapter.MyViewHolder) viewHolder).background.setBackgroundColor(Color.TRANSPARENT);
        ((MyAdapter.MyViewHolder) viewHolder).schedule.setVisibility(View.GONE);
        ((MyAdapter.MyViewHolder) viewHolder).done.setVisibility(View.GONE);
    }

    /*
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        //從靜止狀態變為拖拽或者滑動的時候會回調該方法，參數actionState表示當前的狀態。
    }*/
}
