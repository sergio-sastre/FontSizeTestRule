package sergio.sastre.testing

import androidx.compose.ui.test.junit4.createComposeRule
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import serfio.sastre.testing.MyButton
import sergio.sastre.fontsize.FontScale
import sergio.sastre.fontsize.testrule.FontScaleRules

data class TestPayload(
    val fontScale: FontScale,
    val testName: String,
)

// API 30 emulator
// adb shell settings put global hidden_api_policy 1

@RunWith(Parameterized::class)
class ButtonTest(
    private val data: TestPayload
) : ScreenshotTest {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun data(): Array<TestPayload> =
            arrayOf(
                TestPayload(
                    FontScale.SMALL,
                    "SMALL",
                ),
                TestPayload(
                    FontScale.NORMAL,
                    "NORMAL",
                ),
                TestPayload(
                    FontScale.LARGE,
                    "LARGE",
                ),
            )
    }

    @get:Rule
    val fontSize = FontScaleRules.fontScaleTestRule(data.fontScale)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun buttonIsShownProperly() {
        composeTestRule.setContent {
            MyButton()
        }

        compareScreenshot(composeTestRule, name = data.testName)
    }
}
