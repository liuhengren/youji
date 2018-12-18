package neet.com.youjidemo.biz;

import java.sql.Date;

import neet.com.youjidemo.bean.UserDetail;

public interface IUserDetailEdit {
    UserDetail getUserDetail(String userphone);
    void updateUsername(int user_id,String username);
    void updateUsertouxiang();
    void updateUserSex(int user_id,String userSex);
    void updateUserIntroduction(int user_id,String userIntroduction);
    void updateUserBirthday(int user_id,Date userBirthday);
    void updateUserHometown(int user_id,String userHometown);
}
