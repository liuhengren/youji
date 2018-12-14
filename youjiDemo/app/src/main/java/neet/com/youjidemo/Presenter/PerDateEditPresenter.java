package neet.com.youjidemo.Presenter;



import android.os.Handler;
import android.os.Message;
import android.util.Log;

import neet.com.youjidemo.biz.IUserDetailEdit;
import neet.com.youjidemo.biz.UserDetailbiz;
import neet.com.youjidemo.view.IPerDateEditorView;

public class PerDateEditPresenter {
    private IPerDateEditorView perDateEditorView;
    private IUserDetailEdit userDetailEdit;
    private Handler mhandler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    Log.e("1", "handleMessage: 1");
                    break;
            }
        }
    };
    public PerDateEditPresenter(IPerDateEditorView perDateEditorView){
        this.perDateEditorView=perDateEditorView;
        userDetailEdit=new UserDetailbiz(mhandler);
    }
    public void update(String tag,String msg){
        switch (tag){
            case "username":
                userDetailEdit.updateUsername(msg);
                break;
            case "userbirthday":
                userDetailEdit.updateUserBirthday(msg);
                break;
            case "usersex":
                userDetailEdit.updateUserSex(msg);
                break;
            case"userhometown":
                userDetailEdit.updateUserHometown(msg);
                break;
            case "userintroduction":
                userDetailEdit.updateUserIntroduction(msg);
                break;
        }
    }
}
