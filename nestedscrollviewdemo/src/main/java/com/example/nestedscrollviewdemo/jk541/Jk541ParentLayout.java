package com.example.nestedscrollviewdemo.jk541;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingChild3;
import androidx.core.view.NestedScrollingChildHelper;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.nestedscrollviewdemo.R;
import com.google.android.material.tabs.TabLayout;

public class Jk541ParentLayout extends LinearLayout implements NestedScrollingParent3, NestedScrollingChild3 {
    private NestedScrollingParentHelper parentHelper = new NestedScrollingParentHelper(this);
    private NestedScrollingChildHelper childHelper = new NestedScrollingChildHelper(this);
    private View view1;
    private View view2;
    private TabLayout view3;
    private ViewPager2 view4;

    public Jk541ParentLayout(Context context) {
        this(context, null);
    }

    public Jk541ParentLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Jk541ParentLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
        childHelper.setNestedScrollingEnabled(true);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LinearLayout.LayoutParams params = (LayoutParams) view4.getLayoutParams();
        params.height = getMeasuredHeight() - view3.getMeasuredHeight();
        view4.setLayoutParams(params);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        view1 = findViewById(R.id.jk541_view1);
        view2 = findViewById(R.id.jk541_view2);
        view3 = findViewById(R.id.jk541_tab_layout);
        view4 = findViewById(R.id.jk541_view_pager);
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        Log.e("Jk541ParentLayout >>> onNestedPreScroll", target + "::" + dy);
        if (Math.abs(getScrollY()) < view2.getHeight()) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        Log.e("Jk541ParentLayout >>> onNestedScroll", target + "::" + dyConsumed+"::"+dyUnconsumed);
        if(dyConsumed > 0){
            scrollBy(0,dyConsumed);
            consumed[1] = dyConsumed;
        }
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {
        parentHelper.onNestedScrollAccepted(child, target, axes, type);
    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {
        parentHelper.onStopNestedScroll(target, type);
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        //
    }

    @Override
    public boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY) {
        return false;
    }

    @Override
    public boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return parentHelper.getNestedScrollAxes();
    }

    @Override
    public void dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type, @NonNull int[] consumed) {
        childHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type, consumed);
    }

    @Override
    public boolean startNestedScroll(int axes, int type) {
        return childHelper.startNestedScroll(axes, type);
    }

    @Override
    public void stopNestedScroll(int type) {
        childHelper.stopNestedScroll(type);
    }

    @Override
    public boolean hasNestedScrollingParent(int type) {
        return childHelper.hasNestedScrollingParent(type);
    }

    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type) {
        return childHelper.dispatchNestedScroll(dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, offsetInWindow, type);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow, int type) {
        return childHelper.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow, type);
    }

    @Override
    public boolean isNestedScrollingEnabled() {
        return childHelper.isNestedScrollingEnabled();
    }

    @Override
    public void setNestedScrollingEnabled(boolean enabled) {
        childHelper.setNestedScrollingEnabled(enabled);
    }
}
