package com.example.animbutton

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.graphics.Paint.DITHER_FLAG
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.Nullable
import kotlin.math.abs


/**
 * @ProjectName: CustomViewDemo
 * @Package: com.example.animbutton
 * @ClassName: AnimButtonView
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2020/6/11 16:52
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/11 16:52
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class AnimButtonView(context: Context, @Nullable attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint(ANTI_ALIAS_FLAG or DITHER_FLAG)
    private val rigthPaint = Paint(ANTI_ALIAS_FLAG or DITHER_FLAG)
    private val path = Path()

    //////////////////////////////////////////
    private var mPathMeasure: PathMeasure? = null
    private var mDst: Path = Path()
    private var mAnimValue:Float = 0f
    private var mLength:Float = 0f
    private var mStart:Float = 0f
    /////////////////////////////////////////////

    init {
        setOnClickListener {
            anim1()
        }

        paint.color = Color.parseColor("#6FA7AC")

        rigthPaint.color = Color.WHITE
        rigthPaint.style = Paint.Style.STROKE
        rigthPaint.strokeCap = Paint.Cap.ROUND
        rigthPaint.strokeJoin = Paint.Join.ROUND
        rigthPaint.strokeWidth = 10f
    }

    private var radius: Float = 0f
        set(value) {
            field = value
            invalidate()
        }

    private var btnMargin: Float = 0f
        set(value) {
            field = value
            invalidate()
        }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(resolveSize(400, widthMeasureSpec), resolveSize(150, heightMeasureSpec))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawRoundRect(btnMargin, 0F, width.toFloat() - btnMargin, height.toFloat(), radius, radius, paint)
        // 适用于anim2
//        canvas.drawPath(path, rigthPaint)
        // 适用于anim2

        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>适用于 anim3
        val stop: Float = mLength * mAnimValue
        Log.d("KKKK>>>>","$mLength:::$mAnimValue:::$stop")
        //截取某个片段
        mPathMeasure?.getSegment(mStart, stop, mDst, true)
        canvas.drawPath(mDst, rigthPaint)
        // 这一次的终点就是下一次的起点
        mStart = stop
        // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>适用于 anim3
    }

    private fun anim1() {
        val animatorSet = AnimatorSet()

        val animator1 = ObjectAnimator.ofFloat(this, "radius", 0f, height / 2f)
        val animator2 = ObjectAnimator.ofFloat(this, "btnMargin", 0f, (width - height) / 2f)
        val animator3 = ObjectAnimator.ofFloat(this, "translationY", translationY, translationY - 400)

        animatorSet.play(animator1).with(animator2).before(animator3)

        animatorSet.duration = 500L
        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {

            }

            override fun onAnimationEnd(animation: Animator?) {
//                anim2()

                anim3()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }

        })
        animatorSet.start()
    }

    private fun anim2() {
//        Romain Guy使用DashPathEffect来实现了路径绘制。
//        DashPathEffect(float[] intervals, float phase)
//        DashPathEffect传入了一个intervals数组，用来控制实线和虚线的数组的显示，那么当实线和虚线都是整个路径的长度时，整个路径就只显示实线或者虚线了，
//        这时候通过第二个参数phase来控制起始偏移量，就可以完成整个路径的绘制了，这的确是一个非常trick而且有效的方式

        path.moveTo(width / 2f - 30, height / 2f)
        path.lineTo(width / 2f - 10, height / 2f + 30)
        path.lineTo(width / 2f + 40, height / 2f - 20)
        val pathMeasure = PathMeasure(path, false)
        val length = pathMeasure.length
        val mAnimator = ValueAnimator.ofFloat(1f, 0f)
        mAnimator.duration = 1000L
        mAnimator.addUpdateListener { valueAnimator ->
            val fraction = valueAnimator.animatedValue as Float
            val mEffect = DashPathEffect(floatArrayOf(length, length), fraction * length)
            rigthPaint.pathEffect = mEffect
            invalidate()
        }
        mAnimator.start()
    }

    private fun anim3() {
        // 另一种实现路径动画的方式
        val path3 = Path()
        path3.moveTo(width / 2f - 30, height / 2f)
        path3.lineTo(width / 2f - 10, height / 2f + 30)
        path3.lineTo(width / 2f + 40, height / 2f - 20)
        mPathMeasure = PathMeasure(path3, false)
        mLength = mPathMeasure?.length?:0f
        val mAnimator = ValueAnimator.ofFloat(0f, 1f)
        mAnimator.duration = 1000L
        mAnimator.addUpdateListener { valueAnimator ->
            mAnimValue = valueAnimator.animatedValue as Float
            invalidate()
        }
        mAnimator.start()
    }
}