package com.enofeb.socially.rule

import androidx.annotation.ColorRes

sealed class Rule {

    abstract val name: String

    abstract val regex: Regex

    @ColorRes
    open val textColor: Int? = null

    data class Hashtag(
        override val name: String = "Hashtag",
        override val regex: Regex = "(#[A-Za-z0-9-_]+)".toRegex()
    ) : Rule()


    data class Mention(
        override val name: String = "Mention",
        override val regex: Regex = "(@[A-Za-z0-9-_]+)".toRegex()
    ) : Rule()

    data class Custom(
        override val name: String,
        override val regex: Regex,
        override val textColor: Int?
    ) : Rule()
}
