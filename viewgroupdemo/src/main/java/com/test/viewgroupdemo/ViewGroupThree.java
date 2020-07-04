package com.test.viewgroupdemo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

/**
 * @ProjectName: CustomViewDemo
 * @Package: com.test.viewgroupdemo
 * @ClassName: ViewGroupOne
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2020/7/3 10:25
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/3 10:25
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ViewGroupThree extends ViewGroup {
    private String TAG = "ViewGroupThree";
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
    private float mLastX;
    private float mLastY;
    private boolean isBeingDragged;
    // 总滑动距离
    private float mOffsetX;
    private ValueAnimator mAnimator;
    private float mOffsetPercent;
    private boolean isReordered;

    public ViewGroupThree(Context context) {
        super(context);
        init(context);
    }

    public ViewGroupThree(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ViewGroupThree(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public ViewGroupThree(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context) {
        paint.setColor(Color.parseColor("#02584F"));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(4f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < getChildCount(); i++) {
            canvas.drawLine((i + 1) * getWidth() / 4, 0, (i + 1) * getWidth() / 4, getHeight(), paint);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View child;
        for (int i = 0; i < getChildCount(); i++) {
            child = getChildAt(i);
            //获取基准线
            int baseLine = getBaselineByChild(child);
            //布局子View
            layoutChild(child, baseLine);
        }
    }

    private void layoutChild(View child, int baseLine) {
        LayoutParams lp = (LayoutParams) child.getLayoutParams();

        // 更新不透明度
        child.setAlpha(lp.alpha);
        //更新缩放比例
        child.setScaleX(lp.scale);
        child.setScaleY(lp.scale);

        // 获取子View测量宽高
        int childWidth = child.getMeasuredWidth();
        int childHeight = child.getMeasuredHeight();
        // 垂直的中心位置，即高度的一半
        int baseLineCenterY = getMeasuredHeight() / 2;
        // 根据基准线来定位水平上的位置
        int left = baseLine - childWidth / 2;
        int right = left + childWidth;
        // 垂直居中
        int top = baseLineCenterY - childHeight / 2;
        int bottom = top + childHeight;
        child.layout(left + getPaddingLeft(),
                top + getPaddingTop(),
                right - getPaddingRight(),
                bottom - getPaddingBottom());
    }

    private int getBaselineByChild11(View child) {
        int baseLine = 0;
        if (indexOfChild(child) == 0) {
            baseLine = getMeasuredWidth() / 4;
        } else if (indexOfChild(child) == 1) {
            baseLine = getMeasuredWidth() / 2 + getMeasuredWidth() / 4;
        } else {
            baseLine = getMeasuredWidth() / 2;
        }
        return baseLine;
    }

    private int getBaselineByChild(View child) {
        // 左边view的初始基准线
        int baseLineLeft = getMeasuredWidth() / 4;
        // 中间
        int baseLineCenter = getMeasuredWidth() / 2;
        // 右边
        int baseLineRight = getMeasuredWidth() / 2 + getMeasuredWidth() / 4;

        int baseLine = 0;

        LayoutParams params = (LayoutParams) child.getLayoutParams();

        switch (params.from) {
            case 0:
                if (params.to == 1) {
                    // 目的地是1，证明手指正在向左滑动，所以下面的mOffsetPercent是用负的
                    // 当前基准线 = 初始基准线 + 与目标基准线(现在是右边)的距离 * 滑动百分比
                    baseLine = (int) (baseLineLeft + ((baseLineRight - baseLineLeft) * -mOffsetPercent));
                } else if (params.to == 2) {
                    baseLine = (int) (baseLineLeft + ((baseLineCenter - baseLineLeft) * mOffsetPercent));
                } else {
                    baseLine = baseLineLeft;
                }
                break;
            case 1:
                if (params.to == 0) {
                    baseLine = (int) (baseLineRight + ((baseLineRight - baseLineLeft) * -mOffsetPercent));
                } else if (params.to == 2) {
                    baseLine = (int) (baseLineRight + ((baseLineRight - baseLineCenter) * mOffsetPercent));
                } else {
                    baseLine = baseLineRight;
                }
                break;
            case 2:
                if (params.to == 0) {
                    baseLine = (int) (baseLineCenter + ((baseLineCenter - baseLineLeft) * mOffsetPercent));
                } else if (params.to == 1) {
                    baseLine = (int) (baseLineCenter + ((baseLineRight - baseLineCenter) * mOffsetPercent));
                } else {
                    baseLine = baseLineCenter;
                }
                break;
            default:
                break;
        }
        return baseLine;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);

        setMeasuredDimension(width, height);
    }

    private int measureHeight(int heightMeasureSpec) {
        int height;
        int size = MeasureSpec.getSize(heightMeasureSpec);
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        } else {
            View view;
            LayoutParams params;
            int maxHeight = 0;
            for (int i = 0; i < getChildCount(); i++) {
                view = getChildAt(i);
                params = (LayoutParams) view.getLayoutParams();
                maxHeight = Math.max(maxHeight, view.getMeasuredHeight() + params.topMargin + params.bottomMargin);
            }
            height = maxHeight;
        }
        return height;
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (getChildCount() > 2) {
            // 直接拋异常，提示不能超过三个子View
            throw new IllegalStateException("can only contain 3 child!");
        }

        LayoutParams lp;
        if (!(params instanceof LayoutParams)) {
            lp = new LayoutParams(params);
        } else {
            lp = (LayoutParams) params;
        }
        if (getChildCount() < 2) {
            lp.alpha = 0.4f;
            lp.scale = 0.8f;
        } else {
            lp.alpha = 1f;
            lp.scale = 1f;
        }
        lp.from = index == -1 ? getChildCount() : index;
        super.addView(child, index, params);
    }

    private int measureWidth(int widthMeasureSpec) {
        int width = 0;
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        } else {
            View view;
            LayoutParams params;
            for (int i = 0; i < getChildCount(); i++) {
                view = getChildAt(i);
                params = (LayoutParams) view.getLayoutParams();
                width += view.getMeasuredWidth() + params.leftMargin + params.rightMargin;
            }
        }
        return width;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        float x = ev.getX();
        float y = ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = x - mLastX;
                float dy = y - mLastY;
                if (Math.abs(dx) > 10 || Math.abs(dy) > 10) {
                    mLastX = x;
                    mLastY = y;
                    //标记已开始拖拽
                    isBeingDragged = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                isBeingDragged = false;
                handleActionUp(x, y);
                break;
            default:
                break;
        }
        return isBeingDragged;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float dx = x - mLastX;
                mOffsetX += dx;
                onItemMove();
                break;
            case MotionEvent.ACTION_UP:
                isBeingDragged = false;
                handleActionUp(x, y);
                break;
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    /**
     * 选中动画
     */
    private void handleActionUp(float x, float y) {
        playFixingAnimation();
    }

    private void playFixingAnimation() {
        if (getChildCount() == 0) {
            return;
        }
        //起始点，就是当前的滑动距离
        float start = mOffsetX;
        float end;
        // 如果滑动的距离超过了宽度的一半，那么就顺势往那边走下去
        // 如果滑动百分比是正数，表示是向右滑了>50%，所以目的地就是宽度
        if (mOffsetPercent > 0.5) {
            end = getWidth();
        } else if (mOffsetPercent < -0.5) {
            end = -getWidth();
        } else {
            end = 0;
        }
        Log.e(TAG, "start=" + start + "::" + "end=" + end + ":::" + "mOffsetPercent=" + mOffsetPercent);
        startValueAnimator(start, end);
    }

    private void startValueAnimator(float start, float end) {
        if (start == end) {
            return;
        }
        if (mAnimator != null && mAnimator.isRunning()) {
            mAnimator.cancel();
        } else {
            mAnimator = ValueAnimator.ofFloat(start, end);
            mAnimator.setInterpolator(new DecelerateInterpolator());
            mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float currentValue = (float) animation.getAnimatedValue();
                    Log.e(TAG, "currentValue=" + currentValue);
                    // 更新滑动距离
                    mOffsetX = currentValue;
                    onItemMove();
                }
            });
        }
        mAnimator.start();
    }

    private void onItemMove() {
        // 更新滑动百分比
        mOffsetPercent = mOffsetX / getMeasuredWidth();
        //更新子View的出发点和目的地
        updateChildrenFromAndTo();
        // 更新子View的层级顺序
        updateChildrenOrder();
        // 更新子View的不透明度和缩放比例
        updateChildrenAlphaAndScale();
        // 请求重新布局
        requestLayout();
    }

    private void updateChildrenAlphaAndScale() {
        for (int i = 0; i < getChildCount(); i++) {
            updateAlphaAndScale(getChildAt(i));
        }
    }

    private void updateAlphaAndScale(View child) {
        LayoutParams params = (LayoutParams) child.getLayoutParams();
        // 用出发点来作为条件，而不是当前索引，因为如果使用当前索引的话，在交换顺序之后，就不正确了
        switch (params.from) {
            //最左边的子View
            case 0:
                //如果它目的地是最右边的话
                if (params.to == 1) {
                    //要把它放在最底，为了避免在移动过程中遮挡其他子View
                    setAsBottom(child);
                } else if (params.to == 2) {
                    // 根据滑动比例来计算出当前的透明度和缩放比例
                    params.alpha = (float) (0.4 + (1F - 0.4) * Math.abs(mOffsetPercent));
                    params.scale = (float) (0.8 + (1F - 0.8) * Math.abs(mOffsetPercent));
                }
                break;
            // 最右边的子View
            case 1:
                if (params.to == 0) {
                    //把它放在最底，避免在移动过程中遮挡其他子View
                    setAsBottom(child);
                } else if (params.to == 2) {
                    // 根据滑动比例来计算出当前的透明度和缩放比例
                    params.alpha = (float) (0.4 + (1F - 0.4) * Math.abs(mOffsetPercent));
                    params.scale = (float) (0.8 + (1F - 0.8) * Math.abs(mOffsetPercent));
                }
                break;
            //中间的子View
            case 2:
                //这里不用判断to了，因为无论向哪一边滑动，不透明度和缩放比例都是减少
                params.alpha = (float) (1F - (1F - 0.4) * Math.abs(mOffsetPercent));
                params.scale = (float) (1F - (1F - 0.8) * Math.abs(mOffsetPercent));
                break;
            default:
                break;
        }
    }

    private void setAsBottom(View child) {
        //获取child索引后跟0交换层级顺序
        exchangeOrder(indexOfChild(child), 0);
    }

    private void updateChildrenOrder() {
        if (Math.abs(mOffsetPercent) > 0.5) {
            // 如果滑动距离超过了ViewGroup宽度的一半，
            // 就把索引为1，2的子View交换顺序，并标记已经交换过
            if (!isReordered) {
                exchangeOrder(1, 2);
                isReordered = true;
            }
        } else {
            // 滑动距离没有超过宽度一半，即有可能是滑动超过一半然后滑动回来
            // 如果isReordered=true，就表示本次滑动已经交换过顺序
            // 所以要再次交换一下
            if (isReordered) {
                exchangeOrder(1, 2);
                isReordered = false;
            }
        }
    }

    private void exchangeOrder(int fromIndex, int toIndex) {
        //一样的就不用换了
        if (fromIndex == toIndex) {
            return;
        }
        //先获取引用
        View from = getChildAt(fromIndex);
        View to = getChildAt(toIndex);

        //分离出来
        detachViewFromParent(from);
        detachViewFromParent(to);

        //重新放回去，但是index互换了
        if (toIndex > getChildCount()) {
            toIndex = getChildCount();
        }
        if (fromIndex > getChildCount()) {
            fromIndex = getChildCount();
        }
        attachViewToParent(from, toIndex, from.getLayoutParams());
        attachViewToParent(to, fromIndex, to.getLayoutParams());

        //通知重绘，刷新视图
//        invalidate();
    }

    private void updateChildrenFromAndTo() {
        if (Math.abs(mOffsetPercent) >= 1) {
            // 重置标记
            isReordered = false;
            View child;
            for (int i = 0; i < getChildCount(); i++) {
                child = getChildAt(i);
                LayoutParams params = (LayoutParams) child.getLayoutParams();
                params.from = params.to;
            }
            // 处理溢出: 比如总宽度是100，现在是120，那么处理之后会变成20
            mOffsetX %= getMeasuredWidth();
            // 同理，这个是百分比
            mOffsetPercent %= 1;
        } else {
            View child;
            for (int i = 0; i < getChildCount(); i++) {
                child = getChildAt(i);
                LayoutParams params = (LayoutParams) child.getLayoutParams();
                if (params.from == 0) {
                    // 最左边的子view
                    if (mOffsetPercent > 0) {
                        // 右滑 终点是2
                        params.to = 2;
                    } else {
                        // 左滑 重点是1
                        params.to = 1;
                    }
                } else if (params.from == 1) {
                    // 最右边的view
                    if (mOffsetPercent > 0) {
                        // 右滑 终点是0
                        params.to = 0;
                    } else {
                        // 左滑 终点是2
                        params.to = 2;
                    }
                } else if (params.from == 2) {
                    // 中间的view
                    if (mOffsetPercent > 0) {
                        // 右滑 终点是1
                        params.to = 1;
                    } else {
                        // 左滑 终点是0
                        params.to = 0;
                    }
                }
            }
        }
    }

    /**
     * @param view   目标view
     * @param points 坐标点(x, y)
     * @return 坐标点是否在view范围内
     */
    private boolean pointInView(View view, float[] points) {
        // 像ViewGroup那样，先对齐一下Left和Top
        points[0] -= view.getLeft();
        points[1] -= view.getTop();
        // 获取View所对应的矩阵
        Matrix matrix = view.getMatrix();
        // 如果矩阵有应用过变换
        if (!matrix.isIdentity()) {
            // 反转矩阵
            matrix.invert(matrix);
            // 映射坐标点
            matrix.mapPoints(points);
        }
        //判断坐标点是否在view范围内
        return points[0] >= 0 && points[1] >= 0 && points[0] < view.getWidth() && points[1] < view.getHeight();
    }

    /**
     * 交换view的位置
     */
    public void moveToTop(View target) {
        // 先确定现在在哪个位置
        int startIndex = indexOfChild(target);
        Log.e(TAG, startIndex + "   startIndex");
        // 计算一共需要几次交换，就可到达最上面
        int count = getChildCount() - 1 - startIndex;
        Log.e(TAG, count + "   count");
        for (int i = 0; i < count; i++) {
            //更新索引
            int fromIndex = indexOfChild(target);
            //目标是它的上层
            int toIndex = fromIndex + 1;
            //获取需要交换位置的两个子View
            View from = target;
            View to = getChildAt(toIndex);

            //先把它们拿出来
            detachViewFromParent(toIndex);
            detachViewFromParent(fromIndex);

            //再放回去，但是放回去的位置(索引)互换了
            attachViewToParent(to, fromIndex, to.getLayoutParams());
            attachViewToParent(from, toIndex, from.getLayoutParams());
        }
        //刷新
        invalidate();
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new LayoutParams(p);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return p instanceof LayoutParams;
    }


    /**
     * 让当前Viewgroup支持margin
     */
    static class LayoutParams extends MarginLayoutParams {
        float scale = 0f;
        float alpha = 0f;
        int from = 0;
        int to = 0;

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }
}
