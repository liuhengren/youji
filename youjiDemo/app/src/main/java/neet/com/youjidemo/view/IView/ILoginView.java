package neet.com.youjidemo.view.IView;

import java.util.ArrayList;

import neet.com.youjidemo.bean.User;

public interface ILoginView {
        String getUserPhone();
        String getUserPassword();
        //展示正在登陆
        void showLoading();
        void hideLoading();
        void toMainActivity();
        void showFailedError(String msg);
        void clearUser();
        //第三方登录
        void toThirdPartyLogin();
        //跳转登录
        void toLogupActivity();
        //找回密码
        void forgetPasswordActivity();
        void setUserApp(User user);
}
