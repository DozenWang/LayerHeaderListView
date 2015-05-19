package yida.demoapplication;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

public class LayerHeaderListView extends ListView implements
        AbsListView.OnScrollListener {

    private FrameLayout mHeaderContainer;
    private int mHeaderHeight;
    private ImageView mHeaderImage;

    private OnScrollListener mOnScrollListener;

    public LayerHeaderListView(Context paramContext) {
        super(paramContext);
        init(paramContext);
    }

    public LayerHeaderListView(Context paramContext,
                               AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        init(paramContext);
    }

    public LayerHeaderListView(Context paramContext,
                               AttributeSet paramAttributeSet, int paramInt) {
        super(paramContext, paramAttributeSet, paramInt);
        init(paramContext);
    }

    private void init(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(displayMetrics);
        mHeaderContainer = new FrameLayout(context);
        mHeaderImage = new ImageView(context);
        setHeaderViewSize(displayMetrics.widthPixels, (int) (9 * (displayMetrics.widthPixels / 16)));
        FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        localLayoutParams.gravity = 80;
        mHeaderContainer.addView(mHeaderImage);
        addHeaderView(mHeaderContainer);
        super.setOnScrollListener(this);
    }

    public ImageView getHeaderView() {
        return mHeaderImage;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        super.onLayout(changed, l, t, r, b);
        if (mHeaderHeight == 0) {
            mHeaderHeight = mHeaderContainer.getHeight();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        float f = mHeaderHeight - mHeaderContainer.getBottom();
        if ((f > 0) && (f < mHeaderHeight)) {
            int factor = (int) (0.65 * f);
            mHeaderImage.scrollTo(0, -factor);
        } else if (mHeaderImage.getScrollY() != 0) {
            mHeaderImage.scrollTo(0, 0);

        }
        if (mOnScrollListener != null) {
            mOnScrollListener.onScroll(view, firstVisibleItem,
                    visibleItemCount, totalItemCount);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (mOnScrollListener != null) {
            mOnScrollListener.onScrollStateChanged(view, scrollState);
        }
    }

    public void setHeaderViewSize(int width, int height) {
        Object layoutParams = mHeaderContainer.getLayoutParams();
        if (layoutParams == null)
            layoutParams = new LayoutParams(width, height);
        ((ViewGroup.LayoutParams) layoutParams).width = width;
        ((ViewGroup.LayoutParams) layoutParams).height = height;
        mHeaderContainer.setLayoutParams((ViewGroup.LayoutParams) layoutParams);
        mHeaderHeight = height;
    }

    public void setOnScrollListener(
            OnScrollListener paramOnScrollListener) {
        mOnScrollListener = paramOnScrollListener;
    }

}
