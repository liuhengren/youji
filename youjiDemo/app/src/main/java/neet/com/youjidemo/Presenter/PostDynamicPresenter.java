package neet.com.youjidemo.Presenter;

import android.os.AsyncTask;

import neet.com.youjidemo.bean.Dynamic;
import neet.com.youjidemo.biz.Dynamicbiz;
import neet.com.youjidemo.biz.IDynamic;

public class PostDynamicPresenter {
    private IDynamic dynamicBiz;
    private Dynamic dynamic = new Dynamic();
    public  void postDynamic(){

    }
    private class PostAsyTask extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            dynamicBiz.addDynamic(dynamic);
            return null;
        }
    }
}
