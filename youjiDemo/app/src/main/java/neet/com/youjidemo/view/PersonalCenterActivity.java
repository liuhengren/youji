package neet.com.youjidemo.view;

import android.content.Intent;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;

import com.bigkoo.pickerview.listener.OnOptionsSelectListener;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.ProductionFragmentPagerAdapter;
import neet.com.youjidemo.view.Fragment.AttentionFragment;
import neet.com.youjidemo.view.Fragment.FansFragment;
import neet.com.youjidemo.view.Fragment.ProductionFragment;

/**
 * desc:个人中心Activity
 * author：梁启文
 * time:2018/12/06
 */

public class PersonalCenterActivity extends AppCompatActivity {
    private List productionList;
    private List attentionList;
    private List fansList;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);

        Toolbar toolbar=(Toolbar)findViewById(R.id.tb_personal_center);
        swipeRefreshLayout=findViewById(R.id.srl_downrefresh);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);//主键按钮能否可点击
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示返回图标


        ViewPager mViewPager = findViewById(R.id.viewpager);
        List<Fragment> list = new ArrayList();
        ProductionFragment productionFragment = new ProductionFragment();
        FansFragment fansFragment = new FansFragment();
        AttentionFragment attentionFragment = new AttentionFragment();
        list.add(productionFragment);
        list.add(attentionFragment);
        list.add(fansFragment);
        ProductionFragmentPagerAdapter adapter = new ProductionFragmentPagerAdapter(getSupportFragmentManager(),list);
        mViewPager.setAdapter(adapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("作品"));
        tabLayout.addTab(tabLayout.newTab().setText("收藏"));
        tabLayout.addTab(tabLayout.newTab().setText("粉丝"));
        tabLayout.setupWithViewPager(mViewPager);

        setDownRefresh();
    }

    public void editInformaion(View view){
        Intent intent = new Intent(PersonalCenterActivity.this,PersonalDataEditorActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.tb_personal_center:
                finish();
                break;
        }
        finish();
        return super.onOptionsItemSelected(item);
    }

/*
* 设置个人中心的下拉刷新
* */
    private  void setDownRefresh(){
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
                //整个界面刷新 进行
            }
        });
    }



}

