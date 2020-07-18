package com.example.nestedscrollviewdemo;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.NestedScrollingParent3;

public class CusParent implements NestedScrollingParent3 {


    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {

    }

    /**
     * 当子View开始滑动时，会触发这个方法，判断接下来是否进行嵌套滑动，
     * 返回false，则表示不使用嵌套滑动
     * * type:
     *      *      //表示当前事件是由用户手指触摸产生的
     *      *     public static final int TYPE_TOUCH = 0;
     *      *
     *      *     //表示当前事件不是用户手指触摸产生的，一般是fling
     *      *     public static final int TYPE_NON_TOUCH = 1;
     */
    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        return false;
    }

    /**
     * onStartNestedScroll如果返回true，那么接下来就会调用这个方法
     * 用来做一些初始化操作，一般可以忽略
     * * type:
     *      *      //表示当前事件是由用户手指触摸产生的
     *      *     public static final int TYPE_TOUCH = 0;
     *      *
     *      *     //表示当前事件不是用户手指触摸产生的，一般是fling
     *      *     public static final int TYPE_NON_TOUCH = 1;
     */
    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {

    }

    /**
     * 嵌套滑动结束时会触发这个方法
     * * type:
     *      *      //表示当前事件是由用户手指触摸产生的
     *      *     public static final int TYPE_TOUCH = 0;
     *      *
     *      *     //表示当前事件不是用户手指触摸产生的，一般是fling
     *      *     public static final int TYPE_NON_TOUCH = 1;
     * @param target
     * @param type
     */
    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {

    }

    /***
     * 子View滑动时会触发这个方法，dyConsumed代表子View滑动的距离，dyUnconsumed代表子View本次滑动未消耗的距离，
     * 比如RecyclerView滑到了边界，那么会有一部分y未消耗掉
     * * type:
     *      *      //表示当前事件是由用户手指触摸产生的
     *      *     public static final int TYPE_TOUCH = 0;
     *      *
     *      *     //表示当前事件不是用户手指触摸产生的，一般是fling
     *      *     public static final int TYPE_NON_TOUCH = 1;
     * @param target
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     * @param type
     */
    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {

    }

    /****
     * 子View开始滑动时，会触发这个回调，dy表示滑动的y距离，consumed数组代表父View要消耗的距离,
     * 假如consumed[1] = dy,那么子View就不会滑动了
     * type:
     *      //表示当前事件是由用户手指触摸产生的
     *     public static final int TYPE_TOUCH = 0;
     *
     *     //表示当前事件不是用户手指触摸产生的，一般是fling
     *     public static final int TYPE_NON_TOUCH = 1;
     * @param target
     * @param dx
     * @param dy
     * @param consumed
     * @param type
     */
    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {

    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes) {
        return false;
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes) {

    }

    @Override
    public void onStopNestedScroll(@NonNull View target) {

    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {

    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed) {

    }

    /****
     * 当子View fling时，会触发这个回调,consumed代表速度是否被子View消耗掉，
     * 比如RecyclerView滑动到了边界，那么它显然没法消耗本次的fling
     * @param target
     * @param velocityX
     * @param velocityY
     * @param consumed
     * @return
     */
    @Override
    public boolean onNestedFling(@NonNull View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    /****
     * 当子View要开始fling时，会先询问父View是否要拦截本次fling，返回true表示要拦截，那么子View就不会惯性滑动了
     * @param target
     * @param velocityX
     * @param velocityY
     * @return
     */
    @Override
    public boolean onNestedPreFling(@NonNull View target, float velocityX, float velocityY) {
        return false;
    }

    /****
     * 表示目前正在进行的嵌套滑动的方向，值有
     * ViewCompat.SCROLL_AXIS_HORIZONTAL
     * 或者 ViewCompat.SCROLL_AXIS_VERTICAL
     * 或者 SCROLL_AXIS_NONE
     * @return
     */
    @Override
    public int getNestedScrollAxes() {
        return 0;
    }
}
