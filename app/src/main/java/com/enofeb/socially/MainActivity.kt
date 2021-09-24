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

        val socialThrowable = findViewById<SocialTextView>(R.id.socialTextView)

        socialThrowable.addRules(
            Rule.Mention,
            Rule.PhoneNumber,
            Rule.Mail,
            Rule.WebLink,
            Rule.Custom(
                "Hash",
                "(#[A-Za-z0-9-_]+)",
                this.getColor(R.color.colorRed)
            )
        )


        socialThrowable.onTextClickListener = { word, rule ->
            when (rule) {
                is Rule.Hashtag -> {
                    Log.e("ELLO", rule.name)
                    Log.e("ELLO", word)
                }
                is Rule.Mention -> {
                    Log.e("ELLO", rule.name)
                    Log.e("ELLO", word)
                }
                is Rule.Custom -> {
                    Log.e("ELLO", rule.name)
                    Log.e("ELLO", word)
                }
            }

        }
    }
}