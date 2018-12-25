package neet.com.youjidemo.view;

import android.app.Application;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.google.gson.Gson;


import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import neet.com.youjidemo.Presenter.PerDateEditPresenter;
import neet.com.youjidemo.R;
import neet.com.youjidemo.bean.JsonBean;
import neet.com.youjidemo.bean.User;
import neet.com.youjidemo.bean.UserDateApplication;
import neet.com.youjidemo.command.GetJsonDataUtil;
import neet.com.youjidemo.view.IView.IPerDateEditorView;

public class PersonalDataEditorActivity extends AppCompatActivity implements IPerDateEditorView {
    private LinearLayout pdeLlRoot;
    private LinearLayout mLltouxiang,mLlSex;
    private RelativeLayout mRlHometown;
    private EditText mEtIntroduction,mEtName;
    private TextView mTvBirthday,mTvSex,mTvHometown;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private Thread thread;
    private PopupWindow pdePwSexSelect;
    private PerDateEditPresenter perDateEditPresenter;
    private UserDateApplication userDateApplication;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_data_editor_activity);
        Toolbar toolbar=(Toolbar)findViewById(R.id.tb_pde);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);//主键按钮能否可点击x
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//显示返回图标
        initview();
        openTimePicker();
        setOnLinster();
        perDateEditPresenter.setUserDetail();
        userDateApplication=(UserDateApplication) getApplication();
    }
    private void initview(){
        pdeLlRoot=findViewById(R.id.pde_root);
        mLltouxiang=findViewById(R.id.pde_ll_touxiang);
        mLlSex=findViewById(R.id.pde_ll_sex);
        mRlHometown=findViewById(R.id.pde_rl_hometown);
        mEtIntroduction=findViewById(R.id.pde_et_introduction);
        mEtName=findViewById(R.id.pde_et_name);
        mTvBirthday=findViewById(R.id.pde_tv_birthday);
        mTvHometown=findViewById(R.id.pde_tv_hometown);
        mTvSex=findViewById(R.id.pde_tv_sex);
        pdePwSexSelect=new PopupWindow(PersonalDataEditorActivity.this);
        //初始化地点选择器数据
        if (thread == null) {//如果已创建就不再重新创建子线程了
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    // 子线程中解析省市区数据
                    initJsonData();
                }
            });
            thread.start();
        }
        perDateEditPresenter=new PerDateEditPresenter(PersonalDataEditorActivity.this);
    }

    /**
     * 设置所有事件监听器
     */
    private void setOnLinster(){
        mOnFocusChangeListener mOnFocusChangeListener = new mOnFocusChangeListener();
        mLlSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPupupWindow();
            }
        });
        mRlHometown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPickerView();
            }
        });
        mEtIntroduction.setOnFocusChangeListener(mOnFocusChangeListener);
        mEtName.setOnFocusChangeListener(mOnFocusChangeListener);
    }
    /**

     * 初始化时间选择器
     */
    public void openTimePicker(){
        final TimePickerView tpv=new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                perDateEditPresenter.update("birthday",getTime(date));
                setUserBirthday(getTime(date));
            }
        }).build();
        mTvBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tpv.show();
            }
        });
    }
    private void showPickerView() {// 弹出选择器
        OptionsPickerView pvOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() +
                        options2Items.get(options1).get(options2) +
                        options3Items.get(options1).get(options2).get(options3);
                perDateEditPresenter.update("hometown",tx);
                setUserHometown(tx);
            }
        })
                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
        pvOptions.show();
    }

    private void initJsonData() {//解析数据
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c = 0; c < jsonBean.get(i).getCityList().size(); c++) {//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市
                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        || jsonBean.get(i).getCityList().get(c).getArea().size() == 0) {
                    City_AreaList.add("");
                } else {
                    City_AreaList.addAll(jsonBean.get(i).getCityList().get(c).getArea());
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }
    }
    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }

    /**
     * 解析时间为字符串
     * @param date
     * @return
     */
    private String getTime(Date date) {//可根据需要自行截取数据显示
        //Log.d("getTime()", "choice date millis: " + date.getTime());
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(date);
    }

    /**
     * 初始化性别选择器
     */
    private void initPupupWindow(){
        final View view=LayoutInflater.from(PersonalDataEditorActivity.this).inflate(R.layout.sex_select_layout,null,false);
        RadioGroup rg_sex=view.findViewById(R.id.pde_rg_sex);
        final RadioButton man=view.findViewById(R.id.pde_rb_man);
        final RadioButton woman=view.findViewById(R.id.pde_rb_women);
        final RelativeLayout pdeRlRoot=view.findViewById(R.id.pde_rl_root);
        final String[] tmpSex = new String[1];
        TextView pdeTvCancel=view.findViewById(R.id.pdepp_tv_cancel);
        TextView pdeTvFinish=view.findViewById(R.id.pdepp_tv_finish);
        pdeTvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pdePwSexSelect.dismiss();
            }
        });
        pdeRlRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pdePwSexSelect.dismiss();
            }
        });
        pdeTvFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perDateEditPresenter.update("sex",tmpSex[0]);
                setUserSex(tmpSex[0]);
                pdePwSexSelect.dismiss();
            }
        });
        pdePwSexSelect.setBackgroundDrawable(new BitmapDrawable());
        pdePwSexSelect.setWidth(ConstraintLayout.LayoutParams.MATCH_PARENT);
        pdePwSexSelect.setAnimationStyle(R.style.popwin_anim_style);
        pdePwSexSelect.setContentView(view);

        rg_sex.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                ViewCompat.animate(view.findViewById(checkedId))
                        .setDuration(200)
                        .scaleX(2f)
                        .scaleY(2f)
                        .start();
                tmpSex[0] =((RadioButton)view.findViewById(checkedId)).getText().toString();
                if(R.id.pde_rb_man==checkedId){
                    ViewCompat.animate(woman)
                            .setDuration(100)
                            .scaleX(1f)
                            .scaleY(1f)
                            .start();
                }else{
                    ViewCompat.animate(man)
                            .setDuration(100)
                            .scaleX(1f)
                            .scaleY(1f)
                            .start();
                }
            }

        });
        pdePwSexSelect.showAtLocation(pdeLlRoot, Gravity.NO_GRAVITY,0,0);

    }
    @Override
    public String getUserName() {
        return mEtName.getText().toString();
    }

    @Override
    public String getUserIntroduction() {
        return mEtIntroduction.getText().toString();
    }

    @Override
    public String getUserBirthday() {
        return mTvBirthday.getText().toString();
    }

    @Override
    public String getUserSex() {
        return mTvSex.getText().toString();
    }

    @Override
    public String getUserHometown() {
        return mTvHometown.getText().toString();
    }

    @Override
    public void setUserName(String username) {
        mEtName.setText(username);
    }

    @Override
    public void setUserIntroduction(String userIntroduction) {
        mEtIntroduction.setText(userIntroduction);
    }

    @Override
    public void setUserSex(String userSex) {
        mTvSex.setText(userSex);
    }

    @Override
    public void setUserBirthday(String userBirthday) {
        mTvBirthday.setText(userBirthday);
    }

    @Override
    public void setUserHometown(String userHometown) {
        mTvHometown.setText(userHometown);
    }

    @Override
    public int getmUserId() {
        return userDateApplication.getUser().getUser_id();

    }
    @Override
    public void setUserApp(User user) {
        userDateApplication.setUser(user);
        userDateApplication.setLogin(true);
    }

    @Override
    public void showDig(String msg) {
        Toast.makeText(PersonalDataEditorActivity.this,msg,Toast.LENGTH_SHORT).show();
    }

    /**
     * EditText失去焦点操作
     */
    public class mOnFocusChangeListener implements View.OnFocusChangeListener{
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(!hasFocus){
                String s = ((EditText) v).getText().toString();
                //根据ID更新信息
                switch (v.getId()){
                    case R.id.pde_et_name:
                        perDateEditPresenter.update("username",getUserName());
                        break;
                    case R.id.pde_et_introduction:
                        perDateEditPresenter.update("introduction",getUserIntroduction());
                        break;
                }
            }
        }
    }

    /**
     * 点击空白处使EditText失去焦点
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction()==MotionEvent.ACTION_DOWN){
            View view =getCurrentFocus();
            if(isHideInput(view,ev)){
                HideSoftInput(view.getWindowToken());
                view.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }
    public boolean isHideInput(View v, MotionEvent ev){
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }
    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
