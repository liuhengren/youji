package neet.com.youjidemo.view;

import android.content.Intent;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Set;

import neet.com.youjidemo.R;

public class MainActivity extends AppCompatActivity {
    private HashMap<String, View> tabSpecMap = new HashMap<String, View>();
    private HashMap<String, ImageView> imageViewMap = new HashMap<String, ImageView>();
    private HashMap<String, TextView> tagNameMap = new HashMap<String, TextView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTabHost fragmentTabHost = findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        //初始化选项卡
        TabHost.TabSpec tabSpec1 = fragmentTabHost.newTabSpec("tab1")
                .setIndicator(getTabSpec(R.drawable.fivestar, "首页", "tab1"));
        //添加选项卡
        fragmentTabHost.addTab(tabSpec1, Food_Fragment.class, null);

        //初始化选项卡
        TabHost.TabSpec tabSpec2 = fragmentTabHost.newTabSpec("tab2")
                .setIndicator(getTabSpec(R.drawable.anotherhome, "社区", "tab2"));
        //添加选项卡
        fragmentTabHost.addTab(tabSpec2, Recommend_Fragment.class, null);

        //初始化选项卡
        TabHost.TabSpec tabSpec3 = fragmentTabHost.newTabSpec("tab3")
                .setIndicator(getTabSpec(R.drawable.add, null, "tab3"));
        //添加选项卡(后来补充)
        fragmentTabHost.addTab(tabSpec3, Food_Fragment.class, null);

        //初始化选项卡
        TabHost.TabSpec tabSpec4 = fragmentTabHost.newTabSpec("tab4")
                .setIndicator(getTabSpec(R.drawable.message, "消息", "tab4"));
        //添加选项卡(后来补充)
        fragmentTabHost.addTab(tabSpec4, Food_Fragment.class, null);

        //初始化选项卡
        TabHost.TabSpec tabSpec5 = fragmentTabHost.newTabSpec("tab5")
                .setIndicator(getTabSpec(R.drawable.me, "我", "tab5"));
        //添加选项卡(后来补充)
        fragmentTabHost.addTab(tabSpec5, Food_Fragment.class, null);

        //默认选中第一个
        fragmentTabHost.setCurrentTab(0);
        ImageView indexTabView = imageViewMap.get("tab1");
        indexTabView.setImageResource(R.drawable.fivestar_choosed);
        TextView indexTabTextView = tagNameMap.get("tab1");
        indexTabTextView.setTextColor(getResources().getColor(android.R.color.black));

        //选项卡选中事件
        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Set<String> tabSpecSet = tabSpecMap.keySet();
                ImageView imageViewTab1 = imageViewMap.get("tab1");
                ImageView imageViewTab2 = imageViewMap.get("tab2");
                ImageView imageViewTab3 = imageViewMap.get("tab3");
                ImageView imageViewTab4 = imageViewMap.get("tab4");
                ImageView imageViewTab5 = imageViewMap.get("tab5");
                TextView textViewTab1 = tagNameMap.get("tab1");
                TextView textViewTab2 = tagNameMap.get("tab2");
                TextView textViewTab3 = tagNameMap.get("tab3");
                TextView textViewTab4 = tagNameMap.get("tab4");
                TextView textViewTab5 = tagNameMap.get("tab5");
                //循环标签列表 ，添加选中事件
                for (String tag : tabSpecSet) {
                    if (tabId.equals("tab1")) {
                        imageViewTab1.setImageResource(R.drawable.fivestar_choosed);
                        imageViewTab2.setImageResource(R.drawable.anotherhome);
                        imageViewTab3.setImageResource(R.drawable.add);
                        imageViewTab4.setImageResource(R.drawable.message);
                        imageViewTab5.setImageResource(R.drawable.me);
                        textViewTab1.setTextColor(getResources().getColor(android.R.color.black));
                        textViewTab2.setTextColor(getResources().getColor(R.color.lightgray));
                        textViewTab4.setTextColor(getResources().getColor(R.color.lightgray));
                        textViewTab5.setTextColor(getResources().getColor(R.color.lightgray));
                    }
                    if (tabId.equals("tab2")) {
                        imageViewTab1.setImageResource(R.drawable.fivestar);
                        imageViewTab2.setImageResource(R.drawable.home);
                        imageViewTab3.setImageResource(R.drawable.add);
                        imageViewTab4.setImageResource(R.drawable.message);
                        imageViewTab5.setImageResource(R.drawable.me);
                        textViewTab1.setTextColor(getResources().getColor(R.color.lightgray));
                        textViewTab2.setTextColor(getResources().getColor(android.R.color.black));
                        textViewTab4.setTextColor(getResources().getColor(R.color.lightgray));
                        textViewTab5.setTextColor(getResources().getColor(R.color.lightgray));
                    }
                    if (tabId.equals("tab3")){
                        Intent intent = new Intent(MainActivity.this,ShareActivity.class);
                        startActivity(intent);
                    }
                    if (tabId.equals("tab4")){
                        imageViewTab1.setImageResource(R.drawable.fivestar);
                        imageViewTab2.setImageResource(R.drawable.anotherhome);
                        imageViewTab3.setImageResource(R.drawable.add);
                        imageViewTab4.setImageResource(R.drawable.message_choosed);
                        imageViewTab5.setImageResource(R.drawable.me);
                        textViewTab1.setTextColor(getResources().getColor(R.color.lightgray));
                        textViewTab2.setTextColor(getResources().getColor(R.color.lightgray));
                        textViewTab4.setTextColor(getResources().getColor(android.R.color.black));
                        textViewTab5.setTextColor(getResources().getColor(R.color.lightgray));
                    }
                    if (tabId.equals("tab5")){
                        imageViewTab1.setImageResource(R.drawable.fivestar);
                        imageViewTab2.setImageResource(R.drawable.anotherhome);
                        imageViewTab3.setImageResource(R.drawable.add);
                        imageViewTab4.setImageResource(R.drawable.message);
                        imageViewTab5.setImageResource(R.drawable.me_choosed);
                        textViewTab1.setTextColor(getResources().getColor(R.color.lightgray));
                        textViewTab2.setTextColor(getResources().getColor(R.color.lightgray));
                        textViewTab4.setTextColor(getResources().getColor(R.color.lightgray));
                        textViewTab5.setTextColor(getResources().getColor(android.R.color.black));
                    }
                }
            }
        });

    }


    private View getTabSpec(int imageId, String tagName, String tag) {
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.tabspec_layout, null);
        ImageView imageView = view.findViewById(R.id.tab_icon);
        imageView.setImageResource(imageId);
        TextView textView = view.findViewById(R.id.tab_text);
        textView.setText(tagName);
        tabSpecMap.put(tag, view);
        imageViewMap.put(tag, imageView);
        tagNameMap.put(tag, textView);
        return view;
    }
}