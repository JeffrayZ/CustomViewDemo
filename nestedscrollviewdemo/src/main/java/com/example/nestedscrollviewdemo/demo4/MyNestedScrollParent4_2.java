package com.example.nestedscrollviewdemo.demo4;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;

import com.example.nestedscrollviewdemo.R;

import java.util.Arrays;

public class MyNestedScrollParent4_2 extends LinearLayout implements NestedScrollingParent3 {
    private View top;
    private View bottom;
    private View center;
    private int mTopViewHeight;
    private NestedScrollingParentHelper helper = new NestedScrollingParentHelper(this);

    public MyNestedScrollParent4_2(Context context) {
        super(context);
        setOrientation(VERTICAL);
    }

    public MyNestedScrollParent4_2(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
    }

    public MyNestedScrollParent4_2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(VERTICAL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        ViewGroup.LayoutParams params = bottom.getLayoutParams();
        params.height = getMeasuredHeight() - center.getMeasuredHeight();
        bottom.setLayoutParams(params);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        top = findViewById(R.id.iv_head_image);
        center = findViewById(R.id.tab_layout);
        bottom = findViewById(R.id.view_pager);
    }

    /**
     * 嵌套滑动的子控件在滑动之后，判断父控件是否继续处理（也就是父消耗一定距离后，子再消耗，最后判断父消耗不）
     *
     * @param target       具体嵌套滑动的那个子类
     * @param dxConsumed   水平方向嵌套滑动的子控件滑动的距离(target 消耗的距离)
     * @param dyConsumed   垂直方向嵌套滑动的子控件滑动的距离(target 消耗的距离)
     * @param dxUnconsumed 水平方向嵌套滑动的子控件未滑动的距离(target 未消耗的距离)
     * @param dyUnconsumed 垂直方向嵌套滑动的子控件未滑动的距离(target 未消耗的距离)
     */
    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        Log.e("onNestedScroll 7参数", target + "::" + dxConsumed + "::" + dyConsumed + "::" + dxUnconsumed + "::" + dyUnconsumed + "::" + type+"::"+ Arrays.toString(consumed));
        // 当我们处理了onNestedPreScroll方法后，我们还需要处理onNestedScroll方法。
        // 因为根据嵌套滑动机制，当父控件预处理后，子控件会再消耗剩余的距离，如果子控件消耗后，还有剩余的距离。
        // 那么就又会传递给父控件。也就是会走onNestedScroll方法。
        // dyUnconsumed 子View未消耗的距离
        if (dyUnconsumed < 0) {
            // 表示子View已经向下滑动到头
            scrollBy(0, dyUnconsumed);
            // 父控件消耗的距离，好让子控件做出调整
            consumed[1] = dyUnconsumed;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mTopViewHeight = top.getMeasuredHeight();
    }

    @Override
    public void scrollTo(int x, int y) {
        Log.e("scrollTo(int x, int y)",x+"::"+y);
        if (y < 0) {
            y = 0;
        }
        if (y > top.getMeasuredHeight()) {
            y = top.getMeasuredHeight();
        }
        super.scrollTo(x, y);
    }

    /**
     * 有嵌套滑动到来了，判断父控件是否接受嵌套滑动
     *
     * @param child            嵌套滑动对应的父类的子类(因为嵌套滑动对于的父控件不一定是一级就能找到的，可能挑了两级父控件的父控件，child的辈分>=target)
     * @param target           具体嵌套滑动的那个子类
     * @param axes 支持嵌套滚动轴。水平方向，垂直方向，或者不指定
     * @return 父控件是否接受嵌套滑动， 只有接受了才会执行剩下的嵌套滑动方法
     */
    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        Log.e("onStartNestedScroll",child+":::"+target);
        return (axes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
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
        Log.e("onNestedScroll 6参数", target + "::" + dxConsumed + "::" + dyConsumed + "::" + dxUnconsumed + "::" + dyUnconsumed + "::" + type);
        // 当我们处理了onNestedPreScroll方法后，我们还需要处理onNestedScroll方法。
        // 因为根据嵌套滑动机制，当父控件预处理后，子控件会再消耗剩余的距离，如果子控件消耗后，还有剩余的距离。
        // 那么就又会传递给父控件。也就是会走onNestedScroll方法。
        // dyUnconsumed 子View未消耗的距离
        if (dyUnconsumed < 0) {
            // 表示子View已经向下滑动到头
            scrollBy(0, dyUnconsumed);
        }
    }

