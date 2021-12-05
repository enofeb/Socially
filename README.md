Socially
===============
[![](https://jitpack.io/v/enofeb/Socially.svg)](https://jitpack.io/#enofeb/Socially)
<a href="http://developer.android.com/index.html" target="_blank"><img src="https://img.shields.io/badge/platform-android-green.svg"/></a>
<a href="https://android-arsenal.com/api?level=15" target="_blank"><img src="https://img.shields.io/badge/API-19%2B-green.svg?style=flat"/></a>
<a href="http://opensource.org/licenses/MIT" target="_blank"><img src="https://img.shields.io/badge/License-MIT-blue.svg?style=flat"/></a>

Socially is a textView which is able to create separate clickable views according to your requirements.

# Sample Video

<p align="center">
  <img width="300" height="600" src="https://github.com/enofeb/Socially/blob/dev/screenshot/demo.gif">
</p>

# Simple Usage
1) Add SocialTextView to your xml.
```kotlin
<com.enofeb.socially.view.SocialTextView
        android:id="@+id/socialTextView"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:hashTagColor="@color/colorGreen"
        app:mailColor="@color/colorOrange"
        app:mentionColor="@color/colorBlue"
        app:phoneNumberColor="@color/colorOrange"
        app:webLinkColor="@color/colorRed" />
```
2) Add your rules.
```kotlin
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
```
3) And, finally set your text.
```kotlin
socialTextView.socialText = "I am #Spider @Enes99 +905512026461 eneszor95@gmail.com https://www.lichess.org"
```

NOTES: Please get follow these three steps while setting up this  library.

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

## Download
* Add Jitpack to your root `build.gradle` repositories.
```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

* Add Socially to your module dependencies.
```groovy
dependencies {
    implementation 'com.github.enofeb:Socially:1.0.9'
}
```

## License
```
MIT License

Copyright (c) 2021 Enes Zor

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
