package neet.com.youjidemo.view.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import neet.com.youjidemo.Presenter.DynamicOptionPresenter;
import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.AttentionPagerAdapter;
import neet.com.youjidemo.adapter.ProductionPagerAdapter;
import neet.com.youjidemo.bean.ShowDynamicInAll;
import neet.com.youjidemo.bean.UserDateApplication;
import neet.com.youjidemo.view.DetailActivity;
import neet.com.youjidemo.view.IView.IDynamicOption;

/**
 * desc:个人中心关注Fragment
 * author：梁启文
 * time：2018/12/06
 */
public class AttentionFragment extends Fragment implements IDynamicOption {
    private UserDateApplication userDateApplication;
    private DynamicOptionPresenter dynamicOptionPresenter;
    private  List<ShowDynamicInAll> list = new ArrayList();
    private AttentionPagerAdapter adapter;
    private int user_id;
    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.production_viewpager_layout,null);
        list = new ArrayList();

        RecyclerView recyclerView = view.findViewById(R.id.rv_production);

        //创建默认的线性LayoutManager
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) manager).setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        //创建Adapter
        adapter = new AttentionPagerAdapter(list,this);

        //如果Item的高度是固定的，这句代码可以提高效率
        recyclerView.setHasFixedSize(true);


        //设置Adapter
        recyclerView.setAdapter(adapter);

        //设置Item的点击事件（具体实现方法在Adapter中）
        adapter.setmOnItemClickListener(new AttentionPagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ShowDynamicInAll showDynamicInAll = adapter.getmItem(position);
                Intent intent = new Intent(getContext(), DetailActivity.class);
                intent.putExtra("dynamicDeta",showDynamicInAll);
                startActivity(intent);
            }
        });
        userDateApplication=(UserDateApplication)getActivity().getApplication();
        user_id=getActivity().getIntent().getIntExtra("user_id",userDateApplication.getUser().getUser_id());
        dynamicOptionPresenter=new DynamicOptionPresenter(this);
        dynamicOptionPresenter.getList("collection",user_id);

        return view;
    }

    @Override
    public void setListByTag(List<ShowDynamicInAll> list) {
        this.list=new ArrayList<>();
        this.list=list;
    }

    @Override
    public int getmUserId() {
        return user_id;
    }

    @Override
    public void addCollection(int dynamic_id) {

    }

    @Override
    public void addFollow(int follow_user_id) {

    }

    @Override
    public void likeTheDynamic(int dynamic_id) {

    }

    @Override
    public void cancelLike(int dynamic_id) {

    }

    @Override
    public void cancelFollow(int follow_user_id) {

    }

    @Override
    public void cancelCollection(int dynamic_id) {

    }

    @Override
    public void change() {
        adapter.updateList(this.list);
    }
}
