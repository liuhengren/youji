package neet.com.youjidemo.Presenter;

import android.os.AsyncTask;

import neet.com.youjidemo.bean.Dynamic;
import neet.com.youjidemo.bean.ShowDynamicInAll;
import neet.com.youjidemo.bean.User;
import neet.com.youjidemo.biz.Dynamicbiz;
import neet.com.youjidemo.biz.IDynamic;
import neet.com.youjidemo.biz.IUserDetailEdit;
import neet.com.youjidemo.biz.UserDetailbiz;
import neet.com.youjidemo.view.IView.IShareView;

public class SharePresenter {
    private IShareView shareView;
    private IDynamic dynamicBiz;
    private int dynamic_id;
    private IUserDetailEdit userDetailEdit;
    private ShowDynamicInAll showDynamicInAll;
    public SharePresenter(IShareView shareView){
        this.shareView=shareView;
        dynamicBiz=new Dynamicbiz();
        userDetailEdit=new UserDetailbiz();
    }
    public void snedText(){
        new AsyncTask(){

            @Override
            protected Object doInBackground(Object[] objects) {
                Dynamic dynamic = new Dynamic();
                dynamic.setDynamic_user_id(shareView.getmuserId());
                dynamic.setDynamic_text(shareView.getTextOnDynamic());
                dynamic.setDynamic_address(shareView.getAddress());
                dynamic.setDynamic_partition_id(1);
                dynamic_id = dynamicBiz.addDynamic(dynamic);
                shareView.startUpImg(dynamic_id);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                shareView.showMessage("发表成功");
                shareView.toManinActivity();
            }

        }.execute();
    }
}
