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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

import neet.com.youjidemo.MainActivity;
import neet.com.youjidemo.R;
import neet.com.youjidemo.command.UploadUtil;

public class ShareActivity extends AppCompatActivity implements View.OnClickListener {

    private static String requestURL = "http://10.7.89.253:8080/AndroidServlet/";
    private Button  upload;
    private ImageButton selectImage;
    private String picPath = null;
    private Uri uri = null;

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
            /** 这个是Android内置的Intent，用来过滤图片文件，同时也可以过滤其他文件*/
            case R.id.select_image:
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,1);
                break;
            case R.id.btn_share_upload:
                if (picPath == null) {
                    Toast.makeText(ShareActivity.this, "请选择图片！", Toast.LENGTH_LONG).show();
                } else {
                    Async async = new Async();
                    async.execute();
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

    /**用一个异步任务类来处理网络操作*/
    public class Async extends AsyncTask {

        @Override
        protected Object doInBackground(Object[] objects) {
            final File file = new File(picPath);
            if (file != null) {
                UploadUtil.uploadFile(file, requestURL);
            }
            return requestURL;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            uri = data.getData();
            /** * 当选择的图片不为空的话，在获取到图片的途径 */
            Log.e("tag", "uri = " + uri);
            try {
                String[] pojo = {MediaStore.Images.Media.DATA};
                Cursor cursor = managedQuery(uri, pojo, null, null, null);
                if (cursor != null) {
                    ContentResolver cr = ShareActivity.this.getContentResolver();
                    int colunm_index = cursor
                            .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    String path = cursor.getString(colunm_index);
                    /***
                     * * 这里加这样一个判断主要是为了第三方的软件选择，比如：使用第三方的文件管理器的话，你选择的文件就不一定是图片了，
                     * * 这样的话，我们判断文件的后缀名 如果是图片格式的话，那么才可以
                     */
                    if (path.endsWith("jpg") || path.endsWith("png")) {
                        picPath = path;
                        Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                        selectImage.setImageBitmap(bitmap);
                    } else {
                        alert();
                    }
                } else {
                    alert();
                }
            } catch (Exception e) {
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**当上传图片不符合标准时，弹出提示*/
    private void alert() {
        Dialog dialog = new AlertDialog.Builder(this).setTitle("提示")
                .setMessage("您选择的不是有效的图片")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        picPath = null;
                    }
                }).create();
        dialog.show();
    }
}