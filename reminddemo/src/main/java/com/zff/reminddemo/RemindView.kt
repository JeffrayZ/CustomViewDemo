package com.zff.reminddemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import kotlin.math.abs
import kotlin.math.sqrt

class RemindView : View {
    private var foreGroundBitmap: Bitmap? = null
    private val path: Path = Path()
    private val paint: Paint = Paint()
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    private var cx: Float = 0f
    private var cy: Float = 0f
    private var radius: Float = 0f

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var roundRect: RectF? = null
    private var anchorView: View? = null
    fun setAnchorView(anchorView: View) {
        this.anchorView = anchorView
        val floatArray = IntArray(2)
        anchorView.getLocationInWindow(floatArray)
        Log.i("RemindView", "${floatArray[0]}:::${floatArray[1]}")
        cx = floatArray[0] + anchorView.width / 2f
        cy = floatArray[1] + anchorView.height / 2f
        radius = sqrt((anchorView.width / 2 * anchorView.width / 2 + anchorView.height / 2 * anchorView.height / 2).toDouble()).toFloat() + 10
        Log.i("RemindView", "${cx}:::${cy}:::${radius}")
    }


    init {
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val layerId = canvas.saveLayer(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat(), null)
        paint.color = Color.parseColor("#88000000")
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
        paint.xfermode = xfermode
        paint.color = Color.WHITE
        canvas.drawCircle(cx, cy, radius, paint)
        paint.xfermode = null
        canvas.restoreToCount(layerId)

        getBitmapFromDrawable(context, R.drawable.mainpage_img_clockin_remind)?.let {
            val rectSrc = Rect(0, 0, it.width, it.height)
            Log.i("RemindView", "${it.width}:::${it.height}")
            val rectDst = Rect((cx + resources.getDimension(R.dimen.dp_34) - it.width).toInt(),
                    (cy + resources.getDimension(R.dimen.dp_6) + radius).toInt(),
                    (cx + resources.getDimension(R.dimen.dp_34)).toInt(), (cy + resources.getDimension(R.dimen.dp_6) + radius + it.height).toInt())
            canvas.drawBitmap(it, rectSrc, rectDst, null)

            paint.color = Color.WHITE
            paint.isFakeBoldText = true

//            // 从asset 读取字体
//            // 得到AssetManager
//            val mgr: AssetManager = resources.assets
//            // 根据路径得到Typeface
//            val tf = Typeface.createFromAsset(mgr, "fonts/HelveticaNeueLTPro-UltLt.otf")
//            // 设置字体
//            paint.typeface = tf
            paint.textSize = resources.getDimension(R.dimen.dp_20)
            canvas.drawText("学习打卡来了",
                    (rectDst.left + 350).toFloat(),
                    (rectDst.top + 130).toFloat(), paint)
            paint.isFakeBoldText = false
            paint.textSize = resources.getDimension(R.dimen.dp_16)
            canvas.drawText("好习惯贵在坚持",
                    (rectDst.left + 350).toFloat(),
                    (rectDst.top + 200).toFloat(), paint)

            roundRect = RectF(rectDst.right - resources.getDimension(R.dimen.dp_100),
                    rectDst.bottom - resources.getDimension(R.dimen.dp_35),
                    rectDst.right - resources.getDimension(R.dimen.dp_15),
                    rectDst.bottom - resources.getDimension(R.dimen.dp_10))
            canvas.drawRoundRect(
                    roundRect!!,
                    resources.getDimension(R.dimen.dp_15),
                    resources.getDimension(R.dimen.dp_15),
                    paint)

            paint.textSize = resources.getDimension(R.dimen.dp_14)
            paint.color = Color.parseColor("#FF4F4F")

            // 文字的x轴坐标
            val stringWidth: Float = paint.measureText("知道了")
            val fontMetrics: Paint.FontMetrics = paint.fontMetrics
//            val y = height / 2 + (abs(fontMetrics.ascent) - fontMetrics.descent) / 2
            canvas.drawText("知道了",
                    roundRect!!.left + stringWidth / 2,
                    roundRect!!.top
                            + (abs(fontMetrics.ascent) - fontMetrics.descent) / 2
                            + (roundRect!!.bottom - roundRect!!.top) / 2,
                    paint)
        }

    }

    private var mLastX = 0f
    private var mLastY = 0f
    private var downTime: Long = 0L
    private var onClickListener: (() -> Unit)? = null
    fun setOnClickListener(onClickListener: (() -> Unit)){
        this.onClickListener = onClickListener
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mLastX = event.x
                mLastY = event.y
                if(roundRect?.contains(mLastX,mLastY) == true){
                    downTime = System.currentTimeMillis()
                    return true
                }
            }
            MotionEvent.ACTION_UP -> {
                if (ViewConfiguration.getTapTimeout() >= System.currentTimeMillis() - downTime
                        && abs(event.x - mLastX) <= ViewConfiguration.get(context).scaledTouchSlop
                        && abs(event.y - mLastY) <= ViewConfiguration.get(context).scaledTouchSlop) {
                    // 认为是一次点击事件
                    onClickListener?.invoke()
                }
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    private fun getBitmapFromDrawable(context: Context, @DrawableRes drawableId: Int): Bitmap? {
        try {
            val drawable = ContextCompat.getDrawable(context, drawableId)!!
            val matrix = Matrix()
            matrix.postScale(0.9f, 0.9f)
            var bitmap = Bitmap.createBitmap(drawable.intrinsicWidth,
                    drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            return bitmap
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}