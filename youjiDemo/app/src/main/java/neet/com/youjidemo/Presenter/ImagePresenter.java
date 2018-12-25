package neet.com.youjidemo.Presenter;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.CursorLoader;
import android.text.LoginFilter;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import neet.com.youjidemo.R;
import neet.com.youjidemo.command.UploadUtil;
import neet.com.youjidemo.view.ShareActivity;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
import top.zibin.luban.OnRenameListener;

public class ImagePresenter extends FragmentActivity {
    private static int SELECT_PHOTO=200;


    /**从相册获取图片*/
    public static int selectImage(Activity context) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        context.startActivityForResult(intent,SELECT_PHOTO);
        return 1;
    }

    /**获取路径*/
    public static String getImageSrc(Context context, Intent data, ImageView selectImage){
        String img_src;
        Uri uri = data.getData();
        img_src = uri.getPath();//这是本机的图片路径
        ContentResolver cr = context.getContentResolver();
        try {
            Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
            /* 将Bitmap设定到ImageView */
            selectImage.setImageBitmap(bitmap);
            String[] proj = {MediaStore.Images.Media.DATA};
            CursorLoader loader = new CursorLoader(context, uri, proj, null, null, null);
            Cursor cursor = loader.loadInBackground();
            if (cursor != null) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                img_src = cursor.getString(column_index);//图片实际路径

            }
            cursor.close();

        } catch (FileNotFoundException e) {
            Log.e("Exception", e.getMessage(), e);
        }
        Log.e("img_src",""+img_src);
        return img_src;
    }

    /**上传*/
    public static void uploadImage(final String imgSrc,Context context){

        File file = new File(imgSrc);
        Luban.with(context)
                .load(file)
                .ignoreBy(100)
                .setTargetDir("/storage/emulated/0")
                .setRenameListener(new OnRenameListener() {
                    @Override
                    public String rename(String filePath) {
                        return "test123.jpg";
                    }
                })
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                        Log.e("start","1");
                    }

                    @Override
                    public void onSuccess(final File file) {
                        Log.e("onSuccess","1");

                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                                String uploadurl = "http://10.222.184.38:8080/youjiServer/?username=zhangsan";
                                try {
                                    Log.e("fileLength",file.length()+"");
                                    String result = UploadUtil.uploadImage(file, uploadurl);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("onError","1");
                    }
                }).launch();



    }
}
