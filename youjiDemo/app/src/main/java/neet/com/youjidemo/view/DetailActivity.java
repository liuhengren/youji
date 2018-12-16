package neet.com.youjidemo.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.LinkAddress;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.DetailViewAdapter;
/**
 * desc:详情页（发表内容详情+评论），通过广播方式（每一分钟广播一次）获取当前系统时间
 * author：梁启文
 * time：2018/12/5
 */
public class DetailActivity extends AppCompatActivity {

    private BroadcastReceiver mTimeRefreshReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_TIME_TICK.equals(intent.getAction())) {
                setTime(getSystemTime());
            }
        }
    };

    private TextView time;

    private void setTime(CharSequence systemTime) {

        time.setText(systemTime);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        time = findViewById(R.id.tv_time);
        registerReceiver(mTimeRefreshReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));
        String currentTime = (String) getSystemTime();
        time.setText(currentTime);
        List list = new ArrayList();
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        list.add(1);
        RecyclerView recyclerView = findViewById(R.id.rv_detail_review);
        DetailViewAdapter adapter = new DetailViewAdapter(list);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(mTimeRefreshReceiver);
    }

    /** 获取当前系统时间 */
    private CharSequence getSystemTime() {
        java.text.DateFormat dateFormat = DateFormat.getTimeFormat(DetailActivity.this);
        long sysTime = System.currentTimeMillis();
        return dateFormat.format(sysTime);
    }

}
