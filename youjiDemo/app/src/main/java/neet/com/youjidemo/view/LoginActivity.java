package neet.com.youjidemo.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import neet.com.youjidemo.Presenter.UserLoginPresenter;
import neet.com.youjidemo.R;
import neet.com.youjidemo.bean.User;

public class LoginActivity extends AppCompatActivity implements ILoginView{
    private EditText mEtUserPhone,mEtPassword;
    private Button mBtnLogin;
    private TextView mTvLogup,mTvFindPassword;
    private ImageButton mIBCancel,mIbLogByqq,mIBLogbyWxChat;
    private UserLoginPresenter userLoginPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initview();
    }
    private void initview(){
        mEtUserPhone=findViewById(R.id.et_name);
        mEtPassword=findViewById(R.id.et_user_password);
        mBtnLogin=findViewById(R.id.btn_login);
        mTvLogup=findViewById(R.id.btn_logup);
        mTvFindPassword=findViewById(R.id.tv_find_password);
        mIBCancel=findViewById(R.id.btn_cancel);
        mIbLogByqq=findViewById(R.id.btn_login_qq);
        mIBLogbyWxChat=findViewById(R.id.btn_login_wechat);
        userLoginPresenter=new UserLoginPresenter(LoginActivity.this);
    }

    @Override
    public String getUserPhone() {
        return mEtUserPhone.getText().toString();
    }

    @Override
    public String getUserPassword() {
        return mEtPassword.getText().toString();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void toMainActivity(User user) {
        Intent mainIntent = new Intent();

    }

    @Override
    public void showFailedError() {

    }

    @Override
    public void clearUser() {
        mEtUserPhone.setText("");
        mEtPassword.setText("");
    }

    @Override
    public void toThirdPartyLogin() {

    }

    @Override
    public void toLogupActivity() {
        Intent LogupIntent = new Intent();
        LogupIntent.setClass(LoginActivity.this,LogupActivity.class);
        startActivity(LogupIntent);
    }

    @Override
    public void forgetPasswordActivity() {
        Intent forgetIntent = new Intent();
    }

}
