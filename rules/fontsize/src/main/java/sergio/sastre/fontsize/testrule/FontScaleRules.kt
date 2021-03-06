package sergio.sastre.fontsize.testrule

import androidx.test.platform.app.InstrumentationRegistry
import sergio.sastre.fontsize.FontScale

object FontScaleRules {

    fun fontScaleTestRule(fontScale : FontScale): FontScaleTestRule {
        return FontScaleTestRule(createFontScaleSetting(), fontScale)
    }

    fun smallFontScaleTestRule(): FontScaleTestRule {
        return FontScaleTestRule(createFontScaleSetting(), FontScale.SMALL)
    }

    fun normalFontScaleTestRule(): FontScaleTestRule {
        return FontScaleTestRule(createFontScaleSetting(), FontScale.NORMAL)
    }

    fun largeFontScaleTestRule(): FontScaleTestRule {
        return FontScaleTestRule(createFontScaleSetting(), FontScale.LARGE)
    }

    fun hugeFontScaleTestRule(): FontScaleTestRule {
        return FontScaleTestRule(createFontScaleSetting(), FontScale.HUGE)
    }

    private fun createFontScaleSetting(): FontScaleSetting {
        return FontScaleSetting(InstrumentationRegistry.getInstrumentation().targetContext.resources)
    }
}