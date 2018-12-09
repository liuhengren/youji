package neet.com.youjidemo.biz;

import neet.com.youjidemo.bean.UserDetail;

public interface IUserDetailEdit {
    UserDetail getUserDetail(String userphone);
    void updataUsername(String username);

}
