package neet.com.youjidemo.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;

import neet.com.youjidemo.MainActivity;
import neet.com.youjidemo.R;
import neet.com.youjidemo.command.UploadUtil;

public class ShareActivity extends AppCompatActivity implements View.OnClickListener {
    private int SELECT_PHOTO = 200;
    private static String requestURL = "http://10.7.89.245:8080/AndroidServlet/";
    private Button  upload;
    private ImageButton selectImage;
    private String picPath = null;
    private Uri uri = null;
    private String result;

    private String img_src;

    /**
     * 从相册选取图片
     */
    public void selectImg() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, SELECT_PHOTO);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        findView();
        selectImage.setOnClickListener(this);
        upload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select_image:
                /*** * 这个是调用android内置的intent，来过滤图片文件 ，同时也可以过滤其他的 */
                selectImg();
                break;
            case R.id.btn_share_upload:
                if (img_src == null) {
                    Toast.makeText(ShareActivity.this, "请选择图片！", Toast.LENGTH_LONG).show();
                } else {
                    uploadImage(img_src);
                }
                break;
            default:
                break;
        }
    }

    private void findView(){
        selectImage = findViewById(R.id.select_image);
        upload = findViewById(R.id.btn_share_upload);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 200:
                switch (resultCode) {
                    case RESULT_OK:
                        Uri uri = data.getData();
                        img_src = uri.getPath();//这是本机的图片路径

                        ContentResolver cr = getContentResolver();
                        try {
                            Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                            /* 将Bitmap设定到ImageView */
                            selectImage.setImageBitmap(bitmap);

                            String[] proj = {MediaStore.Images.Media.DATA};
                            CursorLoader loader = new CursorLoader(ShareActivity.this, uri, proj, null, null, null);
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

                        break;
                }
                break;

        }
    }

    /**当上传图片不符合标准时，弹出提示*/
    private void alert() {
        Dialog dialog = new AlertDialog.Builder(this).setTitle("提示")
                .setMessage("您选择的不是有效的图片")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        img_src = null;
                    }
                }).create();
        dialog.show();
    }


    public void uploadImage(String path) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                String uploadurl = "http://10.7.89.245:8080/UploadFile/AServlet?username=zhangsan";
                try {
                    File file = new File(img_src);
                    result = UploadUtil.uploadImage(file, uploadurl,ShareActivity.this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}