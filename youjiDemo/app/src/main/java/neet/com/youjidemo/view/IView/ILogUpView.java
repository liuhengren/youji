package neet.com.youjidemo.view.IView;

import android.widget.Button;

import neet.com.youjidemo.bean.User;

public interface ILogUpView {
    String getUserPhone();
    String getRequestCode();
    String getUserPassword();
    void showFailedError(String msg);
    void showLoading();
    void hideLoading();
    void clearUser();
    void toLoginActivity();
    void toForgetPasswordActivity();
    void toMainActivity();
    void showTimedown();
    void fishTimedown();
    void setUserApp(User user);
}
