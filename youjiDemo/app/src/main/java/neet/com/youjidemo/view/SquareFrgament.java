package neet.com.youjidemo.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ScrollDirectionListener;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.ContentAdapter;
import neet.com.youjidemo.R;
import neet.com.youjidemo.SquareAdapter;
import neet.com.youjidemo.SquareItemAdapter;

/*
* 具体的美食，游玩，游记，推荐和广场
* */
public class SquareFrgament extends Fragment {

    private List list;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private CoordinatorLayout coordinatorLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  =inflater.inflate(R.layout.square_layout, container, false);
        recyclerView=view.findViewById(R.id.rl_square_item);
        floatingActionButton=view.findViewById(R.id.fab_top);
        list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        RecyclerView.LayoutManager manager=new LinearLayoutManager(getContext());
        SquareItemAdapter squareItemAdapter = new SquareItemAdapter(list);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(squareItemAdapter);

        setFloatingActionButton();
        return view;

    }
    /*
     * 设置FloatingActionButton 让ListView回到顶部
     * */
    private void  setFloatingActionButton(){
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
}
