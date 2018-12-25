package neet.com.youjidemo.view;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
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

import neet.com.youjidemo.Presenter.UserLoginPresenter;
import neet.com.youjidemo.R;
import neet.com.youjidemo.bean.User;
import neet.com.youjidemo.customWidget.CustomVideoView;
import neet.com.youjidemo.view.IView.ILoginView;

public class LoginActivity extends AppCompatActivity implements ILoginView {
    private EditText mEtUserPhone,mEtPassword;
    private Button mBtnLogin;
    private TextView mTvLogup,mTvFindPassword;
    private ImageButton mIBCancel,mIbLogByqq,mIBLogbyWxChat;
    private UserLoginPresenter userLoginPresenter;
    private ProgressBar progressBar;
    private CustomVideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        initview();
        initListener();
    }
    private void initview(){
        mEtUserPhone=findViewById(R.id.et_userPhone);
        mEtPassword=findViewById(R.id.et_user_password);
        mBtnLogin=findViewById(R.id.btn_login);
        mTvLogup=findViewById(R.id.tv_logup);
        mTvFindPassword=findViewById(R.id.tv_find_password);
        mIBCancel=findViewById(R.id.btn_cancel);
        mIbLogByqq=findViewById(R.id.btn_login_qq);
        mIBLogbyWxChat=findViewById(R.id.btn_login_wechat);
        userLoginPresenter=new UserLoginPresenter(LoginActivity.this);
        progressBar=findViewById(R.id.spin_kit);
        videoView =  findViewById(R.id.videoview_login);
        videoView.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.wallpaper));

        //播放
        videoView.start();
        //循环播放
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        initview();
    }
