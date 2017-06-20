package com.jssj.jiangye.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jssj.jiangye.utils.KnifeUtil;


/**
 */
public abstract class BaseHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected OnViewClickListener mOnViewClickListener = null;
    protected final String TAG = this.getClass().getSimpleName();
    public BaseHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);//点击事件

        KnifeUtil.bindTarget(this, itemView);//绑定
    }



    /**
     * 设置数据
     * 刷新界面
     *
     * @param
     * @param position
     */
    public abstract void setData(Context context,T data, int position);


    /**
     * 释放资源
     */
    protected void onRelease(){

    }

    @Override
    public void onClick(View view) {
        if (mOnViewClickListener != null) {
            mOnViewClickListener.onViewClick(view, this.getPosition());
        }
    }

    public interface OnViewClickListener {
        void onViewClick(View view, int position);
    }

    public void setOnItemClickListener(OnViewClickListener listener) {
        this.mOnViewClickListener = listener;
    }
}
