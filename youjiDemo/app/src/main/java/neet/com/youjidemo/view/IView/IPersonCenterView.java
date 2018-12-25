package neet.com.youjidemo.view.IView;

import neet.com.youjidemo.bean.User;

/**
 * 实现从任何头像处跳转他人个人中心的功能时所需数据;
 */
public interface IPersonCenterView {
    int getUserIdInPc();
    void setUserMessage();
    void setmUser(User user);
}
