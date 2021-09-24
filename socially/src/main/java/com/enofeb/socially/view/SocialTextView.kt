package com.enofeb.socially.view

import android.content.Context
import android.graphics.Color
import android.text.*
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatTextView
import com.enofeb.socially.R
import com.enofeb.socially.rule.Rule
import java.util.regex.Pattern

typealias OnTextClickListener = ((String, Rule) -> Unit)

class SocialTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.socialTextViewStyle
) : AppCompatTextView(context, attrs, defStyleAttr) {

    private val ruleList: MutableList<Rule> = mutableListOf()

    private val spannableString = SpannableString(this.text)

    @ColorInt
    private var _hashTagColor = Color.BLUE

    @ColorInt
    private var _mentionColor = Color.BLUE

    @ColorInt
    private var _phoneNumberColor = Color.BLUE

    @ColorInt
    private var _mailColor = Color.BLUE

    @ColorInt
    private var _webLinkColor = Color.BLUE

    private var startIndexOfLink = -1

    var hashTagColor: Int
        @ColorInt get() = _hashTagColor
        set(@ColorInt value) {
            _hashTagColor = value
        }

    var mentionColor: Int
        @ColorInt get() = _mentionColor
        set(@ColorInt value) {
            _mentionColor = value
        }

    var phoneNumberColor: Int
        @ColorInt get() = _phoneNumberColor
        set(@ColorInt value) {
            _phoneNumberColor = value
        }

    var mailColor: Int
        @ColorInt get() = _mailColor
        set(@ColorInt value) {
            _mailColor = value
        }

    var webLinkColor: Int
        @ColorInt get() = _webLinkColor
        set(@ColorInt value) {
            _webLinkColor = value
        }

    var onTextClickListener: OnTextClickListener? = null

    init {
        obtainStyledAttributes(attrs, defStyleAttr)
    }

    private fun obtainStyledAttributes(attrs: AttributeSet?, defStyleAttr: Int) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.SocialTextView,
            defStyleAttr,
            0
        )

        try {
            hashTagColor = typedArray.getColor(
                R.styleable.SocialTextView_hashTagColor,
                hashTagColor
            )

            mentionColor = typedArray.getColor(
                R.styleable.SocialTextView_mentionColor,
                mentionColor
            )

            phoneNumberColor = typedArray.getColor(
                R.styleable.SocialTextView_phoneNumberColor,
                phoneNumberColor
            )

            mailColor = typedArray.getColor(
                R.styleable.SocialTextView_mailColor,
                mailColor
            )

            webLinkColor = typedArray.getColor(
                R.styleable.SocialTextView_webLinkColor,
                webLinkColor
            )

        } catch (e: Exception) {
            // ignored
        } finally {
            typedArray.recycle()
        }
    }

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

    private fun setClickableSpannable(word: String, rule: Rule) {
        val clickableSpan = object : ClickableSpan() {
            override fun updateDrawState(textPaint: TextPaint) {
                val textColor = when (rule) {
                    is Rule.Hashtag -> hashTagColor
                    is Rule.Mention -> mentionColor
                    is Rule.PhoneNumber -> phoneNumberColor
                    is Rule.Mail -> mailColor
                    is Rule.WebLink -> webLinkColor
                    is Rule.Custom -> {
                        rule.textColor
                    }
                }

                textPaint.apply {
                    this.isUnderlineText = rule == Rule.WebLink
                    color = textColor
                }
            }

            override fun onClick(view: View) {
                Selection.setSelection((view as TextView).text as Spannable, 0)
                view.invalidate()
                onTextClickListener?.invoke(
                    word.replace(word.first().toString(), ""),
                    rule
                )
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
                if (Pattern.compile(rule.regex).matcher(word).matches()) {
                    setClickableSpannable(word, rule)
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
}