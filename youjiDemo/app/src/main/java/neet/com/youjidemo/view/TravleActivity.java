
package neet.com.youjidemo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.SquareAdapter;
import neet.com.youjidemo.view.Fragment.Travel_RecommendFragment;
import neet.com.youjidemo.view.Fragment.Travel_SquareFragment;


/**
 * 1.分类：旅游的Activity
 */
public class TravleActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List fragmentList = new ArrayList();
    private TextView title;
    private TextView instruction;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourpart_activity);
        Toolbar toolbar=(Toolbar)findViewById(R.id.tb_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);//主键按钮能否可点击
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示返回图标
        findViews();
        setFragment();


        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    private void findViews() {
        tabLayout = findViewById(R.id.tl_recommend);
        viewPager = findViewById(R.id.vp_dynamic);
        title = findViewById(R.id.tv_title);
        instruction = findViewById(R.id.tv_instruction);
        toolbar = findViewById(R.id.tb_back);
    }


    /**
     * 创建广场和推荐 对应的Fragment
     */
    private void setFragment() {
        Travel_SquareFragment squareFregament = new Travel_SquareFragment();
        Travel_RecommendFragment recommendFragment = new Travel_RecommendFragment();

        fragmentList.add(squareFregament);
        fragmentList.add(recommendFragment);

        viewPager.setAdapter(new SquareAdapter(getSupportFragmentManager(), fragmentList));
        tabLayout.setupWithViewPager(viewPager);

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
}


