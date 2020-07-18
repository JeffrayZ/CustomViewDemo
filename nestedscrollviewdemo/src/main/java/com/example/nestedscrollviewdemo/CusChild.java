package com.example.nestedscrollviewdemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.NestedScrollingChild3;

public class CusChild implements NestedScrollingChild3 {
    @Override
    public void dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type, @NonNull int[] consumed) {

    }

    /****
     * * type:
     *      *      //表示当前事件是由用户手指触摸产生的
     *      *     public static final int TYPE_TOUCH = 0;
     *      *
     *      *     //表示当前事件不是用户手指触摸产生的，一般是fling
     *      *     public static final int TYPE_NON_TOUCH = 1;
     * @param axes
     * @param type
     * @return
     */
    @Override
    public boolean startNestedScroll(int axes, int type) {
        return false;
    }

    /****
     * * type:
     *      *      //表示当前事件是由用户手指触摸产生的
     *      *     public static final int TYPE_TOUCH = 0;
     *      *
     *      *     //表示当前事件不是用户手指触摸产生的，一般是fling
     *      *     public static final int TYPE_NON_TOUCH = 1;
     * @param type
     */
    @Override
    public void stopNestedScroll(int type) {

    }

    /***
     * * type:
     *      *      //表示当前事件是由用户手指触摸产生的
     *      *     public static final int TYPE_TOUCH = 0;
     *      *
     *      *     //表示当前事件不是用户手指触摸产生的，一般是fling
     *      *     public static final int TYPE_NON_TOUCH = 1;
     * @param type
     * @return
     */
    @Override
    public boolean hasNestedScrollingParent(int type) {
        return false;
    }

    /***
     * * type:
     *      *      //表示当前事件是由用户手指触摸产生的
     *      *     public static final int TYPE_TOUCH = 0;
     *      *
     *      *     //表示当前事件不是用户手指触摸产生的，一般是fling
     *      *     public static final int TYPE_NON_TOUCH = 1;
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     * @param offsetInWindow
     * @param type
     * @return
     */
    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow, int type) {
        return false;
    }

    /***
     * * type:
     *      *      //表示当前事件是由用户手指触摸产生的
     *      *     public static final int TYPE_TOUCH = 0;
     *      *
     *      *     //表示当前事件不是用户手指触摸产生的，一般是fling
     *      *     public static final int TYPE_NON_TOUCH = 1;
     * @param dx
     * @param dy
     * @param consumed
     * @param offsetInWindow
     * @param type
     * @return
     */
    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow, int type) {
        return false;
    }

    /****
     * //设置当前子View是否支持嵌套滑动
     * @param enabled
     */
    @Override
    public void setNestedScrollingEnabled(boolean enabled) {

    }

    /***
     * //当前子View是否支持嵌套滑动
     * @return
     */
    @Override
    public boolean isNestedScrollingEnabled() {
        return false;
    }

    /***
     * //开始嵌套滑动，对应Parent的onStartNestedScroll
     * @param axes
     * @return
     */
    @Override
    public boolean startNestedScroll(int axes) {
        return false;
    }

    /***
     * //停止本次嵌套滑动，对应Parent的onStopNestedScroll
     */
    @Override
    public void stopNestedScroll() {

    }

    /****
     * //true表示这个子View有一个支持嵌套滑动的父View
     * @return
     */
    @Override
    public boolean hasNestedScrollingParent() {
        return false;
    }

    /****
     * //通知父View子View开始滑动了，对应父View的onNestedScroll方法
     * @param dxConsumed
     * @param dyConsumed
     * @param dxUnconsumed
     * @param dyUnconsumed
     * @param offsetInWindow
     * @return
     */
    @Override
    public boolean dispatchNestedScroll(int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, @Nullable int[] offsetInWindow) {
        return false;
    }

    /***
     * //通知父View即将开始滑动了，对应父View的onNestedPreScroll方法
     * @param dx
     * @param dy
     * @param consumed
     * @param offsetInWindow
     * @return
     */
    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, @Nullable int[] consumed, @Nullable int[] offsetInWindow) {
        return false;
    }

    /***
     *  //通知父View开始Fling了，对应Parent的onNestedFling方法
     * @param velocityX
     * @param velocityY
     * @param consumed
     * @return
     */
    @Override
    public boolean dispatchNestedFling(float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    /****
     * //通知父View要开始fling了，对应Parent的onNestedPreFling方法
     * @param velocityX
     * @param velocityY
     * @return
     */
    @Override
    public boolean dispatchNestedPreFling(float velocityX, float velocityY) {
        return false;
    }
}
