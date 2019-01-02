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
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.Propelling_Adapter;

import neet.com.youjidemo.bean.Message;
import neet.com.youjidemo.view.DetailActivity;


public class NotificationRecommendFragment extends Fragment {
    private List list;
    private ListView listView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View view;

    int lastVisibleItem;
    boolean isLoading = false;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(view==null) {
            view = inflater.inflate(R.layout.notification_recommend_layout, container, false);
            findViews();
            setListView();
            setDownRefresh();
        }
        return view;

    }

    private void setListView() {
        //推送的ListView
        List<Message> messages = new ArrayList<>();
        Message message1 = new Message(R.drawable.head_show_three, "任时光匆匆流去我只在乎你", "世界这么大你想去看看。", "11/27 8:00");
        Message message2 = new Message(R.drawable.head_show_three, "任时光匆匆流去我只在乎你", "世界这么大你想去看看。", "11/27 8:00");
        Message message3 = new Message(R.drawable.head_show_two, "任时光匆匆流去我只在乎你", "世界这么大你想去看看。", "11/27 8:00");
        Message message4 = new Message(R.drawable.head_show_one, "任时光匆匆流去我只在乎你", "世界这么大你想去看看。", "11/27 8:00");
        messages.add(message1);
        messages.add(message2);
        messages.add(message3);
        messages.add(message4);
        ListView listView = view.findViewById(R.id.lv_propelling);
        Propelling_Adapter adapter = new Propelling_Adapter(getContext(), R.layout.propelling_listview_item, messages);
        //推送页ListView的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),DetailActivity.class);
                startActivity(intent);
            }
        });

        listView.setAdapter(adapter);
    }

    private void findViews() {
        listView = view.findViewById(R.id.lv_propelling);
        swipeRefreshLayout = view.findViewById(R.id.srl_downrefresh);

    }

    /*
     * 设置推荐的下拉刷新
     * */
    private void setDownRefresh() {
        swipeRefreshLayout.setProgressViewOffset(true, 50, 200);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //推送刷新 进行请求数据

            }
        });

    }

}