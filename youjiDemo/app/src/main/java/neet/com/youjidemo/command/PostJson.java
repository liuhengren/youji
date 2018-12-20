package neet.com.youjidemo.command;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostJson {
    public static boolean PostDynamicToSever(JSONObject object,String url){
        String content=String.valueOf(object);
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

}
