package neet.com.youjidemo.Presenter;



import android.os.Handler;
import android.os.Message;
import android.util.Log;

import neet.com.youjidemo.biz.IUserDetailEdit;
import neet.com.youjidemo.biz.UserDetailbiz;
import neet.com.youjidemo.view.IView.IPerDateEditorView;

public class PerDateEditPresenter {
    private IPerDateEditorView perDateEditorView;
    private IUserDetailEdit userDetailEdit;
    public PerDateEditPresenter(IPerDateEditorView perDateEditorView){
        this.perDateEditorView=perDateEditorView;
        userDetailEdit=new UserDetailbiz();
    }
    public void update(String tag,String msg){

        }
    }

