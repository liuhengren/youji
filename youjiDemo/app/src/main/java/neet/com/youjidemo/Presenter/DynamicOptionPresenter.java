package neet.com.youjidemo.Presenter;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.bean.Collection;
import neet.com.youjidemo.bean.Dynamic;
import neet.com.youjidemo.bean.ShowDynamicInAll;
import neet.com.youjidemo.bean.User;
import neet.com.youjidemo.biz.Collectionbiz;
import neet.com.youjidemo.biz.Dynamicbiz;
import neet.com.youjidemo.biz.FollowBiz;
import neet.com.youjidemo.biz.ICollection;
import neet.com.youjidemo.biz.IDynamic;
import neet.com.youjidemo.biz.IFollow;
import neet.com.youjidemo.biz.ILikeUp;
import neet.com.youjidemo.biz.IUserDetailEdit;
import neet.com.youjidemo.biz.LikeUpbiz;
import neet.com.youjidemo.biz.UserDetailbiz;
import neet.com.youjidemo.view.Fragment.Index_RecommendFragment;
import neet.com.youjidemo.view.IView.IDynamicOption;

public class DynamicOptionPresenter {
    private IDynamicOption index_recommendFragment;
    private IDynamic dynamicBiz;
    private IUserDetailEdit userDetaBiz;
    private IFollow followBiz;
    private ICollection collectionBiz;
    private ILikeUp likeupBiz;
    private List<Dynamic> list=new ArrayList<>();
    private List<ShowDynamicInAll> showDynamicInAllList=new ArrayList<>();
    public DynamicOptionPresenter(IDynamicOption index_recommendFragment){
        dynamicBiz=new Dynamicbiz();
        userDetaBiz=new UserDetailbiz();
        followBiz=new FollowBiz();
        collectionBiz=new Collectionbiz();
        likeupBiz=new LikeUpbiz();
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
                case"collection":
                    List<Collection> collectionByUserId = collectionBiz.getCollectionByUserId(tag.id);
                    for(int i=-0;i<collectionByUserId.size();i++){
                        Dynamic dynamic=new Dynamic();
                        dynamic=dynamicBiz.getDynamicById(collectionByUserId.get(i).getCollection_dynamic_id());
                        list.add(dynamic);
                    }
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
                    boolean like = likeupBiz.ifLike(index_recommendFragment.getmUserId(), list.get(i).getDynamic_id());
                    boolean b = collectionBiz.ifCollection(index_recommendFragment.getmUserId(), list.get(i).getDynamic_id());
                    showDynamicInAll.setCollection(b);
                    showDynamicInAll.setFollow(follow);
                    showDynamicInAll.setLike(like);
                }else{
                    showDynamicInAll.setFollow(false);
                    showDynamicInAll.setLike(false);
                    showDynamicInAll.setCollection(false);
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
    public void addCollection(final int user_id, final int dynamic_id){
        new AsyncTask(){
            @Override
            protected Object doInBackground(Object[] objects) {
                boolean b = collectionBiz.addCollection(user_id, dynamic_id);
                return  null;
            }
        }.execute();
    }
    public void addFollow(final int user_id, final int follow_user_id){
        new AsyncTask(){
            @Override
            protected Object doInBackground(Object[] objects) {
                boolean b = followBiz.addFollow(user_id, follow_user_id);
                return  null;
            }
        }.execute();
    }
    public void cancelFollow(final int user_id, final int follow_user_id){
        new AsyncTask(){
            @Override
            protected Object doInBackground(Object[] objects) {
                boolean b = followBiz.deleteFollow(user_id, follow_user_id);
                return  null;
            }
        }.execute();
    }
    public void cancelCollection(final int user_id, final int dynamic_id){
        new AsyncTask(){
            @Override
            protected Object doInBackground(Object[] objects) {
                boolean b = collectionBiz.deleteCollection(user_id, dynamic_id);
                return  null;
            }
        }.execute();
    }
    public void addLike(final int user_id, final int dynamic_id){
        new AsyncTask(){
            @Override
            protected Object doInBackground(Object[] objects) {
                boolean b = likeupBiz.addLike(user_id,dynamic_id);
                return  null;
            }
        }.execute();
    }
    public void cancelLike(final int user_id, final int dynamic_id){
        new AsyncTask(){
            @Override
            protected Object doInBackground(Object[] objects) {
                boolean b = likeupBiz.cancelLike(user_id,dynamic_id);
                return  null;
            }
        }.execute();
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
