package sergio.sastre.testing

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import serfio.sastre.testing.MyButton
import sergio.sastre.fontsize.FontScale
import sergio.sastre.fontsize.FontSizeActivityScenario

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
                TestPayload(
                    FontScale.SMALL,
                    "SMALL2",
                ),
                TestPayload(
                    FontScale.NORMAL,
                    "NORMAL2",
                ),
                TestPayload(
                    FontScale.LARGE,
                    "LARGE2",
                ),
                TestPayload(
                    FontScale.SMALL,
                    "SMALL3",
                ),
                TestPayload(
                    FontScale.NORMAL,
                    "NORMAL3",
                ),
                TestPayload(
                    FontScale.LARGE,
                    "LARGE3",
                ),
            )
    }

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    @Test
    fun buttonIsShownProperly() {
        FontSizeActivityScenario.launchWith(data.fontScale).onActivity {
            it.setContent {
                MyButton()
            }
        }

        compareScreenshot(composeTestRule, name = data.testName)
    }
}
