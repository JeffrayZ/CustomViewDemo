package com.example.dragremotecontrol

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.util.TypedValue
import android.view.DragEvent
import android.view.View
import android.view.WindowManager


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
class DragRemoteControl @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0) : View(context, attrs, defStyleAttr),View.OnDragListener {

    private var mPhonePaint: Paint = Paint()
    private var startX: Float = 0f
    private var mPhoneWidth: Float = 0f
    private var mPhoneContentWidth: Float = 0f
    private var mPhoneContentHeight: Float = 0f
    private var mRectF: RectF = RectF()
    private val mBackPath: Path = Path()
    private val mDashPathEffect = DashPathEffect(floatArrayOf(10f, 10f), 0f)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        setMeasuredDimension(resolveSize(dp2px(context, 300f).toInt(), widthMeasureSpec), resolveSize(dp2px(context, 300f).toInt(), heightMeasureSpec))
        // 手机高度为View高度减去上下间隔24dp
        val phoneHeight = measuredHeight - dp2px(context, 24f)
        // 手机内容区域高 ：手机高度 - 手机头尾（48dp）- 手机屏幕间距（5dp） * 2）
        mPhoneContentHeight = phoneHeight - dp2px(context, 58f)
        // 手机内容区域宽 ：手机内容区域高/ 7 * 4（手机内容区域为4：7）
        mPhoneContentWidth = mPhoneContentHeight / 7 * 4;
        // 手机宽度为手机内容区域宽 + 手机屏幕间距 * 2
        mPhoneWidth = mPhoneContentWidth + dp2px(context, 10f)
        // 绘制起始点
        startX = (measuredWidth - mPhoneWidth) / 2;
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//        canvas?.drawColor(Color.parseColor("#B6A7F1"))

        mPhonePaint.color = Color.parseColor("#70FFFFFF")
        mPhonePaint.style = Paint.Style.STROKE
        mPhonePaint.strokeWidth = 2f
        val i = dp2px(context, 12f)
        mRectF.left = startX.toFloat()
        mRectF.right = (measuredWidth - startX);
        mRectF.top = i.toFloat()
        mRectF.bottom = (measuredHeight - i)
        canvas?.drawRoundRect(mRectF, i, i, mPhonePaint)

        canvas?.drawLine(startX.toFloat(), (i * 3).toFloat(), (measuredWidth - startX).toFloat(), (i * 3).toFloat(), mPhonePaint)
        canvas?.drawLine(startX.toFloat(), (measuredHeight - i * 3).toFloat(), (measuredWidth - startX).toFloat(), (measuredHeight - i * 3).toFloat(), mPhonePaint)

        canvas?.drawCircle((measuredWidth / 2 - dp2px(context, 40f)).toFloat(), (i * 2).toFloat(), (i / 3).toFloat(), mPhonePaint)
        canvas?.drawCircle((measuredWidth / 2 + dp2px(context, 40f)).toFloat(), (i * 2).toFloat(), (i / 3).toFloat(), mPhonePaint)
        mRectF.left = getMeasuredWidth() / 2 - dp2px(context, 25f);
        mRectF.right = getMeasuredWidth() / 2 + dp2px(context, 25f);
        mRectF.top = dp2px(context, 22f)
        mRectF.bottom = dp2px(context, 26f)
        canvas?.drawRoundRect(mRectF, dp2px(context, 2f), dp2px(context, 2f), mPhonePaint)

        mRectF.left = getMeasuredWidth() / 2 - dp2px(25);
        mRectF.right = getMeasuredWidth() / 2 + dp2px(25);
        mRectF.top = dp2px(22);
        mRectF.bottom = dp2px(26);
        canvas?.drawRoundRect(mRectF, dp2px(2), dp2px(2), mPhonePaint);
        canvas?.drawCircle(getMeasuredWidth() / 2 - dp2px(40), i * 2, i / 3, mPhonePaint);
        canvas?.drawCircle(getMeasuredWidth() / 2 + dp2px(40), i * 2, i / 3, mPhonePaint);

        // 绘制手机下方按键
        canvas?.drawCircle((getMeasuredWidth() / 2).toFloat(), getMeasuredHeight() - i * 2, i / 2, mPhonePaint);
        canvas?.drawRect(startX + mPhoneWidth / 5, getMeasuredHeight() - dp2px(29), startX + mPhoneWidth / 5 + dp2px(10), getMeasuredHeight() - dp2px(19), mPhonePaint);
        mBackPath.moveTo(getMeasuredWidth() - startX - mPhoneWidth / 5, getMeasuredHeight() - dp2px(30));
        mBackPath.lineTo(getMeasuredWidth() - startX - mPhoneWidth / 5 - dp2px(10), getMeasuredHeight() - dp2px(24));
        mBackPath.lineTo(getMeasuredWidth() - startX - mPhoneWidth / 5, getMeasuredHeight() - dp2px(18));
        mBackPath.close();
        canvas?.drawPath(mBackPath, mPhonePaint);

