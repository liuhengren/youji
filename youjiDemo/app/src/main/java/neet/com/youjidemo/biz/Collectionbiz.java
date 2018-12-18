package neet.com.youjidemo.biz;

import android.os.Handler;

import java.util.List;

import neet.com.youjidemo.bean.Collection;
import neet.com.youjidemo.command.GetJsonStr;

public class Collectionbiz implements ICollection{
    final private String urlForgetCollectionByUserId="";
    @Override
    public List<Collection> getCollectionByUserId(final int user_id) {
        new Thread(){
            @Override
            public void run() {
                super.run();

            }
        };
        return null;
    }

    @Override
    public void addCollection(int user_id, int dynamic_id) {

    }

    @Override
    public void deleteCollection(int collection_id) {

    }

}
