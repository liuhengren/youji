package neet.com.youjidemo.command;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

/**
 * desc:图片上传类
 */

public class UploadUtil {
    private static final String TAG = "uploadFile";
    private static final int TIME_OUT = 10 * 1000;//超时时间
    private static final String CHARSET = "utf-8";//设置编码

    /**
     * android上传文件到服务器
     *
     * @param file       需要上传的文件
     * @param RequestURL  请求的url
     * @return 返回响应的内容
     */
    public static boolean uploadImage(File file, String RequestURL,int dynamic_id) {
        boolean result = false;
        String BOUNDARY = UUID.randomUUID().toString();//边界标识 随机生成
        String PREFIX = "--", LINE_END = "\r\n";
        String CONTENT_TYPE = "multipart/form-data";//内容类型
        try {
            URL url = new URL(RequestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setReadTimeout(TIME_OUT);
//            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true);//允许输入流
            conn.setDoOutput(true);//允许输出流
            conn.setUseCaches(false);//不允许使用缓存
            conn.setRequestMethod("POST");//请求方式
            conn.setRequestProperty("Charset", CHARSET);//设置编码
            conn.setRequestProperty("contentType", "utf-8");//设置编码
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary=" + BOUNDARY);
            conn.setRequestProperty("id",String.valueOf(dynamic_id));

            Log.e("upload",""+conn);
            Log.e("file",""+file.length());
            if (file != null) {
                //当文件不为空，把文件包装并且上传
                DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
//                dos.writeBytes(PREFIX + BOUNDARY + LINE_END);
//                dos.writeBytes("Content-Disposition: form-data; " + "name=\"inputName\";filename=\"" + file.getName() + "\"" + LINE_END);
                FileInputStream is = new FileInputStream(file);
                byte[] bytes = new byte[200000];
                int len = -1;
                while ((len = is.read(bytes)) != -1) {
                    dos.write(bytes, 0, len);
                }
                is.close();
//                dos.write(LINE_END.getBytes());
//                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END).getBytes();
//                dos.write(end_data);
                dos.flush();
                Log.e("列舉哦","兩");
                InputStream in = conn.getInputStream();
                int  s = in.read();
                Log.e("s",""+s);
                in.close();
                /**
                                 * 获取响应码  200=成功
                                 * 当响应成功，获取响应的流  
                                 */
                int res = conn.getResponseCode();
                Log.e("res",""+res);
                if (res == 200) {
                    InputStream input = conn.getInputStream();
                    StringBuilder sbs = new StringBuilder();
                    int ss;
                    while ((ss = input.read()) != -1) {
                        sbs.append((char) ss);
                    }
                    result = true;
                    input.close();
                    Log.i(TAG, "result------------------>>" + result);
                }
            }else{
                //上传失败操作
                Log.e("空文件","kong");
            }
        }
        catch (IOException e) {
            Log.e("error",e.toString());
        }
        return result;
    }

    private static String getMIMEType(File file) {
        String fileName = file.getName();
        if (fileName.endsWith("png") || fileName.endsWith("PNG")) {
            return "image/png";
        } else {
            return "image/jpg";
        }

    }
}
