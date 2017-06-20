package com.jssj.jiangye.module.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jssj.jiangye.R;
import com.jssj.jiangye.api.ApiConstants;
import com.jssj.jiangye.data.HomeRecord;
import com.jssj.jiangye.data.UnreadChapterCount;
import com.jssj.jiangye.utils.ImageLoaderUtils;
import com.jssj.jiangye.utils.InterfaceJumpContorol;
import com.jssj.jiangye.utils.ToastUitl;
import com.jssj.jiangye.widget.DragPointView;
import com.jssj.jiangye.widget.LabelView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by cc on 17-6-7.
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEAD     = 0x101;
    private static final int TYPE_ITEM     = 0x102;
    private static final int TYPE_ITEM_IMG = 0x103;
    private View.OnClickListener onClickButViewListener;
/*
*
*  * BUS_STAR //明星
	 * BUS_STAR_NEWS //明星资讯
	 * BUS_ARTICLE //原著解读
	 * BUS_ACTIVITY //活动
*/

    private Context                 context;
    private List<HomeRecord>        mList;
    private UnreadChapterCount response;

    public HomeAdapter(Context context) {
        this.context = context;
    }

    public void setHeadNum(UnreadChapterCount response) {
        this.response = response;
        notifyDataSetChanged();
    }
    public void setData(List<HomeRecord> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public void addAll(List<HomeRecord> list) {
        int size = this.mList.size();
        //this.mList.addAll(list);
        notifyItemRangeInserted(size + 1, list.size());
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEAD) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_home_head, parent, false);
            return new HeadHolder(view);
        }else
        if (viewType == TYPE_ITEM_IMG) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_imag, parent, false);
            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_imag_text, parent, false);
            return new ItemImagViewHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        if (viewHolder instanceof HeadHolder) {
            final HeadHolder holder = (HeadHolder) viewHolder;
            if (response !=null){
                holder.mDragPointView.setText(response.getUnreadNum());
                holder.mNovelRead.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        InterfaceJumpContorol.InterfaceJump(context,ApiConstants.ID_TYPE_READ,response.getReadpageNo());
                    }
                });
            }
            holder.mNovelDownload.setOnClickListener(onClickButViewListener);
            holder.mNovelList.setOnClickListener(onClickButViewListener);
                return;
        }
        final int pos = position - 1;
        HomeRecord record = mList.get(pos);
        if (viewHolder instanceof ItemImagViewHolder) {
            ItemImagViewHolder holder = (ItemImagViewHolder) viewHolder;
            holder.mItemCommentNum.setText(record.getCommentNum()+"评论");
            holder.mItemLable.setText(record.getBusinessTag());
            holder.mItemTime.setText(record.getCreateTime());
            holder.mItemTitle.setText(record.getBusinessTitle());
            ImageLoaderUtils.display(context,holder.mItemImag,record.getBusinessImgUrl());
            holder.llImgText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUitl.showLong("图文");
                }
            });


        } else {
            ItemViewHolder holder = (ItemViewHolder) viewHolder;
            ImageLoaderUtils.display(context, holder.mIconCover, record.getBusinessImgUrl());
            holder.mItemImagComment.setText(record.getCommentNum() + "评论");
            holder.mItemImagDesc.setText(record.getBusinessTitle());
            holder.mItemImagTime.setText(record.getCreateTime());
            holder.mTvModule.setText(record.getBusinessTag());
            holder.llItemImag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUitl.showLong("图");
                }
            });
        }

    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TYPE_HEAD;
        } else if (position !=0&&TextUtils.equals(mList.get(position-1).getBusinessCode(), ApiConstants.BUS_ACTIVITY))
            return TYPE_ITEM_IMG;
        else
            return TYPE_ITEM;
    }


    @Override
    public int getItemCount() {

        return mList == null ? 1 : mList.size()+1;
    }

    public void setOnClickButViewListener(View.OnClickListener onClickButViewListener) {
        this.onClickButViewListener = onClickButViewListener;
    }

    class ItemImagViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_title)
        TextView  mItemTitle;
        @BindView(R.id.item_time)
        TextView  mItemTime;
        @BindView(R.id.item_comment_num)
        TextView  mItemCommentNum;
        @BindView(R.id.item_imag)
        ImageView mItemImag;
        @BindView(R.id.item_lable)
        LabelView mItemLable;

        @BindView(R.id.llimag_text)
        LinearLayout llImgText;

        public ItemImagViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class HeadHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.novel_download)
        TextView mNovelDownload;
        @BindView(R.id.novel_read)
        TextView mNovelRead;
        @BindView(R.id.novel_list)
        TextView mNovelList;
        @BindView(R.id.tv_dragponit)
        DragPointView mDragPointView;

        public HeadHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.icon_cover)
        ImageView mIconCover;
        @BindView(R.id.tv_module)
        LabelView  mTvModule;
        @BindView(R.id.item_imag_desc)
        TextView  mItemImagDesc;
        @BindView(R.id.item_imag_time)
        TextView  mItemImagTime;
        @BindView(R.id.item_imag_comment)
        TextView  mItemImagComment;

        @BindView(R.id.llimag_item)
        LinearLayout llItemImag;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
