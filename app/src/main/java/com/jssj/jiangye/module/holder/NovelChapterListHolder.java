package com.jssj.jiangye.module.holder;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jssj.jiangye.R;
import com.jssj.jiangye.api.ApiConstants;
import com.jssj.jiangye.api.RequestMethod;
import com.jssj.jiangye.base.BaseHolder;
import com.jssj.jiangye.data.NovelChapterList;
import com.jssj.jiangye.utils.InterfaceJumpContorol;
import com.jssj.jiangye.utils.ShareprefenceHelper;

import butterknife.BindView;

/**
 * Created by cc on 17-6-13.
 */
public class NovelChapterListHolder extends BaseHolder<NovelChapterList> {
    @BindView(R.id.iv_point)
    ImageView    mIvPoint;
    @BindView(R.id.tv_novel_list)
    TextView     mTvNovelList;
    @BindView(R.id.ll_novel_list)
    LinearLayout mLlNovelList;
    public NovelChapterListHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(final Context context, final NovelChapterList data, int position) {
        String stringSF = ShareprefenceHelper.getStringSF(context, ApiConstants.CHAPTERID);
        if (TextUtils.equals(data.getIsRead(),"1"))
            mIvPoint.setImageResource(R.drawable.select_gray_point);

        mTvNovelList.setText(data.getChapterTitle());

        if (TextUtils.equals(data.getNovelChapterId(), stringSF)) {

            mIvPoint.setSelected(true);
            mTvNovelList.setSelected(true);
        } else {

            mIvPoint.setSelected(false);
            mTvNovelList.setSelected(false);
        }

        mLlNovelList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InterfaceJumpContorol.InterfaceJump(context,ApiConstants.ID_TYPE_READ,data.getNovelChapterId());
            }
        });

        RequestMethod.MarkNovelReaded(data.getNovelChapterId());

    }
}
