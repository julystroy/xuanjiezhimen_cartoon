package com.jssj.jiangye.module.adapter;

import android.content.Context;
import android.view.View;

import com.jssj.jiangye.R;
import com.jssj.jiangye.base.BaseHolder;
import com.jssj.jiangye.base.DefaultAdapter;
import com.jssj.jiangye.data.StartsNewsData;
import com.jssj.jiangye.module.holder.StartsNewsHolder;

/**
 * Created by cc on 17-6-8.
 */
public class StartsNewsAdapter extends DefaultAdapter<StartsNewsData> {

    public StartsNewsAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseHolder<StartsNewsData> getHolder(View v, int viewType) {
        return new StartsNewsHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_imag_text;
    }
}
