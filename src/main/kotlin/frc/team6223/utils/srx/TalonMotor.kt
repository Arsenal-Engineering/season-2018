package frc.team6223.utils.srx

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.FeedbackDevice
import com.ctre.phoenix.motorcontrol.SensorCollection
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import frc.team6223.utils.units.Distance
import frc.team6223.utils.units.Velocity

class TalonMotor(talonId: Int, quadratureEnabled: Boolean = false) {
    private val talonSrx: TalonSRX  = TalonSRX(talonId)
    //todo: followers should be managed by methods
    val followers: List<FollowerSRX> = ArrayList()

    private var sensorCollection: SensorCollection? = null

    val position: Distance
        get() {
            return Distance.convertMagPulseToDistance(sensorCollection?.quadraturePosition ?: 0)
        }

    val velocity: Velocity
        get() {
            return Velocity.convertMagPulseRateToVelocity(sensorCollection?.quadratureVelocity ?: 0)
        }

    fun set(mode: MotorControlMode = MotorControlMode.VoltagePercentOut, percentOut: Double) {
        when(mode) {
            MotorControlMode.ExperimentalMotionProfiling ->
                throw UnsupportedOperationException("Motion Profiling is experimental and uses it's own separate API.")
            else -> talonSrx.set(ControlMode.PercentOutput, percentOut)
        }
    }


    inner class FollowerSRX(followerId: Int) {
        private val follower: TalonSRX = TalonSRX(followerId)
        init {
            follower.follow(this@TalonMotor.talonSrx)
        }
    }

    init {
        if (quadratureEnabled) {
            // we have CTRE mag encoder, swap to relative mode
            this.talonSrx.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0)
            // HALVE the period of the frame time: from 160 -> 80
            // This should change frequency
            this.talonSrx.setStatusFramePeriod(StatusFrameEnhanced.Status_3_Quadrature, 80, 0)
            this.sensorCollection = talonSrx.sensorCollection

        }
    }

}

/**
 * The supported control modes for all [TalonMotor]'s.
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