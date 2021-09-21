package sergio.sastre.fontsize.activityscenario

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import androidx.test.core.app.ActivityScenario
import sergio.sastre.fontsize.FontScale

object FontSizeActivityScenario {
    fun launchWith(fontScale: FontScale): ActivityScenario<out MockFontSizeActivity> {
        val clazz = when (fontScale){
            FontScale.SMALL -> SmallFontSizeActivity::class.java
            FontScale.NORMAL -> NormalFontSizeActivity::class.java
            FontScale.LARGE -> LargeFontSizeActivity::class.java
            FontScale.HUGE -> HugeFontSizeActivity::class.java
        }
        return ActivityScenario.launch(clazz)
    }
}

abstract class MockFontSizeActivity : AppCompatActivity() {
    abstract val fontScale: FontScale

    override fun attachBaseContext(newBase: Context?) {
        val newConfig = Configuration(newBase!!.resources.configuration)
        newConfig.fontScale = fontScale.value.toFloat()
        applyOverrideConfiguration(newConfig)
        super.attachBaseContext(newBase)
    }
}

class SmallFontSizeActivity : MockFontSizeActivity() {
    override val fontScale: FontScale
        get() = FontScale.SMALL
}

class NormalFontSizeActivity : MockFontSizeActivity() {
    override val fontScale: FontScale
        get() = FontScale.NORMAL
}

class LargeFontSizeActivity : MockFontSizeActivity() {
    override val fontScale: FontScale
        get() = FontScale.LARGE
}

class HugeFontSizeActivity : MockFontSizeActivity() {
    override val fontScale: FontScale
        get() = FontScale.HUGE
}