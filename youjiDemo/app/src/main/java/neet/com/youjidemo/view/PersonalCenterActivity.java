package neet.com.youjidemo.view;

import android.content.Intent;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.ProductionFragmentPagerAdapter;

/**
 * desc:个人中心Activity
 * author：梁启文
 * time:2018/12/06
 */

public class PersonalCenterActivity extends AppCompatActivity {
    private List productionList;
    private List attentionList;
    private List fansList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);

        Toolbar toolbar=(Toolbar)findViewById(R.id.tb_pde);
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
    }

    public void editInformaion(View view){
        Intent intent = new Intent(PersonalCenterActivity.this,PersonalDataEditorActivity.class);
        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
