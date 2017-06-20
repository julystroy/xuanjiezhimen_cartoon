package com.jssj.jiangye.module.holder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jssj.jiangye.R;
import com.jssj.jiangye.base.BaseHolder;
import com.jssj.jiangye.data.StoreData;
import com.jssj.jiangye.utils.ImageLoaderUtils;
import com.jssj.jiangye.widget.countdown.CountdownView;
import com.jssj.jiangye.widget.dialog.CardDialog;

import butterknife.BindView;

/**
 * Created by cc on 17-6-14.
 */
public class StoreHolder extends BaseHolder<StoreData> {

    @BindView(R.id.iv_card_img)
    ImageView      mIvCardImg;
    @BindView(R.id.tv_card_desc)
    TextView       mTvCardDesc;
    @BindView(R.id.tv_rest_time)
    TextView        mTvRestTime;
    @BindView(R.id.cv_countdownView)
    CountdownView  mCvCountdownView;
    @BindView(R.id.tv_card_paycount)
    TextView        mTvCardPaycount;
    @BindView(R.id.ll_buy_card)
    RelativeLayout mLlBuyCard;

    public StoreHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void setData(final Context context, final StoreData data, int position) {

        ImageLoaderUtils.display(context,mIvCardImg,data.getCoverImgUrl());
        mTvCardDesc.setVisibility(View.VISIBLE);
        mTvCardDesc.setText(data.getProductDesc());
        mTvCardPaycount.setText(data.getSalePrice()+"银两");
        mLlBuyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CardDialog dialog = new CardDialog(context,data.getProductId(),data.getSalePrice(),data.getCoverImgUrl(),data.getProductDesc());
                dialog.setCanceledOnTouchOutside(true);
                dialog.show();
            }
        });

    }
}
