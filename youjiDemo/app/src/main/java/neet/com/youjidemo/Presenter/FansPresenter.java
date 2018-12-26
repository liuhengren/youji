package neet.com.youjidemo.Presenter;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.bean.Follow;
import neet.com.youjidemo.bean.User;
import neet.com.youjidemo.biz.FollowBiz;
import neet.com.youjidemo.biz.IFollow;
import neet.com.youjidemo.biz.IUserDetailEdit;
import neet.com.youjidemo.biz.UserDetailbiz;
import neet.com.youjidemo.view.IView.IFanView;

public class FansPresenter {
    private IFanView fanView;
    private IFollow followBiz;
    private IUserDetailEdit userDetailBiz;
    private List<User> list=new ArrayList<>();
    public FansPresenter(IFanView fanView) {
        followBiz=new FollowBiz();
        userDetailBiz=new UserDetailbiz();
        this.fanView = fanView;
    }
    public void getList(final int user_id){
        list.clear();
        new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] objects) {
                List<Follow> followByUserId = followBiz.getFollowByUserId(user_id);
                for(int i=0;i<followByUserId.size();i++){
                    User user=userDetailBiz.getUserById(followByUserId.get(i).getFollow_user_id());
                    list.add(user);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                fanView.setList(list);
                fanView.change();
            }
        }.execute();
    }
    public void cancelFollow(final int user_id, final int follow_user_id){
        new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] objects) {
                followBiz.deleteFollow(user_id,follow_user_id);
                return null;
            }
        }.execute();
    }
    public void addFollow(final int user_id, final int follow_user_id){
        new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] objects) {
                followBiz.addFollow(user_id,follow_user_id);
                return null;
            }
        }.execute();
    }
}
