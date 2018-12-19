package neet.com.youjidemo.biz;



import android.os.Handler;

import java.sql.Date;

import neet.com.youjidemo.bean.UserDetail;

public class UserDetailbiz implements IUserDetailEdit{
    private Handler handler;
    public  UserDetailbiz(){

    }
    @Override
    public UserDetail getUserDetail(String userphone) {
        return null;
    }

    @Override
    public void updateUsername(int user_id, String username) {

    }

    @Override
    public void updateUsertouxiang() {

    }

    @Override
    public void updateUserSex(int user_id, String userSex) {

    }

    @Override
    public void updateUserIntroduction(int user_id, String userIntroduction) {

    }

    @Override
    public void updateUserBirthday(int user_id, Date userBirthday) {

    }

    @Override
    public void updateUserHometown(int user_id, String userHometown) {

    }


}
