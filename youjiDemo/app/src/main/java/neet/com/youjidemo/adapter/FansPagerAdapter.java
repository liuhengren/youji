package neet.com.youjidemo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import neet.com.youjidemo.R;
import neet.com.youjidemo.bean.User;
import neet.com.youjidemo.view.Fragment.FansFragment;

public class FansPagerAdapter extends RecyclerView.Adapter <FansPagerAdapter.ViewHolder>{

    private List<User> mDataSet;
    private FansFragment fansFragment;
    public FansPagerAdapter(List<User> mDataSet,FansFragment fansFragment) {
        this.mDataSet = mDataSet;
        this.fansFragment=fansFragment;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fans_layout_item,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Glide.with(fansFragment).load(mDataSet.get(i).getUser_touxiang_url()).into(viewHolder.touxiang);
        viewHolder.username.setText(mDataSet.get(i).getUser_name());
        viewHolder.intr.setText(mDataSet.get(i).getUser_introduction());
        viewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if("已关注".equals(viewHolder.btn.getText())){
                    fansFragment.cancel(mDataSet.get(i).getUser_id());
                    viewHolder.btn.setText("关注");
                }else{
                    fansFragment.addfollow(mDataSet.get(i).getUser_id());
                    viewHolder.btn.setText("已关注");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView touxiang;
        private TextView username;
        private TextView intr;
        private Button btn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            touxiang=itemView.findViewById(R.id.cImage_attention_head);
            username=itemView.findViewById(R.id.tv_attention_username);
            intr=itemView.findViewById(R.id.tv_attention_sign);
            btn=itemView.findViewById(R.id.btn_attention_yes);

        }
    }
    public void updataList(List<User> list){
        mDataSet=new ArrayList();
        mDataSet=list;
        notifyDataSetChanged();
    }
}
