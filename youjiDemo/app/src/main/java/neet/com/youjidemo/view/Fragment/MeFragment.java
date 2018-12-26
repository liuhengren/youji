package neet.com.youjidemo.view.Fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import de.hdodenhof.circleimageview.CircleImageView;
import neet.com.youjidemo.R;
import neet.com.youjidemo.bean.Url;
import neet.com.youjidemo.bean.User;
import neet.com.youjidemo.bean.UserDateApplication;
import neet.com.youjidemo.biz.UserDetailbiz;
import neet.com.youjidemo.view.LoginActivity;
import neet.com.youjidemo.view.MyLoveActivity;
import neet.com.youjidemo.view.PersonalCenterActivity;
import neet.com.youjidemo.view.PersonalDataEditorActivity;


public class MeFragment extends Fragment {
    private View view;
    private ImageView headsculpture;
    private TextView login;
    private ImageButton collection;
    private Button btnPersonalCenter,btnLove,btnFootPrint;
    private UserDateApplication userDateApplication;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_me, container, false);
        }
        if(getActivity().getIntent().getStringExtra("touxiang")!=null){
            final UserDetailbiz userDetailbiz = new UserDetailbiz();
            new AsyncTask(){

                @Override
                protected Object doInBackground(Object[] objects) {
                    userDateApplication.setUser(userDetailbiz.getUserById(userDateApplication.getUser().getUser_id()));
                    return null;
                }

                @Override
                protected void onPostExecute(Object o) {
                    User user=userDateApplication.getUser();
                    RequestOptions options=RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE);
                    options.circleCrop();
                    Glide.with(MeFragment.this.getContext()).applyDefaultRequestOptions(options).load(Url.MIMAGEURL+user.getUser_touxiang_url()).into(headsculpture);
                    super.onPostExecute(o);
                }
            }.execute();

        }
        findViews();
        userDateApplication=(UserDateApplication)getActivity().getApplication();
        if(userDateApplication.isLogin()){
            User user=userDateApplication.getUser();
            Log.e("imt",Url.MIMAGEURL+user.getUser_touxiang_url());
            RequestOptions options=RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE);
            options.circleCrop();
            Glide.with(MeFragment.this.getContext()).applyDefaultRequestOptions(options).load(Url.MIMAGEURL+user.getUser_touxiang_url()).into(headsculpture);
            login.setText(user.getUser_name());
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userDateApplication.isLogin()){

                }else{
                    headsculpture.setImageResource(R.drawable.module_message_at);
                    toLoginActivity();
                }
            }
        });
        headsculpture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userDateApplication.isLogin()){
                    toPersonDataEditorActivity();
                }else{
                    toLoginActivity();
                }

            }
        });

        btnPersonalCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userDateApplication.isLogin()){
                    Intent intent = new Intent(getContext(),PersonalCenterActivity.class);
                    startActivity(intent);
                }else{
                    toLoginActivity();
                }
            }
        });

        btnLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userDateApplication.isLogin()){
                    Intent intent = new Intent(getContext(),MyLoveActivity.class);
                    startActivity(intent);
                }else{
                    toLoginActivity();
                }

            }
        });

        btnFootPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"功能开发中，敬请期待...",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


    /**获取布局中的控件*/
    private void findViews(){
        headsculpture = view.findViewById(R.id.iv_head_sculpture);
        login = view.findViewById(R.id.tv_login);
        collection = view.findViewById(R.id.ib_collection);
        btnPersonalCenter = view.findViewById(R.id.btn_personal_center);
        btnFootPrint = view.findViewById(R.id.btn_footprint);
        btnLove = view.findViewById(R.id.btn_love);
    }
    public void toLoginActivity(){
        Intent intent = new Intent(getContext(),LoginActivity.class);
        startActivity(intent);
    }
    public void toPersonDataEditorActivity(){
        Intent intent = new Intent(getContext(), PersonalDataEditorActivity.class);
        startActivity(intent);
    }
}
