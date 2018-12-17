package neet.com.youjidemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import neet.com.youjidemo.R;

/**
 * desc:个人中心关注页适配器
 * author：梁启文
 * time：2018/12/12
 */
public class AttentionPagerAdapter extends RecyclerView.Adapter<AttentionPagerAdapter.ViewHolder> implements View.OnClickListener {

    private List mDataSet;
    private OnItemClickListener mOnItemClickListener=null; /** 声明Item点击事件接口的变量*/

    /**
     * 构造方法
     */
    public AttentionPagerAdapter(List mDataSet) {
        this.mDataSet = mDataSet;
    }

    /**
     * onCreateViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.collect_layout_item,null);
        ViewHolder viewHolder = new ViewHolder(view);

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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    /**
     * 自定义接口，实现RecyclerView的Item点击事件
     */
    public static interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

}
