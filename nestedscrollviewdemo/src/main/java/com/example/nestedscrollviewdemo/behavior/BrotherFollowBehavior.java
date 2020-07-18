package com.example.nestedscrollviewdemo.behavior;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

public class BrotherFollowBehavior extends CoordinatorLayout.Behavior<View> {
    public BrotherFollowBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        //判断依赖的是否是DependedView
        return dependency instanceof DependedView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {
        //如果DependedView的位置、大小改变，跟随小弟始终在DependedView下面
        child.setY(dependency.getBottom() + 50);
        child.setX(dependency.getX());
        return true;
    }
}
