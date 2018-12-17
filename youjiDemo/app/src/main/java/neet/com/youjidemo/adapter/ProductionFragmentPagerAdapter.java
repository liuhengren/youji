package neet.com.youjidemo.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 *desc:联动效果的个人中心的作品页Fragment
 * author：梁启文
 * time:2018/12/06
 */

public class ProductionFragmentPagerAdapter extends FragmentStatePagerAdapter {


    private List<Fragment> mFragments;
    private String[] titleArray;

    public ProductionFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public ProductionFragmentPagerAdapter(FragmentManager fm, List<Fragment> mFragments) {
        super(fm);
        this.mFragments = mFragments;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 :
                return "作品";
            case 1:
                return "收藏";
            case 2:
                return "粉丝";
        }
        return super.getPageTitle(position);
    }
}