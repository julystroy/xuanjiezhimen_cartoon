package com.jssj.jiangye.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.jssj.jiangye.R;


/**
 * Created by cc on 16-10-25.
 */
 public class TwoLineDialog extends Dialog implements View.OnClickListener {

    private String      text1;
    private String      text2;
    private Context     context;
    private TextView    tvText1;
    private TextView    tvText2;
    private TextView    tvCancel;
    private buttonClick buttonClickListener;


    /***
     构造方法
     @param context

     */


    public TwoLineDialog(Context context, String text,String text2) {
        super(context, R.style.DialogStyleDesc);
        this.context = context;

        this.text1 = text;
        this.text2 = text2;
        //修改显示位置 本质就是修改 WindowManager.LayoutParams让内容水平居中 底部对齐
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        //android:gravity=bottom|center_horizonal
        attributes.gravity= Gravity.BOTTOM;
        getWindow().setAttributes(attributes);
    }



    /*** 方法
     @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_line_dialog);
        initView();
    }

    private void initView() {


        tvText1 = (TextView) findViewById(R.id.btn_text1);
        tvText2 = (TextView) findViewById(R.id.btn_text2);
        tvCancel = (TextView) findViewById(R.id.btn_cancel);

        if (text1!=null)
            tvText1.setText(text1);
        if (text2 != null)
            tvText2.setText(text2);

        tvText1.setOnClickListener(this);
        tvText2.setOnClickListener(this);
        tvCancel.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_text1:
                if (buttonClickListener != null) {
                    buttonClickListener.onButtonClickListener(text1);
                }
                dismiss();
                break;
            case R.id.btn_text2:
                if (buttonClickListener != null) {
                    buttonClickListener.onButtonClickListener(text2);
                }
                dismiss();
                break;
            case R.id.btn_cancel:
                dismiss();
                break;

        }
    }
public interface buttonClick{
    void onButtonClickListener(String text0);
}

    public void setOnButtonClickListener(buttonClick buttonClickListener){
        this.buttonClickListener=buttonClickListener;
    }
}
