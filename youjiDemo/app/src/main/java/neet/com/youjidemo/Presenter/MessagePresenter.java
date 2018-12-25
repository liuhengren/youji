package neet.com.youjidemo.Presenter;

import java.util.List;

import neet.com.youjidemo.bean.Comment;
import neet.com.youjidemo.bean.Dynamic;
import neet.com.youjidemo.bean.Follow;
import neet.com.youjidemo.bean.LikeUp;
import neet.com.youjidemo.bean.User;
import neet.com.youjidemo.biz.CommentBiz;
import neet.com.youjidemo.biz.Dynamicbiz;
import neet.com.youjidemo.biz.FollowBiz;
import neet.com.youjidemo.biz.IComment;
import neet.com.youjidemo.biz.IDynamic;
import neet.com.youjidemo.biz.IFollow;
import neet.com.youjidemo.biz.ILikeUp;
import neet.com.youjidemo.biz.IUserDetailEdit;
import neet.com.youjidemo.biz.LikeUpbiz;
import neet.com.youjidemo.biz.UserDetailbiz;
import neet.com.youjidemo.view.IView.IMessageView;

public class MessagePresenter {
    private IMessageView messageView;
    private IDynamic dynamicBiz;
    private IUserDetailEdit userBiz;
    private ILikeUp likeBiz;
    private IComment commentBiz;
    private IFollow follow;
    public MessagePresenter(IMessageView messageView) {
        this.messageView = messageView;
        dynamicBiz=new Dynamicbiz();
        userBiz=new UserDetailbiz();
        likeBiz=new LikeUpbiz();
        commentBiz=new CommentBiz();
        follow=new FollowBiz();
    }
    public void getFollowDynamic(int user_id){
        List<Follow> followList = follow.getFollowByUserId(user_id);
        for(int i=0;i<followList.size();i++){
            List<Dynamic> dynamicByUserId = dynamicBiz.getDynamicByUserId(followList.get(i).getFollow_user_id());
            User userById = userBiz.getUserById(followList.get(i).getFollow_user_id());
        }
    }
    public void getCommentMe(int user_id){
        List<Dynamic> dynamicList = dynamicBiz.getDynamicByUserId(user_id);
        for(int i=0;i<dynamicList.size();i++){
            List<Comment> commentByDynamicId = commentBiz.getCommentByDynamicId(dynamicList.get(i).getDynamic_id());
            for(int j=0;j<commentByDynamicId.size();j++){
                User userById = userBiz.getUserById(commentByDynamicId.get(i).getComment_user_id());

            }
        }

    }
    public void getLikeMe(int user_id){
        List<Dynamic> dynamicList = dynamicBiz.getDynamicByUserId(user_id);
        for(int i=0;i<dynamicList.size();i++){
            List<LikeUp> listByDynamicId = likeBiz.getListByDynamicId(dynamicList.get(i).getDynamic_id());
            for(int j=0;j<listByDynamicId.size();j++){
                User userById = userBiz.getUserById(listByDynamicId.get(i).getLikeUp_user_id());

            }
        }
    }
}
