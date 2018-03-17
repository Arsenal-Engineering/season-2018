package frc.team6223.arsenalFramework.software.units

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

class Angle(private val angle: Double, override val scale: AngleUnits): Unit<AngleUnits>() {

    val sine: Double = sin(this.numericValue(AngleUnits.RADIANS))
    val cosine: Double = cos(this.numericValue(AngleUnits.RADIANS))
    val tangent: Double = tan(this.numericValue(AngleUnits.RADIANS))

    override fun numericValue(): Double {
        return angle * scale.scaleFactor
    }

    override fun numericValue(rep: AngleUnits): Double {
        return this.numericValue() / rep.scaleFactor
    }

    override fun unit(): Unit<AngleUnits> {
        return Angle(angle * scale.scaleFactor, AngleUnits.DEGREES)
    }

    override fun rescale(rep: AngleUnits): Unit<AngleUnits> {
        return Angle(angle / rep.scaleFactor, rep)
    }

    override val defaultScale: AngleUnits = AngleUnits.DEGREES

    override fun plus(inc: Unit<AngleUnits>): Unit<AngleUnits> {
        return Angle(this.numericValue() + inc.numericValue(), defaultScale)
    }

    override fun minus(dec: Unit<AngleUnits>): Unit<AngleUnits> {
        return Angle(this.numericValue() - dec.numericValue(), defaultScale)
    }

    override fun times(mult: Unit<AngleUnits>): Unit<AngleUnits> {
        return Angle(this.numericValue() * mult.numericValue(), defaultScale)
    }

    override fun div(div: Unit<AngleUnits>): Unit<AngleUnits> {
        return Angle(this.numericValue() / div.numericValue(), defaultScale)
    }

    override fun plus(inc: Double): Unit<AngleUnits> {
        return Angle(this.numericValue() + inc, defaultScale)
    }

    override fun minus(dec: Double): Unit<AngleUnits> {
        return Angle(this.numericValue() - dec, defaultScale)
    }

    override fun times(mult: Double): Unit<AngleUnits> {
        return Angle(this.numericValue() * mult, defaultScale)
    }

    override fun div(div: Double): Unit<AngleUnits> {
        return Angle(this.numericValue() / div, defaultScale)
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Angle -> this.numericValue() == other.numericValue()
            else -> false
        }
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + angle.hashCode()
        result = 31 * result + scale.hashCode()
        result = 31 * result + defaultScale.hashCode()
        return result
    }
}

enum class AngleUnits(override val scaleFactor: Double, override val abbreviation: String): ScaleUnit {
    DEGREES(1.0, "deg"),
    RADIANS(180/PI, "rad")
}