package neet.com.youjidemo.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.AttentionPagerAdapter;

/**
 * desc:个人中心关注Fragment
 * author：梁启文
 * time：2018/12/06
 */
public class AttentionFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.production_viewpager_layout,null);
        List list = new ArrayList();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);

        RecyclerView recyclerView = view.findViewById(R.id.rv_production);

        //创建默认的线性LayoutManager
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) manager).setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        //创建Adapter
        AttentionPagerAdapter adapter = new AttentionPagerAdapter(list);

        //如果Item的高度是固定的，这句代码可以提高效率
        recyclerView.setHasFixedSize(true);


        //设置Adapter
        recyclerView.setAdapter(adapter);

        //设置Item的点击事件（具体实现方法在Adapter中）
        adapter.setmOnItemClickListener(new AttentionPagerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getContext(),"点击了"+position,Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
