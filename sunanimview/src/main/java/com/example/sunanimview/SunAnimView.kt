package com.example.sunanimview

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Nullable

/**
 * @ProjectName: CustomViewDemo
 * @Package: com.example.sunanimview
 * @ClassName: SunAnimView
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2020/6/6 16:20
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/6/6 16:20
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class SunAnimView(context: Context, @Nullable attrs: AttributeSet?) : View(context, attrs) {
    private var paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG and Paint.DITHER_FLAG)
    private var whiteCicleRadius: Float = 0f
        set(value) {
            field = value
            invalidate()
        }
    private var yellowCicleRadius: Float = 0f
        set(value) {
            field = value
            invalidate()
        }

    fun changeRadius() {
        val animator1 = ObjectAnimator.ofFloat(this, "yellowCicleRadius", 0f, 90f)
        val animator2 = ObjectAnimator.ofFloat(this, "whiteCicleRadius", 4f, 80f)
        animator2.startDelay = 200L

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(animator1, animator2)
        animatorSet.start()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(resolveSize(400, widthMeasureSpec), resolveSize(400, heightMeasureSpec))
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.style = Paint.Style.FILL
        paint.color = Color.YELLOW
        canvas.drawCircle(width / 2f, height / 2f, yellowCicleRadius, paint)
        paint.reset()
        paint.style = Paint.Style.FILL
        paint.color = Color.WHITE
        canvas.drawCircle(width / 2f, height / 2f, whiteCicleRadius, paint)
    }
}