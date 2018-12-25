package neet.com.youjidemo.view.IView;

import neet.com.youjidemo.bean.ShowDynamicInAll;

public interface IShareView {
    void showMessage(String msg);
    void startUpImg(int dynamic_id);
    int getmuserId();
    String getTextOnDynamic();
    int getPartitionId();
    String getAddress();
    boolean getResultofDynamic();
    void toManinActivity();
}