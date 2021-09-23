package com.enofeb.socially

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.enofeb.socially.rule.Rule
import com.enofeb.socially.view.SocialTextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val socialThrowable = findViewById<SocialTextView>(R.id.socialTextView)

        socialThrowable.addRules(Rule.Hashtag(), Rule.Mention())

        socialThrowable.onTextClickListener = {

        }
    }
}