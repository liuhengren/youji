package neet.com.youjidemo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.SquareAdapter;


public class FoodActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List fragmentList=new ArrayList();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_activity);
        findViews();
        inits();
//        setFloatingActionButton();

        Toolbar toolbar=findViewById(R.id.tb_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    private void findViews() {
        tabLayout = findViewById(R.id.tl_recommend);
        viewPager=findViewById(R.id.vp_dynamic);

    }


    private  void  inits(){

        //创建广场和推荐 对应的Fragment
        SquareFrgament squareFregament=new SquareFrgament();
        SquareFrgament squareFregament1=new SquareFrgament();
        fragmentList.add(squareFregament);
        fragmentList.add(squareFregament1);


        viewPager.setAdapter(new SquareAdapter(getSupportFragmentManager(),fragmentList));
        tabLayout.setupWithViewPager(viewPager);

    }


}
