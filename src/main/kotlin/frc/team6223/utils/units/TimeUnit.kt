package frc.team6223.utils.units

class Time(time: Double, rep: TimeUnits): Unit() {
    /*
     * The moment in milliseconds
     */
    private val internalValue: Double = time / rep.scaleFactor;

    fun getAs(rep: TimeUnits) {
        internalValue * rep.scaleFactor
    }


}

enum class TimeUnits(val scaleFactor: Double) {
    MICROSECONDS(1000.0),
    MILLISECONDS(1.0),
    SECONDS(0.001),
}