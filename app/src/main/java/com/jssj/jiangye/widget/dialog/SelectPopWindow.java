package com.jssj.jiangye.widget.dialog;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jssj.jiangye.R;
import com.jssj.jiangye.api.ApiConstants;
import com.jssj.jiangye.utils.UIutils;

import java.util.ArrayList;
import java.util.List;


public class SelectPopWindow extends PopupWindow {

    List<String> mList = new ArrayList<>();
    @SuppressLint("InflateParams")
    public SelectPopWindow( Context context,String message) {
        LayoutInflater inflater = (LayoutInflater) context
                                  .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View content = inflater.inflate(R.layout.popupwindow_select, null);

        // 设置SelectPicPopupWindow的View
        this.setContentView(content);
        // 设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(LayoutParams.MATCH_PARENT);
        // 设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(LayoutParams.WRAP_CONTENT);
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

       ListView mLv = (ListView) content.findViewById(R.id.lv);

        if (mList!=null)
            mList.clear();
        if (TextUtils.equals(message, ApiConstants.REFRESH_HOME)) {
            mList.add(ApiConstants.TYPE_ALL);
            mList.add(ApiConstants.TYPE_READ);
            mList.add(ApiConstants.TYPE_NEWS);
            mList.add(ApiConstants.TYPE_ACTION);
        } else {
            mList.add(ApiConstants.TYPE_BOOK);
            mList.add(ApiConstants.TYPE_START);
            mList.add(ApiConstants.TYPE_READ);
            mList.add(ApiConstants.TYPE_NEWS);
            mList.add(ApiConstants.TYPE_ACTION);
            mList.add(ApiConstants.TYPE_HOT);
        }

        StringAdpter adpter = new StringAdpter(mList,context);
        adpter.onItemClickListener(new StringAdpter.setOnItemClickListener() {
            @Override
            public void setItemClick(int i) {
                UIutils.RefreshData(i,ApiConstants.REFRESH_HOME);
                SelectPopWindow.this.dismiss();
            }
        });
        mLv.setAdapter(adpter);
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

    static class StringAdpter extends BaseAdapter{
        private final List<String> mData;
        private final Context context;
        private setOnItemClickListener onItemClickListener;

        public StringAdpter(List<String> list,Context context) {
            this.mData = list;
            this.context = context;
        }
        @Override
        public int getCount() {
            return mData!=null?mData.size():0;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            view = LayoutInflater.from(context).inflate(R.layout.item, null);
            String item = mData.get(i);
            ViewHold    viewHold = new ViewHold(view);
            viewHold.mNum.setText(item);
            viewHold.llnum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.setItemClick(i);
                    }
                }
            });
            return view;
        }

       public  interface setOnItemClickListener{
            void setItemClick(int i);
        }

        public void onItemClickListener(setOnItemClickListener onItemClickListener) {
            this.onItemClickListener= onItemClickListener;
        }
    }



  static   class ViewHold{
        public TextView     mNum;
        public CardView llnum;

        public ViewHold(View view) {
            mNum= (TextView) view.findViewById(R.id.text);
            llnum= (CardView) view.findViewById(R.id.ll_item);
        }
    }
}
