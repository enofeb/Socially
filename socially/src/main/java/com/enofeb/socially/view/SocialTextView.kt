package com.enofeb.socially.view

import android.content.Context
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.enofeb.socially.R

class SocialTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.socialTextViewStyle
) : AppCompatTextView(context, attrs, defStyleAttr) {

    init {

        val words = this.text.split("\\s+".toRegex()).map { word ->
            word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
        }

        val spannableString = SpannableString(this.text)

        words.forEach { word ->
            val regexHashtag = Regex(HASHTAG_REGEX)
            val regexMention = Regex(MENTION_REGEX)
            var startIndexOfLink = -1

            startIndexOfLink = this.text.toString().indexOf(word, startIndexOfLink + 1)

            if (regexHashtag.matches(word) || regexMention.matches(word)) {
                val clickableSpan = object : ClickableSpan() {
                    override fun updateDrawState(textPaint: TextPaint) {
                        textPaint.color = ContextCompat.getColor(context,R.color.colorBlue)
                    }

                    override fun onClick(view: View) {
                        Selection.setSelection((view as TextView).text as Spannable, 0)
                        view.invalidate()
                    }
                }
                spannableString.setSpan(
                    clickableSpan,
                    startIndexOfLink,
                    startIndexOfLink + word.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            } else {
                spannableString.setSpan(
                    word,
                    startIndexOfLink,
                    startIndexOfLink + word.length,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }

        }

        this.movementMethod = LinkMovementMethod.getInstance()
        this.setText(spannableString, BufferType.SPANNABLE)

    }


    companion object {
        private const val HASHTAG_REGEX = "(#[A-Za-z0-9-_]+)"
        private const val MENTION_REGEX = "(@[A-Za-z0-9-_]+)"
    }
}