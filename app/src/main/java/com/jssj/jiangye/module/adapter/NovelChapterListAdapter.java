package com.jssj.jiangye.module.adapter;

import android.content.Context;
import android.view.View;

import com.jssj.jiangye.R;
import com.jssj.jiangye.base.BaseHolder;
import com.jssj.jiangye.base.DefaultAdapter;
import com.jssj.jiangye.data.NovelChapterList;
import com.jssj.jiangye.module.holder.NovelChapterListHolder;

/**
 * Created by cc on 17-6-13.
 */
public class NovelChapterListAdapter extends DefaultAdapter<NovelChapterList> {


    public NovelChapterListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseHolder<NovelChapterList> getHolder(View v, int viewType) {
        return new NovelChapterListHolder(v);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.item_novelread_list;
    }
}
