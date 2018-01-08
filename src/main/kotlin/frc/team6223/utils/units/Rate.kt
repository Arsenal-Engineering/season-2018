package frc.team6223.utils.units

open class Rate<tU: ScaleUnit, dU: ScaleUnit>(val topUnit: Unit<tU>, val bottomUnit: Unit<dU>) {
    private val internalRate = topUnit.numericValue(topUnit.scale) / bottomUnit.numericValue(bottomUnit.scale)

    fun getAs(): Double {
        return internalRate
    }

    fun getAsTop(rep: tU): Double {
        return internalRate / rep.scaleFactor
    }

    fun getAsTopRate(rep: tU): Rate<tU, dU> {
        return Rate(topUnit.rescale(rep), bottomUnit)
    }

    fun getAsBottom(rep: dU): Double {
        return this.getAsBottomRate(rep).getAs()
    }

    fun getAsBottomRate(rep: dU): Rate<tU, dU> {
        return Rate(topUnit, bottomUnit.rescale(rep))
    }

    fun getAsTopBottom(rep: tU, repBot: dU): Double {
        return this.getAsTopBottomRate(rep, repBot).getAs()
    }

    fun getAsTopBottomRate(rep: tU, repBot: dU): Rate<tU, dU> {
        return Rate(this.topUnit.rescale(rep), this.bottomUnit.rescale(repBot))
    }

    /*
    operator fun plus(inc: Rate<tU, dU>): Rate<tU, dU> {

    }
    operator fun minus(dec: Rate<tU, dU>): Rate<tU, dU> {

    }
    operator fun times(mult: Rate<tU, dU>): Rate<tU, dU> {

    }
    operator fun div(div: Rate<tU, dU>): Rate<tU, dU> {

    }
    */
}

sealed class TimeRate<tU: ScaleUnit>(topUnit: Unit<tU>): Rate<tU, TimeUnits>(topUnit, Time(1.0, TimeUnits.SECONDS))
sealed class Velocity(distance: Distance, time: Time): Rate<DistanceUnits, TimeUnits>(distance, time)