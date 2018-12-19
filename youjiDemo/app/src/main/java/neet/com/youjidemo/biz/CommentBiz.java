package neet.com.youjidemo.biz;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.bean.Comment;
import neet.com.youjidemo.command.GetJsonStr;
import neet.com.youjidemo.command.JsonObjiecrToObject;

public class CommentBiz implements IComment {
    private final String CommentUrl="";
    private List<Comment> commentList=new ArrayList<>();
    @Override
    public List<Comment> getCommentByDynamicId(int dynamic_id) {
        String msg="";
        commentList=new ArrayList<>();
        String str = GetJsonStr.getJsonStrbyUrl(CommentUrl + "?msg=");
        try {
            JSONArray jsonArray = (new JSONObject(str).getJSONArray("list"));
            for(int i=0;i<jsonArray.length();i++){
                JSONObject object=jsonArray.getJSONObject(i);
                Comment comment=JsonObjiecrToObject.JsonToComment(object);
                if(comment!=null){
                    commentList.add(comment);
                }
            }
        } catch (JSONException e) {
            commentList=null;
            e.printStackTrace();
        }
        return commentList;
    }

    @Override
    public void addComment(Comment comment) {

    }

    @Override
    public void likeComment(int comment_id) {

    }
}