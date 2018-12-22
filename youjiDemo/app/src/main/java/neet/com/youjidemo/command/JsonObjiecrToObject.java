package neet.com.youjidemo.command;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.Date;

import neet.com.youjidemo.bean.Collection;
import neet.com.youjidemo.bean.Comment;
import neet.com.youjidemo.bean.Dynamic;
import neet.com.youjidemo.bean.Follow;
import neet.com.youjidemo.bean.User;

public class JsonObjiecrToObject {
    public static Dynamic JsonToDynamic(JSONObject jsonObject){
        Dynamic dynamic=new Dynamic();
        try {
            dynamic.setDynamic_id(jsonObject.getInt("id"));
            dynamic.setDynamic_address(jsonObject.getString("address"));
            dynamic.setDynamic_user_id(jsonObject.getInt("user_id"));
            dynamic.setDynamic_text(jsonObject.getString("text"));
            dynamic.setDynamic_img(jsonObject.getString("img"));
            dynamic.setDynamic_collection_num(jsonObject.getInt("collection_num"));
            dynamic.setDynamic_like_num(jsonObject.getInt("like_num"));
            dynamic.setDynamic_comment_num(jsonObject.getInt("comment_num"));
            dynamic.setDynamic_id(jsonObject.getInt("partition_id"));
            dynamic.setDynamic_time((jsonObject.getString("time")));
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e("动态字符串解析","出错");
            dynamic=null;
        }
        return dynamic;
    }
    public static Collection JsonToCollection(JSONObject jsonObject){
        Collection collection=new Collection();
        try {
            collection.setCollection_dynamic_id(jsonObject.getInt("dynamic_id"));
            collection.setCollection_id(jsonObject.getInt("id"));
            collection.setCollection_user_id(jsonObject.getInt("user_id"));
        } catch (JSONException e) {
            e.printStackTrace();
            collection=null;
        }
        return collection;
    }
    public static User JsonToUser(JSONObject jsonObject){
        User user=new User();
        try {
            user.setUser_id(jsonObject.getInt("user_id"));
            user.setUser_phone(jsonObject.getString("user_phone"));
            user.setUser_name(jsonObject.getString("user_name"));
            user.setUser_sex(jsonObject.getString("user_sex"));
            user.setUser_touxiang_url(jsonObject.getString("user_touxiang_url"));
            user.setUser_background_url(jsonObject.getString("user_background_url"));
            user.setUser_birthday(jsonObject.getString("user_birthday"));
            user.setUser_address(jsonObject.getString("user_address"));
            user.setUser_funnum(jsonObject.getInt("user_funnum"));
            user.setUser_collection_num(jsonObject.getInt("user_collection_num"));
            user.setUser_introduction(jsonObject.getString("user_introduction"));
            user.setUser_psssword(" ");
        } catch (JSONException e) {
            e.printStackTrace();
            user=null;
        }
        return user;
    }
    public static Comment JsonToComment(JSONObject jsonObject){
        Comment comment=new Comment();
        try {
            comment.setComment_id(jsonObject.getInt("id"));
            comment.setComment_dynamic_id(jsonObject.getInt("dynamic_id"));
            comment.setComment_text(jsonObject.getString("text"));
            comment.setComment_like_num(jsonObject.getInt("like_num"));
            comment.setComment_user_id(jsonObject.getInt("user_id"));
        } catch (JSONException e) {
            e.printStackTrace();
            comment=null;
        }
        return comment;
    }
    public static Follow JsonToFollow(JSONObject jsonObject){
        Follow follow=new Follow();
        try {
            follow.setFollow_id(jsonObject.getInt("id"));
            follow.setFollow_user_id(jsonObject.getInt("user_id"));
            follow.setFollow_user_id(jsonObject.getInt("follow_user_id"));
        } catch (JSONException e) {
            e.printStackTrace();
            follow=null;
        }
        return follow;
    }

}
