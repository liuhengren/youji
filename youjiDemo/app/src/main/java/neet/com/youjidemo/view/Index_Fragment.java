package neet.com.youjidemo.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.IndexRecommendAdapter;
import neet.com.youjidemo.adapter.SquareAdapter;

import static android.support.design.widget.TabLayout.MODE_FIXED;


/**
 * 首页
 */
public class Index_Fragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private  View view;
    private List fragmentList=new ArrayList();
    IndexRecommendAdapter indexRecommendAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(view==null) {
            view = inflater.inflate(R.layout.index_layout, container, false);
            findViews();
            setFragment();

        }
        return view;

    }

    private void findViews() {
        tabLayout =view.findViewById(R.id.tl_index);
        viewPager=view.findViewById(R.id.vp_indexrecommend);

    }

    /*
    创建首页  ：推荐   对应的Fragment
     */
    private  void  setFragment(){

        Index_RecommendFragment index_recommendFragment=new Index_RecommendFragment();
        Index_FoodFragment index_foodFragment=new Index_FoodFragment();
        Index_FoodFragment index_foodFragment2=new Index_FoodFragment();
        Index_FoodFragment index_foodFragment3=new Index_FoodFragment();
        Index_FoodFragment index_foodFragment4=new Index_FoodFragment();
        Index_FoodFragment index_foodFragment5=new Index_FoodFragment();

        fragmentList.add(index_recommendFragment);
        fragmentList.add(index_foodFragment);
        fragmentList.add(index_foodFragment2);
        fragmentList.add(index_foodFragment3);
        fragmentList.add(index_foodFragment4);
        fragmentList.add(index_foodFragment5);
        indexRecommendAdapter = new IndexRecommendAdapter
                (getActivity().getSupportFragmentManager(), fragmentList);
        viewPager.setAdapter(indexRecommendAdapter);

        tabLayout.setupWithViewPager(viewPager);

    }




}
