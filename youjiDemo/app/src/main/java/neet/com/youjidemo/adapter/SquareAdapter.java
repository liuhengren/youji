package neet.com.youjidemo.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

public class SquareAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList;
    public SquareAdapter(FragmentManager fm) {
        super(fm);
    }

    public SquareAdapter(FragmentManager fm, List<Fragment> fragmentList) {
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
                return "广场";
        }
        return super.getPageTitle(position);
    }
}