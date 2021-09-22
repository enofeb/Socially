package com.enofeb.socially.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatTextView
import com.enofeb.socially.R

class SocialTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.socialTextViewStyle
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {
        this.isClickable = true

        val words = this.text.split("\\s+".toRegex()).map { word ->
            word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
        }
        Log.e("TEXTCHECK", words.toString())
    }
}