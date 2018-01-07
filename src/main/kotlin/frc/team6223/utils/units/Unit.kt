package frc.team6223.utils.units

abstract class Unit<in T: ScaleUnit> {
    abstract fun getAs(rep: T): Double
}

interface ScaleUnit {
    val scaleFactor: Double
}