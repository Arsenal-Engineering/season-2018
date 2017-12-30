package frc.team6223.utils

public data class PIDFConstants(val kP: Double, val kI: Double?, val kD: Double?, val kF: Double)

public class PIDFController(val constants: PIDFConstants) {

    var setPoint: Double = 0.0;
    private var previousError: Double = 0.0;

    public fun runController(current: Double): Double {
        var output: Double = 0.0;
        // first calculate the P, which should always exist
        output = this.constants.kP * (setPoint - current);
        // next check for the I gain
        output += (this.constants.kI?.times(previousError + (setPoint - current)) ?: 0.0);
        // next check for the D gain
        output += (this.constants.kD?.times((setPoint - current) - previousError) ?: 0.0);
        // next apply the kF gain
        output += (this.constants.kF * setPoint);
        return output;
    }

}