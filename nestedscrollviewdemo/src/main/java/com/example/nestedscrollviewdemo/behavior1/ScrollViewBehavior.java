package com.example.nestedscrollviewdemo.behavior1;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nestedscrollviewdemo.R;

/**
 * 向上滑动：
 * 只有当TextView滑动至屏幕外时，RecyclerView才能处理内部内容的滚动。
 * 向下滑动：
 * 当TextView已经被划出屏幕且RecylerView中的内容不能继续向下滑动时，那么就将TextView滑动至显示。否则RecyclerView单独处理内部内容的滚动。
 */
public class ScrollViewBehavior extends CoordinatorLayout.Behavior<TextView> {
    private RecyclerView recyclerView;

    public ScrollViewBehavior() {
    }

    public ScrollViewBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onLayoutChild(@NonNull CoordinatorLayout parent, @NonNull TextView child, int layoutDirection) {
        recyclerView = parent.findViewById(R.id.behavior1_recyclerview);
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View directTargetChild, @NonNull View target, int axes, int type) {
        // 只要竖直方向上就拦截
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedPreScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);

        if (target != recyclerView) {
            return;
        }
        int currentTop = child.getTop();
        int newTop = currentTop - dy;
        Log.e("ScrollViewBehavior", child.getTop() + "::" + dy + ":::" + child.getMeasuredHeight());
        if (dy > 0) {
            // 向上滑动
            if (newTop >= -child.getMeasuredHeight()) {
                // child 还没完全隐藏，此时child还要继续向上滑动
                consumed[1] = dy;
                ViewCompat.offsetTopAndBottom(child, -dy);
                coordinatorLayout.dispatchDependentViewsChanged(child);
            } else {
                // child 完全隐藏，此时child不需要继续向上滑动了
                Log.e("ScrollViewBehavior", child.getMeasuredHeight() + "::" + currentTop);
                consumed[1] = child.getMeasuredHeight() + currentTop;
                // 消耗多余的滚动
                ViewCompat.offsetTopAndBottom(child, -consumed[1]);
                coordinatorLayout.dispatchDependentViewsChanged(child);
            }
        }
        if (dy < 0) {
            // 向下滑动
            if (newTop <= 0 && !target.canScrollVertically(-1)) {
                // child 还没完全显示，此时child还要继续向下滑动
                consumed[1] = dy;
                ViewCompat.offsetTopAndBottom(child, -dy);
                coordinatorLayout.dispatchDependentViewsChanged(child);
            }
        }
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull TextView child, @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type, consumed);
//        Log.e("onNestedScroll", String.valueOf(dyUnconsumed));
        if (dyUnconsumed < 0) {
            // 表示已经向下滑动到头
            int currentTop = child.getTop();
            int newTop = currentTop - dyUnconsumed;
            if (newTop <= 0) {
                Log.e("onNestedScroll", "newTop <= 0");
                ViewCompat.offsetTopAndBottom(child, -dyUnconsumed);
            } else {
                Log.e("onNestedScroll", "newTop > 0");
                ViewCompat.offsetTopAndBottom(child, -currentTop);
            }
            coordinatorLayout.dispatchDependentViewsChanged(child);
        }
    }
}
