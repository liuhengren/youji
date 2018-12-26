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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;

import neet.com.youjidemo.MainActivity;
import neet.com.youjidemo.Presenter.ImagePresenter;
import neet.com.youjidemo.Presenter.SharePresenter;
import neet.com.youjidemo.R;
import neet.com.youjidemo.bean.ShowDynamicInAll;
import neet.com.youjidemo.bean.UserDateApplication;
import neet.com.youjidemo.command.UploadUtil;
import neet.com.youjidemo.view.IView.IShareView;

public class ShareActivity extends AppCompatActivity implements View.OnClickListener ,IShareView {
    private int SELECT_PHOTO = 200;
    private Button  upload;
    private ImageButton selectImage;
    private String picPath = null;
    private Uri uri = null;
    private String result;
    private UserDateApplication userDateApplication;
    private String img_src;
    private EditText dynmaictext;
    private boolean b;
    private SharePresenter sharePresenter;

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
        sharePresenter=new SharePresenter(this);
        findView();
        selectImage.setOnClickListener(this);
        upload.setOnClickListener(this);
        userDateApplication=(UserDateApplication)getApplication();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select_image:
                /*** * 这个是调用android内置的intent，来过滤图片文件 ，同时也可以过滤其他的 */
                ImagePresenter.selectImage(ShareActivity.this);
                break;
            case R.id.btn_share_upload:
                if (img_src == null) {
                    Toast.makeText(ShareActivity.this, "请选择图片！", Toast.LENGTH_LONG).show();
                } else {
                    sharePresenter.snedText();
                }
                break;
            default:
                break;
        }
    }

    private void findView(){
        selectImage = findViewById(R.id.select_image);
        upload = findViewById(R.id.btn_share_upload);
        dynmaictext=findViewById(R.id.et_share_passage);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 200:
                switch (resultCode) {
                    case RESULT_OK:
                        Log.e("data:",""+data.getData());
                        img_src = ImagePresenter.getImageSrc(ShareActivity.this, data,selectImage);
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

    @Override
    public void showMessage(String msg) {
        Toast.makeText(ShareActivity.this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void startUpImg(int dynamic_id) {
        b = ImagePresenter.uploadImage(img_src, ShareActivity.this, dynamic_id);
    }

    @Override
    public int getmuserId() {
        return userDateApplication.getUser().getUser_id();
    }

    @Override
    public String getTextOnDynamic() {
        return dynmaictext.getText().toString();
    }

    @Override
    public int getPartitionId() {
        return 0;
    }

    @Override
    public String getAddress() {
        return userDateApplication.getUser().getUser_address();
    }

    @Override
    public boolean getResultofDynamic() {
        return b;
    }

    @Override
    public void toManinActivity() {
            Intent intent = new Intent(ShareActivity.this, MainActivity.class);
            intent.putExtra("flag","flush");
            startActivity(intent);
    }
}