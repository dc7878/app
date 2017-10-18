package com.cc.dc.ui.find.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cc.dc.bean.find.FindCommentBean;
import com.cc.dc.bean.find.FindDigestBean;
import com.cc.dc.dc.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dc on 2017/10/18.
 */
public class FindDigestAdapter extends RecyclerView.Adapter {

    private List<Object> data;
    private LayoutInflater inflater;
    private Context context;

    private final int ITEM_TYPE_NORMAL = 1;

    private final int ITEM_TYPE_MORE = 2;

    public FindDigestAdapter(Context context, List<Object> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view ;
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case ITEM_TYPE_NORMAL:
                view = inflater.inflate(R.layout.item_fragment_find, parent, false);
                holder = new FindNormalViewHolder(view);
                break;
            case ITEM_TYPE_MORE:
                view = inflater.inflate(R.layout.item_fragment_find_comment, parent, false);
                holder = new FindCommentViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemType = getItemViewType(position);
        switch (itemType) {
            case ITEM_TYPE_NORMAL:
                FindNormalViewHolder normalViewHolder = (FindNormalViewHolder) holder;
                FindDigestBean bean = (FindDigestBean) data.get(position);
                normalViewHolder.tvName.setText(bean.getNickName());
                break;
            case ITEM_TYPE_MORE:
                FindCommentViewHolder moreViewHolder = (FindCommentViewHolder) holder;
                FindCommentBean commentBean = (FindCommentBean) data.get(position);
                moreViewHolder.tvInfo.setText(commentBean.getContent());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position) instanceof FindDigestBean) {
            return ITEM_TYPE_NORMAL;
        } else if (data.get(position) instanceof FindCommentBean) {
            return ITEM_TYPE_MORE;
        }
        return ITEM_TYPE_NORMAL;
    }

    public class FindNormalViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_desc)
        TextView tvDesc;

        public FindNormalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class FindCommentViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_info)
        TextView tvInfo;

        public FindCommentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
