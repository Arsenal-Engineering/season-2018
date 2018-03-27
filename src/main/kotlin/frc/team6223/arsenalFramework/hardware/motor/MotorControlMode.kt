package frc.team6223.arsenalFramework.hardware.motor

/**
 * The supported control modes for all [ArsenalTalon]'s.
 *
 * If a method is labeled as Closed Loop, it _must_ still receive input. The reasoning behind marking something as
 * closed or open loop is for checking if the motor controller can be finished (closed-loop can be, open-loop cannot).
 */
enum class MotorControlMode {
    /**
     * Open Loop Voltage Percentage Out
     */
    VoltagePercentOut,
    /**
     * Closed Loop [frc.team6223.utils.pid.PIDFController] Distance
     */
    PIDDistance,
    /**
     * Closed Loop [frc.team6223.utils.pid.PIDFController] Velocity
     */
    PIDVelocity,
    /**
     * Closed Loop [frc.team6223.utils.pid.PIDFController] Turn to Relative Angle
     */
    PIDRelativeAngle,
    /**
     * Closed Loop Motion Profiling (experimental!)
     */
    ExperimentalMotionProfiling,

}