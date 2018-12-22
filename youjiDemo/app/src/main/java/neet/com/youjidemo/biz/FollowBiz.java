package neet.com.youjidemo.biz;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.bean.Follow;
import neet.com.youjidemo.command.GetJsonStr;
import neet.com.youjidemo.command.JsonObjiecrToObject;
import neet.com.youjidemo.command.PostJson;

public class FollowBiz implements IFollow {
    private final String followUrl="http://10.222.184.38:8080/youjiServer/FollowServlet";
    private List<Follow> followList=new ArrayList<>();
    @Override
    public List<Follow> getFollowByUserId(int user_id) {
        String msg="follow_ByUserId";
        followList=new ArrayList<>();
        String str = GetJsonStr.getJsonStrbyUrl(followUrl+"?message="+msg+"&user_id="+user_id);//修改
        try {
            JSONArray jsonArray = (new JSONObject(str).getJSONArray("list"));
            for(int i=0;i<jsonArray.length();i++){
                JSONObject object=jsonArray.getJSONObject(i);
                Follow follow=JsonObjiecrToObject.JsonToFollow(object);
                if(follow!=null){
                    followList.add(follow);
                }
            }
        } catch (JSONException e) {
            followList=null;
            e.printStackTrace();
        }
        return followList;
    }

    @Override
    public boolean addFollow(int user_id, int follow_user_id) {
        String msg="follow_addFollow";
        String url=followUrl+"?message="+msg+"&user_id="+user_id+"follow_user_id="+follow_user_id;
        boolean b = PostJson.PostByUrl(url);
        return b;
    }

    @Override
    public boolean deleteFollow(int user_id, int follow_user_id) {
        String msg="follow_deleteFollow";
        String url=followUrl+"?message="+msg+"&user_id="+user_id+"follow_user_id="+follow_user_id;
        boolean b = PostJson.PostByUrl(url);
        return b;
    }

    @Override
    public boolean isFollow(int user_id, int follow_user_id) {
        String msg="follow_isFollow";
        String url=followUrl+"?message="+msg+"&user_id="+user_id+"&follow_user_id="+follow_user_id;
        boolean b = PostJson.PostByUrl(url);
        return b;
    }
}
