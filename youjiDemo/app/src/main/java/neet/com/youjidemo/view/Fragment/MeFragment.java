package neet.com.youjidemo.view.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import neet.com.youjidemo.R;
import neet.com.youjidemo.view.LoginActivity;
import neet.com.youjidemo.view.MyLoveActivity;
import neet.com.youjidemo.view.PersonalCenterActivity;


public class MeFragment extends Fragment {
    private View view;
    private ImageView headsculpture;
    private TextView login;
    private ImageButton collection;
    private Button btnPersonalCenter,btnLove,btnFootPrint;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.fragment_me, container, false);
        }

        findViews();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headsculpture.setImageResource(R.drawable.module_message_at);
                Intent intent = new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
        headsculpture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                headsculpture.setImageResource(R.drawable.module_message_at);
                Intent intent = new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        btnPersonalCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),PersonalCenterActivity.class);
                startActivity(intent);
            }
        });

        btnLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MyLoveActivity.class);
                startActivity(intent);
            }
        });

        btnFootPrint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"功能开发中，敬请期待...",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


    /**获取布局中的控件*/
    private void findViews(){
        headsculpture = view.findViewById(R.id.iv_head_sculpture);
        login = view.findViewById(R.id.tv_login);
        collection = view.findViewById(R.id.ib_collection);
        btnPersonalCenter = view.findViewById(R.id.btn_personal_center);
        btnFootPrint = view.findViewById(R.id.btn_footprint);
        btnLove = view.findViewById(R.id.btn_love);
    }
}
