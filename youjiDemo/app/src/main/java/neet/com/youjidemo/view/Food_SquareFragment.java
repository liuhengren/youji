package neet.com.youjidemo.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.melnykov.fab.ScrollDirectionListener;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.SquareItemAdapter;


/*
 * 具体的美食，游玩，游记，广场
 * */
public class Food_SquareFragment extends Fragment {

    private List list;
    private RecyclerView recyclerView;
    private com.melnykov.fab.FloatingActionButton floatingActionButton;
    private CoordinatorLayout coordinatorLayout;
    private SwipeRefreshLayout mySwipeRefreshLayout;
    private SquareItemAdapter squareItemAdapter;
    private View view;
    RecyclerView.LayoutManager manager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.square_layout, container, false);
        findViews();
        setRecyclerView();
        setFloatingActionButton();
        setPullRefresh();

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
    }

    /*
    设置RecycleView的数据 ，adapter，
    */
    private  void setRecyclerView(){
         manager = new LinearLayoutManager(getContext());


        recyclerView.setLayoutManager(manager);

        //从服务器获得的笔记的list
        list = new ArrayList();
        list.add(1);
        //这里填入数据list
        squareItemAdapter = new SquareItemAdapter(list);

        recyclerView.setAdapter(squareItemAdapter);
    }

}
