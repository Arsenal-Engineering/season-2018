package frc.team6223.utils.units

abstract class Unit<T: ScaleUnit> {
    abstract fun numericValue(): Double
    abstract fun numericValue(rep: T): Double
    abstract fun unit(): Unit<T>
    abstract fun rescale(rep: T): Unit<T>

    abstract val scale: T
    abstract val defaultScale: T

    abstract operator fun plus(inc: Unit<T>): Unit<T>
    abstract operator fun minus(dec: Unit<T>): Unit<T>
    abstract operator fun times(mult: Unit<T>): Unit<T>
    abstract operator fun div(div: Unit<T>): Unit<T>

    abstract operator fun plus(inc: Double): Unit<T>
    abstract operator fun minus(dec: Double): Unit<T>
    abstract operator fun times(mult: Double): Unit<T>
    abstract operator fun div(div: Double): Unit<T>
    abstract override operator fun equals(other: Any?): Boolean

    override fun hashCode(): Int {
        var result = scale.hashCode()
        result = 31 * result + defaultScale.hashCode()
        return result
    }

}

interface ScaleUnit {
    val scaleFactor: Double
}