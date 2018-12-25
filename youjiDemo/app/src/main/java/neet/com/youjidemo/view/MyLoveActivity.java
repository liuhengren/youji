package neet.com.youjidemo.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import neet.com.youjidemo.MainActivity;
import neet.com.youjidemo.Presenter.DynamicOptionPresenter;
import neet.com.youjidemo.R;
import neet.com.youjidemo.bean.LoveMessage;
import neet.com.youjidemo.bean.ShowDynamicInAll;
import neet.com.youjidemo.bean.UserDateApplication;
import neet.com.youjidemo.view.IView.IDynamicOption;


/**
 * @author ieasy360_1
 */
public class MyLoveActivity extends AppCompatActivity implements OnClickListener,IDynamicOption {
    private boolean judge = true;
    private ListView listview;
    private List<Integer> array = new ArrayList<Integer>(); //显示的内容
    private List<Integer> selectid = new ArrayList<Integer>();
    private boolean isMulChoice = false; //是否多选
    private Adapter adapter;
    private RelativeLayout layout;
    private Button cancle, delete;
    private TextView editor;
    private TextView txtcount;
    private List<ShowDynamicInAll> list=new ArrayList<>();
    private DynamicOptionPresenter dynamicOptionPresenter;
    private UserDateApplication userDateApplication;
    private int user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);

        userDateApplication=(UserDateApplication)getApplication();
        dynamicOptionPresenter=new DynamicOptionPresenter(this);
        user_id=userDateApplication.getUser().getUser_id();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);//主键按钮能否可点击
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示返回图标
        listview = (ListView) findViewById(R.id.list);
        layout = (RelativeLayout) findViewById(R.id.relative);
        txtcount = (TextView) findViewById(R.id.txtcount);
        cancle = (Button) findViewById(R.id.cancle);
        delete = (Button) findViewById(R.id.delete);
        editor = findViewById(R.id.tv_editor);
        cancle.setOnClickListener(this);
        delete.setOnClickListener(this);
        dynamicOptionPresenter.getList("collection",user_id);

    }
    public void init(){
        for(int i=0;i<list.size();i++){
            array.add(i);
        }
    }


    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.cancle:
                isMulChoice = false;
                selectid.clear();
                adapter = new Adapter(this, txtcount, list);
                listview.setAdapter(adapter);
                layout.setVisibility(View.INVISIBLE);
                editor.setText("编辑");
                judge = true;
                break;
            case R.id.delete:
                isMulChoice = false;
                for (int i = 0; i < selectid.size(); i++) {
                    for (int j = 0; j < array.size(); j++) {
                        if (selectid.get(i)==array.get(j)) {
                            array.remove(j);
                            cancelCollection(array.get(j));
                        }
                    }
                }
                selectid.clear();
                adapter = new Adapter(this, txtcount, list);
                listview.setAdapter(adapter);
                layout.setVisibility(View.INVISIBLE);
                editor.setText("编辑");
                judge = true;
                break;
            default:
                break;
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        // TODO Auto-generated method stub
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("操作");
    }

    @Override
    public void setListByTag(List<ShowDynamicInAll> list) {
        this.list=new ArrayList<>();
        this.list=list;
        //adapter.updateList(list);
        init();
    }

    @Override
    public int getmUserId() {
        return user_id;
    }

    @Override
    public void addCollection(int dynamic_id) {

    }

    @Override
    public void addFollow(int follow_user_id) {

    }

    @Override
    public void likeTheDynamic(int dynamic_id) {

    }

    @Override
    public void cancelLike(int dynamic_id) {

    }

    @Override
    public void cancelFollow(int follow_user_id) {

    }

    @Override
    public void cancelCollection(int dynamic_id) {
        dynamicOptionPresenter.cancelCollection(getmUserId(),dynamic_id);
    }

    @Override
    public void change() {
        adapter = new Adapter(this, txtcount, list);
        listview.setAdapter(adapter);
        //adapter.updateList(this.list);
    }

    /**
     * @author ieasy360_1
     * 自定义Adapter
     */
    class Adapter extends BaseAdapter {
        private List<ShowDynamicInAll> messages;
        private Context context;
        private LayoutInflater inflater = null;
        private HashMap<Integer, View> mView;
        public HashMap<Integer, Integer> visiblecheck;//用来记录是否显示checkBox
        public HashMap<Integer, Boolean> ischeck;
        private TextView txtcount;
        public void updateList(List<ShowDynamicInAll> list){
            this.messages=new ArrayList<>();
            this.messages=list;
        }
        public Adapter(Context context, TextView txtcount, List<ShowDynamicInAll> messages) {
            this.messages = messages;
            this.context = context;
            this.txtcount = txtcount;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = new HashMap<Integer, View>();
            visiblecheck = new HashMap<Integer, Integer>();
            ischeck = new HashMap<Integer, Boolean>();
            if (isMulChoice) {
                for (int i = 0; i < array.size(); i++) {
                    ischeck.put(i, false);
                    visiblecheck.put(i, CheckBox.VISIBLE);
                }
            } else {
                for (int i = 0; i < array.size(); i++) {
                    ischeck.put(i, false);
                    visiblecheck.put(i, CheckBox.INVISIBLE);
                }
            }
        }

        public int getCount() {
            // TODO Auto-generated method stub
            return array.size();
        }

        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return array.get(position);
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            View view = mView.get(position);
            if (view == null) {

                view = inflater.inflate(R.layout.activity_love_item, null);
//                TextView txt = (TextView)view.findViewById(R.id.txtName);
                ImageView headsculpture = view.findViewById(R.id.iv_sculpture);
                TextView name = view.findViewById(R.id.tv_name);
                TextView time = view.findViewById(R.id.tv_time);
                TextView content = view.findViewById(R.id.tv_content);
                ImageView picture = view.findViewById(R.id.iv_picture);
                final CheckBox ceb = (CheckBox) view.findViewById(R.id.checks);
                Glide.with(MyLoveActivity.this).load(messages.get(position).getUser_touxiang()).into(headsculpture);
                name.setText(messages.get(position).getUsername());
                time.setText(messages.get(position).getTime());
                content.setText(messages.get(position).getDynamic_text());
                Glide.with(MyLoveActivity.this).load(messages.get(position).getDynamicImg_url()).into(picture);
//                txt.setText(array.get(position));


                ceb.setChecked(ischeck.get(position));
                ceb.setVisibility(visiblecheck.get(position));

//                view.setOnLongClickListener(new Onlongclick());
                editor.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (judge) {
                            isMulChoice = true;
                            selectid.clear();
                            layout.setVisibility(View.VISIBLE);
                            for (int i = 0; i < array.size(); i++) {
                                adapter.visiblecheck.put(i, CheckBox.VISIBLE);
                            }
                            adapter = new Adapter(context, txtcount, messages);
                            listview.setAdapter(adapter);
                            txtcount.setText("共选择了" + selectid.size() + "项");
                            editor.setText("取消");
                            judge = false;
                        } else {
                            //下午
                            isMulChoice = false;
                            selectid.clear();
                            adapter = new Adapter(context, txtcount, messages);
                            listview.setAdapter(adapter);
                            layout.setVisibility(View.INVISIBLE);
                            editor.setText("编辑");
                            judge = true;
                        }

                    }
                });

                view.setOnClickListener(new OnClickListener() {

                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        if (isMulChoice) {
                            if (ceb.isChecked()) {
                                ceb.setChecked(false);
                                selectid.remove(array.get(position));
                            } else {
                                ceb.setChecked(true);
                                selectid.add(array.get(position));
                            }
                            txtcount.setText("共选择了" + selectid.size() + "项");
                        } else {
                            Intent intent = new Intent(MyLoveActivity.this, DetailActivity.class);
                            ShowDynamicInAll showDynamicInAll=(ShowDynamicInAll) adapter.getItem(position);
                            intent.putExtra("dynamicDeta",showDynamicInAll);
                            startActivity(intent);
                        }

                    }
                });

                mView.put(position, view);
            }
            return view;
        }

        class Onlongclick implements OnLongClickListener {

            public boolean onLongClick(View v) {
                // TODO Auto-generated method stub

                isMulChoice = true;
                selectid.clear();
                layout.setVisibility(View.VISIBLE);
                for (int i = 0; i < array.size(); i++) {
                    adapter.visiblecheck.put(i, CheckBox.VISIBLE);
                }
                adapter = new Adapter(context, txtcount, messages);
                listview.setAdapter(adapter);
                return true;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.tb_personal_center:
                finish();
                break;
        }
        finish();
        return super.onOptionsItemSelected(item);
    }
}
