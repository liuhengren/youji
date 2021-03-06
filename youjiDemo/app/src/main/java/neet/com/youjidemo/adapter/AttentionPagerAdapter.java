package neet.com.youjidemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import neet.com.youjidemo.R;
import neet.com.youjidemo.bean.ShowDynamicInAll;
import neet.com.youjidemo.bean.Url;
import neet.com.youjidemo.view.Fragment.AttentionFragment;

/**
 * desc:个人中心关注页适配器
 * author：梁启文
 * time：2018/12/12
 */
public class AttentionPagerAdapter extends RecyclerView.Adapter<AttentionPagerAdapter.ViewHolder> implements View.OnClickListener {

    private List<ShowDynamicInAll> mDataSet=new ArrayList<>();
    private AttentionFragment attentionFragment;
    private OnItemClickListener mOnItemClickListener=null; /** 声明Item点击事件接口的变量*/

    /**
     * 构造方法
     */
    public AttentionPagerAdapter(List mDataSet,AttentionFragment attentionFragment) {
        this.mDataSet = mDataSet;
        this.attentionFragment=attentionFragment;
    }
    public ShowDynamicInAll getmItem(int i){
        return mDataSet.get(i);
    }
    /**
     * onCreateViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.collect_layout_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        Glide.with(attentionFragment).load(Url.MIMAGEURL+mDataSet.get(i).getUser_touxiang()).into(viewHolder.head);
        viewHolder.user_name.setText(mDataSet.get(i).getUsername());
        viewHolder.address.setText(mDataSet.get(i).getAddress());
        viewHolder.text.setText(mDataSet.get(i).getDynamic_text());
        viewHolder.collection_num.setText(mDataSet.get(i).getCollection_num()+"");
        viewHolder.comment_num.setText(mDataSet.get(i).getComment_num()+"");
        viewHolder.time.setText(mDataSet.get(i).getTime());
        Glide.with(attentionFragment).load(mDataSet.get(i).getDynamicImg_url()).into(viewHolder.img);
        view.setOnClickListener((View.OnClickListener) this);//将创建的View注册点击事件
        return viewHolder;
    }


    /**
     * onBIndViewHolder
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.itemView.setTag(i);//将position保存在itemView的Tag中，以便点击时进行获取
    }

    /**
     * 重写Onclick方法，将事件转移给外部的调用者
     */
    @Override
    public void onClick(View v) {
        mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
    }


    /**
     * 设置Listenter
     * @param listener
     */
    public void setmOnItemClickListener(OnItemClickListener listener){
        this.mOnItemClickListener = (OnItemClickListener) listener;
    }

    /**
     * getItemCount ,获取list<数据>的长度
     */
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    /**
     * 自定义ViewHolder类，处理获取Item里的布局,持有每个Item的所有元素
     */
    class ViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView head;
        private TextView user_name;
        private TextView address;
        private TextView text;
        private ImageView img;
        private TextView collection_num;
        private TextView comment_num;
        private TextView time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            head=itemView.findViewById(R.id.image_production_head);
            user_name=itemView.findViewById(R.id.tv_productions_username);
            address=itemView.findViewById(R.id.tv_publish);
            text=itemView.findViewById(R.id.tv_productions_description);
            img=itemView.findViewById(R.id.dynamic_img);
            collection_num=itemView.findViewById(R.id.collect_num);
            comment_num=itemView.findViewById(R.id.comment_num);
            time=itemView.findViewById(R.id.time);
        }
    }

    /**
     * 自定义接口，实现RecyclerView的Item点击事件
     */
    public static interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
    public void updateList(List<ShowDynamicInAll> list){
        mDataSet=new ArrayList<>();
        mDataSet=list;
        notifyDataSetChanged();
    }
}
