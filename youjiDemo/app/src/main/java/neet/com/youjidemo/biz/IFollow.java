package neet.com.youjidemo.biz;

import java.util.List;

import neet.com.youjidemo.bean.Follow;

public interface IFollow {
    List<Follow> getFollowByUserId(int user_id);
    boolean addFollow(int user_id,int follow_user_id);
    boolean deleteFollow(int user_id,int follow_user_id);
    boolean isFollow(int user_id,int follow_user_id);

}
