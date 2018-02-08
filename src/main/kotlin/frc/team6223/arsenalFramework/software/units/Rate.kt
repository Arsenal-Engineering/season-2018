package frc.team6223.arsenalFramework.software.units

open class Rate<tU: ScaleUnit, dU: ScaleUnit>(private val topUnit: Unit<tU>, private val bottomUnit: Unit<dU>): Unit<RateScaleFactor<tU, dU>>() {
    private val internalRate = topUnit.numericValue(topUnit.scale) / bottomUnit.numericValue(bottomUnit.scale)
    override val scale = RateScaleFactor(topUnit.scale, bottomUnit.scale)
    override val defaultScale: RateScaleFactor<tU, dU> = RateScaleFactor(topUnit.defaultScale, bottomUnit.defaultScale)

    override fun numericValue(): Double {
        return internalRate
    }

    override fun numericValue(rep: RateScaleFactor<tU, dU>): Double {
        return this.rescaleRate(rep.topScaleUnit, rep.bottomScaleUnit).numericValue()
    }

    fun rescaleTopScalar(rep: tU): Double {
        return this.rescaleTopRate(rep).numericValue()
    }

    fun rescaleTopRate(rep: tU): Rate<tU, dU> {
        return Rate(topUnit.rescale(rep), bottomUnit)
    }

    fun rescaleBottomScalar(rep: dU): Double {
        return this.rescaleBottomRate(rep).numericValue()
    }

    fun rescaleBottomRate(rep: dU): Rate<tU, dU> {
        return Rate(topUnit, bottomUnit.rescale(rep))
    }

    fun rescaleScalar(rep: tU, repBot: dU): Double {
        return this.rescaleRate(rep, repBot).numericValue()
    }

    fun rescaleRate(rep: tU, repBot: dU): Rate<tU, dU> {
        return Rate(this.topUnit.rescale(rep), this.bottomUnit.rescale(repBot))
    }

    override fun rescale(rep: RateScaleFactor<tU, dU>): Unit<RateScaleFactor<tU, dU>> {
        return this.rescaleRate(rep.topScaleUnit, rep.bottomScaleUnit)
    }

    override fun unit(): Unit<RateScaleFactor<tU, dU>> {
        return this
    }

    override fun plus(inc: Unit<RateScaleFactor<tU, dU>>): Unit<RateScaleFactor<tU, dU>> {
        return Rate(this.topUnit.plus(inc.rescale(this.scale).numericValue()), this.bottomUnit)
    }

    override fun minus(dec: Unit<RateScaleFactor<tU, dU>>): Unit<RateScaleFactor<tU, dU>> {
        return Rate(this.topUnit.minus(dec.rescale(this.scale).numericValue()), this.bottomUnit)
    }

    override fun times(mult: Unit<RateScaleFactor<tU, dU>>): Unit<RateScaleFactor<tU, dU>> {
        return Rate(this.topUnit.times(mult.rescale(this.scale).numericValue()), this.bottomUnit)
    }

    override fun div(div: Unit<RateScaleFactor<tU, dU>>): Unit<RateScaleFactor<tU, dU>> {
        return Rate(this.topUnit.div(div.rescale(this.scale).numericValue()), this.bottomUnit)
    }

    override fun plus(inc: Double): Unit<RateScaleFactor<tU, dU>> {
        return Rate(this.topUnit.plus(inc), this.bottomUnit)
    }

    override fun minus(dec: Double): Unit<RateScaleFactor<tU, dU>> {
        return Rate(this.topUnit.minus(dec), this.bottomUnit)
    }

    override fun times(mult: Double): Unit<RateScaleFactor<tU, dU>> {
        return Rate(this.topUnit.times(mult), this.bottomUnit)
    }

    override fun div(div: Double): Unit<RateScaleFactor<tU, dU>> {
        return Rate(this.topUnit.div(div), this.bottomUnit)
    }

    override fun equals(other: Any?): Boolean {
        return when(other) {
            is Rate<*,*> -> this.topUnit == other.topUnit && this.bottomUnit == other.bottomUnit
            else -> false
        }
    }

    override fun hashCode(): Int {
        var result = super.hashCode()
        result = 31 * result + topUnit.hashCode()
        result = 31 * result + bottomUnit.hashCode()
        result = 31 * result + internalRate.hashCode()
        result = 31 * result + scale.hashCode()
        return result
    }

    override fun toString(): String {
        return this.numericValue().toString() + " " + this.scale.abbreviation
    }


}

open class RateScaleFactor<out tU: ScaleUnit, out dU: ScaleUnit>(val topScaleUnit: tU, val bottomScaleUnit: dU): ScaleUnit {
    override val scaleFactor = topScaleUnit.scaleFactor / bottomScaleUnit.scaleFactor
    override val abbreviation: String = topScaleUnit.abbreviation + "/" + bottomScaleUnit.abbreviation
}

typealias VelocityScaleFactor = RateScaleFactor<DistanceUnits, TimeUnits>

class Velocity(distance: Distance, time: Time): Rate<DistanceUnits, TimeUnits>(distance, time) {
    companion object {
        inline fun convertMagPulseRateToVelocity(magPulseRate: Int): Velocity {
            // divide mag encoder rate by 4096 and multiply by wheel circumference to get rate per 100 ms
            return Velocity(Distance.convertMagPulseToDistance(magPulseRate), Time(100.0, TimeUnits.MILLISECONDS))
        }
    }
}
class Acceleration(velocity: Velocity, time: Time): Rate<VelocityScaleFactor, TimeUnits>(velocity, time)