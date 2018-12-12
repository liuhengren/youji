package neet.com.youjidemo.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.ContentAdapter;

public class OthersActivity extends AppCompatActivity {
    private List list;
    private ListView listView;
    private AppBarLayout appBarLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orthers_personaldata_layout);
        findViews();
        list=new ArrayList();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);

        ContentAdapter contentAdapter=new ContentAdapter(this,
                list,R.layout.others_personaldata_item);
        listView.setAdapter(contentAdapter);
    }
    private  void findViews(){
        listView=findViewById(R.id.lv_others);
       appBarLayout= findViewById(R.id.rl_part1);

    }


}
