package com.cc.dc.ui.follow.adapter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cc.dc.bean.follow.FollowBean;
import com.cc.dc.bean.follow.FollowCommentBean;
import com.cc.dc.dc.R;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dc on 2017/10/18.
 */
public class FollowRecyclerViewAdapter extends RecyclerView.Adapter {

    private List<Object> data;
    private LayoutInflater inflater;
    private Context context;

    private final int ITEM_TYPE_NORMAL = 1;

    private final int ITEM_TYPE_MORE = 2;

    public FollowRecyclerViewAdapter(Context context, List<Object> data) {
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
                view = inflater.inflate(R.layout.item_fragment_follow, parent, false);
                holder = new FollowNormalViewHolder(view);
                break;
            case ITEM_TYPE_MORE:
                view = inflater.inflate(R.layout.item_fragment_follow_comment, parent, false);
                holder = new FollowMoreViewHolder(view);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int itemType = getItemViewType(position);
        switch (itemType) {
            case ITEM_TYPE_NORMAL:
                FollowNormalViewHolder normalViewHolder = (FollowNormalViewHolder) holder;
                FollowBean bean = (FollowBean) data.get(position);
                normalViewHolder.tvName.setText(bean.getName());
                normalViewHolder.tvDesc.setText(bean.getDesc());
                break;
            case ITEM_TYPE_MORE:
                FollowMoreViewHolder moreViewHolder = (FollowMoreViewHolder) holder;
                FollowCommentBean commentBean = (FollowCommentBean) data.get(position);
                moreViewHolder.tvInfo.setText(commentBean.getInfo());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position) instanceof FollowBean) {
            return ITEM_TYPE_NORMAL;
        } else if (data.get(position) instanceof FollowCommentBean) {
            return ITEM_TYPE_MORE;
        }
        return ITEM_TYPE_NORMAL;
    }

    public class FollowNormalViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_name)
        TextView tvName;
        @Bind(R.id.tv_desc)
        TextView tvDesc;

        public FollowNormalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class FollowMoreViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_info)
        TextView tvInfo;

        public FollowMoreViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
