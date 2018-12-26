package neet.com.youjidemo.biz;

import java.sql.Date;

import neet.com.youjidemo.bean.User;
import neet.com.youjidemo.bean.UserDetail;

public interface IUserDetailEdit {
    User getUserById(int user_id);
    User getUserDetail(String userphone);
    boolean updateUsername(int user_id,String username);
    boolean updateUsertouxiang();
    boolean updateUserSex(int user_id,String userSex);
    boolean updateUserIntroduction(int user_id,String userIntroduction);
    boolean updateUserBirthday(int user_id,String userBirthday);
    boolean updateUserHometown(int user_id,String userHometown);
}
