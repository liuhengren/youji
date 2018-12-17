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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.ContentAdapter;
import neet.com.youjidemo.adapter.ProductionPagerAdapter;

/**
 * desc:作品页的Fragment
 * author：梁启文
 * time:2018/12/11
 */
public class ProductionFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.production_viewpager_layout, null);

        List list = new ArrayList();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);

        RecyclerView recyclerView = view.findViewById(R.id.rv_production);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        ProductionPagerAdapter adapter = new ProductionPagerAdapter(list);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
