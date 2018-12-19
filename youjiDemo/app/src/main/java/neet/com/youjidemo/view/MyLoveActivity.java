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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import neet.com.youjidemo.R;
import neet.com.youjidemo.bean.LoveMessage;


/**
 * @author ieasy360_1
 */
public class MyLoveActivity extends AppCompatActivity implements OnClickListener {
    private boolean judge = true;
    private ListView listview;
    private List<String> array = new ArrayList<String>(); //显示的内容
    private List<String> selectid = new ArrayList<String>();
    private boolean isMulChoice = false; //是否多选
    private Adapter adapter;
    private RelativeLayout layout;
    private Button cancle, delete;
    private TextView editor;
    private TextView txtcount;
    private List<LoveMessage> messages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        listview = (ListView) findViewById(R.id.list);
        layout = (RelativeLayout) findViewById(R.id.relative);
        txtcount = (TextView) findViewById(R.id.txtcount);
        cancle = (Button) findViewById(R.id.cancle);
        delete = (Button) findViewById(R.id.delete);
        editor = findViewById(R.id.tv_editor);
        cancle.setOnClickListener(this);
        delete.setOnClickListener(this);
        messages = new ArrayList<>();
        LoveMessage loveMessage1 = new LoveMessage(R.drawable.module_message_at, "我嘞个去", "2018/11/12", "任时光匆匆流去我只在乎你，你我之间的感情。", R.drawable.picture);
        LoveMessage loveMessage2 = new LoveMessage(R.drawable.module_message_at, "我嘞个去", "2018/11/12", "任时光匆匆流去我只在乎你，你我之间的感情。", R.drawable.picture);
        LoveMessage loveMessage3 = new LoveMessage(R.drawable.module_message_at, "我嘞个去", "2018/11/12", "任时光匆匆流去我只在乎你，你我之间的感情。", R.drawable.picture);
        LoveMessage loveMessage4 = new LoveMessage(R.drawable.module_message_at, "我嘞个去", "2018/11/12", "任时光匆匆流去我只在乎你，你我之间的感情。", R.drawable.picture);
        messages.add(loveMessage1);
        messages.add(loveMessage2);
        messages.add(loveMessage3);
        messages.add(loveMessage4);
        init();
        adapter = new Adapter(this, txtcount, messages);
        listview.setAdapter(adapter);

    }

    void init() {
        for (int i = 0; i < 4; i++) {
            array.add("" + i);
        }
    }

    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.cancle:
                isMulChoice = false;
                selectid.clear();
                adapter = new Adapter(this, txtcount, messages);
                listview.setAdapter(adapter);
                layout.setVisibility(View.INVISIBLE);
                editor.setText("编辑");
                judge = true;
                break;
            case R.id.delete:
                isMulChoice = false;
                for (int i = 0; i < selectid.size(); i++) {
                    for (int j = 0; j < array.size(); j++) {
                        if (selectid.get(i).equals(array.get(j))) {
                            array.remove(j);
                        }
                    }
                }
                selectid.clear();
                adapter = new Adapter(this, txtcount, messages);
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

    /**
     * @author ieasy360_1
     * 自定义Adapter
     */
    class Adapter extends BaseAdapter {
        private List<LoveMessage> messages;
        private Context context;
        private LayoutInflater inflater = null;
        private HashMap<Integer, View> mView;
        public HashMap<Integer, Integer> visiblecheck;//用来记录是否显示checkBox
        public HashMap<Integer, Boolean> ischeck;
        private TextView txtcount;

        public Adapter(Context context, TextView txtcount, List<LoveMessage> messages) {
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
                final CheckBox ceb = (CheckBox) view.findViewById(R.id.check);
                headsculpture.setImageResource(messages.get(position).getHeadsculpture());
                name.setText(messages.get(position).getName());
                time.setText(messages.get(position).getTime());
                content.setText(messages.get(position).getContent());
                picture.setImageResource(messages.get(position).getPicture());
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
}
