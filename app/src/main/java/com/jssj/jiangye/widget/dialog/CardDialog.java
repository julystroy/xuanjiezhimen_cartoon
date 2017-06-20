package com.jssj.jiangye.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.jssj.jiangye.R;
import com.jssj.jiangye.utils.ImageLoaderUtils;

/**
 * Created by cc on 16-10-25.
 */
 public class CardDialog extends Dialog implements View.OnClickListener {
    private Context   context;
    /***
     构造方法
     @param context

     */

    private TextView  MPay;
    private TextView  MarketsDesc;
    private TextView  MarketsCount;
    private ImageView ivCard;

    private String item_id;
    private String image_url;
    private String stone_count;
    private String desc;
    public CardDialog(Context context, String item_id, String stone_count, String image_url, String desc) {
        super(context, R.style.DialogStyleDesc);
        this.item_id = item_id;
        this.context = context;
        this.stone_count = stone_count;
        this.image_url = image_url;
        this.desc = desc;
        //修改显示位置 本质就是修改 WindowManager.LayoutParams让内容水平居中 底部对齐
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        //android:gravity=bottom|center_horizonal
        attributes.gravity= Gravity.CENTER| Gravity.CENTER_HORIZONTAL;
        getWindow().setAttributes(attributes);
    }
    /*** 方法
     @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_card_dialog);
       // LoadData();
        initData();

    }
//下载数据


    private void initData() {
         MPay= (TextView) findViewById(R.id.tv_pay_stone_count);
         MarketsDesc= (TextView) findViewById(R.id.tv_markets_desc);
         MarketsCount= (TextView) findViewById(R.id.tv_markets_count);
         ivCard= (ImageView) findViewById(R.id.iv_experience_card);
        MarketsDesc.setText(desc);
        MarketsCount.setText(stone_count+"灵石");
        if (TextUtils.equals(item_id, "3") || TextUtils.equals(item_id, "4")) {
            MarketsDesc.setTextColor(Color.parseColor("#ffffff"));
            MarketsCount.setTextColor(Color.parseColor("#ffffff"));
        } else {
            MarketsDesc.setTextColor(Color.parseColor("#000000"));
            MarketsCount.setTextColor(Color.parseColor("#000000"));
        }

        MPay.setOnClickListener(this);

        ImageLoaderUtils.display(context,ivCard,image_url);

    }

    @Override
    public void onClick(View v) {
//购买

    }

    @Override
    public void dismiss() {
        super.dismiss();
    }



}
