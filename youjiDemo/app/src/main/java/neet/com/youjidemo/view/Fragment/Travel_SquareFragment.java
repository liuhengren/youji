package neet.com.youjidemo.view.Fragment;

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
import android.widget.Toast;

import com.melnykov.fab.ScrollDirectionListener;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.Presenter.DynamicOptionPresenter;
import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.SquareItemAdapter;
import neet.com.youjidemo.bean.ShowDynamicInAll;
import neet.com.youjidemo.bean.UserDateApplication;
import neet.com.youjidemo.command.PullRefreshTask;
import neet.com.youjidemo.view.IView.IDynamicOption;


/*
 * 1.类别：游迹
 * 2.推荐或广场：广场
 * */
public class Travel_SquareFragment extends Fragment implements IDynamicOption {

    private List<ShowDynamicInAll> list;
    private RecyclerView recyclerView;
    private com.melnykov.fab.FloatingActionButton floatingActionButton;
    private CoordinatorLayout coordinatorLayout;
    private SwipeRefreshLayout mySwipeRefreshLayout;
    private SquareItemAdapter squareItemAdapter;
    private View view;
    RecyclerView.LayoutManager manager;
    int lastVisibleItem;
    boolean isLoading=false;
    private UserDateApplication userDateApplication;
    private int user_id;
    private DynamicOptionPresenter dynamicOptionPresenter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(view==null) {
            view = inflater.inflate(R.layout.square_layout, container, false);

        findViews();
            userDateApplication=(UserDateApplication) getActivity().getApplication();
            user_id=userDateApplication.getUser().getUser_id();
            dynamicOptionPresenter=new DynamicOptionPresenter(this);
        setRecyclerView();
        setFloatingActionButton();
        setPullRefresh();
        }

        return view;

    }

    private void findViews() {
        recyclerView = view.findViewById(R.id.rl_square_item);
        floatingActionButton = view.findViewById(R.id.fab_top);
        mySwipeRefreshLayout = view.findViewById(R.id.srl_downrefresh);

    }


    /*
     * 设置FloatingActionButton 让ListView回到顶部
     * */
    private void setFloatingActionButton() {
        floatingActionButton.hide();

//            floatingActionButton和RecycleView绑定
        floatingActionButton.attachToRecyclerView(recyclerView, new ScrollDirectionListener() {
            @Override
            public void onScrollDown() {
                floatingActionButton.hide();
            }

            @Override
            public void onScrollUp() {
                floatingActionButton.show();
            }
        }, new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        /*floatingButton点击回到顶部*/
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerView.scrollToPosition(0);
            }
        });
    }


    /*
    设置RecycleView下拉刷新
    */
    private void setPullRefresh() {
        // 设置下拉出现小圆圈是否是缩放出现，出现的位置，最大的下拉位置
        mySwipeRefreshLayout.setProgressViewOffset(true, 50, 200);
        // 设置下拉圆圈的大小，两个值 LARGE， DEFAULT
        mySwipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
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
                dynamicOptionPresenter.getList("partition",1);
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
        //这里填入数据list
        dynamicOptionPresenter.getList("partition",1);
        squareItemAdapter = new SquareItemAdapter(list,this,this.getContext());

        recyclerView.setAdapter(squareItemAdapter);

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
        if(userDateApplication.isLogin()){
            dynamicOptionPresenter.addCollection(getmUserId(),dynamic_id);
        }
        else{
            Toast.makeText(this.getContext(),"请登录后操作",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void addFollow(int follow_user_id) {
        if(userDateApplication.isLogin()){
            dynamicOptionPresenter.addFollow(getmUserId(),follow_user_id);
        }
        else{
            Toast.makeText(this.getContext(),"请登录后操作",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void likeTheDynamic(int dynamic_id) {
        if(userDateApplication.isLogin()){
            dynamicOptionPresenter.addLike(getmUserId(),dynamic_id);
        }
        else{
            Toast.makeText(this.getContext(),"请登录后操作",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void cancelLike(int dynamic_id) {
        if(userDateApplication.isLogin()){
            dynamicOptionPresenter.cancelLike(getmUserId(),dynamic_id);
        }
        else{
            Toast.makeText(this.getContext(),"请登录后操作",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void cancelFollow(int follow_user_id) {
        if(userDateApplication.isLogin()){
            dynamicOptionPresenter.cancelFollow(getmUserId(),follow_user_id);
        }
        else{
            Toast.makeText(this.getContext(),"请登录后操作",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void cancelCollection(int dynamic_id) {
        if(userDateApplication.isLogin()){
            dynamicOptionPresenter.cancelCollection(getmUserId(),dynamic_id);
        }
        else{
            Toast.makeText(this.getContext(),"请登录后操作",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void change() {
        squareItemAdapter.updateList(this.list);
        mySwipeRefreshLayout.setEnabled(false);
    }
}
