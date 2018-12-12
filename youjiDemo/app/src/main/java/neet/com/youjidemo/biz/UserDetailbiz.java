package neet.com.youjidemo.biz;



import android.os.Handler;

import neet.com.youjidemo.bean.UserDetail;

public class UserDetailbiz implements IUserDetailEdit{
    private Handler handler;
    public  UserDetailbiz( Handler handler){
        this.handler=handler;
    }
    @Override
    public UserDetail getUserDetail(String userphone) {
        return null;
    }

    @Override
    public void updateUsername(String username) {
        handler.sendEmptyMessage(1);
    }

    @Override
    public void updateUsertouxiang() {

    }

    @Override
    public void updateUserSex(String userSex) {

    }

    @Override
    public void updateUserIntroduction(String userIntroduction) {

    }

    @Override
    public void updateUserBirthday(String userBirthday) {

    }

    @Override
    public void updateUserHometown(String userHometown) {

    }

}
