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
import neet.com.youjidemo.command.PostJson;

public class Collectionbiz implements ICollection{
    //CollectionServletUrl地址
    final private String CollectionUrl="http://10.222.189.117:8080/youjiServer/CollectionServlet";
    private List<Collection> collectionList=new ArrayList<>();
    @Override
    public List<Collection> getCollectionByUserId(final int user_id) {
        String msg="collection_ByUserId";
        String JsonStr = GetJsonStr.getJsonStrbyUrl(CollectionUrl+"?message="+msg+"&user_id="+user_id);
        addList(JsonStr);
        return collectionList;
    }

    @Override
    public boolean addCollection(int user_id, int dynamic_id) {
        String msg="collection_addCollection";
        String url=CollectionUrl+"?message="+msg+"&user_id="+user_id+"&dynamic_id="+dynamic_id;
        boolean b = PostJson.PostByUrl(url);
        return b;
    }

    @Override
    public boolean deleteCollection(int collection_id) {
        String msg="collection_deleteCollection";
        String url=CollectionUrl+"?message="+msg+"&collection_id="+collection_id;
        boolean b = PostJson.PostByUrl(url);
        return b;
    }

    @Override
    public boolean deleteCollection(int user_id, int dynamic_id) {
        String msg="collection_deleteCollection";
        String url=CollectionUrl+"?message="+msg+"&user_id="+user_id+"&dynamic_id="+dynamic_id;
        boolean b = PostJson.PostByUrl(url);
        return b;
    }

    @Override
    public boolean ifCollection(int user_id, int dynamic_id) {
        String msg="http://10.222.189.117:8080/youjiServer/DynamicServlet?message=dynamic_isCollected";
        String url=msg+"&user_id="+user_id+"&dynamic_id="+dynamic_id;
        boolean b = PostJson.PostByUrl(url);
        return b;
    }

    private void addList(String str){
        try {
            collectionList=new ArrayList<>();
            JSONArray jsonArray = (new JSONArray(str));
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
