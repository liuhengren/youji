package neet.com.youjidemo.view.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.Presenter.DynamicOptionPresenter;
import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.AttentionPagerAdapter;
import neet.com.youjidemo.adapter.ContentAdapter;
import neet.com.youjidemo.adapter.ProductionPagerAdapter;
import neet.com.youjidemo.bean.ShowDynamicInAll;
import neet.com.youjidemo.bean.UserDateApplication;
import neet.com.youjidemo.view.DetailActivity;
import neet.com.youjidemo.view.IView.IDynamicOption;

/**
 * desc:作品页的Fragment
 * author：梁启文
 * time:2018/12/11
 */
public class ProductionFragment extends Fragment implements IDynamicOption {
    @Nullable
    private UserDateApplication userDateApplication;
    private DynamicOptionPresenter dynamicOptionPresenter;
    private  List<ShowDynamicInAll> list = new ArrayList();
    private ProductionPagerAdapter adapter;
    private int user_id;
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.production_viewpager_layout, null);



        RecyclerView recyclerView = view.findViewById(R.id.rv_production);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        adapter = new ProductionPagerAdapter(list,this);
        recyclerView.setAdapter(adapter);
        userDateApplication=(UserDateApplication)getActivity().getApplication();
        user_id=getActivity().getIntent().getIntExtra("user_id",userDateApplication.getUser().getUser_id());
        dynamicOptionPresenter=new DynamicOptionPresenter(this);
        dynamicOptionPresenter.getList("user",user_id);

//        adapter.setmOnItemClickListener(new ProductionPagerAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                ShowDynamicInAll showDynamicInAll = adapter.getmItem(position);
//                Intent intent = new Intent(getContext(), DetailActivity.class);
//                intent.putExtra("dynamicDeta",showDynamicInAll);
//                startActivity(intent);
//            }
//        });
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
