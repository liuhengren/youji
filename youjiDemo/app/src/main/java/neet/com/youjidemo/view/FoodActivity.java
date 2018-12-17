package neet.com.youjidemo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.SquareAdapter;


/*
* 1.分类：食物的Activity
* */
public class FoodActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List fragmentList=new ArrayList();
    private TextView title;
    private  TextView instruction;
    Toolbar toolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fourpart_activity);
        findViews();
        setFragment();


        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);






    }

    private void findViews() {
        tabLayout = findViewById(R.id.tl_recommend);
        viewPager=findViewById(R.id.vp_dynamic);
        title=findViewById(R.id.tv_title);
        instruction=findViewById(R.id.tv_instruction);
        toolbar=findViewById(R.id.tb_back);
    }


    /*
    创建广场和推荐 对应的Fragment
     */
    private  void  setFragment(){

        Food_RecommendFragment recommendFragment=new Food_RecommendFragment();
        Food_SquareFragment squareFregament=new Food_SquareFragment();

        fragmentList.add(recommendFragment);
        fragmentList.add(squareFregament);
        viewPager.setAdapter(new SquareAdapter(getSupportFragmentManager(),fragmentList));
        tabLayout.setupWithViewPager(viewPager);

    }



    /*
    * 设置 “美食”对应的TextView  以及 底部的简介
    * */
    private void setData(){
    }

}