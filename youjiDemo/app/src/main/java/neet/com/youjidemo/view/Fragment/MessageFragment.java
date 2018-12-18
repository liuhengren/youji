package neet.com.youjidemo.view.Fragment;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.AtAdapter;
import neet.com.youjidemo.adapter.CommentAdapter;
import neet.com.youjidemo.adapter.GoodAdapter;
import neet.com.youjidemo.adapter.Propelling_Adapter;
import neet.com.youjidemo.bean.AtMessage;
import neet.com.youjidemo.bean.CommentMessae;
import neet.com.youjidemo.bean.GoodMessage;
import neet.com.youjidemo.bean.Message;
import neet.com.youjidemo.view.DetailActivity;

public class MessageFragment extends Fragment {
    private LinearLayout ll_at;
    private LinearLayout ll_comment;
    private LinearLayout ll_good;
    private boolean is_at;
    private boolean is_comment;
    private boolean is_good;
    private TextView movement;
    private TextView message;
    SwipeRefreshLayout swipeRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_message,container,false);
        is_at = false;is_comment = false; is_good = false;
        movement = view.findViewById(R.id.tv_movement);
        message = view.findViewById(R.id.tv_message);

        swipeRefreshLayout=view.findViewById(R.id.srl_downrefresh);
        setDownRefresh();
        final TabHost tabHost = view.findViewById(android.R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("First").setIndicator("推送").setContent(R.id.tab1);
        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("Second").setIndicator("私信").setContent(R.id.tab2);
        tabHost.addTab(tabSpec1);
        tabHost.addTab(tabSpec2);
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                if(tabId.equals("First")){
                    movement.setAutoLinkMask(Linkify.ALL);
                }
                else{
                    message.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                }
            }
        });
        //推送的ListView
        List<Message> messages = new ArrayList<>();
        Message message1 = new Message(R.drawable.module_message_at, "任时光匆匆流去我只在乎你", "世界这么大你想去看看。", "11/27 8:00");
        Message message2 = new Message(R.drawable.module_message_at, "任时光匆匆流去我只在乎你", "世界这么大你想去看看。", "11/27 8:00");
        Message message3 = new Message(R.drawable.module_message_at, "任时光匆匆流去我只在乎你", "世界这么大你想去看看。", "11/27 8:00");
        Message message4 = new Message(R.drawable.module_message_at, "任时光匆匆流去我只在乎你", "世界这么大你想去看看。", "11/27 8:00");
        messages.add(message1);
        messages.add(message2);
        messages.add(message3);
        messages.add(message4);
        ListView listView = view.findViewById(R.id.lv_propelling);
        Propelling_Adapter adapter = new Propelling_Adapter(this, R.layout.propelling_listview_item, messages);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(),DetailActivity.class);
                startActivity(intent);
            }
        });

        listView.setAdapter(adapter);
        //at人的ListView
        List<AtMessage> atOnes = new ArrayList<>();
        AtMessage atMessage1 = new AtMessage(R.drawable.module_message_at, "任时光匆匆流去我只在乎你", "11-13 11:00", "我嘞个去", "你这该死的温柔，让我心在痛泪在留。");
        AtMessage atMessage2 = new AtMessage(R.drawable.module_message_at, "任时光匆匆流去我只在乎你", "11-13 11:00", "我嘞个去", "你这该死的温柔，让我心在痛泪在留。");
        AtMessage atMessage3 = new AtMessage(R.drawable.module_message_at, "任时光匆匆流去我只在乎你", "11-13 11:00", "我嘞个去", "你这该死的温柔，让我心在痛泪在留。");
        AtMessage atMessage4 = new AtMessage(R.drawable.module_message_at, "任时光匆匆流去我只在乎你", "11-13 11:00", "我嘞个去", "你这该死的温柔，让我心在痛泪在留。");
        atOnes.add(atMessage1);
        atOnes.add(atMessage2);
        atOnes.add(atMessage3);
        atOnes.add(atMessage4);
        final ListView atListview = view.findViewById(R.id.lv_at);
        AtAdapter at_adapter = new AtAdapter(this, R.layout.at_listview_adpter, atOnes);
        atListview.setAdapter(at_adapter);
        //评论的ListView
        List<CommentMessae> commentOnes = new ArrayList<>();
        CommentMessae commentMessage1 = new CommentMessae("回复@iBeyond丶:我们之间的距离好像忽远又忽近"," @iBeyond丶:有一种感觉我想说明。",R.drawable.collection_picture,"你明明不在我身边确感觉很亲，有一种感觉我要告诉你");
        CommentMessae commentMessage2 = new CommentMessae("回复@iBeyond丶:我们之间的距离好像忽远又忽近"," @iBeyond丶:有一种感觉我想说明。",R.drawable.collection_picture,"你明明不在我身边确感觉很亲，有一种感觉我要告诉你");
        CommentMessae commentMessage3 = new CommentMessae("回复@iBeyond丶:我们之间的距离好像忽远又忽近"," @iBeyond丶:有一种感觉我想说明。",R.drawable.collection_picture,"你明明不在我身边确感觉很亲，有一种感觉我要告诉你");
        CommentMessae commentMessage4 = new CommentMessae("回复@iBeyond丶:我们之间的距离好像忽远又忽近"," @iBeyond丶:有一种感觉我想说明。",R.drawable.collection_picture,"你明明不在我身边确感觉很亲，有一种感觉我要告诉你");
        commentOnes.add(commentMessage1);commentOnes.add(commentMessage2);commentOnes.add(commentMessage3);commentOnes.add(commentMessage4);
        final ListView commentListview = view.findViewById(R.id.lv_comment);
        CommentAdapter commentAdapter = new CommentAdapter(this,R.layout.comment_listview_adpter,commentOnes);
        commentListview.setAdapter(commentAdapter);
        //收藏的listview
        List<GoodMessage> goodOnes = new ArrayList<>();
        GoodMessage goodMessage1 = new GoodMessage(R.drawable.module_message_at, "Arthur", R.drawable.collection_picture, "我们之间的距离好像忽远又忽进。");
        GoodMessage goodMessage2 = new GoodMessage(R.drawable.module_message_at, "Arthur", R.drawable.collection_picture, "我们之间的距离好像忽远又忽进。");
        GoodMessage goodMessage3 = new GoodMessage(R.drawable.module_message_at, "Arthur", R.drawable.collection_picture, "我们之间的距离好像忽远又忽进。");
        GoodMessage goodMessage4 = new GoodMessage(R.drawable.module_message_at, "Arthur", R.drawable.collection_picture, "我们之间的距离好像忽远又忽进。");
        goodOnes.add(goodMessage1);
        goodOnes.add(goodMessage2);
        goodOnes.add(goodMessage3);
        goodOnes.add(goodMessage4);
        final ListView goodListview = view.findViewById(R.id.lv_good);
        GoodAdapter good_adapter = new GoodAdapter(this, R.layout.good_listview_item, goodOnes);
        goodListview.setAdapter(good_adapter);
        setListViewHeightBasedOnChildren(atListview);
        setListViewHeightBasedOnChildren(goodListview);
        setListViewHeightBasedOnChildren(commentListview);
        ll_at = view.findViewById(R.id.ll_at);
        ll_comment = view.findViewById(R.id.ll_comment);
        ll_good = view.findViewById(R.id.ll_good);
        ll_at.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(is_at==false){
                    atListview.setVisibility(View.VISIBLE);
                    is_at = true;
                }
                else{
                    atListview.setVisibility(View.GONE);
                    is_at = false;
                }
            }
        });
        ll_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(is_comment==false){
                    commentListview.setVisibility(View.VISIBLE);
                    is_comment = true;
                }
                else{
                    commentListview.setVisibility(View.GONE);
                    is_comment = false;
                }
            }
        });
        ll_good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(is_good == false){
                    goodListview.setVisibility(View.VISIBLE);
                    is_good = true;
                }
                else {
                    goodListview.setVisibility(View.GONE);
                    is_good = false;
                }
            }
        });
        return view;
    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

  /*
   * 设置推荐的下拉刷新
* */
    private  void setDownRefresh(){
        swipeRefreshLayout.setProgressViewOffset(true, 50, 200);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //推送刷新 进行请求数据

            }
        });
    }


}
