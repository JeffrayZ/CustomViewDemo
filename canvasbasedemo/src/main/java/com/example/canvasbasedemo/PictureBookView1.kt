package com.example.canvasbasedemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

/**
 * @ProjectName: ZMBrainTrain-android
 * @Package: com.zhangmen.braintrain.mainpage.ui.view
 * @ClassName: BlurView
 * @Description: 圆形下载进度
 * @Author: Jeffray
 * @CreateDate: 2020/4/2 16:47
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/4/2 16:47
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
class PictureBookView1 : View {
    private var foreGroundBitmap: Bitmap? = null
    private val path: Path = Path()
    private val paint: Paint = Paint()
        private val xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN);
    private var ltr: Float = 0f
    private var rtr: Float = 0f
    private var lbr: Float = 0f
    private var rbr: Float = 0f

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        setLayerType(LAYER_TYPE_HARDWARE, null)
        ltr = resources.getDimension(R.dimen.dp_2)
        rtr = resources.getDimension(R.dimen.dp_14)
        lbr = resources.getDimension(R.dimen.dp_2)
        rbr = resources.getDimension(R.dimen.dp_14)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        foreGroundBitmap = getBitmapFromDrawable(context, R.drawable.mainpage_picturebook_foreground)
    }

    private fun init(context: Context) {
        foreGroundBitmap = getBitmapFromDrawable(context, R.drawable.mainpage_picturebook_foreground)
    }

    override fun onDraw(canvas: Canvas) {
//        setBackgroundColor(Color.RED)
        super.onDraw(canvas)
        paint.isAntiAlias = true
        // 画笔为填充模式
        paint.style = Paint.Style.FILL
        paint.color = Color.YELLOW

        // 使用离屏缓存，新建一个srcRectF区域大小的图层
        val layer = canvas.saveLayer(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat(), null);
        val bitmapBottom = BitmapFactory.decodeResource(resources, R.drawable.test)
        val bitmapTop = Bitmap.createBitmap(measuredWidth,measuredHeight,Bitmap.Config.ARGB_8888)
        path.addRoundRect(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat(),
                60f, 60f, Path.Direction.CCW)
        val canvasTop = Canvas(bitmapTop)
        // 绘制path
        canvasTop.drawPath(path, paint)

        canvas.drawBitmap(bitmapBottom, 0f, 0f, paint)
        // 设置混合模式
        paint.xfermode = xfermode
        canvas.drawBitmap(bitmapTop, 0f, 0f, paint)
        // 清除Xfermode
        paint.xfermode = null
        // 恢复画布状态
        canvas.restoreToCount(layer)
    }

    private fun getBitmapFromDrawable(context: Context, @DrawableRes drawableId: Int): Bitmap? {
        try {
            val drawable = ContextCompat.getDrawable(context, drawableId)!!
            val bitmap = Bitmap.createBitmap(drawable.intrinsicWidth,
                    drawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, measuredWidth, measuredHeight)
//            Log.i("PictureBookView","${canvas.width}：：：${canvas.height}") // 306：：：393
//            Log.i("PictureBookView","${measuredWidth}：：：${measuredHeight}") // 294：：：363
            drawable.draw(canvas)
            return bitmap
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}