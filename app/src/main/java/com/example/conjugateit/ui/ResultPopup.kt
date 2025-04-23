package com.example.conjugateit.ui

import android.app.Activity
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.conjugateit.R

class ResultPopup(private val context: Context) {

    private val popup: PopupWindow
    private val message: TextView
    private val icon: ImageView
    private val container: LinearLayout

    init {
        val popupView = LayoutInflater.from(context).inflate(
            R.layout.popup_result,
            (context as Activity).window.decorView.findViewById(android.R.id.content),
            false
        )

        message = popupView.findViewById(R.id.message)
        icon = popupView.findViewById(R.id.icon)
        container = popupView.findViewById(R.id.popup_container)

        popup = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            false
        ).apply {
            elevation = 8f
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    fun show(anchorView: View, isAltered: Boolean, isCorrect: Boolean, correctAnswer: String = "") {
        var delay = 2000L
        if (!isAltered) {
            message.text = correctAnswer
            icon.setImageResource(R.drawable.ic_close)
            container.background = ContextCompat.getDrawable(context, R.drawable.popup_background)?.mutate()
            container.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.neutral_gray))
        } else if (isCorrect) {
            message.text = context.getString(R.string.message_correct)
            icon.setImageResource(R.drawable.ic_check)
            container.background = ContextCompat.getDrawable(context, R.drawable.popup_background)?.mutate()
            container.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.success_green))
            delay = 1000
        } else {
            message.text = correctAnswer
            icon.setImageResource(R.drawable.ic_close)
            container.background = ContextCompat.getDrawable(context, R.drawable.popup_background)?.mutate()
            container.backgroundTintList = ColorStateList.valueOf(ContextCompat.getColor(context, R.color.error_red))
        }
        popup.animationStyle = android.R.style.Animation_Dialog

        popup.showAtLocation(
            anchorView,
            Gravity.TOP or Gravity.CENTER_HORIZONTAL,
            0,
            (anchorView.height * 0.3).toInt()
        )

        Handler(Looper.getMainLooper()).postDelayed({ popup.dismiss() }, delay)
    }
}