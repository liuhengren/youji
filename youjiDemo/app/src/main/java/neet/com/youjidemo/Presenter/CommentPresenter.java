package neet.com.youjidemo.Presenter;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.bean.Comment;
import neet.com.youjidemo.bean.ShowCommentBean;
import neet.com.youjidemo.bean.User;
import neet.com.youjidemo.biz.Collectionbiz;
import neet.com.youjidemo.biz.CommentBiz;
import neet.com.youjidemo.biz.FollowBiz;
import neet.com.youjidemo.biz.ICollection;
import neet.com.youjidemo.biz.IComment;
import neet.com.youjidemo.biz.IFollow;
import neet.com.youjidemo.biz.IUserDetailEdit;
import neet.com.youjidemo.biz.UserDetailbiz;
import neet.com.youjidemo.view.IView.ICommentOption;

public class CommentPresenter {
    private ICommentOption commentOption;
    private IUserDetailEdit userDetailBiz;
    private IComment commentBiz;
    private IFollow followBiz;
    private ICollection collectionBiz;
    private List<ShowCommentBean> commentBeanList=new ArrayList<>();
    public CommentPresenter(ICommentOption commentOption){
        userDetailBiz=new UserDetailbiz();
        commentBiz=new CommentBiz();
        followBiz=new FollowBiz();
        collectionBiz=new Collectionbiz();
        this.commentOption=commentOption;
    }
    public void getCommentList(final int dynamic_id){
        new AsyncTask(){
            List<Comment> commentByDynamicId;
            @Override
            protected Object doInBackground(Object[] objects) {
                commentByDynamicId = commentBiz.getCommentByDynamicId(dynamic_id);
                for(int i=0;i<commentByDynamicId.size();i++){
                    User user=new User();
                    user=userDetailBiz.getUserById(commentByDynamicId.get(i).getComment_user_id());
                    ShowCommentBean showCommentBean=new ShowCommentBean(
                            user.getUser_id(),
                            user.getUser_name(),
                            user.getUser_touxiang_url(),
                            commentByDynamicId.get(i).getComment_time(),
                            commentByDynamicId.get(i).getComment_text(),
                            commentByDynamicId.get(i).getComment_like_num()
                    );
                    commentBeanList.add(showCommentBean);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                commentOption.setList(commentBeanList);
            }
        }.execute();

    }
    public void addCommnet(final Comment comment){
        final boolean[] b = new boolean[1];
        new AsyncTask(){
            @Override
            protected Object doInBackground(Object[] objects) {
                b[0] = commentBiz.addComment(comment);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                commentOption.ifCommentSussess(b[0]);
            }
        }.execute();
    }
}
