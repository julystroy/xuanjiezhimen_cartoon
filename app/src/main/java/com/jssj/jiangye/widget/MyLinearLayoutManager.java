package com.jssj.jiangye.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Created by cc on 17-6-12.
 */
public class MyLinearLayoutManager extends LinearLayoutManager {
    //recycleView  原生bug oldPos=12, pLpos:12 scrap [attachedScrap] tmpDetached no parent
    public MyLinearLayoutManager(Context context){ super(context); }
    public MyLinearLayoutManager(Context context, int orientation, boolean reverseLayout) { super(context,orientation,reverseLayout); }
    public MyLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) { super(context, attrs, defStyleAttr, defStyleRes); }

    @Override public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try { super.onLayoutChildren(recycler, state); }
        catch (IndexOutOfBoundsException e) {
            e.printStackTrace(); }
    }
}
