package neet.com.youjidemo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import neet.com.youjidemo.R;
import neet.com.youjidemo.view.DetailActivity;

public class IndexFoodRecycleItemAdapter extends RecyclerView.Adapter <IndexFoodRecycleItemAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List list;
    private IndexFoodRecycleItemAdapter.OnItemClickListener mOnItemClickListener = null;
    /**
     * 声明Item点击事件接口的变量
     */



    public IndexFoodRecycleItemAdapter(List list,Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public IndexFoodRecycleItemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.recommend_tabspec_item, viewGroup, false);
       ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener((View.OnClickListener) this);//将创建的View注册点击事件
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final IndexFoodRecycleItemAdapter.ViewHolder viewHolder, int i) {

       /*
    设置首页上的头像，名字，笔记的内容，是否关注，评论数量，点赞数量，收藏数量
     */
        //例如： viewHolder.name.setText("李四");
        viewHolder.itemView.setTag(i);//将position保存在itemView的Tag中，以便点击时进行获取


        /**关注按钮的点击事件*/
        viewHolder.care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.care.getText()=="关注"){
                    viewHolder.care.setText("已关注");
                }else{
                    viewHolder.care.setText("关注");
                }
            }
        });

        /**收藏按钮的点击事件*/
        viewHolder.collectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.collectNum.getText() == "收藏"){
                    viewHolder.collectButton.setImageResource(R.drawable.havecollect);
                    viewHolder.collectNum.setText("已收藏");
                }else {
                    viewHolder.collectButton.setImageResource(R.drawable.collect);
                    viewHolder.collectNum.setText("收藏");
                }
            }
        });

        /**点赞按钮的点击事件*/
        viewHolder.goodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.goodNum.getText() == "点赞"){
                    viewHolder.goodButton.setImageResource(R.drawable.havelike);
                    viewHolder.goodNum.setText("Like");
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * 重写OnClick方法
     */
    @Override
    public void onClick(View v) {
        mOnItemClickListener.onItemClick(v, (Integer) v.getTag());
    }

    /**
     * 设置Listenter
     *
     * @param listener
     */
    public void setmOnItemClickListener(IndexFoodRecycleItemAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = (IndexFoodRecycleItemAdapter.OnItemClickListener) listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Button care;
        TextView name;
        TextView location;
        TextView description;
        TextView collectNum;
        TextView judgeNum;
        TextView goodNum;
        CircleImageView headPhoto;
        ImageView contentImage;
        ImageButton goodButton;
        ImageButton collectButton;
        ImageButton judgeButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            care = itemView.findViewById(R.id.btn_care);
            name = itemView.findViewById(R.id.tv_name);
            location = itemView.findViewById(R.id.tv_location);
            description = itemView.findViewById(R.id.tv_description);
            collectNum = itemView.findViewById(R.id.tv_collectNum);
            judgeNum = itemView.findViewById(R.id.tv_judgeNum);
            goodNum = itemView.findViewById(R.id.tv_goodNum);
            headPhoto = itemView.findViewById(R.id.iv_headphoto);
            contentImage = itemView.findViewById(R.id.iv_contentimage);
            goodButton = itemView.findViewById(R.id.ib_good);
            judgeButton = itemView.findViewById(R.id.ib_judge);
            collectButton = itemView.findViewById(R.id.ib_collect);
        }
    }

    /**
     * 自定义接口，实现RecyclerView的Item点击事件
     */
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
}
