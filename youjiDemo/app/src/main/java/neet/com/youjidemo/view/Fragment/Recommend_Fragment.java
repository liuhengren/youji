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

import de.hdodenhof.circleimageview.CircleImageView;
import neet.com.youjidemo.R;
import neet.com.youjidemo.view.FoodActivity;
import neet.com.youjidemo.view.TravleActivity;

/**
 * desc:首页的推荐Fragment
 * author:梁启文
 * time：2018/12/06
 */

public class Recommend_Fragment extends Fragment {
    private ImageButton btnImageFood, btnImageTravel, btnImagePlay, btnImageHappy;
    private View view;
    private CircleImageView circleImageView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.community_layout, null);
        }


        findViews();
        /**左上角图的点击事件*/
        btnImageFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), FoodActivity.class);
                startActivity(intent);
            }
        });

        /**右上角图的点击事件*/
        btnImageTravel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), TravleActivity.class);
//                startActivity(intent);
            }
        });

        /**左下角图的点击事件*/
        btnImagePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /**右下角图的点击事件*/
        btnImageHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;


    }

    private void findViews() {
        btnImageFood = view.findViewById(R.id.image_btn_food);
        btnImageTravel = view.findViewById(R.id.image_btn_travel);
        btnImagePlay = view.findViewById(R.id.image_btn_play);
        btnImageHappy = view.findViewById(R.id.image_btn_note);
        circleImageView=view.findViewById(R.id.civ_map);
    }

}

