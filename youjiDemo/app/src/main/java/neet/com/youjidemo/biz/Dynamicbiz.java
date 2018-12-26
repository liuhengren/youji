package neet.com.youjidemo.biz;


import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import neet.com.youjidemo.bean.Dynamic;
import neet.com.youjidemo.bean.Url;
import neet.com.youjidemo.command.GetJsonStr;
import neet.com.youjidemo.command.JsonObjiecrToObject;
import neet.com.youjidemo.command.ObjectToJsonObject;
import neet.com.youjidemo.command.PostJson;

public class Dynamicbiz implements IDynamic{
    private List<Dynamic> dynamicList=new ArrayList<>();
    final private String DynamicUrl=Url.mURL+"DynamicServlet";
    @Override
    public List<Dynamic> getDynamic() {
        String msg="dynamic_allDynamic";
        String jsonStr = GetJsonStr.getJsonStrbyUrl(DynamicUrl+"?message="+msg);
        Log.e("js",jsonStr);
        addListData(jsonStr);
        return dynamicList;
    }

    @Override
    public List<Dynamic> getDynamicOrderHot() {
        //传递参数确认方法
        String msg="";
        String jsonStr = GetJsonStr.getJsonStrbyUrl(DynamicUrl+"?message="+msg);
        addListData(jsonStr);
        return dynamicList;
    }

    @Override
    public List<Dynamic> getDynamicByPartitionId(int partition_id) {
        String msg="dynamic_getDynamicByPartitionId";
        String jsonStr = GetJsonStr.getJsonStrbyUrl(DynamicUrl+"?message="+msg+"&id="+partition_id);
        addListData(jsonStr);
        return dynamicList;
    }

    @Override
    public List<Dynamic> getDynamicByUserId(int user_id) {
        String msg="dynamic_getDynamicByUserId";
        String jsonStr = GetJsonStr.getJsonStrbyUrl(DynamicUrl+"?message="+msg+"&id="+user_id);
        addListData(jsonStr);
        return dynamicList;
    }

    @Override
    public Dynamic getDynamicById(int dynamic_id) {
        String msg="dynamic_getDynamicById";
        Dynamic dynamic;
        String jsonStr = GetJsonStr.getJsonStrbyUrl(DynamicUrl+"?message="+msg+"&id="+dynamic_id);
        try {
            JSONObject object = new JSONObject(jsonStr);
            dynamic = JsonObjiecrToObject.JsonToDynamic(object);
        } catch (JSONException e) {
            e.printStackTrace();
            dynamic=null;
        }
        return dynamic;
    }

    @Override
    public int addDynamic(Dynamic dynamic) {
        String msg="dynamic_addDynamic";
        String url=Url.mURL+"AddDynamicText";
        JSONObject object = ObjectToJsonObject.DynamicToJson(dynamic);

        int b = PostJson.PostDynamicToSever(object, url);
        return b;
    }

    @Override
    public boolean addDynamicImg(int dynamic_id) {
        String msg="";
        String url="";
        return false;
    }

    @Override
    public boolean deleteDynamic(int Dynamic_id) {
        String msg="dynamic_deleteDynamic";
        String url=DynamicUrl+"?message="+msg+"&id="+Dynamic_id;
        boolean b = PostJson.PostByUrl(url);
        return b;
    }
    private void addListData(String str){
        try {
            dynamicList=new ArrayList<>();
            JSONArray jsonArray = (new JSONArray(str));
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
