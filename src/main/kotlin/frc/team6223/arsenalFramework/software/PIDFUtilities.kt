package frc.team6223.arsenalFramework.software

import frc.team6223.arsenalFramework.hardware.currentTimeSec
import frc.team6223.arsenalFramework.software.units.Time
import frc.team6223.arsenalFramework.software.units.TimeUnits

public data class PIDFConstants(val kP: Double, val kI: Double, val kD: Double, val kF: Double)

public class PIDFController(constants: PIDFConstants, target: Double) {

    var constants: PIDFConstants = constants
        set(value) {
            field = value;

            integralGain = 0.0;
            lastTime = Time(Double.NaN, TimeUnits.SECONDS);
            lastError = Double.NaN;
        };

    var setPoint: Double = target
        set(value) {
            field = value;

            integralGain = 0.0;
            lastTime = Time(Double.NaN, TimeUnits.SECONDS);
            lastError = Double.NaN;
        };

    var currentError = setPoint
        private set

    var isFinished = (setPoint - currentError) == 0.0

    private var lastError: Double = Double.NaN;
    private var lastTime = Time(Double.NaN, TimeUnits.SECONDS);

    private var integralGain: Double = 0.0;

    fun runController(current: Double): Double {

        // calculate constants for this iteration of the loop
        this.currentError = setPoint - current;
        val deltaTime = currentTimeSec - lastTime
        this.lastTime = currentTimeSec

        val calculated: Double = this.constants.kD * (currentError - this.lastError / deltaTime.numericValue(TimeUnits.SECONDS));
        val derivative = if (calculated != 0.0 && calculated != Double.NaN) {
            calculated
        } else {
            0.0
        }

        integralGain += currentError;

        // first calculate the P, which should always exist
        var output = (this.constants.kP * currentError);
        // next check for the I gain
        output += (this.constants.kI * integralGain);
        // next check for the D gain
        output += derivative;
        // next apply the kF gain
        output += (this.constants.kF * setPoint);
        return output;
    }

}