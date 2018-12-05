package neet.com.youjidemo.Presenter;

import android.os.Handler;

import neet.com.youjidemo.biz.IUserLog;
import neet.com.youjidemo.biz.Userbiz;
import neet.com.youjidemo.view.ILoginView;

public class UserLoginPresenter {
    private IUserLog userLog;
    private ILoginView loginView;
    private Handler mHandler =new Handler();
    public UserLoginPresenter(ILoginView loginView){
        this.loginView=loginView;
        this.userLog=new Userbiz();
    }
    public void login(){
        loginView.showLoading();
        userLog.login(loginView.getUserPhone(),loginView.getUserPassword());

    }
}
