package neet.com.youjidemo.adapter;


import android.content.Context;
import android.content.Intent;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import neet.com.youjidemo.R;
import neet.com.youjidemo.bean.Dynamic;

import neet.com.youjidemo.bean.ShowCommentBean;
import neet.com.youjidemo.bean.ShowDynamicInAll;
import neet.com.youjidemo.view.DetailActivity;
import neet.com.youjidemo.biz.Dynamicbiz;

import neet.com.youjidemo.view.Fragment.Index_RecommendFragment;
import neet.com.youjidemo.view.PersonalCenterActivity;

public class IndexRecommendRecycleItemAdapter extends RecyclerView.Adapter<IndexRecommendRecycleItemAdapter.ViewHolder> implements View.OnClickListener {
    private List<ShowDynamicInAll> list=new ArrayList<>();
    private IndexRecommendRecycleItemAdapter.OnItemClickListener mOnItemClickListener = null;
    private Context context;
    private Index_RecommendFragment index_recommendFragment;
    /**
     * 声明Item点击事件接口的变量
     */



    public IndexRecommendRecycleItemAdapter(List<ShowDynamicInAll> list, Index_RecommendFragment context) {
        this.list = list;
        this.context=context.getContext();
        this.index_recommendFragment=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.recommend_tabspec_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener((View.OnClickListener) this);//将创建的View注册点击事件
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

       /*
    设置首页上的头像，名字，笔记的内容，是否关注，评论数量，点赞数量，收藏数量
     */
        //例如： viewHolder.name.setText("李四");
        //viewHolder.collectNum.setText(list.get(i).getDynamic_collection_num()+"");
        String url=list.get(i).getDynamicImg_url();
        //String url="http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
        RequestOptions options=RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE);
        //图片加载时的占位图片
        //options.placeholder(R.drawable.ic_what_is_vip);
        //图片加载失败是占位图片
        //options.error(R.drawable.img_holder_error_style1);
        viewHolder.name.setText(list.get(i).getUsername());
        viewHolder.location.setText(list.get(i).getAddress());
        Glide.with(context).load(list.get(i).getUser_touxiang()).apply(options).into(viewHolder.headPhoto);
        viewHolder.description.setText(list.get(i).getDynamic_text());
        Glide.with(context).load(list.get(i).getDynamicImg_url()).apply(options).into(viewHolder.contentImage);
        viewHolder.collectNum.setText(list.get(i).getCollection_num()+"");
        viewHolder.judgeNum.setText(list.get(i).getComment_num()+"");
        viewHolder.goodNum.setText(list.get(i).getLike_num()+"");
        Glide.with(context).load(url).apply(options).into(viewHolder.contentImage);
        if(index_recommendFragment.getmUserId()==list.get(i).getUser_id()){
            viewHolder.care.setVisibility(View.GONE);
        }
        else if(list.get(i).isFollow()){
            viewHolder.care.setText("已关注");
        }else{
            viewHolder.care.setText("关注");
        }
        if (!list.get(i).isCollection() ){
            viewHolder.collectButton.setImageResource(R.drawable.collect);
        }else {
            viewHolder.collectButton.setImageResource(R.drawable.havecollect);
        }
        if (!list.get(i).isLike()){
            viewHolder.goodButton.setImageResource(R.drawable.like);
        }else{
            viewHolder.goodButton.setImageResource(R.drawable.havelike);
        }
        viewHolder.itemView.setTag(i);//将position保存在itemView的Tag中，以便点击时进行获取

        viewHolder.care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.care.getText().equals("关注")){
                    viewHolder.care.setText("已关注");
                    index_recommendFragment.addFollow(list.get(i).getUser_id());


                }else {
                    viewHolder.care.setText("关注");
                    index_recommendFragment.cancelFollow(list.get(i).getUser_id());
                }
            }
        });

        viewHolder.collectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(i).isCollection() ){
                    viewHolder.collectButton.setImageResource(R.drawable.collect);
                    index_recommendFragment.cancelCollection(list.get(i).getDyanmic_id());
                    viewHolder.collectNum.setText(list.get(i).getCollection_num()-1+"");
                    list.get(i).setCollection_num(list.get(i).getCollection_num()-1);
                    list.get(i).setCollection(false);
                }else {
                    viewHolder.collectButton.setImageResource(R.drawable.havecollect);
                    index_recommendFragment.addCollection(list.get(i).getDyanmic_id());
                    viewHolder.collectNum.setText(list.get(i).getCollection_num()+1+"");
                    list.get(i).setCollection_num(list.get(i).getCollection_num()+1);
                    list.get(i).setCollection(true);
                }
            }
        });

        viewHolder.judgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("dynamicDeta",list.get(i));
                context.startActivity(intent);
            }
        });

/**点赞按钮的点击事件*/
        viewHolder.goodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(i).isLike()){
                    viewHolder.goodButton.setImageResource(R.drawable.like);
                    index_recommendFragment.cancelLike(list.get(i).getDyanmic_id());
                    viewHolder.goodNum.setText(list.get(i).getLike_num()-1+"");
                    list.get(i).setLike_num(list.get(i).getLike_num()-1);
                    list.get(i).setLike(false);
                }else{
                    viewHolder.goodButton.setImageResource(R.drawable.havelike);
                    index_recommendFragment.likeTheDynamic(list.get(i).getDyanmic_id());
                    viewHolder.goodNum.setText(list.get(i).getLike_num()+1+"");
                    list.get(i).setLike_num(list.get(i).getLike_num()+1);
                    list.get(i).setLike(true);
                }
            }
        });
        viewHolder.headPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PersonalCenterActivity.class);
                intent.putExtra("user_id",list.get(i).getUser_id());
                context.startActivity(intent);
            }
        });
    }
    public Object getmItem(int i){
        return list.get(i);
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
    public void setmOnItemClickListener(IndexRecommendRecycleItemAdapter.OnItemClickListener listener) {
        this.mOnItemClickListener = (IndexRecommendRecycleItemAdapter.OnItemClickListener) listener;
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
        Boolean isgood = false;

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
    public void updataList(List<ShowDynamicInAll> list){
        this.list=new ArrayList<>();
        this.list.addAll(list);
        notifyDataSetChanged();
    }
}
