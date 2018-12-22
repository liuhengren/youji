package neet.com.youjidemo.Presenter;

import android.os.AsyncTask;

import neet.com.youjidemo.bean.User;
import neet.com.youjidemo.biz.IUserDetailEdit;
import neet.com.youjidemo.biz.UserDetailbiz;
import neet.com.youjidemo.view.IView.IPersonCenterView;

public class GetUserPresenter {
    private IPersonCenterView userView;
    private IUserDetailEdit userBiz;
    private User user;
    private int user_id;
    public GetUserPresenter(IPersonCenterView userView){
        this.userView=userView;
        userBiz=new UserDetailbiz();
    }
    public void getUserByIdInPc(){
        user_id=userView.getUserIdInPc();
        new GetUserAsyTask().execute();
    }
    private class GetUserAsyTask extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            user=userBiz.getUserById(user_id);
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            userView.setmUser(user);
            userView.setUserMessage();
        }
    }
}
