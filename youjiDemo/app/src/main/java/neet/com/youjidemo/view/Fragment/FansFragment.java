package neet.com.youjidemo.view.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.FansPagerAdapter;

/**
 * desc:个人中心粉丝页Fragment
 * author：梁启文
 * time:2018/12/11
 */
public class FansFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fans_view_pager_layout,null);
        List list = new ArrayList();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);

        RecyclerView recyclerView = view.findViewById(R.id.rv_fans);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        FansPagerAdapter adapter = new FansPagerAdapter(list);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
