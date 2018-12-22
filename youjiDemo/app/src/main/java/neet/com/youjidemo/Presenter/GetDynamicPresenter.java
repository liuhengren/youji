package neet.com.youjidemo.Presenter;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.bean.Dynamic;
import neet.com.youjidemo.bean.ShowDynamicInAll;
import neet.com.youjidemo.bean.User;
import neet.com.youjidemo.biz.Dynamicbiz;
import neet.com.youjidemo.biz.FollowBiz;
import neet.com.youjidemo.biz.IDynamic;
import neet.com.youjidemo.biz.IFollow;
import neet.com.youjidemo.biz.IUserDetailEdit;
import neet.com.youjidemo.biz.UserDetailbiz;
import neet.com.youjidemo.view.Fragment.Index_RecommendFragment;

public class GetDynamicPresenter {
    private Index_RecommendFragment index_recommendFragment;
    private IDynamic dynamicBiz;
    private IUserDetailEdit userDetaBiz;
    private IFollow followBiz;
    private List<Dynamic> list;
    private List<ShowDynamicInAll> showDynamicInAllList=new ArrayList<>();
    public GetDynamicPresenter(Index_RecommendFragment index_recommendFragment){
        dynamicBiz=new Dynamicbiz();
        userDetaBiz=new UserDetailbiz();
        followBiz=new FollowBiz();
        this.index_recommendFragment=index_recommendFragment;
    }
    public void getList(String tag,int id){
        Tag tag1 = new Tag(tag, id);
        DynamicTask dynamicTask = new DynamicTask();
        dynamicTask.execute(tag1);
    }
    public class DynamicTask extends AsyncTask {
        @Override
        protected Object doInBackground(Object[] objects) {
            Tag tag=(Tag)(objects[0]);
            switch(tag.tag){
                case"all":
                    list=dynamicBiz.getDynamic();
                    break;
                case"hot":
                    list=dynamicBiz.getDynamicOrderHot();
                    break;
                case"partition":
                    list=dynamicBiz.getDynamicByPartitionId(tag.id);
                    break;
                case"user":
                    list=dynamicBiz.getDynamicByUserId(tag.id);
                    break;
                    default:
                        list=dynamicBiz.getDynamic();
                        break;
            }

            for(int i=0;i<list.size();i++){
                User user = new User();
                user=userDetaBiz.getUserById(list.get(i).getDynamic_user_id());
                ShowDynamicInAll showDynamicInAll = new ShowDynamicInAll(list.get(i).getDynamic_user_id(),
                        user.getUser_name(),
                        user.getUser_touxiang_url(),
                        user.getUser_address(),
                        list.get(i).getDynamic_id(),
                        list.get(i).getDynamic_text(),
                        list.get(i).getDynamic_img(),
                        list.get(i).getDynamic_collection_num(),
                        list.get(i).getDynamic_comment_num(),
                        list.get(i).getDynamic_like_num(),
                        list.get(i).getDynamic_time()
                );
                if(index_recommendFragment.getmUserId()!=0){
                    boolean follow = followBiz.isFollow(index_recommendFragment.getmUserId(), list.get(i).getDynamic_user_id());
                    showDynamicInAll.setFollow(follow);
                }else{
                    showDynamicInAll.setFollow(false);
                }
                showDynamicInAllList.add(showDynamicInAll);
            }
            return null;
        }
        @Override
        protected void onPostExecute(Object o) {
            index_recommendFragment.setListByTag(showDynamicInAllList);
            index_recommendFragment.change();
        }
    }

    /**
     * 用于区分查询内容
     */
    private class Tag{
        private String tag;
        private int id;

        public Tag(String tag, int id) {
            this.tag = tag;
            this.id = id;
        }
    }
}
