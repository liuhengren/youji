package neet.com.youjidemo.customWidget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import neet.com.youjidemo.R;

public class FootView extends LinearLayout {
    public FootView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public FootView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public FootView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context){
        LayoutInflater.from(context).inflate(R.layout.foot_refresh_layout, this, true);
    }

}
