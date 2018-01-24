package frc.team6223.arsenalFramework.software.units

/**
 * A representation of a scalar (or vector) quantity that scales via a given ScaleUnit
 *
 * Unit is a basis for all other units of value that can be measured or derived from measurements. It automatically
 * converts any vector unit to scalar (as in the case of velocity) so direction cannot be derived from any unit
 * provided.
 *
 * @property T The scale unit that is used to scale the value up and down internally
 */
abstract class Unit<T: ScaleUnit> {
    /**
     * Returns the value of the unit stored in it's DEFAULT scale.
     */
    abstract fun numericValue(): Double

    /**
     * Returns the value of the unit stored in a provided scale.
     *
     * @param rep An instance of the scale unit used to rescale the provided unit.
     */
    abstract fun numericValue(rep: T): Double

    /**
     * Returns a new [Unit] of the specified ScaleUnit that has been rescaled to it's default scale.
     */
    abstract fun unit(): Unit<T>

    /**
     * Returns a new [Unit] of the specified ScaleUnit that has been rescaled to the provided scale.
     *
     * @param rep An instance of the scale unit used to rescale the provided unit
     */
    abstract fun rescale(rep: T): Unit<T>

    /**
     * The current scale used for the unit
     */
    abstract val scale: T

    /**
     * The default scale used for the unit
     */
    abstract val defaultScale: T

    /**
     * Adds two units together, returning a new unit in the default scale.
     *
     * @param inc The unit to add to the current unit.
     */
    abstract operator fun plus(inc: Unit<T>): Unit<T>

    /**
     * Subtracts two units, returning a new unit in the default scale.
     *
     * @param dec The unit to subtract from the current unit.
     */
    abstract operator fun minus(dec: Unit<T>): Unit<T>

    /**
     * Multiply two units, returning a new unit in the default scale.
     *
     * @param mult The unit to multiply with the current unit.
     */
    abstract operator fun times(mult: Unit<T>): Unit<T>

    /**
     * Divides two units, returning a new unit in the default scale
     *
     * @param div The unit to divide from the current unit
     */
    abstract operator fun div(div: Unit<T>): Unit<T>

    /**
     * Adds a scalar quantity to the unit, returning a new unit in the default scale.
     *
     * Note that individual implementations of units choose what scale factor they consider the scalar to fall under.
     * Most units use the default scale factor but others may choose to use some other scale factor. Consult their
     * documentation to find out more.
     *
     * @param inc The scalar to add to the current unit.
     */
    abstract operator fun plus(inc: Double): Unit<T>

    /**
     * Subtracts a scalar quantity from the unit, returning a new unit in the default scale.
     *
     * Note that individual implementations of units choose what scale factor they consider the scalar to fall under.
     * Most units use the default scale factor but others may choose to use some other scale factor. Consult their
     * documentation to find out more.
     *
     * @param dec The scalar to subtract from the current unit.
     */
    abstract operator fun minus(dec: Double): Unit<T>

    /**
     * Multiplies a scalar quantity with the unit, returning a new unit in the default scale.
     *
     * Note that individual implementations of units choose what scale factor they consider the scalar to fall under.
     * Most units use the default scale factor but others may choose to use some other scale factor. Consult their
     * documentation to find out more.
     *
     * @param mult The scalar to multiply to the current unit.
     */
    abstract operator fun times(mult: Double): Unit<T>

    /**
     * Divides a scalar quantity from the unit, returning a new unit in the default scale.
     *
     * Note that individual implementations of units choose what scale factor they consider the scalar to fall under.
     * Most units use the default scale factor but others may choose to use some other scale factor. Consult their
     * documentation to find out more.
     *
     * @param div The scalar to divide from the current unit.
     */
    abstract operator fun div(div: Double): Unit<T>
    abstract override operator fun equals(other: Any?): Boolean

    override fun hashCode(): Int {
        var result = scale.hashCode()
        result = 31 * result + defaultScale.hashCode()
        return result
    }

    override fun toString(): String {
        return this.numericValue(scale).toString() + " " + this.scale.abbreviation
    }

}

interface ScaleUnit {
    val scaleFactor: Double
    val abbreviation: String
}