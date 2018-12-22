package neet.com.youjidemo.Presenter;



import android.os.AsyncTask;

import neet.com.youjidemo.bean.User;
import neet.com.youjidemo.biz.IUserDetailEdit;
import neet.com.youjidemo.biz.UserDetailbiz;
import neet.com.youjidemo.view.IView.IPerDateEditorView;

public class PerDateEditPresenter {
    private IPerDateEditorView perDateEditorView;
    private IUserDetailEdit userDetailEdit;
    private int User_id;
    public PerDateEditPresenter(IPerDateEditorView perDateEditorView){
        this.perDateEditorView=perDateEditorView;
        userDetailEdit=new UserDetailbiz();
    }
    public void update(String tag,String msg){
        Tag tag1 = new Tag(tag, msg);
        //User_id=perDateEditorView.getmUserId();
        User_id=3;
        PerDateEditAsyTask perDateEditAsyTask = new PerDateEditAsyTask();
        perDateEditAsyTask.execute(tag1);
    }
    private class PerDateEditAsyTask extends AsyncTask {
        private boolean result;
        @Override
        protected Object doInBackground(Object[] objects) {
            Tag tag=(Tag)objects[0];
            switch (tag.tag){
                case"username":
                    result =userDetailEdit.updateUsername(User_id, tag.msg);
                    break;
                case"introduction":
                    result=userDetailEdit.updateUserIntroduction(User_id,tag.msg);
                    break;
                case"sex":
                    result=userDetailEdit.updateUserSex(User_id,tag.msg);
                    break;
                case"birthday":
                    //result=userDetailEdit.updateUserBirthday(User_id,tag.msg);
                    break;
                case"hometown":
                    result=userDetailEdit.updateUserHometown(User_id,tag.msg);
                    break;
            }
            if(result){
                User user = userDetailEdit.getUserById(User_id);
                perDateEditorView.setUserApp(user);
            }
            return tag;
        }

        @Override
        protected void onPostExecute(Object o) {
            Tag tag=(Tag)o;
            if(result){
                perDateEditorView.showDig("修改成功");
            }
            else{
                perDateEditorView.showDig("修改失败");
            }
        }
    }
    private class setDetails extends AsyncTask{
        User users=new User();
        @Override
        protected Object doInBackground(Object[] objects) {
           users= userDetailEdit.getUserById(perDateEditorView.getmUserId());
            //perDateEditorView.setUserBirthday();
            return users;
        }

        @Override
        protected void onPostExecute(Object o) {
            User user=(User)o;
            perDateEditorView.setUserHometown(user.getUser_address());
            perDateEditorView.setUserIntroduction(user.getUser_introduction());
            perDateEditorView.setUserName(user.getUser_name());
            perDateEditorView.setUserSex(user.getUser_sex());
        }
    }
    public void setUserDetail(){
        setDetails setDetails = new setDetails();
        setDetails.execute();
    }
    private class Tag{
        String tag;
        String msg;

        public Tag(String tag, String msg) {
            this.tag = tag;
            this.msg = msg;
        }
    }

}