        // 绘制网格（4 * 7的田字格）田字格外框为实线，内侧为虚线
        // 手机屏幕间距5pd

        // 绘制网格（4 * 7的田字格）田字格外框为实线，内侧为虚线
        // 手机屏幕间距5pd
        val j = dp2px(5).toInt()
        // 格子的宽高
        // 格子的宽高
        val size: Float = mPhoneContentHeight / 7

        // 横线
        for (z in 0..7) {
            mPhonePaint.pathEffect = null
            mPhonePaint.color = Color.parseColor("#30FFFFFF")
            mPhonePaint.strokeWidth = 1f
            // 实线
            canvas!!.drawLine(startX + j, dp2px(41) + z * size,
                    measuredWidth - startX - j, dp2px(41) + z * size, mPhonePaint)
            // 虚线
            if (z != 7) {
                mPhonePaint.pathEffect = mDashPathEffect
                mPhonePaint.color = Color.parseColor("#20FFFFFF")
                canvas.drawLine(startX + j, dp2px(41) + z * size + size / 2,
                        measuredWidth - startX - j, dp2px(41) + z * size + size / 2, mPhonePaint)
            }
        }

        // 竖线
        for (z in 0..4) {
            mPhonePaint.pathEffect = null
            mPhonePaint.color = Color.parseColor("#30FFFFFF")
            mPhonePaint.strokeWidth = 1f
            // 实线
            canvas!!.drawLine(startX + j + z * size, dp2px(41),
                    startX + j + z * size, measuredHeight - dp2px(41), mPhonePaint)
            // 虚线
            if (z != 4) {
                mPhonePaint.pathEffect = mDashPathEffect
                mPhonePaint.color = Color.parseColor("#20FFFFFF")
                canvas.drawLine(startX + j + z * size + size / 2, dp2px(41),
                        startX + j + z * size + size / 2, measuredHeight - dp2px(41), mPhonePaint)
            }
        }

        var bitmap: Bitmap
        itemList.forEach {
            bitmap = BitmapFactory.decodeResource(resources, it.pic)
            canvas?.drawBitmap(bitmap,it.posX - bitmap.width / 2,it.posY - bitmap.height/2,null)
        }
    }

    private val itemList = mutableListOf<DraggableInfo>()



    override fun onDragEvent(event: DragEvent?): Boolean {
        //获取事件
        when (event?.action) {
            DragEvent.ACTION_DRAG_STARTED -> Log.i("BLUE", "开始拖拽")
            DragEvent.ACTION_DRAG_ENDED -> Log.i("BLUE", "结束拖拽")
            DragEvent.ACTION_DRAG_ENTERED -> Log.i("BLUE", "拖拽的view进入监听的view时")
            DragEvent.ACTION_DRAG_EXITED -> Log.i("BLUE", "拖拽的view离开监听的view时")
            DragEvent.ACTION_DRAG_LOCATION -> {
                val x = event.x
                val y = event.y
//                val item = event.clipData.getItemAt(0).intent.getSerializableExtra("data") as DraggableInfo
//                item.posX = x
//                item.posY = y
//                invalidate()
                Log.i("BLUE", "拖拽的view在监听view中的位置:x =$x,y=$y")
            }
            DragEvent.ACTION_DROP -> Log.i("BLUE", "释放拖拽的view")
        }
        //是否响应拖拽事件，true响应，返回false只能接受到ACTION_DRAG_STARTED事件，后续事件不会收到
        return true
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

    override fun onDrag(v: View?, event: DragEvent?): Boolean {
        //获取事件
        when (event?.action) {
            DragEvent.ACTION_DRAG_STARTED -> Log.i("WHITE", "开始拖拽")
            DragEvent.ACTION_DRAG_ENDED -> Log.i("WHITE", "结束拖拽")
            DragEvent.ACTION_DRAG_ENTERED -> Log.i("WHITE", "拖拽的view进入监听的view时")
            DragEvent.ACTION_DRAG_EXITED -> Log.i("WHITE", "拖拽的view离开监听的view时")
            DragEvent.ACTION_DRAG_LOCATION -> {
                val x = event.x
                val y = event.y
                val item = event.clipData.getItemAt(0).intent.getSerializableExtra("data") as DraggableInfo
//                item.posX = x
//                item.posY = y
//                invalidate()
                Log.i("BLUE", "拖拽的view在监听view中的位置:x =$x,y=$y")
            }
            DragEvent.ACTION_DROP -> Log.i("WHITE", "释放拖拽的view")
        }
        //是否响应拖拽事件，true响应，返回false只能接受到ACTION_DRAG_STARTED事件，后续事件不会收到
        return true
    }
}