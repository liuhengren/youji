package neet.com.youjidemo.biz;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class Userbiz implements IUserLog {
    private boolean logupResulet=false;
    @Override
    public void login(String userphone, String password) {
        //请求网络服务器
    }

    @Override
    public boolean logup(final String userphone, String password) {
        return false;
    }

}
