package com.cc.dc.ui.live.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cc.dc.bean.LiveBean;
import com.cc.dc.R;
import com.cc.dc.util.StringUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by dc on 2017/9/19.
 */
public class LiveRecyclerViewAdapter extends RecyclerView.Adapter<LiveRecyclerViewAdapter.LiveViewHolder> implements View.OnClickListener {

    private Context context;

    private List<LiveBean> data;

    private LayoutInflater inflater;

    private OnItemClickListener listener;

    public LiveRecyclerViewAdapter(Context context, List<LiveBean> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public LiveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_fragment_live, parent, false);
        return new LiveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LiveViewHolder holder, int position) {
        holder.tvNickName.setText(data.get(position).getNickName() +  ">>>" + data.get(position).getRoomId());
        holder.tvOnline.setText(StringUtil.getOnlineStr(data.get(position).getOnline()));
        Glide.with(context).load(data.get(position).getRoomSrc()).into(holder.imageRoom);

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onClick(View v) {
        if (null != listener) {
            int position = (int) v.getTag();
            listener.onItemClick(position);
        }
    }

    public class LiveViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image_room)
        ImageView imageRoom;
        @Bind(R.id.tv_nick_name)
        TextView tvNickName;
        @Bind(R.id.tv_online)
        TextView tvOnline;

        View itemView;

        public LiveViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
