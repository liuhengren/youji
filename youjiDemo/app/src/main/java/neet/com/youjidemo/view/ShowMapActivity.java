package neet.com.youjidemo.view;
/**
 * 基础地图——高德地图中介绍如何使用mapview显示地图
 * @author jiatao
 * @date 2015-4-28
 * @version 1.0
 */

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import android.app.Activity;
import android.os.Bundle;

import neet.com.youjidemo.R;

public class ShowMapActivity extends Activity {

    private MapView mapView;
    private AMap aMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showmap_layout);
        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);//必须要写
        init();
    }

    /**
     * 初始化AMap对象
     */
    private void init(){
        if(aMap == null){
            aMap = mapView.getMap();
        }
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        mapView.onDestroy();
    }

}