package com.jssj.jiangye.module.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jssj.jiangye.R;
import com.jssj.jiangye.api.ApiConstants;
import com.jssj.jiangye.base.BaseHolder;
import com.jssj.jiangye.data.StartsNewsData;
import com.jssj.jiangye.utils.ImageLoaderUtils;
import com.jssj.jiangye.utils.InterfaceJumpContorol;
import com.jssj.jiangye.widget.LabelView;

import butterknife.BindView;

/**
 * Created by cc on 17-6-8.
 */
public class StartsNewsHolder extends BaseHolder<StartsNewsData> {
    @BindView(R.id.item_title)
    TextView     mItemTitle;
    @BindView(R.id.item_time)
    TextView      mItemTime;
    @BindView(R.id.item_comment_num)
    TextView      mItemCommentNum;
    @BindView(R.id.item_imag)
    ImageView    mItemImag;
    @BindView(R.id.item_lable)
    LabelView    mItemLable;
    @BindView(R.id.llimag_text)
    LinearLayout mLlimagText;
    public StartsNewsHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(final Context context, final StartsNewsData data, int position) {
        mItemTitle.setText(data.getNewsTitle());
        mItemTime.setText(data.getPublishTime());
        mItemCommentNum.setText(data.getCommentNum()+"评论");
        ImageLoaderUtils.display(context,mItemImag,data.getNewsCoverUrl());
        mItemLable.setText(data.getBusinessTag()+"");
        mLlimagText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InterfaceJumpContorol.InterfaceJump(context, ApiConstants.ID_TYPE_NEWS,data.getStarNewsId());
            }
        });

    }
}
