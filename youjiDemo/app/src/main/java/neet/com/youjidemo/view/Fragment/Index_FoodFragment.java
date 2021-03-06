package neet.com.youjidemo.view.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melnykov.fab.ScrollDirectionListener;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.IndexFoodRecycleItemAdapter;
import neet.com.youjidemo.adapter.IndexRecommendRecycleItemAdapter;
import neet.com.youjidemo.adapter.SquareItemAdapter;
import neet.com.youjidemo.command.PullRefreshTask;
import neet.com.youjidemo.view.DetailActivity;


/*
 * 1.位置：首页的推荐
 * 2.作者：李俊霞
 * */
public class Index_FoodFragment extends Fragment {

    private List list;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mySwipeRefreshLayout;
    private IndexFoodRecycleItemAdapter indexFoodRecycleItemAdapter;
    private View view;
    RecyclerView.LayoutManager manager;
    boolean isLoading=false;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
     if(view==null) {
         view = inflater.inflate(R.layout.index_recommend_layout, container, false);
         findViews();
         setPullRefresh();
         setRecyclerView();

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
            public void onRefresh() {

                isLoading = false;
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
        list = new ArrayList();
        list.add(1);
        list.add(1);
        list.add(1);
        //这里填入数据list

        indexFoodRecycleItemAdapter = new IndexFoodRecycleItemAdapter(list,this.getContext());


        recyclerView.setAdapter(indexFoodRecycleItemAdapter);

        //RecyclerView点击事件
        indexFoodRecycleItemAdapter.setmOnItemClickListener(new IndexFoodRecycleItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getContext(),DetailActivity.class);
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





}
