package com.github.app.ui

import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import androidx.fragment.app.FragmentActivity
import com.example.github.R

open class MainActivityBase : FragmentActivity() {

    fun addNextQuickButton(buttonPrev: Button?, text: String): Button {
        val layout: RelativeLayout = findViewById(R.id.main_app_button_container)
        val buttonNew = Button(this)

        buttonNew.id = View.generateViewId()
        buttonNew.text = text
        layout.addView(buttonNew)

        var lp: RelativeLayout.LayoutParams =
            if (buttonPrev != null)
                RelativeLayout.LayoutParams(buttonPrev.layoutParams)
            else
                RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT)
        lp.apply {
            if (buttonPrev == null) {
                addRule(RelativeLayout.ALIGN_PARENT_LEFT)
            } else {
                addRule(RelativeLayout.RIGHT_OF, buttonPrev.id)
            }
            removeRule(RelativeLayout.LEFT_OF)
        }
        buttonNew.layoutParams = lp

        return buttonNew
    }
    fun dpToPixels(dps: Int): Int {
        val scale: Float = resources.displayMetrics.density
        return (dps * scale + 0.5f).toInt()
    }
}