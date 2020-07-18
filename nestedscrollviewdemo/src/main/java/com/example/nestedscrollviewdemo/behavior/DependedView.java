package com.example.nestedscrollviewdemo.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;

public class DependedView extends View {
    private float mLastX;
    private float mLastY;
    private final int mDragSlop;//最小的滑动距离

    public DependedView(Context context) {
        this(context, null);
    }

    public DependedView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DependedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public DependedView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mDragSlop = ViewConfiguration.get(context).getScaledTouchSlop();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                mLastX = event.getX();
                mLastY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) (event.getX() - mLastX);
                int dy = (int) (event.getY() - mLastY);
                if(Math.abs(dx) > mDragSlop || Math.abs(dy) > mDragSlop){
                    ViewCompat.offsetTopAndBottom(this,dy);
                    ViewCompat.offsetLeftAndRight(this,dx);
                }
                mLastY = event.getY();
                mLastX = event.getX();
                break;
            default:
                break;
        }
        return true;
    }
}
