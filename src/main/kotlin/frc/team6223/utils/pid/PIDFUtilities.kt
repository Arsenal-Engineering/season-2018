package frc.team6223.utils.pid

import frc.team6223.utils.time.currentTimeSec
import frc.team6223.utils.units.Time
import frc.team6223.utils.units.TimeUnits

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

    private var lastError: Double = Double.NaN;
    private var lastTime = Time(Double.NaN, TimeUnits.SECONDS);

    private var integralGain: Double = 0.0;

    fun runController(current: Double): Double {

        // calculate constants for this iteration of the loop
        val error = setPoint - current;
        val deltaTime = currentTimeSec - lastTime
        this.lastTime = currentTimeSec

        val calculated: Double = this.constants.kD * (error - this.lastError / deltaTime.numericValue(TimeUnits.SECONDS));
        val derivative = if (calculated != 0.0 && calculated != Double.NaN) {
            calculated
        } else {
            0.0
        }

        integralGain += error;

        // first calculate the P, which should always exist
        var output = (this.constants.kP * error);
        // next check for the I gain
        output += (this.constants.kI * integralGain);
        // next check for the D gain
        output += derivative;
        // next apply the kF gain
        output += (this.constants.kF * setPoint);
        return output;
    }

}