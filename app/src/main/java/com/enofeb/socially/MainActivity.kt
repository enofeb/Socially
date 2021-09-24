package com.enofeb.socially

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import com.enofeb.socially.rule.Rule
import com.enofeb.socially.view.SocialTextView
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val socialTextView = findViewById<SocialTextView>(R.id.socialTextView)

        socialTextView.addRules(
            Rule.Mention,
            Rule.PhoneNumber,
            Rule.Mail,
            Rule.WebLink,
            Rule.Custom(
                "Date",
                "^(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-([12][0-9]{3})$",
                this.getColor(R.color.colorRed)
            )
        )


        socialTextView.onTextClickListener = { word, rule ->
            when (rule) {
                is Rule.Hashtag -> {

                }
                is Rule.Mention -> {

                }
                is Rule.Custom -> {

                }
            }
        }
    }
}