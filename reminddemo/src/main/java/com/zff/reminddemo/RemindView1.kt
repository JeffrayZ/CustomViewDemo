package com.zff.reminddemo

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import kotlin.math.sqrt

class RemindView1 : ConstraintLayout {

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var anchorView: View? = null
    fun setAnchorView(anchorView: View) {
        this.anchorView = anchorView

        Log.i("RemindView", "${anchorView?.left}:::${anchorView?.top}:::${anchorView?.right}:::${anchorView?.bottom}")
    }
}