package neet.com.youjidemo.view.IView;

import java.util.List;

import neet.com.youjidemo.bean.ShowDynamicInAll;

public interface IDynamicOption {
    void setListByTag(List<ShowDynamicInAll> list);
    int getmUserId();
    void addCollection(int dynamic_id);
    void addFollow(int follow_user_id);
    void likeTheDynamic(int dynamic_id);
    void cancelLike(int dynamic_id);
    void cancelFollow(int follow_user_id);
    void cancelCollection(int dynamic_id);
    void change();
}
