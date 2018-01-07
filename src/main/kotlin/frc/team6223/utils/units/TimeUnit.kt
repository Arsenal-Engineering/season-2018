package frc.team6223.utils.units

class Time(time: Double, rep: TimeUnits): Unit<TimeUnits>() {
    /*
     * The moment in milliseconds
     */
    private val internalValue: Double = time / rep.scaleFactor;

    override fun getAs(rep: TimeUnits): Double {
        return internalValue * rep.scaleFactor
    }


}

enum class TimeUnits(override val scaleFactor: Double): ScaleUnit {
    MICROSECONDS(1000.0),
    MILLISECONDS(1.0),
    SECONDS(0.001),
}