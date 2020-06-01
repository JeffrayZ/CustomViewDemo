package com.example.dragremotecontrol

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.WindowManager
import androidx.appcompat.widget.AppCompatImageView


/**
 * @ProjectName: CustomViewDemo
 * @Package: com.example.dragremotecontrol
 * @ClassName: DragRemoteControl
 * @Description: java类作用描述
 * @Author: Jeffray
 * @CreateDate: 2020/5/28 19:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/5/28 19:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class DraggableButton @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : AppCompatImageView(context, attrs, defStyleAttr) {

    private var mPhonePaint: Paint = Paint()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth
        val height = measuredHeight
        val size = Math.min(width, height)
        setMeasuredDimension(size, size)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        canvas?.drawColor(Color.parseColor("#B6A7F1"))
    }

    private fun getScreenRelatedInformation(context: Context) {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        if (windowManager != null) {
            val outMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(outMetrics)
            val widthPixels = outMetrics.widthPixels // 可用显示大小的绝对宽度（以像素为单位）。
            val heightPixels = outMetrics.heightPixels // 可用显示大小的绝对高度（以像素为单位）。
            val densityDpi = outMetrics.densityDpi // 屏幕密度表示为每英寸点数。
            val density = outMetrics.density // 显示器的逻辑密度。
            val scaledDensity = outMetrics.scaledDensity // 显示屏上显示的字体缩放系数。
            Log.d("display", "widthPixels = $widthPixels,heightPixels = $heightPixels\n,densityDpi = $densityDpi\n,density = $density,scaledDensity = $scaledDensity")
        }
    }

    private fun getScreenWidth(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        if (windowManager != null) {
            val outMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(outMetrics)
            return outMetrics.widthPixels
        }
        return 0
    }

    private fun getScreenHeight(context: Context): Int {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        if (windowManager != null) {
            val outMetrics = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(outMetrics)
            return outMetrics.heightPixels
        }
        return 0
    }

    private fun dp2px(context: Context, dpValue: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, context.resources.displayMetrics)
    }

    private fun dp2px(dpValue: Int): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue.toFloat(), context.resources.displayMetrics)
    }

    private fun sp2px(context: Context, dpValue: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, dpValue, context.resources.displayMetrics)
    }
}