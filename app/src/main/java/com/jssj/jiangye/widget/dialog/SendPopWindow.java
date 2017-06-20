package com.jssj.jiangye.widget.dialog;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jssj.jiangye.R;
import com.jssj.jiangye.api.ApiConstants;
import com.jssj.jiangye.module.someui.ComposeActivity;


public class SendPopWindow extends PopupWindow implements View.OnClickListener {


    private  Context context;

    @SuppressLint("InflateParams")
    public SendPopWindow(Context context) {
        this.context = context;
        LayoutInflater inflater = (LayoutInflater) context
                                  .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View content = inflater.inflate(R.layout.popupwindow_send, null);

        // 设置SelectPicPopupWindow的View
        this.setContentView(content);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(200);
        // 设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        this.setOutsideTouchable(true);
        // 刷新状态
        this.update();
        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0000000000);
        // 点back键和其他地方使其消失,设置了这个才能触发OnDismisslistener ，设置其他控件变化等操作
        this.setBackgroundDrawable(dw);

        // 设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.AnimationPreview);

        TextView sendState = (TextView) content.findViewById(R.id.send_state);
        TextView sendNote = (TextView) content.findViewById(R.id.send_note);

        sendNote.setOnClickListener(this);
        sendState.setOnClickListener(this);


    }

    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent, 0, 0);
        } else {
            this.dismiss();
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_state:
                Intent intent = new Intent(context,ComposeActivity.class);
                intent.putExtra(ApiConstants.COMPOSE,ApiConstants.SEND);
                context.startActivity(intent);
                SendPopWindow.this.dismiss();
                break;
            case R.id.send_note:
                Intent intent2 = new Intent(context,ComposeActivity.class);
                intent2.putExtra(ApiConstants.COMPOSE,1);
                context.startActivity(intent2);
                SendPopWindow.this.dismiss();
                break;
        }
    }
}
