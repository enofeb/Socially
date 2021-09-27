package com.example.socially

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.enofeb.socially.rule.Rule
import com.enofeb.socially.view.SocialTextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val socialTextView = findViewById<SocialTextView>(R.id.socialTextView)

        socialTextView.addRules(
            Rule.Hashtag,
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
                    Toast.makeText(this, word, Toast.LENGTH_LONG).show()
                }
                is Rule.Mention -> {
                    Toast.makeText(this, word, Toast.LENGTH_LONG).show()
                }
                is Rule.Custom -> {

                }
            }
        }
    }
}