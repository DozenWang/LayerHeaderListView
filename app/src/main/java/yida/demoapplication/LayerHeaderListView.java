package yida.demoapplication;

import android.app.Activity;
import android.content.Context;

import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;

public class LayerHeaderListView extends ListView implements
        AbsListView.OnScrollListener {

    private FrameLayout mHeaderContainer;
    private int mHeaderHeight;
    private ImageView mHeaderImage;

    public LayerHeaderListView(Context context) {
        super(context);
        init(context);
    }

    public LayerHeaderListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public LayerHeaderListView(Context context, AttributeSet attr, int style) {
        super(context, attr, style);
        init(context);
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
        setOnScrollListener(this);
    }

    public ImageView getHeaderView() {
        return mHeaderImage;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
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
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    public void setHeaderViewSize(int width, int height) {
        ViewGroup.LayoutParams layoutParams = mHeaderContainer.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(width, height);
        }
        layoutParams.width = width;
        layoutParams.height = height;
        mHeaderContainer.setLayoutParams(layoutParams);
        mHeaderHeight = height;
    }


}
