package neet.com.youjidemo.biz;

import android.os.Handler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.bean.Collection;
import neet.com.youjidemo.command.GetJsonStr;
import neet.com.youjidemo.command.JsonObjiecrToObject;

public class Collectionbiz implements ICollection{
    //CollectionServletUrl地址
    final private String CollectionUrl="";
    private List<Collection> collectionList=new ArrayList<>();
    @Override
    public List<Collection> getCollectionByUserId(final int user_id) {
        String JsonStr = GetJsonStr.getJsonStrbyUrl(CollectionUrl);

        return null;
    }

    @Override
    public void addCollection(int user_id, int dynamic_id) {

    }

    @Override
    public void deleteCollection(int collection_id) {

    }
    private void addList(String str){
        try {
            collectionList=new ArrayList<>();
            JSONArray jsonArray = (new JSONObject(str).getJSONArray("list"));
            for(int i=0;i<jsonArray.length();i++){
                JSONObject object=jsonArray.getJSONObject(i);
                Collection collection=JsonObjiecrToObject.JsonToCollection(object);
                if(collection!=null){
                    collectionList.add(collection);
                }
            }
        } catch (JSONException e) {
            collectionList=null;
            e.printStackTrace();
        }
    }
}
