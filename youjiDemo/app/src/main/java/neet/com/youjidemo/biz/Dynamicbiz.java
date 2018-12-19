package neet.com.youjidemo.biz;


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
    final private String DynamicUrl="http://10.222.185.41:8080/youjiServer/Dynamicservlet";
    @Override
    public List<Dynamic> getDynamic() {
        String msg="dynamic_allDynamic";
        String jsonStr = GetJsonStr.getJsonStrbyUrl(DynamicUrl+"?message="+msg);
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
        String jsonStr = GetJsonStr.getJsonStrbyUrl(DynamicUrl+"?message="+msg+"&partition_id="+partition_id);
        addListData(jsonStr);
        return dynamicList;
    }

    @Override
    public List<Dynamic> getDynamicByUserId(int user_id) {
        String msg="dynamic_getDynamicByUserId";
        String jsonStr = GetJsonStr.getJsonStrbyUrl(DynamicUrl+"?msg="+msg+"&user_id="+user_id);
        addListData(jsonStr);
        return dynamicList;
    }

    @Override
    public Dynamic getDynamicById(int dynamic_id) {
        String msg="dynamic_getDynamicById";
        String jsonStr = GetJsonStr.getJsonStrbyUrl(DynamicUrl+"?msg="+msg+"&id="+dynamic_id);
        addListData(jsonStr);
        return dynamicList.get(0);
    }

    @Override
    public void addDynamic(Dynamic dynamic) {
        String msg="dynamic_addDynamic";
        String url=DynamicUrl+"?msg="+msg;
    }

    @Override
    public void deleteDynamic(int Dynamic_id) {
        String msg="";
        String url=DynamicUrl+"?msg=";
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
