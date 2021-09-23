package com.enofeb.socially.view

import android.content.Context
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.enofeb.socially.R
import com.enofeb.socially.rule.Rule

typealias OnTextClickListener = ((String) -> Unit)

class SocialTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.socialTextViewStyle
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private val ruleList: MutableList<Rule> = mutableListOf()

    private val spannableString = SpannableString(this.text)

    private var startIndexOfLink = -1

    var onTextClickListener: OnTextClickListener? = null

    private fun start() {
        val words = this.text.split("\\s+".toRegex()).map { word ->
            word.replace("""^[,\.]|[,\.]$""".toRegex(), "")
        }

        words.forEach { word ->
            startIndexOfLink = this.text.toString().indexOf(word, startIndexOfLink + 1)
            checkRuleAndSpan(word)
        }

        this.movementMethod = LinkMovementMethod.getInstance()
        this.setText(spannableString, BufferType.SPANNABLE)
    }

    private fun setSpannable(word: String) {
        spannableString.setSpan(
            word,
            startIndexOfLink,
            startIndexOfLink + word.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

    private fun setClickableSpannable(word: String) {
        val clickableSpan = object : ClickableSpan() {
            override fun updateDrawState(textPaint: TextPaint) {
                textPaint.color = ContextCompat.getColor(context, R.color.colorBlue)
            }

            override fun onClick(view: View) {
                Selection.setSelection((view as TextView).text as Spannable, 0)
                view.invalidate()
                onTextClickListener?.invoke(word.replace(word.first().toString(), ""))
            }
        }
        spannableString.setSpan(
            clickableSpan,
            startIndexOfLink,
            startIndexOfLink + word.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

    }

    private fun checkRuleAndSpan(word: String) {
        if (ruleList.isNullOrEmpty()) {
            setSpannable(word)
        } else {
            ruleList.forEach { rule ->
                if (rule.regex.matches(word)) {
                    setClickableSpannable(word)
                } else {
                    setSpannable(word)
                }
            }
        }
    }

    fun addRules(vararg rules: Rule) {
        ruleList.addAll(rules)
        start()
    }

    companion object {
        private const val HASHTAG_REGEX = "(#[A-Za-z0-9-_]+)"
        private const val MENTION_REGEX = "(@[A-Za-z0-9-_]+)"
    }
}