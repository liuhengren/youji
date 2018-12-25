package neet.com.youjidemo.biz;




import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;

import neet.com.youjidemo.bean.User;
import neet.com.youjidemo.bean.UserDetail;
import neet.com.youjidemo.command.GetJsonStr;
import neet.com.youjidemo.command.JsonObjiecrToObject;
import neet.com.youjidemo.command.PostJson;

public class UserDetailbiz implements IUserDetailEdit{
    private final String UserUrl="http://10.222.189.117:8080/youjiServer/UserServlet";
    public  UserDetailbiz(){

    }

    @Override
    public User getUserById(int user_id) {
        String msg="user_getUserById";
        User user=new User();
        String url=UserUrl+"?message="+msg+"&id="+user_id;
        String str = GetJsonStr.getJsonStrbyUrl(url);
        Log.e("1",str);
        try {
            user = JsonObjiecrToObject.JsonToUser(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserDetail(String userphone) {
        String msg="user_UserDetail";
        User user=new User();
        String url=UserUrl+"?message="+msg+"&phone="+userphone;
        String str = GetJsonStr.getJsonStrbyUrl(url);
        Log.e("3",str);
        try {
            user = JsonObjiecrToObject.JsonToUser(new JSONObject(str));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean updateUsername(int user_id, String username) {
        String msg="user_Username";
        String url=UserUrl+"?message="+msg+"&id="+user_id+"&name="+username;
        Log.e("1",url);
        boolean b = PostJson.PostByUrl(url);
        return b;
    }

    @Override
    public boolean updateUsertouxiang() {
        return  false;
    }

    @Override
    public boolean updateUserSex(int user_id, String userSex) {
        String msg="user_sex";
        String url=UserUrl+"?message="+msg+"&id="+user_id+"&sex="+userSex;
        boolean b = PostJson.PostByUrl(url);
        return b;
    }

    @Override
    public boolean updateUserIntroduction(int user_id, String userIntroduction) {
        String msg="user_instruction";
        String url=UserUrl+"?message="+msg+"&id="+user_id+"&instruction="+userIntroduction;
        boolean b = PostJson.PostByUrl(url);
        return b;
    }

    @Override
    public boolean updateUserBirthday(int user_id, Date userBirthday) {
        String msg="user_birthday";
        String url=UserUrl+"?message="+msg+"&id="+user_id+"&birthday="+userBirthday;
        boolean b = PostJson.PostByUrl(url);
        return b;
    }

    @Override
    public boolean updateUserHometown(int user_id, String userHometown) {
        String msg="user_address";
        String url=UserUrl+"?message="+msg+"&id="+user_id+"&address="+userHometown;
        boolean b = PostJson.PostByUrl(url);
        return b;
    }



}

