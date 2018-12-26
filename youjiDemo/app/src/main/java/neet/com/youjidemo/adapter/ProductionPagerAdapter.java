package neet.com.youjidemo.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import neet.com.youjidemo.view.Fragment.ProductionFragment;

/**
 * desc:个人中心作品页适配器
 * author: 梁启文
 * time：2018/12/12
 */

public class ProductionPagerAdapter extends RecyclerView.Adapter <ProductionPagerAdapter.ViewHolder>{

    private List<ShowDynamicInAll> mDataSet;
    private ProductionFragment productionFragment;
    public ProductionPagerAdapter(List mDataSet,ProductionFragment productionFragment) {
        this.mDataSet = mDataSet;
        this.productionFragment=productionFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.personal_center_production_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Glide.with(productionFragment).load(Url.MIMAGEURL+mDataSet.get(i).getUser_touxiang()).into(viewHolder.head);
        viewHolder.user_name.setText(mDataSet.get(i).getUsername());
        viewHolder.address.setText(mDataSet.get(i).getAddress());
        viewHolder.text.setText(mDataSet.get(i).getDynamic_text());
        viewHolder.collection_num.setText(mDataSet.get(i).getCollection_num()+"");
        viewHolder.comment_num.setText(mDataSet.get(i).getComment_num()+"");
        viewHolder.time.setText(mDataSet.get(i).getTime());
        Glide.with(productionFragment).load(mDataSet.get(i).getDynamicImg_url()).into(viewHolder.img);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(productionFragment.getActivity(),DetailActivity.class);
                intent.putExtra("dynamicDeta",mDataSet.get(i));
                productionFragment.getActivity().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
    public ShowDynamicInAll getmItem(int i){
        return mDataSet.get(i);
    }
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
    public void updateList(List<ShowDynamicInAll> list){
        mDataSet=new ArrayList<>();
        mDataSet=list;
        notifyDataSetChanged();
    }


}
