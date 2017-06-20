package com.jssj.jiangye.utils;

import android.text.Html;
import android.text.Spanned;
import android.widget.TextView;

import com.jssj.jiangye.R;


/**
 * Created by cc
 *
 * 文字
 * 用户名  0  黑色
 *        uid   红色
 * Title  1
 * 一级评论  2
 * 二级评论 3
 * 其他字体（@人蓝色）
 *
 */

public class TextColorHelper {
    public static void setColor(TextView textView, int level) {

        if (level == 1) {// admin or editor
            textView.setTextColor(textView.getContext().getResources().getColor(R.color.black));
        } else {
            textView.setTextColor(textView.getContext().getResources().getColor(R.color.default_nickname));
            String currentName = textView.getText().toString();
            Spanned coloredName = Html.fromHtml(currentName);
            textView.setText(coloredName);
        }
    }


}
