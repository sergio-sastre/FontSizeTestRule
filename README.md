<a href="https://androidweekly.net/issues/issue-485">
<img src="https://androidweekly.net/issues/issue-485/badge">
</a>

# FontSizeActivityScenario and FontSizeTestRule
An ActivityScenario and a Junit4 TestRule to be used together with its `org.junit.runners.Parameterized`. It simulates changing the font size on a device/emulator, as it would be done by going to "Settings > display > Font size"

This helps to write snapshot tests that can catch visual regression bugs like this one

| **FONT SIZE NORMAL**   |      **FONT SIZE HUGE**      |
|----------|:-------------:|
| <img src="https://user-images.githubusercontent.com/6097181/129961748-5daa42a2-8801-4b26-832a-d4191e205bc9.png" width="450"> |  <img src="https://user-images.githubusercontent.com/6097181/129962082-f2ff110f-6500-4a02-8765-7f70a7b8ee61.png" width="450"> |

This library has been mentioned in
1. [Kotlin Weekly #266](https://newsletterest.com/message/69919/Kotlin-Weekly-266)

You can read more about it in my series on [snapshot testing](https://sergiosastre.hashnode.dev/an-introduction-to-snapshot-testing-on-android-in-2021), featured in :
1. [Android Weekly #479](https://androidweekly.net/issues/issue-479)
2. [.kotlinTesting (September 2021)](https://kotlintesting.com/jvm-testing-newsletter-september-2021/)
3. [Software Testing Notes #20](https://softwaretestingnotes.substack.com/p/issue-20-software-testing-notes)
4. [Test Automation Weekly #8](https://www.testautomationweekly.com/post/issue-8)

## Integration
To integrate FontSizeTestRule into your project:

Add jitpack to your root `build.gradle` file:
```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
Add a dependency to `build.gradle`
```groovy
dependencies {
    androidTestImplementation 'com.github.sergio-sastre:FontSizeTestRule:v1.1.1'
}
```

## What to keep in mind
Prefer FontSizeActivityScenario to FontSizeTestRule. That is because Google deprecated
`resources.updateConfiguration(resources.configuration, metrics)` on **API 25+**, and the test rule is based on that method.
Therefore, previous to v1.1.0-SNAPSHOT, the TestRule does not work on devices/emulators running 25+.
In order to solve this, from 25+, the rule will execute the corresponding adb shell command to change the font size:
<br/>
`"settings put system font_scale ${fontScale.value}`
</br>
However, this has the drawback that the font size change does not happen immediately, making your tests run more slow.


This issue is overcome by using `FontSizeActivityScenario.launchWith(fontScale)`, available since
'com.github.sergio-sastre:FontSizeTestRule:v1.1.0-SNAPSHOT'. The only inconvenient is that you cannot snapshot-test your
own activities with it. That is because in order to use `resources.updateConfiguration(resources.configuration, metrics)` replacement,
we need to override `attachBaseContext()` in an activity.

*Summary*
| **FontSizeActivityScenario**    |      **FontSizeTestRule**     |
|----------|:-------------:|
|Any API | < API 25, otherwise might be more slow |
| **Cannot** snapshot-test *Activities* | **Can** snapshot-test any View|
| Preferred over FontSizeTesRule | Recommended only to snapshot Activites |

## Usage
If you are using `FontSizeActivityScenario` you need to add the following activities to your `debug/manifest`
```xml
<application
        ...
   <activity android:name="sergio.sastre.fontsize.activityscenario.SmallFontSizeActivity"></activity>
   <activity android:name="sergio.sastre.fontsize.activityscenario.NormalFontSizeActivity"></activity>
   <activity android:name="sergio.sastre.fontsize.activityscenario.LargeFontSizeActivity"></activity>
   <activity android:name="sergio.sastre.fontsize.activityscenario.HugeFontSizeActivity"></activity>
</application>
```
or if you are using `v1.1.0-SNAPSHOT`
```xml
<application
        ...
   <activity android:name="sergio.sastre.fontsize.SmallFontSizeActivity"></activity>
   <activity android:name="sergio.sastre.fontsize.NormalFontSizeActivity"></activity>
   <activity android:name="sergio.sastre.fontsize.LargeFontSizeActivity"></activity>
   <activity android:name="sergio.sastre.fontsize.HugeFontSizeActivity"></activity>
</application>
```

in order to test **Jetpack Compose** views, use `createEmptyComposeRule()` together with `FontSizeActivityScenario` as follows:
```kotlin

@get:Rule
val composeTestRule = createEmptyComposeRule()

@Test
fun composeWithFontSizeTest() {
    FontSizeActivityScenario.launchWith(testItem.fontScale).onActivity {
            it.setContent {
                myComposeView()
            }
        }

    compareScreenshot(composeTestRule, name = testItem.testName)
}
```

You can find some test samples of `FontSizeActivityScenario` and `FontScaleTestRule` in the [Road to Effective Snapshot Testing](https://github.com/sergio-sastre/RoadToEffectiveSnapshotTesting) repo:
1. FontActivityScenario -> in the [DeleteDialogTest.kt](https://github.com/sergio-sastre/RoadToEffectiveSnapshotTesting/blob/master/app/src/androidTest/java/com/example/road/to/effective/snapshot/testing/parameterized/DeleteDialogTest.kt) and [TrainingViewHolderTest.kt](https://github.com/sergio-sastre/RoadToEffectiveSnapshotTesting/blob/master/app/src/androidTest/java/com/example/road/to/effective/snapshot/testing/parameterized/TrainingViewHolderTest.kt) files.
2. FontScaleTestRule -> also in the [DeleteDialogTest.kt](https://github.com/sergio-sastre/RoadToEffectiveSnapshotTesting/blob/master/app/src/androidTest/java/com/example/road/to/effective/snapshot/testing/parameterized/DeleteDialogTest.kt) file.


