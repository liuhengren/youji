package neet.com.youjidemo.biz;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import neet.com.youjidemo.bean.Dynamic;
import neet.com.youjidemo.command.GetJsonStr;
import neet.com.youjidemo.command.JsonObjiecrToObject;

public class Dynamicbiz implements IDynamic{
    private List<Dynamic> dynamicList=new ArrayList<>();
    final private String getDynamicUrl="";
    @Override
    public List<Dynamic> getDynamic() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                String jsonStr = GetJsonStr.getJsonStrbyUrl(getDynamicUrl);
                Log.e("测试",jsonStr);
                addListData(jsonStr);
            }
        };
        return dynamicList;
    }

    @Override
    public List<Dynamic> getDynamicOrderHot() {

        return dynamicList;
    }

    @Override
    public List<Dynamic> getDynamicByPartitionId(int partition_id) {

        return dynamicList;
    }

    @Override
    public List<Dynamic> getDynamicByUserId(int user_id) {
        return dynamicList;
    }

    @Override
    public Dynamic getDynamicById(int dynamic_id) {
        return null;
    }

    @Override
    public void addDynamic(Dynamic dynamic) {

    }

    @Override
    public void deleteDynamic(int Dynamic_id) {

    }
    private void addListData(String str){
        try {
            dynamicList=new ArrayList<>();
            JSONArray jsonArray = (new JSONObject(str).getJSONArray("list"));
            for (int i=0;i<jsonArray.length();i++){
                JSONObject object=jsonArray.getJSONObject(i);
                Dynamic dynamic=JsonObjiecrToObject.JsonToDynamic(object);
                if(dynamic!=null){
                    dynamicList.add(dynamic);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            dynamicList=null;
        }
    }
}
