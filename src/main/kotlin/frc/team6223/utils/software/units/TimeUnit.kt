package frc.team6223.utils.software.units

class Time(val time: Double, override val scale: TimeUnits): Unit<TimeUnits>() {

    override fun numericValue(): Double {
        return time * scale.scaleFactor
    }

    override fun numericValue(rep: TimeUnits): Double {
        return this.numericValue() / rep.scaleFactor
    }

    override fun unit(): Time {
        return Time(this.numericValue(), this.defaultScale)
    }

    override val defaultScale: TimeUnits = TimeUnits.MILLISECONDS

    override fun rescale(rep: TimeUnits): Time {
        return Time(this.numericValue() / rep.scaleFactor, rep)
    }

    override fun plus(inc: Unit<TimeUnits>): Unit<TimeUnits> {
        return Time(this.numericValue() + inc.numericValue(), scale)
    }

    override fun minus(dec: Unit<TimeUnits>): Unit<TimeUnits> {
        return Time(this.numericValue() - dec.numericValue(), scale)
    }

    override fun times(mult: Unit<TimeUnits>): Unit<TimeUnits> {
        return Time(this.numericValue() * mult.numericValue(), scale)
    }

    override fun div(div: Unit<TimeUnits>): Unit<TimeUnits> {
        return Time(this.numericValue() / div.numericValue(), scale)
    }

    override fun plus(inc: Double): Unit<TimeUnits> {
        return Time(this.numericValue() + inc, scale)
    }

    override fun minus(dec: Double): Unit<TimeUnits> {
        return Time(this.numericValue() - dec, scale)
    }

    override fun times(mult: Double): Unit<TimeUnits> {
        return Time(this.numericValue() * mult, scale)
    }

    override fun div(div: Double): Unit<TimeUnits> {
        return Time(this.numericValue() / div, scale)
    }

    override fun equals(other: Any?): Boolean {
        when (other) {
            is Time -> return other.numericValue() == this.numericValue()
            else -> return false
        }
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + time.hashCode()
        result = 31 * result + scale.hashCode()
        result = 31 * result + defaultScale.hashCode()
        return result
    }


}

enum class TimeUnits(override val scaleFactor: Double, override val abbreviation: String): ScaleUnit {
    MICROSECONDS(.001, "microseconds"),
    MILLISECONDS(1.0, "ms"),
    SECONDS(1000.0, "s"),
    MINUTE(60000.0, "min")
}