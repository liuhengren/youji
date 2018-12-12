package neet.com.youjidemo.view;

import android.widget.Button;

public interface ILogUpView {
    String getUserPhone();
    String getRequestCode();
    String getUserPassword();
    void showFailedError();
    void showLoading();
    void clearUser();
    void toLoginActivity();
    void toForgetPasswordActivity();
    void toMainActivity();
    void showTimedown();
    void fishTimedown();
}
