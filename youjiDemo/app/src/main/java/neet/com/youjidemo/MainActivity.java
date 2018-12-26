 package neet.com.youjidemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Set;

import de.hdodenhof.circleimageview.CircleImageView;
import neet.com.youjidemo.bean.UserDateApplication;
import neet.com.youjidemo.view.Fragment.Index_Fragment;
import neet.com.youjidemo.view.Fragment.MeFragment;
import neet.com.youjidemo.view.Fragment.MessageFragment;
import neet.com.youjidemo.view.Fragment.Recommend_Fragment;
import neet.com.youjidemo.view.ShareActivity;

public class MainActivity extends AppCompatActivity {
    private HashMap<String,View> tabSpecMap = new HashMap<String,View>();
    private HashMap<String,ImageView> imageViewMap = new HashMap<String, ImageView>();
    private HashMap<String,TextView> tagNameMap = new HashMap<String,TextView>();
    private FrameLayout frameLayout;
    private TabWidget tabWidget;
    private FragmentTabHost fragmentTabHost;
    private DisplayMetrics dm;
    private ImageButton newAdd;
    private UserDateApplication userDateApplication;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userDateApplication=(UserDateApplication)getApplication();
        fragmentTabHost = findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);

        frameLayout = findViewById(android.R.id.content);
        tabWidget = findViewById(android.R.id.tabs);
        newAdd = findViewById(R.id.new_add);
        newAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userDateApplication.isLogin()){
                Intent intent = new Intent(MainActivity.this,ShareActivity.class);
                startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this,"请登录后操作",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //初始化z
        init();

        //选项卡更换事件
        setChanged();

        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        SharedPreferences user = getSharedPreferences("user", Context.MODE_PRIVATE);
        Log.e("1",user.getAll().toString());

    }


    /**
     *加载tabSpec布局，并将标签中的Icon和标签名存储到Map中
     */
    private View getTabSpec(int imageId,String tagName,String tag){
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.tabspec_layout,null);
        ImageView imageView = view.findViewById(R.id.tab_icon);
        imageView.setImageResource(imageId);
        TextView textView = view.findViewById(R.id.tab_text);
        textView.setText(tagName);
        tabSpecMap.put(tag,view);
        imageViewMap.put(tag,imageView);
        tagNameMap.put(tag,textView);
        return view;
    }


    //选项卡更改事件
    private void setChanged(){
        fragmentTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                ImageView imageViewtab1 = imageViewMap.get("tab1");
                ImageView imageViewtab2 = imageViewMap.get("tab2");

                ImageView imageViewtab4 = imageViewMap.get("tab4");
                ImageView imageViewtab5 = imageViewMap.get("tab5");
                TextView textViewtab1 = tagNameMap.get("tab1");
                TextView textViewtab2 = tagNameMap.get("tab2");
                TextView textViewtab4 = tagNameMap.get("tab4");
                TextView textViewtab5 = tagNameMap.get("tab5");


                //获取选项卡的标签集合
                Set<String> keys = tabSpecMap.keySet();
                for (String s:keys){
                    if (tabId.equals("tab1")){

                        imageViewtab1.setImageResource(R.drawable.home2);
                        imageViewtab2.setImageResource(R.drawable.community);
                        //imageViewtab3.setImageResource(R.drawable.add);
                        imageViewtab4.setImageResource(R.drawable.message);
                        imageViewtab5.setImageResource(R.drawable.me);
                        textViewtab1.setTextColor(getResources().getColor(android.R.color.holo_green_light));
                        textViewtab2.setTextColor(getResources().getColor(R.color.lightgray));
                        textViewtab4.setTextColor(getResources().getColor(R.color.lightgray));
                        textViewtab5.setTextColor(getResources().getColor(R.color.lightgray));
                    }
                    if (tabId.equals("tab2")){

                        imageViewtab1.setImageResource(R.drawable.home);
                        imageViewtab2.setImageResource(R.drawable.community2);
                        //imageViewtab3.setImageResource(R.drawable.add);
					    imageViewtab4.setImageResource(R.drawable.message);
                        imageViewtab5.setImageResource(R.drawable.me);
                        textViewtab1.setTextColor(getResources().getColor(R.color.lightgray));
                        textViewtab2.setTextColor(getResources().getColor(android.R.color.holo_green_light));
                        textViewtab4.setTextColor(getResources().getColor(R.color.lightgray));
                        textViewtab5.setTextColor(getResources().getColor(R.color.lightgray));
                    }
                    if (tabId.equals("tab4")){


                        imageViewtab1.setImageResource(R.drawable.home);
                        imageViewtab2.setImageResource(R.drawable.community);
                        //imageViewtab3.setImageResource(R.drawable.add);
                        imageViewtab4.setImageResource(R.drawable.message2);

                        imageViewtab5.setImageResource(R.drawable.me);
                        textViewtab1.setTextColor(getResources().getColor(R.color.lightgray));
                        textViewtab2.setTextColor(getResources().getColor(R.color.lightgray));
                        textViewtab4.setTextColor(getResources().getColor(android.R.color.holo_green_light));
                        textViewtab5.setTextColor(getResources().getColor(R.color.lightgray));
                    }
                    if (tabId.equals("tab5")){


                        imageViewtab1.setImageResource(R.drawable.home);
                        imageViewtab2.setImageResource(R.drawable.community);
                        //imageViewtab3.setImageResource(R.drawable.add);

                        imageViewtab4.setImageResource(R.drawable.message);
                        imageViewtab5.setImageResource(R.drawable.me_choosed);
                        textViewtab1.setTextColor(getResources().getColor(R.color.lightgray));
                        textViewtab2.setTextColor(getResources().getColor(R.color.lightgray));
                        textViewtab4.setTextColor(getResources().getColor(R.color.lightgray));
                        textViewtab5.setTextColor(getResources().getColor(android.R.color.holo_green_light));
                    }
                }
            }
        });
    }


    //初始化
    private void init(){
        //初始化选项卡
        TabHost.TabSpec tabSpec1 = fragmentTabHost.newTabSpec("tab1")
                .setIndicator(getTabSpec(R.drawable.home,"首页","tab1"));
        //添加选项卡
        fragmentTabHost.addTab(tabSpec1,Index_Fragment.class,null);

        //初始化选项卡
        TabHost.TabSpec tabSpec2 = fragmentTabHost.newTabSpec("tab2")
                .setIndicator(getTabSpec(R.drawable.community,"社区","tab2"));
        //添加选项卡(后来补充)
        fragmentTabHost.addTab(tabSpec2,Recommend_Fragment.class,null);

        //初始化选项卡


        TabHost.TabSpec tabSpec3 = fragmentTabHost.newTabSpec("tab3")
                .setIndicator(getTabSpec(R.drawable.jia,null,"tab3"));

        //添加选项卡(后来补充)
        fragmentTabHost.addTab(tabSpec3,Index_Fragment.class,null);


        //初始化选项卡
        TabHost.TabSpec tabSpec4 = fragmentTabHost.newTabSpec("tab4")
                .setIndicator(getTabSpec(R.drawable.message,"消息","tab4"));
        //添加选项卡(后来补充)
        fragmentTabHost.addTab(tabSpec4,MessageFragment.class,null);

        //初始化选项卡
        TabHost.TabSpec tabSpec5 = fragmentTabHost.newTabSpec("tab5")
                .setIndicator(getTabSpec(R.drawable.me,"我","tab5"));
        //添加选项卡(后来补充)
        fragmentTabHost.addTab(tabSpec5,MeFragment.class,null);

        //设置默认选中的选项卡
        fragmentTabHost.setCurrentTab(0);
        ImageView imageView = imageViewMap.get("tab1");
        imageView.setImageResource(R.drawable.home2);
        final TextView textView = tagNameMap.get("tab1");
        textView.setTextColor(getResources().getColor(android.R.color.holo_green_light));
    }

}