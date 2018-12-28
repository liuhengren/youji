package neet.com.youjidemo.biz;

import java.util.List;

import neet.com.youjidemo.bean.LikeUp;

public interface ILikeUp {
    boolean addLike(int user_id,int dynamic_id);
    boolean cancelLike(int user_id,int dynamic_id);
    boolean ifLike(int user_id,int dynamic_id);
    List<LikeUp> getListByDynamicId(int dynamic_id);
}
