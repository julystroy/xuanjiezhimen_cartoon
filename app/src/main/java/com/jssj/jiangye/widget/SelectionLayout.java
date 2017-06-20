/**
 * Copyright 2016 JustWayward Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.jssj.jiangye.widget;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.ListPopupWindow;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jssj.jiangye.R;
import com.jssj.jiangye.data.SysModule;

import java.util.ArrayList;
import java.util.List;

/**
 *选择标题
 */
public class SelectionLayout extends LinearLayout {

    private Context      mContext;
    private LinearLayout parent;

    private OnSelectListener listener;
    private List<SysModule> dataList;

    public SelectionLayout(Context context) {
        this(context, null);
    }

    public SelectionLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SelectionLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        parent = this;
        this.mContext = context;
    }

    public void setData(List<SysModule> data) {
        this.dataList = data;
        //设置头
        if (data != null && data.size() > 0) {

                ChildView childView = new ChildView(mContext);
                LayoutParams params = new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.weight = 1;//tou
                childView.setLayoutParams(params);
                childView.setData(data);
                addView(childView);

        }
    }


    private void closeAll() {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            ChildView childView = (ChildView) getChildAt(i);
            childView.closePopWindow();
        }
    }

    class ChildView extends LinearLayout implements OnClickListener, AdapterView.OnItemClickListener {

        private LinearLayout layout;

        private ImageView ivArrow;
        private TextView  tvTitle;

        private boolean isOpen = false;

        private List<String> data = new ArrayList<>();
        private ListPopupWindow mListPopupWindow;
        private SelAdapter      mAdapter;

        Animation          operatingAnim1 = AnimationUtils.loadAnimation(mContext, R.anim.roate_0_180);
        Animation          operatingAnim2 = AnimationUtils.loadAnimation(mContext, R.anim.roate_180_360);
        LinearInterpolator lin1           = new LinearInterpolator();
        LinearInterpolator lin2           = new LinearInterpolator();

        public ChildView(Context context) {
            this(context, null);
        }

        public ChildView(Context context, AttributeSet attrs) {
            this(context, attrs, 0);
        }

        public ChildView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = (LinearLayout) inflater.inflate(R.layout.view_selection, this);

            initView();
        }

        private void initView() {
            ivArrow = (ImageView) findViewById(R.id.ivSelArrow);
            ivArrow.setScaleType(ImageView.ScaleType.MATRIX);   //required
            tvTitle = (TextView) findViewById(R.id.tvSelTitle);
            setOnClickListener(this);
            operatingAnim1.setInterpolator(lin1);
            operatingAnim1.setFillAfter(true);
            operatingAnim2.setInterpolator(lin2);
            operatingAnim2.setFillAfter(true);
        }

        List<String> mlist = new ArrayList<>();
        private void setData(List<SysModule> list) {
            if (list != null && !list.isEmpty()) {
                for (SysModule sysModule : list) {
                    mlist.add(sysModule.getBusinessName());
                }
                data.addAll(mlist);
                tvTitle.setText(mlist.get(0));
            }
        }

        public void openPopupWindow() {
            if (mListPopupWindow == null) {
                createPopupWindow();
            }
            mListPopupWindow.show();
        }

        private void createPopupWindow() {
            mListPopupWindow = new ListPopupWindow(mContext);
            mAdapter = new SelAdapter(mContext, data);
            mListPopupWindow.setAdapter(mAdapter);
            mListPopupWindow.setVerticalOffset(10);//竖直margon
            mListPopupWindow.setWidth(600);
            mListPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
            mListPopupWindow.setAnchorView(parent.getChildAt(0));
            mListPopupWindow.setForceIgnoreOutsideTouch(false);
            mListPopupWindow.setOnItemClickListener(this);
            mListPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    ivArrow.startAnimation(operatingAnim2);
                    isOpen = false;
                }
            });
            mListPopupWindow.setModal(true);
        }

        public void closePopWindow() {
            if (mListPopupWindow != null && mListPopupWindow.isShowing()) {
                mListPopupWindow.dismiss();
            }
        }

        @Override
        public void onClick(View v) {
            if (isOpen) {
                ivArrow.startAnimation(operatingAnim2);
                closePopWindow();
                isOpen = false;
            } else {
                ivArrow.startAnimation(operatingAnim1);
                openPopupWindow();
                isOpen = true;
            }
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            mAdapter.setSelPosition(position);
            tvTitle.setText(data.get(position));
            if (listener != null) {
                listener.onSelect( position,dataList.get(position) );
            }
            mListPopupWindow.dismiss();
        }


       public class SelAdapter extends BaseAdapter {
            private  Context context;
            private List<String> list;

            public SelAdapter(Context context, List<String> list) {
                this.context = context;
                this.list = list;

            }
            @Override
            public int getCount() {
                return list!=null?list.size():0;
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
            public View getView(int i, View view, ViewGroup viewGroup) {
                String s=list.get(i);
                ViewHolder  holder;

                    view = LayoutInflater.from(context).inflate(R.layout.item_selection_view, viewGroup, false);
                    holder = new ViewHolder(view);
                    view.setTag(this);

                if (!TextUtils.isEmpty(s))
                holder.tvSelect.setText(s);
                if (selPosition == i) {
                    holder.tvSelect.setTextColor(ContextCompat.getColor(mContext, R.color.light_pink));
                } else {
                    holder.tvSelect.setTextColor(ContextCompat.getColor(mContext, R.color.light_black));
                }
                return view;
            }


            int selPosition = 0;


            public void setSelPosition(int position) {
                selPosition = position;
                notifyDataSetChanged();
            }


        }
        class ViewHolder{
            private TextView tvSelect;
            public ViewHolder(View view) {
                tvSelect = (TextView) view.findViewById(R.id.tvSelTitleItem);
            }
        }
    }

    public interface OnSelectListener {
        void onSelect( int position, SysModule title);
    }

    public void setOnSelectListener(OnSelectListener listener) {
        this.listener = listener;
    }
}
