package neet.com.youjidemo.view.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import neet.com.youjidemo.R;
import neet.com.youjidemo.view.LoginActivity;


public class MeFragment extends Fragment {
    private ImageView headsculpture;
    private TextView login;
    private ImageButton collection;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me,container,false);
        headsculpture = view.findViewById(R.id.iv_head_sculpture);
        login = view.findViewById(R.id.tv_login);
        collection = view.findViewById(R.id.ib_collection);
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
        return view;
    }
}
