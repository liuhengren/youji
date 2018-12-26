package neet.com.youjidemo.view;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import neet.com.youjidemo.Presenter.ImagePresenter;
import neet.com.youjidemo.Presenter.SharePresenter;
import neet.com.youjidemo.R;
import neet.com.youjidemo.bean.UserDateApplication;

public class TouxiangActivity extends AppCompatActivity implements View.OnClickListener {

    private int SELECT_PHOTO = 200;
    private Button upload;
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
        setContentView(R.layout.activity_touxiang);
        findView();
        selectImage.setOnClickListener(this);
        upload.setOnClickListener(this);
        userDateApplication=(UserDateApplication)getApplication();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.select_image:
                /*** 这个是调用android内置的intent，来过滤图片文件 ，同时也可以过滤其他的 */
                ImagePresenter.selectImage(TouxiangActivity.this);
                break;
            case R.id.btn_share_upload:
                if (img_src == null) {
                    Toast.makeText(TouxiangActivity.this, "请选择图片！", Toast.LENGTH_LONG).show();
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
                        img_src = ImagePresenter.getImageSrc(TouxiangActivity.this, data,selectImage);
                        break;
                }
                break;
        }
    }
}
