package neet.com.youjidemo.command;

import org.json.JSONException;
import org.json.JSONObject;

import neet.com.youjidemo.bean.Comment;
import neet.com.youjidemo.bean.Dynamic;

public class ObjectToJsonObject {
    public static JSONObject DynamicToJson(Dynamic dynamic){
        JSONObject object = new JSONObject();
        try {
            object.put("user_id",dynamic.getDynamic_user_id());
            object.put("text",dynamic.getDynamic_text());
            //object.put("img",dynamic.getDynamic_img());
            object.put("collection_num",dynamic.getDynamic_collection_num());
            object.put("like_num",dynamic.getDynamic_like_num());
            object.put("comment_num",dynamic.getDynamic_comment_num());
            object.put("address",dynamic.getDynamic_address());
            object.put("partition_id",dynamic.getDynamic_partition_id());
            //object.put("time",dynamic.getDynamic_time());
        } catch (JSONException e) {
            e.printStackTrace();
            object=null;
        }
        return object;
    }
    public static JSONObject CommentToJson(Comment comment){
        JSONObject object = new JSONObject();
        try {
            object.put("text",comment.getComment_text());
            object.put("dynamic_id",comment.getComment_dynamic_id());
            object.put("like_num",comment.getComment_like_num());
            object.put("user_id",comment.getComment_user_id());
        } catch (JSONException e) {
            e.printStackTrace();
            object=null;
        }
        return object;
    }
}
