package com.zff.reminddemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.Toast
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn.post {
            val remindView:RemindView = RemindView(this)
            remindView.setOnClickListener {
                Toast.makeText(this,"我在点击范围内",Toast.LENGTH_SHORT).show()
            }
            remindView.setAnchorView(btn)
            (window.decorView as FrameLayout).addView(remindView)
        }
    }
}
