package neet.com.youjidemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import neet.com.youjidemo.R;
import neet.com.youjidemo.bean.Dynamic;
import neet.com.youjidemo.view.Fragment.Index_RecommendFragment;

public class IndexRecommendRecycleItemAdapter extends RecyclerView.Adapter<IndexRecommendRecycleItemAdapter.ViewHolder> implements View.OnClickListener {
    private List<Dynamic> list;
    private IndexRecommendRecycleItemAdapter.OnItemClickListener mOnItemClickListener = null;
    private Context context;
    /**
     * 声明Item点击事件接口的变量
     */
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


    public IndexRecommendRecycleItemAdapter(List list,Context context) {
        this.list = list;
        this.context=context;
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
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

       /*
    设置首页上的头像，名字，笔记的内容，是否关注，评论数量，点赞数量，收藏数量
     */
        //例如： viewHolder.name.setText("李四");
        //viewHolder.collectNum.setText(list.get(i).getDynamic_collection_num()+"");
        //String url=list.get(i).getDynamic_img();
        String url="http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
        RequestOptions options=RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE);
        //图片加载时的占位图片
        //options.placeholder(R.drawable.ic_what_is_vip);
        //图片加载失败是占位图片
        //options.error(R.drawable.img_holder_error_style1);
        Glide.with(context).load(url).apply(options).into(viewHolder.contentImage);
        viewHolder.itemView.setTag(i);//将position保存在itemView的Tag中，以便点击时进行获取
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


    /*
    所有的Button点击事件
     */
    private void setAllButtonClickListener() {

        //关注点击事件 点击关注就去关注 或者再次点击取消关注
        care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //收藏点击事件 点击收藏就收藏
        collectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


        //点赞点击事件  点击给点赞
        goodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });


    }

    /**
     * 自定义接口，实现RecyclerView的Item点击事件
     */
    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

}
