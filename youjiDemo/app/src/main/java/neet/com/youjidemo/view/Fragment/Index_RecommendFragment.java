package neet.com.youjidemo.view.Fragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.Presenter.DynamicOptionPresenter;
import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.IndexRecommendRecycleItemAdapter;
import neet.com.youjidemo.bean.ShowDynamicInAll;
import neet.com.youjidemo.bean.UserDateApplication;
import neet.com.youjidemo.view.DetailActivity;
import neet.com.youjidemo.view.IView.IDynamicOption;


/*
 * 1.位置：首页的推荐
 * 2.作者：李俊霞
 * */
public class Index_RecommendFragment extends Fragment implements IDynamicOption {

    private List<ShowDynamicInAll> list=new ArrayList<>();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mySwipeRefreshLayout;
    private IndexRecommendRecycleItemAdapter indexRecommendRecycleItemAdapter;
    private View view;
    RecyclerView.LayoutManager manager;
    boolean isLoading=false;
    private DynamicOptionPresenter dynamicOptionPresenter;
    private UserDateApplication userDateApplication;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null) {
            view = inflater.inflate(R.layout.index_recommend_layout, container, false);
            findViews();
            setPullRefresh();
            setRecyclerView();
            userDateApplication=(UserDateApplication)(getActivity().getApplication());

        }
        dynamicOptionPresenter =new DynamicOptionPresenter(this);
        dynamicOptionPresenter.getList("all",0);
        if(getActivity().getIntent().getStringExtra("flag")!=null){
            Log.e("1","flag");
                indexRecommendRecycleItemAdapter.reget();
                dynamicOptionPresenter.getList("all",0);
        }
        return view;
    }
    private void findViews() {
        recyclerView = view.findViewById(R.id.rl_index_recommend);
        mySwipeRefreshLayout = view.findViewById(R.id.srl_downrefresh);

    }

    /*
    设置RecycleView下拉刷新
    */
    private void setPullRefresh() {
        // 设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
        mySwipeRefreshLayout.setProgressViewOffset(true, 50, 150);
        // 设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        mySwipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        // 设置下拉圆圈上的颜色，蓝色、绿色、橙色、红色
        mySwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        // 通过 setEnabled(false) 禁用下拉刷新
        //  mySwipeRefreshLayout.setEnabled(false);
        // 设定下拉圆圈的背景
        //  mySwipeRefreshLayout.setProgressBackgroundColor(R.color.);


        mySwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(){
                isLoading = false;
                dynamicOptionPresenter.getList("all",0);
                //  footView.setVisibility(View.GONE);
            }
        });



    }

    /*
    设置RecycleView的数据 ，adapter，
    */
    private void setRecyclerView() {

        manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);

        //从服务器获得的笔记的list
        indexRecommendRecycleItemAdapter = new IndexRecommendRecycleItemAdapter(list,this);
        recyclerView.setAdapter(indexRecommendRecycleItemAdapter);


        indexRecommendRecycleItemAdapter.setmOnItemClickListener(new IndexRecommendRecycleItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(),DetailActivity.class);
                ShowDynamicInAll showDynamicInAll=(ShowDynamicInAll) indexRecommendRecycleItemAdapter.getmItem(position);
                intent.putExtra("dynamicDeta",showDynamicInAll);
                startActivity(intent);
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int lastVisibleItemPosition = 0;
                //滑动手指且之前滑动的加载更多已经完成
                if (newState == RecyclerView.SCROLL_STATE_IDLE && !isLoading) {
                    RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                    lastVisibleItemPosition =
                            ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();

                    if (lastVisibleItemPosition >= layoutManager.getItemCount() - 1) {//到达最后一条数据是

                        isLoading = true;
                    }
                }
            }
        });
    }

    @Override
    public void setListByTag(List<ShowDynamicInAll> list) {
        this.list=new ArrayList<>();
        this.list.addAll(list);
    }

    @Override
    public int getmUserId() {
        int user_id = userDateApplication.getUser().getUser_id();
        return user_id;
    }

    @Override
    public void addCollection(int dynamic_id) {
        if(userDateApplication.isLogin()){
            dynamicOptionPresenter.addCollection(getmUserId(),dynamic_id);
        }
        else{
            Toast.makeText(Index_RecommendFragment.this.getContext(),"请登录后操作",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void addFollow(int follow_user_id) {
        if(userDateApplication.isLogin()){
            dynamicOptionPresenter.addFollow(getmUserId(),follow_user_id);
        }
        else{
            Toast.makeText(Index_RecommendFragment.this.getContext(),"请登录后操作",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void likeTheDynamic(int dynamic_id) {
        if(userDateApplication.isLogin()){
            dynamicOptionPresenter.addLike(getmUserId(),dynamic_id);
        }
        else{
            Toast.makeText(Index_RecommendFragment.this.getContext(),"请登录后操作",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void cancelLike(int dynamic_id) {
        if(userDateApplication.isLogin()){
            dynamicOptionPresenter.cancelLike(getmUserId(),dynamic_id);
        }
        else{
            Toast.makeText(Index_RecommendFragment.this.getContext(),"请登录后操作",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void cancelFollow(int follow_user_id) {
        if(userDateApplication.isLogin()){
            dynamicOptionPresenter.cancelFollow(getmUserId(),follow_user_id);
        }
        else{
            Toast.makeText(Index_RecommendFragment.this.getContext(),"请登录后操作",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void cancelCollection(int dynamic_id) {
        if(userDateApplication.isLogin()){
            dynamicOptionPresenter.cancelCollection(getmUserId(),dynamic_id);
        }
        else{
            Toast.makeText(Index_RecommendFragment.this.getContext(),"请登录后操作",Toast.LENGTH_SHORT).show();
        }

    }

    public void change(){
        indexRecommendRecycleItemAdapter.updataList(this.list);
    }

}
