package com.example.mytasks.helper;

import android.content.Context;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerItemClickListener implements RecyclerView.OnItemTouchListener {

    private OnItemClickListener onItemClickListener;
    GestureDetector gestureDetector;

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View view = rv.findChildViewUnder(e.getX(), e.getY());
        if(view != null && onItemClickListener != null && gestureDetector.onTouchEvent(e)){
            onItemClickListener.onItemClick(view, rv.getChildAdapterPosition(view));
            return true;
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
    public interface OnItemClickListener extends AdapterView.OnItemClickListener{
        public void onItemClick(View view, int position);
        public void onLongItemClick(View view, int position);
    }

    public RecyclerItemClickListener(Context context, final RecyclerView recyclerView, OnItemClickListener listener){
        onItemClickListener = listener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
           @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if(view != null && onItemClickListener != null){
                    onItemClickListener.onLongItemClick(view, recyclerView.getChildAdapterPosition(view));
                }
            }
        });

    }
}











