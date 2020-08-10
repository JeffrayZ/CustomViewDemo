package com.example.canvasbasedemo

import android.R.attr.bitmap
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.AppCompatImageView
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
class PictureBookView : AppCompatImageView {
    private var foreGroundBitmap: Bitmap? = null
    private val path: Path = Path()
    private val paint: Paint = Paint()
    private val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    private var ltr: Float = 0f
    private var rtr: Float = 0f
    private var lbr: Float = 0f
    private var rbr: Float = 0f

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        setLayerType(View.LAYER_TYPE_SOFTWARE,null)
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.color = Color.YELLOW
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
        super.onDraw(canvas)
        val layerId = canvas.saveLayer(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat(), null);
        val drawable = this.drawable
        if (drawable != null) {
            path.addRoundRect(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat(),
                    floatArrayOf(ltr, ltr, rtr, rtr, rbr, rbr, lbr, lbr), Path.Direction.CCW)
            canvas.drawPath(path,paint)
            paint.xfermode = xfermode
            val bitmapTop = Bitmap.createBitmap(drawable.intrinsicWidth, drawable.intrinsicHeight,
                    Bitmap.Config.RGB_565)
            val canvasTop = Canvas(bitmapTop)
            drawable.setBounds(0, 0, drawable.intrinsicWidth,
                    drawable.intrinsicHeight)
            drawable.draw(canvasTop)
            canvas.drawBitmap(bitmapTop,0f,0f,paint)
            paint.xfermode = null

        }
        canvas.restoreToCount(layerId)
        foreGroundBitmap?.let { canvas.drawBitmap(it, 0f, 0f, null) }
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