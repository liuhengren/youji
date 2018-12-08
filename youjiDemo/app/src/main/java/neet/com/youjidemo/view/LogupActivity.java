package neet.com.youjidemo.view;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mob.MobSDK;

import cn.smssdk.SMSSDK;
import neet.com.youjidemo.Presenter.UserLogupPresenter;
import neet.com.youjidemo.R;

public class LogupActivity extends AppCompatActivity implements ILogUpView{
    private EditText mEtUserPhone,mEtCode,mUserPassword;
    private Button mBtLogup,mBtgetCode;
    private TextView mTvToLogin,mTvForgetPassword;
    private UserLogupPresenter userLogupPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logup_activity);
        //getActionBar().hide();
        MobSDK.init(this);
        initview();
        initClickListenrt();
    }

    private void initview(){
        mEtUserPhone=findViewById(R.id.et_logup_userPhone);
        mEtCode=findViewById(R.id.et_logup_code);
        mUserPassword=findViewById(R.id.et_logup_password);
        mBtLogup=findViewById(R.id.btn_logup);
        mTvToLogin=findViewById(R.id.tv_login);
        mTvForgetPassword=findViewById(R.id.tv_logup_find_password);
        mBtgetCode=findViewById(R.id.btn_get_code);
        userLogupPresenter=new UserLogupPresenter(LogupActivity.this);
    }
    private void initClickListenrt(){
        mBtgetCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBtgetCode.setEnabled(false);//禁止按钮的可点击性
                userLogupPresenter.sendSmsCode();
            }
        });
        mBtLogup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogupPresenter.logup();
            }
        });
    }
    @Override
    public String getUserPhone() {
        return mEtUserPhone.getText().toString();
    }

    @Override
    public String getRequestCode() {
        return mEtCode.getText().toString();
    }

    @Override
    public String getUserPassword() {
        return mUserPassword.getText().toString();
    }

    @Override
    public void showFailedError() {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void clearUser() {
        mEtCode.setText("");
        mUserPassword.setText("");
    }

    @Override
    public void toLoginActivity() {
        Intent toLoginIntent = new Intent();
        toLoginIntent.setClass(LogupActivity.this,LoginActivity.class);
        startActivity(toLoginIntent);
    }

    @Override
    public void toForgetPasswordActivity() {

    }

    @Override
    public void toMainActivity() {
        Intent intent = new Intent();
        intent.setClass(LogupActivity.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showTimedown() {
        mTimer.start();
    }

    @Override
    public void fishTimedown() {
        mTimer.cancel();
    }

    /**
     * 倒计时
     */
    private CountDownTimer mTimer = new CountDownTimer(60000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            //时间间隔固定回调该方法

            mBtgetCode.setText(millisUntilFinished/1000+"s重新获取");
        }
        @Override
        public void onFinish() {
            //倒计时结束时，回调该方法
            mBtgetCode.setText("重新获取");
            mBtgetCode.setEnabled(true);
        }
    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        SMSSDK.unregisterAllEventHandler();
    }
}
