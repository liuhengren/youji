package neet.com.youjidemo.view.IView;

import java.util.List;

public interface IMessageView {
    int getmUserid();
    void toDetaActivity();
    void setCommentList(List list);
    void setFollowList(List list);
    void setLikeupList(List list);
    void mListchange();
}
