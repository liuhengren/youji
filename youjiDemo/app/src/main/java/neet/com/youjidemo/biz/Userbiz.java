package neet.com.youjidemo.biz;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import neet.com.youjidemo.command.PostJson;

public class Userbiz implements IUserLog {
    private boolean logupResulet=false;
    private final String UserUrl="http://10.222.184.38:8080/youjiServer/UserServlet";
    @Override
    public boolean login(String userphone, String password) {
        //请求网络服务器
        String msg="user_login";
        String url=UserUrl+"?message="+msg+"&userphone="+userphone+"&password="+password;
        boolean b = PostJson.PostByUrl(url);
        return b;
    }

    @Override
    public boolean logup(final String userphone, String password) {
        String msg="user_logup";
        String url=UserUrl+"?message="+msg+"&phone="+userphone+"&password="+password;
        boolean b = PostJson.PostByUrl(url);
        return b;
    }

}
