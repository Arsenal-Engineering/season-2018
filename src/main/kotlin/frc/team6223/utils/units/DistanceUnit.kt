package frc.team6223.utils.units


class Distance(distance: Double, unit: DistanceUnits): Unit<DistanceUnits>() {

    /*
     * The distance of the value, in meters
     */
    private val internalVal: Double = distance / unit.scaleFactor;

    override fun getAs(rep: DistanceUnits): Double {
        return internalVal * rep.scaleFactor
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