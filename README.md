Socially
===============
<a href="http://developer.android.com/index.html" target="_blank"><img src="https://img.shields.io/badge/platform-android-green.svg"/></a>
<a href="https://android-arsenal.com/api?level=15" target="_blank"><img src="https://img.shields.io/badge/API-21%2B-green.svg?style=flat"/></a>
<a href="http://opensource.org/licenses/MIT" target="_blank"><img src="https://img.shields.io/badge/License-MIT-blue.svg?style=flat"/></a>

Socially is a textView which is able to create seperate clickable views according to your requirements.

# Simple Usage

```kotlin
<com.enofeb.socially.view.SocialTextView
        android:id="@+id/socialTextView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="I am #Spider @Enes99 +905512026461 eneszor95@gmail.com https://www.facebook.com/"
        app:hashTagColor="@color/colorGreen"
        app:mailColor="@color/colorOrange"
        app:mentionColor="@color/colorBlue"
        app:phoneNumberColor="@color/colorOrange"
        app:webLinkColor="@color/colorRed" />
```

```kotlin
socialTextView.addRules(
          Rule.Hashtag,
          Rule.Mention
          Rule.PhoneNumber,
          Rule.Mail,
          Rule.WebLink,
          Rule.Custom(
               "Date",
               "^(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-([12][0-9]{3})$",
               this.getColor(R.color.colorRed)
         )
)
```

## Features
* It already supports hashtag, mention, phone number, mail and weblinks. To enable them , you need to add rule which you want to use.
```kotlin
socialTextView.addRules(
         Rule.Mention,
         Rule.PhoneNumber
)
```
* It is possible to add Custom regex rule with text color.
```kotlin
socialTextView.addRules(
        Rule.Custom(
            "Date",
            "^(0?[1-9]|[12][0-9]|3[01])-(0?[1-9]|1[012])-([12][0-9]{3})$",
            this.getColor(R.color.colorRed)
        )
)
```
* Listen when the user click any text.
```kotlin
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
```
## XML Attributes

```xml
<attr name="hashTagColor" format="color" />
<attr name="mentionColor" format="color" />
<attr name="phoneNumberColor" format="color" />
<attr name="mailColor" format="color" />
<attr name="webLinkColor" format="color" />
```
