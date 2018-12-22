package neet.com.youjidemo.biz;

import java.util.List;

import neet.com.youjidemo.bean.Comment;

public interface IComment {
    List<Comment> getCommentByDynamicId(int dynamic_id);
    boolean addComment(Comment comment);
    boolean likeComment(int comment_id);
}
