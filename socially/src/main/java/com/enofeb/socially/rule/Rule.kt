package com.enofeb.socially.rule

import android.util.Patterns
import androidx.annotation.ColorRes

sealed class Rule {

    abstract val name: String

    abstract val regex: String

    @ColorRes
    open val textColor: Int? = null

    object Hashtag : Rule() {
        override val name = "Hashtag"
        override val regex = "(#[A-Za-z0-9-_]+)"
    }

    object Mention : Rule() {
        override val name = "Mention"
        override val regex = "(@[A-Za-z0-9-_]+)"
    }

    object PhoneNumber : Rule() {
        override val name = "PhoneNumber"
        override val regex = Patterns.PHONE.toRegex().pattern
    }

    object Mail : Rule() {
        override val name = "Mail"
        override val regex = Patterns.EMAIL_ADDRESS.toRegex().pattern
    }

    object WebLink : Rule() {
        override val name = "Link"
        override val regex = Patterns.WEB_URL.toRegex().pattern
    }

    data class Custom(
        override val name: String,
        override val regex: String,
        override val textColor: Int?
    ) : Rule()
}
