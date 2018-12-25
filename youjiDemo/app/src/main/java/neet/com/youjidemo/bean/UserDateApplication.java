package neet.com.youjidemo.bean;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import neet.com.youjidemo.biz.UserDetailbiz;

public class UserDateApplication extends Application {
    private boolean isLogin;
    private User user;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int user_id;
    @Override
    public String toString() {
        return "UserDateApplication{" +
                "isLogin=" + isLogin +
                ", user=" + user +
                '}';
    }

    public boolean isLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
        editor.putBoolean("isLogin",isLogin);
        editor.commit();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        editor.putInt("user_id",user.getUser_id());
        editor.commit();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        user_id=sharedPreferences.getInt("user_id",0);
        isLogin=sharedPreferences.getBoolean("isLogin",false);
        this.user=new User();
        if(user_id!=0){
            user.setUser_id(user_id);
            new GetUserTask().execute();
        }else {
            user.setUser_id(0);
        }
    }
    private class GetUserTask extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            UserDetailbiz userDetailbiz = new UserDetailbiz();
            User userById = userDetailbiz.getUserById(user_id);
            user=userById;
            return null;
        }
    }
}
