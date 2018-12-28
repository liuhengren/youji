package neet.com.youjidemo.view;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import neet.com.youjidemo.customWidget.CustomVideoView;
import android.media.MediaPlayer;
import android.net.Uri;
import neet.com.youjidemo.MainActivity;
import neet.com.youjidemo.Presenter.UserLoginPresenter;
import neet.com.youjidemo.R;
import neet.com.youjidemo.bean.User;
import neet.com.youjidemo.bean.UserDateApplication;
import neet.com.youjidemo.view.IView.ILoginView;

public class LoginActivity extends AppCompatActivity implements ILoginView {
    private EditText mEtUserPhone,mEtPassword;
    private Button mBtnLogin;
    private TextView mTvLogup,mTvFindPassword;
    private ImageButton mIBCancel,mIbLogByqq,mIBLogbyWxChat;
    private UserLoginPresenter userLoginPresenter;
    private ProgressBar progressBar;
    private UserDateApplication userDateApplication;
    private CustomVideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initview();
        initListener();
    }
    private void initview() {
        mEtUserPhone = findViewById(R.id.et_userPhone);
        mEtPassword = findViewById(R.id.et_user_password);
        mBtnLogin = findViewById(R.id.btn_login);
        mTvLogup = findViewById(R.id.tv_logup);
        mTvFindPassword = findViewById(R.id.tv_find_password);
        mIBCancel = findViewById(R.id.btn_cancel);
        mIbLogByqq = findViewById(R.id.btn_login_qq);
        mIBLogbyWxChat = findViewById(R.id.btn_login_wechat);
        userLoginPresenter = new UserLoginPresenter(LoginActivity.this);
        progressBar = findViewById(R.id.spin_kit);
        userDateApplication = (UserDateApplication) getApplication();
        videoView = findViewById(R.id.videoview_login);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.wallpaper));
        videoView.start();
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }

        });
    }
    private void initListener(){
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLoginPresenter.login();
            }
        });
        mTvLogup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLogupActivity();
            }
        });
        mTvFindPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forgetPasswordActivity();
            }
        });
        mIBCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void toMainActivity() {
        Intent mainIntent = new Intent();
        mainIntent.setClass(LoginActivity.this,MainActivity.class);
        mainIntent.putExtra("res",200);
        startActivity(mainIntent);
        finish();
    }

    @Override
    public void showFailedError(String msg) {
        Toast.makeText(LoginActivity.this,msg,Toast.LENGTH_SHORT).show();
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

    @Override
    public void setUserApp(User user) {
        userDateApplication.setUser(user);
        userDateApplication.setLogin(true);
    }

    /**
     * 点击空白处使EditText失去焦点
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_DOWN){
            View view =getCurrentFocus();
            if(isHideInput(view,ev)){
                HideSoftInput(view.getWindowToken());
                view.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    public boolean isHideInput(View v, MotionEvent ev){
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initview();
    }
}
