package neet.com.youjidemo.Presenter;

import neet.com.youjidemo.biz.IUserLog;
import neet.com.youjidemo.biz.Userbiz;
import neet.com.youjidemo.view.IView.ILoginView;

public class UserLoginPresenter {
    private IUserLog userLog;
    private ILoginView loginView;
    public UserLoginPresenter(ILoginView loginView){
        this.loginView=loginView;
        this.userLog=new Userbiz();
    }
    public void login(){
        loginView.showLoading();
        userLog.login(loginView.getUserPhone(),loginView.getUserPassword());
    }
}
