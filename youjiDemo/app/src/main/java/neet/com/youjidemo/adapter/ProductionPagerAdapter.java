package neet.com.youjidemo.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import neet.com.youjidemo.R;
import neet.com.youjidemo.view.DetailActivity;

/**
 * desc:个人中心作品页适配器
 * author: 梁启文
 * time：2018/12/12
 */

public class ProductionPagerAdapter extends RecyclerView.Adapter <ProductionPagerAdapter.ViewHolder> {

    private List mDataSet;
    private Activity context;


    public ProductionPagerAdapter(List mDataSet,Activity context) {
        this.mDataSet = mDataSet;
        this.context = context;
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
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,DetailActivity.class);
//                intent.putExtra("dynamicDeta",mDataSet.get(i));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
