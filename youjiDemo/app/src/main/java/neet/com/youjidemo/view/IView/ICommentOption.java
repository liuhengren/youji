package neet.com.youjidemo.view.IView;

import java.util.List;

import neet.com.youjidemo.bean.ShowCommentBean;

/**
 * 详情页评论区功能
 */
public interface ICommentOption {
    void setList(List<ShowCommentBean> list);
    int getmUserId();
    void ifCommentSussess(boolean b);
}
