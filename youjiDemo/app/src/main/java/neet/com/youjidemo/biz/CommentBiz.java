package neet.com.youjidemo.biz;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.bean.Comment;
import neet.com.youjidemo.bean.Url;
import neet.com.youjidemo.command.GetJsonStr;
import neet.com.youjidemo.command.JsonObjiecrToObject;
import neet.com.youjidemo.command.ObjectToJsonObject;
import neet.com.youjidemo.command.PostJson;

public class CommentBiz implements IComment {
    private final String CommentUrl=Url.mURL+"CommentServlet";
    private List<Comment> commentList=new ArrayList<>();
    @Override
    public List<Comment> getCommentByDynamicId(int dynamic_id) {
        String msg="comment_ByDynamicId";
        commentList=new ArrayList<>();
        String str = GetJsonStr.getJsonStrbyUrl(CommentUrl + "?message="+msg+"&dynamic_id="+dynamic_id);
        try {
            JSONArray jsonArray = (new JSONArray(str));
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
    public boolean addComment(Comment comment) {
        String msg="comment_addComment";
        String url=Url.mURL+"AddCommentServlet";
        JSONObject object = ObjectToJsonObject.CommentToJson(comment);
        boolean b = PostJson.PostToSever(object, url);
        return b;
    }

    @Override
    public boolean likeComment(int comment_id) {
        String msg="comment_likeComment";
        String url=CommentUrl+"?message="+msg+"&comment_id="+comment_id;
        boolean b = PostJson.PostByUrl(url);
        return b;
    }

    @Override
    public List<Comment> getCommentByUserId(int user_id) {
        return null;
    }
}
