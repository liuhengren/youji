package neet.com.youjidemo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;


import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ScrollDirectionListener;

import java.util.ArrayList;
import java.util.List;

public class FoodActivity extends AppCompatActivity {
    private List list;
    private ListView listView;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_activity);
        findViews();
        list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);
        list.add(2);




        ContentAdapter contentAdapter = new ContentAdapter(this, list, R.layout.square_list_item);
        listView.setAdapter(contentAdapter);
        setFloatingActionButton();

        Toolbar toolbar=findViewById(R.id.tb_back);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void findViews() {
        listView = findViewById(R.id.lv_dynamic);
   floatingActionButton = findViewById(R.id.fab_top);
        }

        /*
        * 设置FloatingActionButton 让ListView回到顶部
        * */
    private void  setFloatingActionButton(){
        floatingActionButton.hide();
        floatingActionButton.attachToListView(listView, new ScrollDirectionListener() {
            @Override
            public void onScrollDown() {
                floatingActionButton.hide();
            }

            @Override
            public void onScrollUp() {
                floatingActionButton.show();
            }
        }, new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {


            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (visibleItemCount > 3) {
                    floatingActionButton.show();
                } else {
                    floatingActionButton.hide();
                }
            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listView.setSelection(0);

            }
        });
    }
}