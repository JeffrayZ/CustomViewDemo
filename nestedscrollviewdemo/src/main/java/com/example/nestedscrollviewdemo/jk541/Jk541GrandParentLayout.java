package com.example.nestedscrollviewdemo.jk541;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.nestedscrollviewdemo.R;
import com.google.android.material.tabs.TabLayout;

public class Jk541GrandParentLayout extends LinearLayout implements NestedScrollingParent3 {
    private NestedScrollingParentHelper helper = new NestedScrollingParentHelper(this);
    private View view1;
    private View view2;
    private ViewPager2 viewPager2;
    private TabLayout tabLayout;

    public Jk541GrandParentLayout(Context context) {
        this(context, null);
    }

    public Jk541GrandParentLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Jk541GrandParentLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        LinearLayout.LayoutParams params = (LayoutParams) viewPager2.getLayoutParams();
        params.height = getMeasuredHeight() - tabLayout.getMeasuredHeight();
        viewPager2.setLayoutParams(params);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        view1 = findViewById(R.id.jk541_view1);
        view2 = findViewById(R.id.jk541_view2);
        viewPager2 = findViewById(R.id.jk541_view_pager);
        tabLayout = findViewById(R.id.jk541_tab_layout);
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
//        Log.e("Jk541GrandParentLayout >>> onNestedPreScroll", String.valueOf(nestedScrollView.getScrollY()));
//        Log.e("Jk541GrandParentLayout >>> onNestedPreScroll", String.valueOf(nestedScrollView.getTop()));
//        Log.e("Jk541GrandParentLayout >>> onNestedPreScroll", String.valueOf(view2.getTop()));
//        Log.e("Jk541GrandParentLayout >>> onNestedPreScroll", String.valueOf(getScrollY()));
//        Log.e("Jk541GrandParentLayout >>> onNestedPreScroll", String.valueOf(view1.getTop()));
        Log.e("Jk541GrandParentLayout >>> onNestedPreScroll", target + "::" + dy);
//        if(dy > 0){
//            // 上滑
//            if(nestedScrollView.getScrollY() > 0 && ){
//
//            }
//        }

//        scrollBy(0,dx);
//        consumed[1] = dy;
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        Log.e("Jk541GrandParentLayout >>> onNestedScroll", target + "::" + dyConsumed);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        Log.e("Jk541GrandParentLayout >>> onStartNestedScroll", String.valueOf(target instanceof RecyclerView));
        Log.e("Jk541GrandParentLayout >>> onStartNestedScroll", child + "::" + target);
        return target instanceof RecyclerView;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {
        helper.onNestedScrollAccepted(child, target, axes, type);
    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {
        helper.onStopNestedScroll(target, type);
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
        return helper.getNestedScrollAxes();
    }

}
