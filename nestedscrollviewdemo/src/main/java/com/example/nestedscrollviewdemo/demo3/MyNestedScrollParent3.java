package com.example.nestedscrollviewdemo.demo3;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.view.NestedScrollingParent3;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyNestedScrollParent3 extends ViewGroup implements NestedScrollingParent3 {
    String TAG = "MyNestedScrollParent3";
    private NestedScrollingParentHelper parentHelper = new NestedScrollingParentHelper(this);

    public MyNestedScrollParent3(Context context) {
        super(context);
    }

    public MyNestedScrollParent3(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyNestedScrollParent3(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        getChildAt(0).layout(
                0,
                0,
                getChildAt(0).getMeasuredWidth(),
                getChildAt(0).getMeasuredHeight());
        getChildAt(1).layout(
                0,
                getChildAt(0).getMeasuredHeight(),
                getChildAt(1).getMeasuredWidth(),
                getChildAt(0).getMeasuredHeight() + getChildAt(1).getMeasuredHeight());
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        Log.d(TAG,"onNestedScroll >>>>>>\n"+"target:"+target+"\n"+"dxConsumed:"+dxConsumed+"\n"+"dyConsumed:"+dyConsumed+"\n"+"dxUnconsumed:"+dxUnconsumed+"\n"+"dyUnconsumed:"+dyUnconsumed+"\n"+"type:"+type+"\n"+"consumed:"+consumed);
//        parentHelper.onStopNestedScroll(target, type);
    }

    /**
     * 即将开始嵌套滑动，此时嵌套滑动尚未开始，由子控件的 startNestedScroll 方法调用
     *
     * @param child  嵌套滑动对应的父类的子类(因为嵌套滑动对于的父控件不一定是一级就能找到的，可能挑了两级父控件的父控件，child的辈分>=target)
     * @param target 具体嵌套滑动的那个子类
     * @param axes   嵌套滑动支持的滚动方向
     * @param type   嵌套滑动的类型，有两种ViewCompat.TYPE_NON_TOUCH fling效果,ViewCompat.TYPE_TOUCH 手势滑动
     * @return true 表示此父类开始接受嵌套滑动，只有true时候，才会执行下面的 onNestedScrollAccepted 等操作
     */
    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        Log.d(TAG,"onStartNestedScroll >>>>>>\n"+"child:"+child+"\n"+"target:"+target+"\n"+"axes:"+axes+"\n"+"type:"+type);
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    /**
     * 当onStartNestedScroll返回为true时，也就是父控件接受嵌套滑动时，该方法才会调用
     *
     * @param child
     * @param target
     * @param axes
     * @param type
     */
    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {
        Log.d(TAG,"onNestedScrollAccepted >>>>>>\n"+"child:"+child+"\n"+"target:"+target+"\n"+"axes:"+axes+"\n"+"type:"+type);
        parentHelper.onNestedScrollAccepted(child, target, axes, type);
    }

    /**
     * 停止滑动
     *
     * @param target
     * @param type   滑动类型，ViewCompat.TYPE_NON_TOUCH fling效果,ViewCompat.TYPE_TOUCH 手势滑动
     */
    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {
        Log.d(TAG,"onStopNestedScroll >>>>>>\n"+"target:"+target+"\n"+"type:"+type);
        parentHelper.onStopNestedScroll(target, type);
    }

    /**
     * 在 onNestedPreScroll 中，父控件消耗一部分距离之后，剩余的再次给子控件，
     * 子控件消耗之后，如果还有剩余，则把剩余的再次还给父控件
     *
     * @param target       具体嵌套滑动的那个子类
     * @param dxConsumed   水平方向嵌套滑动的子控件滑动的距离(消耗的距离)
     * @param dyConsumed   垂直方向嵌套滑动的子控件滑动的距离(消耗的距离)
     * @param dxUnconsumed 水平方向嵌套滑动的子控件未滑动的距离(未消耗的距离)
     * @param dyUnconsumed 垂直方向嵌套滑动的子控件未滑动的距离(未消耗的距离)
     * @param type         滑动类型，ViewCompat.TYPE_NON_TOUCH fling效果,ViewCompat.TYPE_TOUCH 手势滑动
     */
    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {
        Log.d(TAG,"onNestedScroll少 >>>>>>\n"+"target:"+target+"\n"+"dxConsumed:"+dxConsumed+"\n"+"dyConsumed:"+dyConsumed+"\n"+"dxUnconsumed:"+dxUnconsumed+"\n"+"dyUnconsumed:"+dyUnconsumed+"\n"+"type:"+type);
//        parentHelper.onStopNestedScroll(target, type);
    }

    /**
     * 在子控件开始滑动之前，会先调用父控件的此方法，由父控件先消耗一部分滑动距离，并且将消耗的距离存在consumed中，传递给子控件
     * 在嵌套滑动的子View未滑动之前
     * ，判断父view是否优先与子view处理(也就是父view可以先消耗，然后给子view消耗）
     *
     * @param target   具体嵌套滑动的那个子类
     * @param dx       水平方向嵌套滑动的子View想要变化的距离
     * @param dy       垂直方向嵌套滑动的子View想要变化的距离 dy<0向下滑动 dy>0 向上滑动
     * @param consumed 这个参数要我们在实现这个函数的时候指定，回头告诉子View当前父View消耗的距离
     *                 consumed[0] 水平消耗的距离，consumed[1] 垂直消耗的距离 好让子view做出相应的调整
     * @param type     滑动类型，ViewCompat.TYPE_NON_TOUCH fling效果,ViewCompat.TYPE_TOUCH 手势滑动
     */
    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
        Log.d(TAG,"onNestedPreScroll >>>>>>\n"+"target:"+target+"\n"+"dx:"+dx+"\n"+"dy:"+dy+"\n"+"consumed:"+consumed+"type:"+type);
        if (dy == 0) {
            return;
        }

        // 完全隐藏/显示ImageView所需的滑动距离
        int needToMove;
        //ViewGroup在本次滑动事件中实际滑动的距离
        int toMove = 0;

        // 上滑，此时优先隐藏顶部的图片，余下的部分用于列表的滑动
        if (dy > 0) {
            needToMove = getChildAt(0).getMeasuredHeight() - dy;
        }
        // 下滑，此时优先将列表滑动至顶部，如已经在顶部则显示图片
        else {
            RecyclerView recyclerView = (RecyclerView) getChildAt(1);
            if (((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition() == 0
                    && recyclerView.getChildAt(0).getTop() == 0) {
                needToMove = 0 - getScrollY();
            } else {
                needToMove = 0;
            }
        }

        if (needToMove != 0) {
            // 如果提供的dy足够完全隐藏/显示ImageView，则实际滑动距离为 needToMove
            if (Math.abs(dy) >= Math.abs(needToMove)) {
                consumed[1] = needToMove;
                toMove = needToMove;
            } else {
                consumed[1] = dy;
                toMove = dy;
            }
        }

        // 如果实际滑动距离不为0
        if (toMove != 0) {
            scrollBy(0, toMove);
            getChildAt(0).setAlpha(1f - getScrollY() / getChildAt(0).getMeasuredHeight());
        }
    }

    /**
     * 表示我们只接收纵向的事件
     *
     * @return
     */
    @Override
    public int getNestedScrollAxes() {
        return parentHelper.getNestedScrollAxes();
//        return ViewCompat.SCROLL_AXIS_VERTICAL;
    }
}
