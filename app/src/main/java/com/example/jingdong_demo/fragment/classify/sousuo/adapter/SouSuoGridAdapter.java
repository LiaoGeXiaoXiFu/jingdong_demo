package com.example.jingdong_demo.fragment.classify.sousuo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jingdong_demo.R;
import com.example.jingdong_demo.fragment.classify.sousuo.bean.SousuoBean;

import java.util.List;

/**
 *
 * ━━━━━━草泥马神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃ 神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 *
 * ━━━━━━当善良的人摘下面具.你连下跪的机会都没有━━━━━━
 */

public class SouSuoGridAdapter extends RecyclerView.Adapter<SouSuoGridAdapter.MyHolder> {
    private Context context;
    private List<SousuoBean.DataBean> data;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public SouSuoGridAdapter(Context context, List<SousuoBean.DataBean> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.sousuo_grid_item_layout, parent, false);
        MyHolder myHolder = new MyHolder(inflate);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        final SousuoBean.DataBean dataBean = data.get(position);
        holder.textPrice.setText(dataBean.getTitle());
        //给原价加横线
        SpannableString spannableString = new SpannableString("原价:" + dataBean.getPrice() + "元");
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        spannableString.setSpan(strikethroughSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        holder.textTitle.setText(spannableString);
        holder.textPrices.setText("优惠价:" + dataBean.getSalenum() + "元");
        String images = dataBean.getImages();
        String[] split = images.split("\\|");
        Glide.with(context).load(split[position % split.length]).into(holder.imageView);
        holder.rr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onRecyclerViewItemClickListener != null) {
                    onRecyclerViewItemClickListener.OnItemClick(dataBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    //条目监听接口
    public interface OnRecyclerViewItemClickListener {
        void OnItemClick(SousuoBean.DataBean dataBean);
    }

    //暴露给外部调用的方法
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public void add(List<SousuoBean.DataBean> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView textTitle;
        private final TextView textPrice;
        private final TextView textPrices;
        private final RelativeLayout rr;

        public MyHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
            textTitle = itemView.findViewById(R.id.texttitle);
            textPrice = itemView.findViewById(R.id.textprice);
            textPrices = itemView.findViewById(R.id.textprices);
            rr = itemView.findViewById(R.id.rr);
        }
    }
}