    /**
     * 在嵌套滑动的子控件未滑动之前，判断父控件是否优先与子控件处理(也就是父控件可以先消耗，然后给子控件消耗）
     *
     * @param target   具体嵌套滑动的那个子类
     * @param dx       水平方向嵌套滑动的子控件想要变化的距离 dx<0 向右滑动 dx>0 向左滑动
     * @param dy       垂直方向嵌套滑动的子控件想要变化的距离 dy<0 向下滑动 dy>0 向上滑动
     * @param consumed 这个参数要我们在实现这个函数的时候指定，回头告诉子控件当前父控件消耗的距离
     *                 consumed[0] 水平消耗的距离，consumed[1] 垂直消耗的距离 好让子控件做出相应的调整
     */
    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        Log.e("onNestedPreScroll 5参数", target + "::" + dx + "::" + dy + "::" + Arrays.toString(consumed) + "::" + type);
        Log.e("onNestedPreScroll 5参数", getScrollY() + "::" + top.getHeight());

        // 这里不管手势滚动还是fling都处理
        // 如果子view欲向上滑动，则先交给父view滑动
        boolean hideTop = dy > 0 && getScrollY() < mTopViewHeight;
        // 如果子view欲向下滑动，必须要子view不能向下滑动后，才能交给父view滑动
        // 我们通过调用View的canScrollVertically(int direction)方法来判断是否能够向下滑动，
        // 其中:当direcation为负数时，是检查对应View是否能够向下滑动，能，返回为true，反之返回false。
        // 当direcation为正数时，是检查对应View是否能够向上滑动，能，返回为true,反之返回false。
        boolean showTop = dy < 0 && getScrollY() >= 0 && !target.canScrollVertically(-1);
        if (hideTop || showTop) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    /**
     * 当子控件产生fling滑动时，判断父控件是否处拦截fling，如果父控件处理了fling，那子控件就没有办法处理fling了。
     * 为了让子控件也处理fling，我们需要在onNestedPreFling方法中返回false。
     * 因为在嵌套滑动机制中，如果该方法返回true,那么子控件就没有机会处理fling了。
     *
     * @param target    具体嵌套滑动的那个子类
     * @param velocityX 水平方向上的速度 velocityX > 0  向左滑动，反之向右滑动
     * @param velocityY 竖直方向上的速度 velocityY > 0  向上滑动，反之向下滑动
     * @return 父控件是否拦截该fling
     */
    @Override
    public boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY) {
        // 自己判断是否处理
        return false;
    }

    /**
     * 当父控件不拦截该fling,那么子控件会将fling传入父控件
     * 为了让子控件也处理fling，我们需要在onNestedPreFling方法中返回false。
     * 因为在嵌套滑动机制中，如果该方法返回true,那么子控件就没有机会处理fling了。
     *
     * @param target    具体嵌套滑动的那个子类
     * @param velocityX 水平方向上的速度 velocityX > 0  向左滑动，反之向右滑动
     * @param velocityY 竖直方向上的速度 velocityY > 0  向上滑动，反之向下滑动
     * @param consumed  子控件是否可以消耗该fling，也可以说是子控件是否消耗掉了该fling
     * @return 父控件是否消耗了该fling
     */
    @Override
    public boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed) {
        // 自己处理逻辑
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return helper.getNestedScrollAxes();
    }
}
