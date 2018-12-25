package neet.com.youjidemo.view.IView;

import java.util.List;

import neet.com.youjidemo.bean.User;

public interface IFanView {
    void setList(List<User> list);
    void change();
    void cancel(int follow_user_id);
    void addfollow(int follow_user_id);
    void toPerCenterActivity();
}
