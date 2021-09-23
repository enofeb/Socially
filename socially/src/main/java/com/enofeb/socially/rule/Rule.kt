package com.enofeb.socially.rule

import androidx.annotation.ColorRes

sealed class Rule {

    abstract val name: String

    abstract val regex: Regex

    @ColorRes
    open val textColor: Int? = null

    object Hashtag : Rule() {
        override val name: String = "Hashtag"
        override val regex: Regex = "(#[A-Za-z0-9-_]+)".toRegex()
    }

    object Mention : Rule() {
        override val name: String = "Mention"
        override val regex: Regex = "(@[A-Za-z0-9-_]+)".toRegex()
    }

    data class Custom(
        override val name: String,
        override val regex: Regex,
        override val textColor: Int?
    ) : Rule()
}
