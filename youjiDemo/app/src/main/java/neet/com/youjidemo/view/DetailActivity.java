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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.DetailViewAdapter;
import neet.com.youjidemo.bean.Comment;

/**
 * desc:详情页（发表内容详情+评论），通过广播方式（每一分钟广播一次）获取当前系统时间
 * author：梁启文
 * time：2018/12/5
 */
public class DetailActivity extends AppCompatActivity {

    /**
     * 系统定时发广播来通知APP时间的变化
     */
    private BroadcastReceiver mTimeRefreshReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Intent.ACTION_TIME_TICK.equals(intent.getAction())) {
                setTime(getSystemTime());
            }
        }
    };

    private ImageButton imageButton;

    private ImageView imageCollect; //底部收藏图片

    private TextView imageComment; //底部评论图片

    private ImageView imageLike; //底部点赞图片

    private int isAttentioned;//用来标记该用户是否被关注

    private ClickListener mClickListener; //点击事件监听器

    private CircleImageView btnImageHead; //头像

    private TextView time; //当前系统时间

    private List<Comment> mDataList; //数据源

    private RecyclerView recyclerView; //列表

    private TextView tvUserName; //用户名

    private ImageButton btnAttention; //关注按钮

    private DetailViewAdapter adapter;

    private LinearLayout rl_enroll;

    private RelativeLayout rl_comment;

    private TextView hide_down;

    private Button comment_send;

    private TextView comment_content;

    private Toolbar toolbar;

    /**
     * 设置时间
     */
    private void setTime(CharSequence systemTime) {

        time.setText(systemTime);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        findViews();
        registerReceiver(mTimeRefreshReceiver, new IntentFilter(Intent.ACTION_TIME_TICK));
        String currentTime = (String) getSystemTime();
        time.setText(currentTime);
        init();
        setClickListener();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mTimeRefreshReceiver);
    }

    /**
     * 获取当前系统时间
     */
    private CharSequence getSystemTime() {
        java.text.DateFormat dateFormat = DateFormat.getTimeFormat(DetailActivity.this);
        long sysTime = System.currentTimeMillis();
        return dateFormat.format(sysTime);
    }

    /**
     * 初始化数据
     */
    private void init() {
        mDataList = new ArrayList();
        adapter = new DetailViewAdapter(mDataList);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    /**
     * 设置监听器
     */
    private void setClickListener() {
        btnImageHead.setOnClickListener(mClickListener);
        tvUserName.setOnClickListener(mClickListener);
        btnAttention.setOnClickListener(mClickListener);
        imageCollect.setOnClickListener(mClickListener);
        imageComment.setOnClickListener(mClickListener);
        imageLike.setOnClickListener(mClickListener);
        comment_content.setOnClickListener(mClickListener);
        comment_send.setOnClickListener(mClickListener);
        hide_down.setOnClickListener(mClickListener);
    }

    /***初始化控件*/
    private void findViews() {
        mClickListener = new ClickListener();
        time = findViewById(R.id.tv_time);
        toolbar = findViewById(R.id.tbdetail_pde);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);//主键按钮能否可点击x
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示返回图标
        tvUserName = findViewById(R.id.tv_detail_username);
        recyclerView = findViewById(R.id.rv_detail_review);
        btnImageHead = findViewById(R.id.cImage_detail_head);
        tvUserName = findViewById(R.id.tv_detail_username);
        btnAttention = findViewById(R.id.btn_detail_attention);
        isAttentioned = R.drawable.befans;
        imageCollect = findViewById(R.id.image_detail_collect);
        imageComment = findViewById(R.id.image_detail_comment);
        imageLike = findViewById(R.id.image_detail_like);
        rl_enroll = findViewById(R.id.rl_enroll);
        rl_comment = findViewById(R.id.rl_comment);
        hide_down = findViewById(R.id.hide_down);
        comment_send = findViewById(R.id.comment_send);
        comment_content = findViewById(R.id.comment_content);
    }

    /**
     * 定义一个内部类来处里Activity中的点击事件
     */
    public class ClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                /**点击用户名和头像可以跳转至个人中心，注意数据的获取*/
                case R.id.cImage_detail_head:
                    Intent intent = new Intent(DetailActivity.this, PersonalCenterActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_detail_username:
                    Intent intent1 = new Intent(DetailActivity.this, PersonalCenterActivity.class);
                    startActivity(intent1);
                    break;
                /**点击之后应在数据库里增加一条数据，并标识，
                 * 在绘制Activity时也用先判断该用户是否被关注了
                 * ，来实现指定好图标
                 */
                case R.id.btn_detail_attention:
                    if (isAttentioned == R.drawable.befans) {
                        btnAttention.setImageResource(R.drawable.have_attention);
                        isAttentioned = R.drawable.have_attention;
                    } else if (isAttentioned == R.drawable.have_attention) {
                        btnAttention.setImageResource(R.drawable.befans);
                        isAttentioned = R.drawable.befans;
                    }
                    break;
                case R.id.image_detail_collect:
                    /**实现.....your code*/
                    break;
                /**评论*/
                case R.id.image_detail_comment:
                    // 弹出输入法
                    InputMethodManager imm = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                    // 显示评论框
                    rl_enroll.setVisibility(View.GONE);
                    rl_comment.setVisibility(View.VISIBLE);
                    break;
                case R.id.image_detail_like:

                    break;
                case R.id.comment_send:
                    sendComment();
                    break;
                case R.id.hide_down:
                    // 隐藏评论框
                    rl_enroll.setVisibility(View.VISIBLE);
                    rl_comment.setVisibility(View.GONE);
                    // 隐藏输入法，然后暂存当前输入框的内容，方便下次使用
                    InputMethodManager im = (InputMethodManager) getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    im.hideSoftInputFromWindow(comment_content.getWindowToken(), 0);
                    break;
                /**底部收藏按钮事件*/


            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tbdetail_pde:
                finish();
                break;
        }
        finish();
        return super.onOptionsItemSelected(item);
    }

    public void sendComment() {
        if (comment_content.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "评论不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            // 生成评论数据
            Comment comment = new Comment();
            comment.setComment_text(comment_content.getText().toString());
            adapter.addComment(comment);
            // 发送完，清空输入框
            comment_content.setText("");

            Toast.makeText(getApplicationContext(), "评论成功！", Toast.LENGTH_SHORT).show();
        }
    }
}
