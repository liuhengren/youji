package neet.com.youjidemo.view.IView;

import neet.com.youjidemo.bean.User;

public interface IPerDateEditorView {
    String getUserName();
    String getUserIntroduction();
    String getUserBirthday();
    String getUserSex();
    String getUserHometown();
    void setUserName(String username);
    void setUserIntroduction(String userIntroduction);
    void setUserSex(String userSex);
    void setUserBirthday(String userBirthday);
    void setUserHometown(String userHometown);
    int getmUserId();
    void setUserApp(User user);
    void showDig(String msg);
}
