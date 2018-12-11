package neet.com.youjidemo.bean;

import android.app.Application;

public class UserDateApplication extends Application {
    private boolean isLogin=false;
    private User user;
    private UserDetail userDetail;

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if(isLogin) {
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    //从网络获取用户信息,解析JSON串
                    user=new User();
                    userDetail=new UserDetail();
                }
            }.start();
        }
    }
}
