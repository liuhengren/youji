package neet.com.youjidemo.view;

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
}
