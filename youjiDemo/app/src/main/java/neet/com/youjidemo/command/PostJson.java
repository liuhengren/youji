package neet.com.youjidemo.command;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostJson {
    public static int PostDynamicToSever(JSONObject object,String url){
        String content=String.valueOf(object);
        int id=0;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("User-Agent", "Fiddler");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Charset", "UTF-8");
            OutputStream os = connection.getOutputStream();
            os.write(content.getBytes());
            os.close();
            InputStream is=connection.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            String res=br.readLine();
            JSONObject jsonObject=new JSONObject(res);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return id;
    }
    public static boolean PostByUrl(String url){
        boolean ischeack=false;
        try {
            Log.e("url",url);
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("contentType","utf-8");
            InputStream is=connection.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            String res=br.readLine();
            Log.e("123",res);
            //服务器返回标志
            JSONObject jsonObject=new JSONObject(res);
            ischeack=jsonObject.getBoolean("res");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  ischeack;
    }
    public static boolean PostToSever(JSONObject object,String url){
        String content=String.valueOf(object);
        boolean b=false;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("User-Agent", "Fiddler");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Charset", "UTF-8");
            OutputStream os = connection.getOutputStream();
            os.write(content.getBytes());
            InputStream is=connection.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(is));
            String res=br.readLine();
            JSONObject jsonObject=new JSONObject(res);
            b=jsonObject.getBoolean("res");
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return b;
    }
}
