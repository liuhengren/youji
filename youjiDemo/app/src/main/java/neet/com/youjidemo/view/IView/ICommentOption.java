package neet.com.youjidemo.view.IView;

import java.util.List;

import neet.com.youjidemo.bean.ShowCommentBean;

/**
 * 详情页评论区功能
 */
public interface ICommentOption {
    void setCommentList(List<ShowCommentBean> list);
    void changeCommnetList();
    String getCommnetText();
}
