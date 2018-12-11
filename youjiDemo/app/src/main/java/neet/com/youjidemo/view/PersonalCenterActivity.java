package neet.com.youjidemo.view;

import android.content.Intent;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.MainActivity;
import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.PersonalContentAdapter;

public class PersonalCenterActivity extends AppCompatActivity {
    private List productionList;
    private List attentionList;
    private List fansList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_center);

        //初始化并添加选项卡
        TabHost tabHost = findViewById(android.R.id.tabhost);
        tabHost.setup();
        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("tab1")
                .setIndicator("作品")
                .setContent(R.id.tab1);
        tabHost.addTab(tabSpec1);

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("tab2")
                .setIndicator("关注")
                .setContent(R.id.tab2);
        tabHost.addTab(tabSpec2);

        TabHost.TabSpec tabSpec3 = tabHost.newTabSpec("tab3")
                .setIndicator("粉丝")
                .setContent(R.id.tab3);
        tabHost.addTab(tabSpec3);

        ListView listView1 = findViewById(R.id.lv_production);

        //初始化数据（测试数据）
        productionList = new ArrayList();
        productionList.add(2);
        productionList.add(2);
        productionList.add(2);
        productionList.add(2);
        productionList.add(2);

        PersonalContentAdapter adapter = new PersonalContentAdapter(this,productionList,R.layout.personal_center_production_item);
        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(PersonalCenterActivity.this, "点击了"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void editInformaion(View view){
        Intent intent = new Intent(PersonalCenterActivity.this,PersonalDataEditorActivity.class);
        startActivity(intent);

    }
}
