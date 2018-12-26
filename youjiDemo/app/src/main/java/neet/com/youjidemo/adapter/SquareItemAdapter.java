package neet.com.youjidemo.adapter;

import android.content.Context;
import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import neet.com.youjidemo.R;
import neet.com.youjidemo.bean.ShowDynamicInAll;
import neet.com.youjidemo.bean.Url;
import neet.com.youjidemo.view.DetailActivity;
import neet.com.youjidemo.view.Fragment.Food_RecommendFragment;
import neet.com.youjidemo.view.IView.IDynamicOption;

public class SquareItemAdapter extends RecyclerView.Adapter<SquareItemAdapter.ViewHolder>{
    private List<ShowDynamicInAll> list=new ArrayList<>();
    Button care;
    ImageButton goodButton;
    ImageButton collectButton;
    ImageButton judgeButton;
    private IDynamicOption food_recommendFragment;
    private Context context;

    public SquareItemAdapter(List<ShowDynamicInAll> list,IDynamicOption food_recommendFragment,Context context) {
        this.list = list;
        this.food_recommendFragment=food_recommendFragment;
        this.context=context;

}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
       View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.square_list_item, viewGroup,false);
        ViewHolder viewHolder=new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

       /*
    设置广场上的头像，名字，笔记的内容，是否关注，评论数量，点赞数量，收藏数量
     */
        //例如： viewHolder.name.setText("李四");
        Glide.with(context).load(list.get(i).getDynamicImg_url()).into(viewHolder.contentImage);
        Glide.with(context).load(Url.MIMAGEURL+list.get(i).getUser_touxiang()).into(viewHolder.headPhoto);
        viewHolder.name.setText(list.get(i).getUsername());
        viewHolder.date.setText(list.get(i).getTime());
        viewHolder.signatures.setText(list.get(i).getDynamic_text());
        viewHolder.collectNum.setText(list.get(i).getCollection_num()+"");
        viewHolder.judgeNum.setText(list.get(i).getComment_num()+"");
        viewHolder.goodNum.setText(list.get(i).getLike_num()+"");
        if(food_recommendFragment.getmUserId()==list.get(i).getUser_id()){
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
        viewHolder.care.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.care.getText().equals("关注")){
                    viewHolder.care.setText("已关注");
                    food_recommendFragment.addFollow(list.get(i).getUser_id());

                }else {
                    viewHolder.care.setText("关注");
                    food_recommendFragment.cancelFollow(list.get(i).getUser_id());
                }
            }
        });
        //收藏点击事件 点击收藏就收藏
        viewHolder.collectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(i).isCollection() ){
                    viewHolder.collectButton.setImageResource(R.drawable.collect);
                    food_recommendFragment.cancelCollection(list.get(i).getDyanmic_id());
                    viewHolder.collectNum.setText(list.get(i).getCollection_num()-1+"");
                    list.get(i).setCollection_num(list.get(i).getCollection_num()-1);
                    list.get(i).setCollection(false);
                }else {
                    viewHolder.collectButton.setImageResource(R.drawable.havecollect);
                    food_recommendFragment.addCollection(list.get(i).getDyanmic_id());
                    viewHolder.collectNum.setText(list.get(i).getCollection_num()+1+"");
                    list.get(i).setCollection_num(list.get(i).getCollection_num()+1);
                    list.get(i).setCollection(true);
                }
            }
        });
        //点赞点击事件  点击给点赞
        viewHolder.goodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(i).isLike()){
                    viewHolder.goodButton.setImageResource(R.drawable.like);
                    food_recommendFragment.cancelLike(list.get(i).getDyanmic_id());
                    viewHolder.goodNum.setText(list.get(i).getLike_num()-1+"");
                    list.get(i).setLike_num(list.get(i).getLike_num()-1);
                    list.get(i).setLike(false);
                }else{
                    viewHolder.goodButton.setImageResource(R.drawable.havelike);
                    food_recommendFragment.likeTheDynamic(list.get(i).getDyanmic_id());
                    viewHolder.goodNum.setText(list.get(i).getLike_num()+1+"");
                    list.get(i).setLike_num(list.get(i).getLike_num()+1);
                    list.get(i).setLike(true);
                }
            }
        });
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("dynamicDeta",list.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public  class  ViewHolder extends RecyclerView.ViewHolder{
        Button care;
        TextView name;
        TextView date;
        TextView signatures;
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
            date = itemView.findViewById(R.id.tv_date);
            signatures = itemView.findViewById(R.id.tv_signatures);
            collectNum = itemView.findViewById(R.id.tv_collectNum);
            judgeNum = itemView.findViewById(R.id.tv_judgeNum);
            goodNum = itemView.findViewById(R.id.tv_goodNum);
            headPhoto = itemView.findViewById(R.id.iv_headphoto);
            contentImage = itemView.findViewById(R.id.iv_contentimage);
            goodButton = itemView.findViewById(R.id.ib_good);
            judgeButton = itemView.findViewById(R.id.ib_collect);
            collectButton = itemView.findViewById(R.id.ib_collect);


        }
    }




    public void updateList(List<ShowDynamicInAll> list){
        this.list=new ArrayList<>();
        this.list=list;
        notifyDataSetChanged();
    }
}
