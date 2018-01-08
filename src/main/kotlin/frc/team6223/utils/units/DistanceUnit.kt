package frc.team6223.utils.units


class Distance(val distance: Double, override val scale: DistanceUnits): Unit<DistanceUnits>() {

    override fun numericValue(): Double {
        return distance * scale.scaleFactor
    }

    override fun numericValue(rep: DistanceUnits): Double {
        return this.numericValue() / rep.scaleFactor
    }

    override val defaultScale: DistanceUnits = DistanceUnits.METERS

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


}

enum class DistanceUnits(override val scaleFactor: Double): ScaleUnit {
    MILLIMETERS(.001),
    CENTIMETERS(.01),
    METERS(1.0),
    KILOMETERS(1000.0),
    INCHES(0.0254),
    FEET(0.3048),
}