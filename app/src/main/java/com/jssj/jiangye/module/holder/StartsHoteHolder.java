package com.jssj.jiangye.module.holder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jssj.jiangye.R;
import com.jssj.jiangye.api.ApiConstants;
import com.jssj.jiangye.base.BaseHolder;
import com.jssj.jiangye.data.StartsHotData;
import com.jssj.jiangye.module.detail.StarHotDetailActivity;
import com.jssj.jiangye.utils.ImageLoaderUtils;
import com.jssj.jiangye.utils.ToastUitl;

import butterknife.BindView;

/**
 * Created by cc on 17-6-12.
 */
public class StartsHoteHolder extends BaseHolder<StartsHotData> {
    @BindView(R.id.itemiv_hot_star)
    ImageView      mItemivHotStar;
    @BindView(R.id.star_hot_name)
    TextView       mStarHotName;
    @BindView(R.id.star_hot_charm_num)
    TextView       mStarHotCharmNum;
    @BindView(R.id.send_flower)
    ImageView      mSendFlower;
    @BindView(R.id.item_hot_star_desc)
    RelativeLayout mLinearLayout;
    public StartsHoteHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(final Context context, final StartsHotData data, int position) {
        ImageLoaderUtils.display(context,mItemivHotStar,data.getStarPhotoUrl());
        mStarHotName.setText(data.getStarName());
        mStarHotCharmNum.setText("魅力值："+data.getCharmValues());
        mLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StarHotDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(ApiConstants.TYPE_CHARM_STAR,data);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        mSendFlower.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUitl.showLong("send");
            }
        });
    }
}
