package com.jssj.jiangye.module.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jssj.jiangye.R;
import com.jssj.jiangye.api.ApiConstants;
import com.jssj.jiangye.data.NovelListData;
import com.jssj.jiangye.utils.ShareprefenceHelper;
import com.jssj.jiangye.utils.ToastUitl;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by cc on 17-6-13.
 */
public class NovelReadAdaper extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private List<NovelListData> mlist;

    private int fontSizePx;
    private int readFontSize;

    public NovelReadAdaper(Context context) {
        this.context = context;
         readFontSize = ShareprefenceHelper.getIntergerSF(context,ApiConstants.FONTSIZE);
        fontSizePx=readFontSize;

    }

    public void setData(List<NovelListData> mlIst) {
        this.mlist = mlIst;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_novelist, parent, false);
        return new HeadHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HeadHolder viewholder = (HeadHolder) holder;
        NovelListData data = mlist.get(position);
        viewholder.mNovelTitle.setText(data.getChapterTitle());
        viewholder.mNovelContent.setText(data.getChapterContent());
        viewholder.mNovelContent.setTextSize(fontSizePx);
        viewholder.mNovelAnswerQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUitl.showLong("anwser question");
            }
        });

        viewholder.mNovelComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUitl.showLong("comment");
            }
        });

        viewholder.mNovelWriteFeel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUitl.showLong("write feel");
            }
        });

        ShareprefenceHelper.SetStringSF(context,ApiConstants.TYPE_READ_LASTCHAPTER,data.getPageNo());

    }



    @Override
    public int getItemCount() {
        return mlist ==null?0:mlist.size();
    }
    public synchronized void setFontSize( int fontSizePx) {
        this.fontSizePx=fontSizePx;
        ShareprefenceHelper.SetIntergerSF(context, ApiConstants.FONTSIZE,fontSizePx);
        notifyDataSetChanged();

    }

    class HeadHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.novel_title)
        TextView          mNovelTitle;
        @BindView(R.id.novel_content)
        TextView       mNovelContent;
        @BindView(R.id.novel_answer_num)
        TextView       mNovelAnswerNum;
        @BindView(R.id.novel_answer_question)
        RelativeLayout    mNovelAnswerQuestion;
        @BindView(R.id.novel_coument_num)
        TextView       mNovelCoumentNum;
        @BindView(R.id.novel_comment)
        RelativeLayout mNovelComment;
        @BindView(R.id.novel_write_feel)
        TextView       mNovelWriteFeel;

        public HeadHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
