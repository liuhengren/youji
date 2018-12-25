package neet.com.youjidemo.view.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import neet.com.youjidemo.Presenter.FansPresenter;
import neet.com.youjidemo.R;
import neet.com.youjidemo.adapter.FansPagerAdapter;
import neet.com.youjidemo.bean.User;
import neet.com.youjidemo.bean.UserDateApplication;
import neet.com.youjidemo.view.IView.IFanView;

/**
 * desc:个人中心粉丝页Fragment
 * author：梁启文
 * time:2018/12/11
 */
public class FansFragment extends Fragment implements IFanView {
    private List<User> list;
    private UserDateApplication userDateApplication;
    private FansPagerAdapter adapter;
    private FansPresenter fansPresenter;
    private int user_id;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fans_view_pager_layout,null);
        list = new ArrayList();
        RecyclerView recyclerView = view.findViewById(R.id.rv_fans);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        adapter = new FansPagerAdapter(list,this);
        recyclerView.setAdapter(adapter);
        userDateApplication=(UserDateApplication)getActivity().getApplication();
        fansPresenter=new FansPresenter(this);
        user_id=getActivity().getIntent().getIntExtra("user_id",userDateApplication.getUser().getUser_id());
        fansPresenter.getList(user_id);
        return view;
    }

    @Override
    public void setList(List<User> list) {
        this.list=new ArrayList<>();
        this.list=list;
    }

    @Override
    public void change() {
        adapter.updataList(this.list);
    }

    @Override
    public void cancel(int follow_user_id) {
        fansPresenter.cancelFollow(user_id,follow_user_id);
    }

    @Override
    public void addfollow(int follow_user_id) {
        fansPresenter.addFollow(user_id,follow_user_id);
    }

    @Override
    public void toPerCenterActivity() {

    }
}
