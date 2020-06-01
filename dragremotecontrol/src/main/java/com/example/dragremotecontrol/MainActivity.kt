package com.example.dragremotecontrol

import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.HapticFeedbackConstants
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db_button?.apply {
            val info = DraggableInfo(R.mipmap.offered_cursor, -1f, -1f)
            tag = info
            setOnDragListener(dr)
            setOnLongClickListener {
                startDrag(it)
            }
        }
    }

    private fun startDrag(view: View) :Boolean {
        val tagg: DraggableInfo? = view.tag as DraggableInfo
        Log.i("BLUE", tagg.toString())
        val intent = Intent()
        intent.putExtra("data", tagg);
        val dragData = ClipData.newIntent("value", intent)
        val myShadow = View.DragShadowBuilder(view)
        // 震动反馈，不需要震动权限
        view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS, HapticFeedbackConstants.FLAG_IGNORE_GLOBAL_SETTING);
        return view.startDragAndDrop(dragData, myShadow, null,
                View.DRAG_FLAG_GLOBAL or View.DRAG_FLAG_GLOBAL_URI_READ or View.DRAG_FLAG_GLOBAL_URI_WRITE);
    }
}