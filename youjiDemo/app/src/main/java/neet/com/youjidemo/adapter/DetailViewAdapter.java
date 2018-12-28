package neet.com.youjidemo.adapter;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import neet.com.youjidemo.R;
import neet.com.youjidemo.bean.Comment;
import neet.com.youjidemo.bean.ShowCommentBean;
import neet.com.youjidemo.bean.Url;

/**
 *desc:详情页的适配器
 * author:梁启文
 * time：2018/12/8
 */
public class DetailViewAdapter extends RecyclerView.Adapter<DetailViewAdapter.ViewHolder> {

    private List<ShowCommentBean> mDataSet;
    private Context context;
    public DetailViewAdapter(List<ShowCommentBean> mDataSet,Context context) {
        this.mDataSet = mDataSet;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.share_review_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(context).load(Url.MIMAGEURL+mDataSet.get(i).getUser_touxiang()).into(viewHolder.user_hander);
        viewHolder.user_name.setText(mDataSet.get(i).getUsername());
        //viewHolder.like_num.setText(mDataSet.get(i).getLike_num()+"");
        viewHolder.comment_text.setText(mDataSet.get(i).getComment_text());
        viewHolder.time.setText(mDataSet.get(i).getTime());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    /**
     * 添加一条评论,刷新列表
     * @param comment
     */
    public void addComment(ShowCommentBean comment){
        mDataSet.add(comment);
        notifyDataSetChanged();
    }
    public void updataList(List<ShowCommentBean> list){
        mDataSet=new ArrayList<>();
        mDataSet=list;
        Log.e("size",mDataSet.size()+"");
        Log.e("size",list.size()+"");
        notifyDataSetChanged();
    }
    class ViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView user_hander;
        private TextView user_name;
        private ImageButton like;
        private TextView like_num;
        private TextView comment_text;
        private TextView time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user_hander=itemView.findViewById(R.id.image_review_user_header);
            user_name=itemView.findViewById(R.id.tv_review_username);
            like_num=itemView.findViewById(R.id.tv_like_count);
            comment_text=itemView.findViewById(R.id.tv_review);
            time=itemView.findViewById(R.id.time);
        }

    }
}
