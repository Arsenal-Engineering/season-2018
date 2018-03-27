package frc.team6223.arsenalFramework.software

import frc.team6223.arsenalFramework.hardware.currentTimeSec
import frc.team6223.arsenalFramework.software.units.Time
import frc.team6223.arsenalFramework.software.units.TimeUnits

/**
 * Constants for a PIDF controller
 *
 * @param kP The proportional constant
 * @param kI The integral constant
 * @param kD The derivative constant
 * @param kF The feed-forward constant
 */
data class PIDFConstants(val kP: Double, val kI: Double, val kD: Double, val kF: Double)

/**
 * A internal controller that runs based on a set of constants and a target.
 *
 * @param constants The PIDF constants for the controller
 * @param target The setpoint value of the controller
 */
class PIDFController(constants: PIDFConstants, target: Double) {

    /**
     * The internal constants for PIDF
     */
    var constants: PIDFConstants = constants
        set(value) {
            field = value

            integralGain = 0.0
            lastTime = Time(Double.NaN, TimeUnits.SECONDS)
            lastError = Double.NaN
        }

    /**
     * The current setpoint for the controller
     */
    var setPoint: Double = target
        set(value) {
            field = value

            integralGain = 0.0
            lastTime = Time(Double.NaN, TimeUnits.SECONDS)
            lastError = Double.NaN
        }

    /**
     * The current error for the controller
     */
    var currentError = setPoint
        private set

    /**
     * Whether or not the setpoint is equal to the current error
     */
    var isFinished = (setPoint == currentError)

    /**
     * The previous error
     */
    private var lastError: Double = Double.NaN

    /**
     * The previous time
     */
    private var lastTime = Time(Double.NaN, TimeUnits.SECONDS)

    /**
     * The integration gain
     */
    private var integralGain: Double = 0.0

    /**
     * Run the PIDF loop for one frame.
     */
    fun runController(current: Double): Double {

        // calculate constants for this iteration of the loop
        this.currentError = setPoint - current
        val deltaTime = currentTimeSec() - lastTime
        this.lastTime = currentTimeSec()

        val calculated: Double = this.constants.kD * (currentError - this.lastError / deltaTime.numericValue(TimeUnits.SECONDS))
        val derivative = if (calculated != 0.0 && calculated != Double.NaN) {
            calculated
        } else {
            0.0
        }

        integralGain += currentError

        // first calculate the P, which should always exist
        var output = (this.constants.kP * currentError)
        // next check for the I gain
        output += (this.constants.kI * integralGain)
        // next check for the D gain
        output += derivative
        // next apply the kF gain
        output += (this.constants.kF * setPoint)
        return output
    }

}