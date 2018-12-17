package neet.com.youjidemo.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;

import java.util.List;

import neet.com.youjidemo.MainActivity;
import neet.com.youjidemo.R;

public class IndexRecommendAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;


    public IndexRecommendAdapter(FragmentManager fm) {
        super(fm);
    }

    public IndexRecommendAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fragmentList = fragmentList;
    }


    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "推荐";
            case 1:
                return "美食";
            case 2:
                return "游玩";
            case 3:
                return "逛街";
            case 4:
                return "滑冰";
            case 5:
                return "摄影";
        }
        return super.getPageTitle(position);
    }



}
