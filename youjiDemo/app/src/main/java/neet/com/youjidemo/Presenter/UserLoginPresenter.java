package neet.com.youjidemo.Presenter;

import android.os.AsyncTask;
import android.util.Log;

import neet.com.youjidemo.bean.User;
import neet.com.youjidemo.biz.IUserDetailEdit;
import neet.com.youjidemo.biz.IUserLog;
import neet.com.youjidemo.biz.UserDetailbiz;
import neet.com.youjidemo.biz.Userbiz;
import neet.com.youjidemo.view.IView.ILoginView;

public class UserLoginPresenter {
    private IUserLog userLog;
    private ILoginView loginView;
    private IUserDetailEdit userDetaBiz;
    public UserLoginPresenter(ILoginView loginView){
        this.loginView=loginView;
        userDetaBiz=new UserDetailbiz();
        this.userLog=new Userbiz();
    }
    public void login(){
        loginView.showLoading();
        LogInAsyTask logInAsyTask = new LogInAsyTask();
        logInAsyTask.execute();
    }
    private class LogInAsyTask extends AsyncTask{
        private boolean b=false;
        private User user=new User();
        @Override
        protected Object doInBackground(Object[] objects) {
            b=userLog.login(loginView.getUserPhone(),loginView.getUserPassword());
            Log.e("2",b+"");
            if(b){
                user=userDetaBiz.getUserDetail(loginView.getUserPhone());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            if(b){
                loginView.setUserApp(user);
                loginView.toMainActivity();
//                loginView
            }
            else{
                loginView.showFailedError("登录失败");
                loginView.hideLoading();
            }
        }
    }
}
