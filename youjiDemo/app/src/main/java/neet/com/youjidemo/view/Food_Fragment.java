package neet.com.youjidemo.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.adapter.IndexListViewAdapter;
import neet.com.youjidemo.R;


/**
 * 首页
 */
public class Food_Fragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.index_tab_layout, container, false);
        TabHost tabHost = view.findViewById(android.R.id.tabhost);
        //初始化
        tabHost.setup();
        //创建选项卡
        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("recommend")
                .setIndicator("推荐")
                .setContent(R.id.tab1);
        //添加选项卡
        tabHost.addTab(tabSpec1);

        //创建选项卡
        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("delicious")
                .setIndicator("美食")
                .setContent(R.id.tab2);
        //添加选项卡
        tabHost.addTab(tabSpec2);

        //创建选项卡
        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("play")
                .setIndicator("游玩")
                .setContent(R.id.tab3);
        //添加选项卡
        tabHost.addTab(tabSpec3);

        //创建选项卡
        TabHost.TabSpec tabSpec4 = tabHost.newTabSpec("travel_notes")
                .setIndicator("游记")
                .setContent(R.id.tab4);
        //添加选项卡
        tabHost.addTab(tabSpec4);

        ListView listView = view.findViewById(R.id.recommend);
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        IndexListViewAdapter adapter = new IndexListViewAdapter(getContext(), list, R.layout.recommend_tabspec_item);
        listView.setAdapter(adapter);

        ListView listView1 = view.findViewById(R.id.delicious);
        List list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
        IndexListViewAdapter adapter1 = new IndexListViewAdapter(getContext(), list, R.layout.recommend_tabspec_item);
        listView1.setAdapter(adapter1);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),"点击了"+position,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getContext(), DetailActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
