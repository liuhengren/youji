package neet.com.youjidemo.Presenter;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import neet.com.youjidemo.bean.Dynamic;
import neet.com.youjidemo.biz.Dynamicbiz;
import neet.com.youjidemo.biz.IDynamic;
import neet.com.youjidemo.view.Fragment.Index_RecommendFragment;

public class GetDynamicPresenter {
    private Index_RecommendFragment index_recommendFragment;
    private IDynamic dynamicBiz;
    private List<Dynamic> list;
    public GetDynamicPresenter(Index_RecommendFragment index_recommendFragment){
        dynamicBiz=new Dynamicbiz();
        this.index_recommendFragment=index_recommendFragment;
    }
    public void getList(){
        DynamicTask dynamicTask = new DynamicTask();
        dynamicTask.execute();
    }
    public class DynamicTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            Dynamicbiz dynamicbiz = new Dynamicbiz();
            list=dynamicbiz.getDynamic();
            return null;
        }
        @Override
        protected void onPostExecute(Object o) {
            index_recommendFragment.setListByTag(list);
            index_recommendFragment.change();
        }
    }
}
