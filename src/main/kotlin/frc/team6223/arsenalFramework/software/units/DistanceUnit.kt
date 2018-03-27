package frc.team6223.arsenalFramework.software.units

import frc.team6223.robot.conf.wheelCircumference


class Distance(val distance: Double, override val scale: DistanceUnits): Unit<DistanceUnits>() {

    override fun numericValue(): Double {
        return distance * scale.scaleFactor
    }

    override fun numericValue(rep: DistanceUnits): Double {
        return this.numericValue() / rep.scaleFactor
    }

    override val defaultScale: DistanceUnits = DistanceUnits.FEET

    override fun unit(): Distance {
        return Distance(distance * scale.scaleFactor, defaultScale)
    }

    override fun rescale(rep: DistanceUnits): Distance {
        return Distance(this.numericValue() / rep.scaleFactor, rep)
    }

    override fun plus(inc: Unit<DistanceUnits>): Unit<DistanceUnits> {
        return Distance(this.numericValue() + inc.numericValue(), defaultScale)
    }

    override fun minus(dec: Unit<DistanceUnits>): Unit<DistanceUnits> {
        return Distance(this.numericValue() - dec.numericValue(), defaultScale)
    }

    override fun times(mult: Unit<DistanceUnits>): Unit<DistanceUnits> {
        return Distance(this.numericValue() * mult.numericValue(), defaultScale)
    }

    override fun div(div: Unit<DistanceUnits>): Unit<DistanceUnits> {
        return Distance(this.numericValue() / div.numericValue(), defaultScale)
    }

    override fun plus(inc: Double): Unit<DistanceUnits> {
        return Distance(this.numericValue() + inc, defaultScale)
    }

    override fun minus(dec: Double): Unit<DistanceUnits> {
        return Distance(this.numericValue() - dec, defaultScale)
    }

    override fun times(mult: Double): Unit<DistanceUnits> {
        return Distance(this.numericValue() * mult, defaultScale)
    }

    override fun div(div: Double): Unit<DistanceUnits> {
        return Distance(this.numericValue() / div, defaultScale)
    }

    override fun equals(other: Any?): Boolean {
        return when(other) {
            is Distance -> other.numericValue() == this.numericValue()
            else -> false
        }
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + distance.hashCode()
        result = 31 * result + scale.hashCode()
        result = 31 * result + defaultScale.hashCode()
        return result
    }

    companion object {
        inline fun convertMagPulseToDistance(magPulse: Int): Distance {
            // divide mag pulse number by 4096 then multiply that by wheel circumference
            return Distance(((magPulse / 4096) * wheelCircumference), DistanceUnits.INCHES)
        }
    }

}

enum class DistanceUnits(override val scaleFactor: Double, override val abbreviation: String): ScaleUnit {
    METERS(39.37, "m"),
    INCHES(12.0, "in"),
    FEET(1.0, "ft"),
}