package neet.com.youjidemo;

import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private HashMap<String,View> tabSpecMap = new HashMap<String,View>();
    private HashMap<String,ImageView> imageViewMap = new HashMap<String, ImageView>();
    private HashMap<String,String> tagNameMap = new HashMap<String,String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTabHost fragmentTabHost = findViewById(android.R.id.tabhost);
        fragmentTabHost.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);

        //初始化选项卡
        TabHost.TabSpec tabSpec1 = fragmentTabHost.newTabSpec("tab1")
                .setIndicator(getTabSpec(R.drawable.fivestar,"首页","tab1"));
        //添加选项卡
        fragmentTabHost.addTab(tabSpec1,Food_Fragment.class,null);

        //初始化选项卡
        TabHost.TabSpec tabSpec2 = fragmentTabHost.newTabSpec("tab2")
                .setIndicator(getTabSpec(R.drawable.anotherhome,"社区","tab2"));
        //添加选项卡(后来补充)
        fragmentTabHost.addTab(tabSpec2,Food_Fragment.class,null);

        //初始化选项卡
        TabHost.TabSpec tabSpec3 = fragmentTabHost.newTabSpec("tab3")
                .setIndicator(getTabSpec(R.drawable.add,null,"tab3"));
        //添加选项卡(后来补充)
        fragmentTabHost.addTab(tabSpec3,Food_Fragment.class,null);

        //初始化选项卡
        TabHost.TabSpec tabSpec4 = fragmentTabHost.newTabSpec("tab4")
                .setIndicator(getTabSpec(R.drawable.anotherhome,"消息","tab4"));
        //添加选项卡(后来补充)
        fragmentTabHost.addTab(tabSpec4,Food_Fragment.class,null);

        //初始化选项卡
        TabHost.TabSpec tabSpec5 = fragmentTabHost.newTabSpec("tab5")
                .setIndicator(getTabSpec(R.drawable.anotherhome,"我","tab5"));
        //添加选项卡(后来补充)
        fragmentTabHost.addTab(tabSpec5,Food_Fragment.class,null);


    }

    private View getTabSpec(int imageId,String tagName,String tag){
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.tabspec_layout,null);
        ImageView imageView = view.findViewById(R.id.tab_icon);
        imageView.setImageResource(imageId);
        TextView textView = view.findViewById(R.id.tab_text);
        textView.setText(tagName);
        tabSpecMap.put(tag,view);
        imageViewMap.put(tag,imageView);
        return view;
    }
}