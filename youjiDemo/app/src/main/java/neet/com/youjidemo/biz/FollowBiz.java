package neet.com.youjidemo.biz;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.bean.Follow;
import neet.com.youjidemo.command.GetJsonStr;
import neet.com.youjidemo.command.JsonObjiecrToObject;

public class FollowBiz implements IFollow {
    private final String followUrl="";
    private List<Follow> followList=new ArrayList<>();
    @Override
    public List<Follow> getFollowByUserId(int user_id) {
        followList=new ArrayList<>();
        String str = GetJsonStr.getJsonStrbyUrl(followUrl);//修改
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
    public void addFollow(int user_id, int follow_user_id) {

    }

    @Override
    public void deleteFollow(int user_id, int follow_user_id) {

    }

    @Override
    public boolean ifFollow(int user_id, int follow_user_id) {
        return false;
    }
}
