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
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.DetailViewAdapter;
import neet.com.youjidemo.bean.ShowDynamicInAll;

/**
 * desc:详情页（发表内容详情+评论），通过广播方式（每一分钟广播一次）获取当前系统时间
 * author：梁启文
 * time：2018/12/5
 */
public class DetailActivity extends AppCompatActivity {

    /**系统定时发广播来通知APP时间的变化*/
    private BroadcastReceiver mTimeRefreshReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_TIME_TICK.equals(intent.getAction())) {
                setTime(getSystemTime());
            }
        }
    };

    private ImageButton imageButton;

    private int isAttentioned;//用来标记该用户是否被关注

    private ClickListener mClickListener; //点击事件监听器

    private CircleImageView btnImageHead; //头像

    private TextView time; //当前系统时间

    private List mDataList; //数据源

    private RecyclerView recyclerView; //列表

    private TextView tvUserName; //用户名

    private ImageButton btnAttention; //关注按钮

    private TextView tvDetaTime;

    private TextView tvDetaDes;

    private ImageView iVDetaPic;

    private ShowDynamicInAll showDynamicInAll;


    /**设置时间*/
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

        Toolbar toolbar=(Toolbar)findViewById(R.id.tbdetail_pde);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);//主键按钮能否可点击x
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示返回图标
        findViews();
        init();
        btnImageHead.setOnClickListener(mClickListener);
        tvUserName.setOnClickListener(mClickListener);
        btnAttention.setOnClickListener(mClickListener);
        DetailViewAdapter adapter = new DetailViewAdapter(mDataList);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        showDynamicInAll=(ShowDynamicInAll)getIntent().getSerializableExtra("dynamicDeta");
        setDynamic(showDynamicInAll);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mTimeRefreshReceiver);
    }

    /** 获取当前系统时间 */
    private CharSequence getSystemTime() {
        java.text.DateFormat dateFormat = DateFormat.getTimeFormat(DetailActivity.this);
        long sysTime = System.currentTimeMillis();
        return dateFormat.format(sysTime);
    }

    /**初始化数据*/
    private void init(){
        mDataList = new ArrayList();
        mDataList.add(1);
        mDataList.add(1);
        mDataList.add(1);
        mDataList.add(1);
        mDataList.add(1);
        mDataList.add(1);
        mDataList.add(1);
    }

    /***初始化控件*/
    private void findViews(){
        mClickListener = new ClickListener();
        tvUserName = findViewById(R.id.tv_detail_username);
        recyclerView = findViewById(R.id.rv_detail_review);
        btnImageHead = findViewById(R.id.cImage_detail_head);
        tvUserName = findViewById(R.id.tv_detail_username);
        btnAttention = findViewById(R.id.btn_detail_attention);
        isAttentioned = R.drawable.befans;
        tvDetaTime=findViewById(R.id.tv_detail_time);
        tvDetaDes=findViewById(R.id.tv_detail_description);
        iVDetaPic=findViewById(R.id.image_detail_picture);
    }

    /**定义一个内部类来处里Activity中的点击事件*/
    public class ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                /**点击用户名和头像可以跳转至个人中心，注意数据的获取*/
                case R.id.cImage_detail_head:
                    Intent intent = new Intent(DetailActivity.this,PersonalCenterActivity.class);
                    intent.putExtra("user_id",showDynamicInAll.getUser_id());
                    startActivity(intent);
                    break;
                case R.id.tv_detail_username:
                    Intent intent1 = new Intent(DetailActivity.this,PersonalCenterActivity.class);
                    intent1.putExtra("user_id",showDynamicInAll.getUser_id());
                    startActivity(intent1);
                    break;
                /**点击之后应在数据库里增加一条数据，并标识，
                 * 在绘制Activity时也用先判断该用户是否被关注了
                 * ，来实现指定好图标
                 */
                case R.id.btn_detail_attention:
                    if (isAttentioned == R.drawable.befans){
                        btnAttention.setImageResource(R.drawable.have_attention);
                        isAttentioned = R.drawable.have_attention;
                    }
                    else if (isAttentioned == R.drawable.have_attention){
                        btnAttention.setImageResource(R.drawable.befans);
                        isAttentioned = R.drawable.befans;
                    }
                    break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.tbdetail_pde:
            finish();
            break;
        }
        finish();
        return super.onOptionsItemSelected(item);
    }
    private void setDynamic(ShowDynamicInAll showDynamicInAll){
        tvUserName.setText(showDynamicInAll.getUsername());
        RequestOptions options=RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE);
        Glide.with(DetailActivity.this).load(showDynamicInAll.getUser_touxiang()).into(btnImageHead);
        tvDetaDes.setText(showDynamicInAll.getDynamic_text());
        tvDetaTime.setText(showDynamicInAll.getTime());
        Glide.with(this).load(showDynamicInAll.getDynamicImg_url()).into(iVDetaPic);
    }
}
