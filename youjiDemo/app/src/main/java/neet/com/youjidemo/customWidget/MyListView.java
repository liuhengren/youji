package neet.com.youjidemo.customWidget;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.NestedScrollingChildHelper;
import android.util.AttributeSet;
import android.widget.ListView;
import android.support.v4.view.NestedScrollingChild;

public class MyListView extends ListView implements NestedScrollingChild {

    private final NestedScrollingChildHelper mScrollChildHelper;

    public MyListView(Context context, AttributeSet attrs, NestedScrollingChildHelper mScrollChildHelper) {
        super(context, attrs);
        this.mScrollChildHelper = mScrollChildHelper;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            setNestedScrollingEnabled(true);
        }
    }


    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        mScrollChildHelper.setNestedScrollingEnabled(enabled);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return  mScrollChildHelper.isNestedScrollingEnabled();
    }

    @Override
    public boolean startNestedScroll(int axes) {
        return mScrollChildHelper.startNestedScroll(axes);
    }

    @Override
    public void stopNestedScroll() {
        mScrollChildHelper.stopNestedScroll();
    }

    @Override
    public boolean hasNestedScrollingParent() {
        return mScrollChildHelper.hasNestedScrollingParent();
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed,
                                        int dyUnconsumed, int[] offsetInWindow) {
        return mScrollChildHelper.dispatchNestedScroll(dxConsumed, dyConsumed,
                dxUnconsumed, dyUnconsumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        return mScrollChildHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return mScrollChildHelper.dispatchNestedFling(velocityX, velocityY, consumed);
    }

    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return mScrollChildHelper.dispatchNestedPreFling(velocityX, velocityY);
    }


}
