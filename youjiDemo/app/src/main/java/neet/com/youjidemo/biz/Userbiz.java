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
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if("15303316718".equals(userphone)){
                    logupResulet=true;
                }else{
                    logupResulet=false;
                }
            }
        }.start();
        return logupResulet;
    }
}
