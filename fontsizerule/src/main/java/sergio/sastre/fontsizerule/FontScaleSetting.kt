package sergio.sastre.fontsizerule

/**
 * Concept taken from : https://github.com/novoda/espresso-support
 */
import android.content.res.Resources

@Suppress("DEPRECATION")
class FontScaleSetting internal constructor(private val resources: Resources) {

    fun get(): FontScale {
        return FontScale.from(resources.configuration.fontScale)
    }

    fun set(scale: FontScale) {
        try {
            resources.configuration.fontScale = java.lang.Float.parseFloat(scale.value)
            val metrics = Resources.getSystem().displayMetrics
            metrics.scaledDensity = resources.configuration.fontScale * metrics.density
            resources.updateConfiguration(resources.configuration, metrics)

        } catch (e: Exception) {
            throw saveFontScaleError(scale)
        }
    }

    private fun saveFontScaleError(scale: FontScale): RuntimeException {
        return RuntimeException("Unable to save font size " + scale.name)
    }
}
