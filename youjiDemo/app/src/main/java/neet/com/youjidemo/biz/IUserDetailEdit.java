package neet.com.youjidemo.biz;

import neet.com.youjidemo.bean.UserDetail;

public interface IUserDetailEdit {
    UserDetail getUserDetail(String userphone);
    void updateUsername(String username);
    void updateUsertouxiang();
    void updateUserSex(String userSex);
    void updateUserIntroduction(String userIntroduction);
    void updateUserBirthday(String userBirthday);
    void updateUserHometown(String userHometown);
}
