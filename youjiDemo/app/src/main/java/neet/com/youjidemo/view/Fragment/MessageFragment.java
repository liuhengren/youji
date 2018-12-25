package neet.com.youjidemo.view.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.NotificationAdapter;
import neet.com.youjidemo.adapter.SquareAdapter;

public class MessageFragment extends Fragment {
    private LinearLayout ll_at;
    private LinearLayout ll_comment;
    private LinearLayout ll_good;
    private boolean is_at;
    private boolean is_comment;
    private boolean is_good;
    private TextView movement;
    private TextView message;
    private View view;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List fragmentList = new ArrayList();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.activity_notification, container, false);
            findViews();

            setFragment();
        }
            is_at = false;
            is_comment = false;
            is_good = false;




        return view;
    }

    private void findViews() {
        tabLayout = view.findViewById(R.id.tl_recommend);
        viewPager = view.findViewById(R.id.vp_dynamic);
    }

    /*
   创建消息和推荐 对应的Fragment
    */
    private void setFragment() {

        NotificationMessageFragment notificationMessageFragment = new NotificationMessageFragment();
        NotificationRecommendFragment notificationRecommendFragment = new NotificationRecommendFragment();


        fragmentList.add(notificationRecommendFragment);
        fragmentList.add(notificationMessageFragment);
        viewPager.setAdapter(new NotificationAdapter(getActivity().getSupportFragmentManager(), fragmentList));
        tabLayout.setupWithViewPager(viewPager);

    }
}

