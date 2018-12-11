package neet.com.youjidemo.Presenter;

import neet.com.youjidemo.biz.IUserDetailEdit;
import neet.com.youjidemo.biz.UserDetailbiz;
import neet.com.youjidemo.view.IPerDateEditorView;

public class PerDateEditPresenter {
    private IPerDateEditorView perDateEditorView;
    private IUserDetailEdit userDetailEdit;
    public PerDateEditPresenter(IPerDateEditorView perDateEditorView){
        this.perDateEditorView=perDateEditorView;
        userDetailEdit=new UserDetailbiz();
    }

}
