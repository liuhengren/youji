package neet.com.youjidemo.biz;

import java.util.List;

import neet.com.youjidemo.bean.Collection;

public interface ICollection {
    List<Collection> getCollectionByUserId(int user_id);
    void addCollection(int user_id,int dynamic_id);
    void deleteCollection(int collection_id);
}
