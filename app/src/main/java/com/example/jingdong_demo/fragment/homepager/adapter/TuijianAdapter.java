package com.example.jingdong_demo.fragment.homepager.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jingdong_demo.R;
import com.example.jingdong_demo.fragment.homepager.bean.GetAdBean;

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

public class TuijianAdapter extends RecyclerView.Adapter{
    private List<GetAdBean.TuijianBean.ListBean> tuijian;
    private Context context;
    private LayoutInflater inflater;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public TuijianAdapter(List<GetAdBean.TuijianBean.ListBean> tuijian, Context context) {
        this.tuijian = tuijian;
        this.context = context;
        inflater = LayoutInflater.from(context);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.tuijian_layout, parent, false);
        MyViewHolder classViewHolder = new MyViewHolder(view);
        return classViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        //绑定holder，显示数据
        MyViewHolder classViewHolder = (MyViewHolder) holder;
        GetAdBean.TuijianBean.ListBean listBean = tuijian.get(position);
        Glide.with(context).load(listBean.getImages().split("\\|")[0]).into(classViewHolder.img);
        classViewHolder.tv.setText(listBean.getTitle()+"");
        classViewHolder.price.setText(listBean.getPrice()+"");
        //设置条目点击
//        classViewHolder.rlayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (onItemClickListener != null) {
//                    onItemClickListener.onItemClick(position);
//                }
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return tuijian.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView tv;
//        private final RelativeLayout rlayout;
        private final TextView price;

        public MyViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.class_img);
            tv = itemView.findViewById(R.id.class_title);
            price = itemView.findViewById(R.id.class_price);
//            rlayout = itemView.findViewById(R.id.class_item);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onLongItemClick(int position);
    }
}
