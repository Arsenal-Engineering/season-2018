package frc.team6223.arsenalFramework.hardware

import com.ctre.phoenix.motorcontrol.ControlMode
import com.ctre.phoenix.motorcontrol.FeedbackDevice
import com.ctre.phoenix.motorcontrol.SensorCollection
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.team6223.arsenalFramework.logging.Loggable
import frc.team6223.arsenalFramework.software.units.*

/**
 * Manages a [TalonSRX] in order to keep the code complexity low.
 *
 * The [TalonSRX] class provides a large amount of utilities that are unnecessarily complicated. The [ArsenalTalon]
 * class simplifies the inner workings of a Talon in order to allow for ease of use. The [ArsenalTalon] class assumes
 * you are using a CTRE Magnetic Encoder - support for more encoder classes may come eventually. Furthermore, the
 * [ArsenalTalon] class has simple follower support, and it has a set of useful default control modes for open- and
 * closed- loop control that set a flag usable by other pieces of code.
 *
 * The abstraction layer created by the [ArsenalTalon] should allow for ease of use by new coders and new teams alike,
 * while still allowing for powerful creations.
 *
 * @param talonId The identifier for the [TalonSRX] to initialize (should be between 0 and 62)
 * @param quadratureEnabled If the CTRE Magnetic Encoder is attached to the Talon
 * @param startInverted If the Talon should start inverted or not (ie motor leads are flipped)
 * @param startingSensorPhase The starting sensor phase of the Talon
 */
class ArsenalTalon(private val talonId: Int, quadratureEnabled: Boolean = false,
                   startInverted: Boolean = false,
                   startingSensorPhase: Boolean = false,
                   var invertSensorOutput: Boolean = false): Loggable {
    /**
     * The internal Talon
     */
    private val talonSrx: TalonSRX = TalonSRX(talonId)

    /**
     * A list of the followers this Talon has attached to it
     */
    private val followers: MutableList<FollowerSRX> = ArrayList()

    /**
     * The internal sensor collection of the Talon. Only used if quadrature encoding is enabled
     */
    private var sensorCollection: SensorCollection? = null

    /**
     * The current internal control mode. This setting does NOT affect how you send data to the Talon.
     *
     * Instead, the internal control mode will be used in the future to provide completion status to commands and
     * linked subsystems.
     */
    var currentInternalControlMode: MotorControlMode = MotorControlMode.VoltagePercentOut
        private set

    /**
     * If the motor is currently inverted.
     *
     * Setting the value to true will invert the positive and negative output. Do this only if the motor turns the
     * opposite of the intended direction
     */
    var inverted: Boolean = talonSrx.inverted
        set(value) {
            this.talonSrx.inverted = value
            field = value
        }
        get() {
            return this.talonSrx.inverted
        }

    /**
     * The current encoder position, translated from Talon native units (encoder ticks) to inches.
     */
    val position: Distance
        get() {
            SmartDashboard.putNumber("rawPos ${talonId}", sensorCollection?.quadraturePosition?.toDouble() ?: 0.0)
            if (invertSensorOutput) {
                return Distance.convertMagPulseToDistance(sensorCollection?.quadraturePosition?.apply { -this } ?: 0)
            } else {
                return Distance.convertMagPulseToDistance(sensorCollection?.quadraturePosition ?: 0)
            }
        }

    val rawPosition: Double
        get() = sensorCollection?.quadraturePosition?.toDouble() ?: 0.0

    /**
     * The current encoder rate (velocity), translated from Talon native units per 100 ms to inches per millisecond.
     */
    val velocity: Velocity
        get() {
            return Velocity.convertMagPulseRateToVelocity(sensorCollection?.quadratureVelocity ?: 0)
        }

    /**
     * Set the current voltage percentage output of the Talon.
     *
     * The number passed to this method should be between -1 and 1, and it is the voltage percent output.
     * If you're not sure which control mode you should be using, VoltagePercentOut is your best bet. This disables
     * any extra closed-loop features that may be implemented.
     *
     * @param mode The [MotorControlMode] to use
     * @param percentOut A number between -1 and 1 that is the relative percentage output to push to the Talon. If
     * negative doesn't make the motor move clockwise, ensure the Talon is inverted correctly
     */
    fun set(mode: MotorControlMode = MotorControlMode.VoltagePercentOut, percentOut: Double) {
        currentInternalControlMode = mode
        talonSrx.set(ControlMode.PercentOutput, percentOut)
    }

    /**
     * Add a new follower to the Talon
     *
     * Given a Talon ID number, this method adds a new [FollowerSRX] to the follower list, which is then following
     * the exact voltage percent output as this Talon.
     */
    fun addFollower(followerId: Int) {
        followers.add(FollowerSRX(followerId))
    }

    /**
     * Logs the current position and velocity of the talon, as well as if the talon is inverted and the internal
     * control mode.
     */
    override fun dashboardPeriodic() {
        SmartDashboard.putNumber("Talon {$talonId} Position", position.numericValue(DistanceUnits.FEET))
        SmartDashboard.putNumber("Talon {$talonId} Velocity (ft/sec)",
                velocity.numericValue(RateScaleFactor(DistanceUnits.FEET, TimeUnits.SECONDS)))
        SmartDashboard.putBoolean("Talon {$talonId} Inverted", inverted)
        SmartDashboard.putString("Talon {$talonId} MCM", currentInternalControlMode.toString())
    }

    /**
     * Set the encoder phase of the internal SRX.
     */
    fun setEncoderPhase(phase: Boolean) {
        this.talonSrx.setSensorPhase(phase)
    }

    /**
     * Reset the encoder to position 0
     */
    fun resetEncoder() {
        sensorCollection?.setQuadraturePosition(0, 0)
    }

    /**
     * The internal class for the TalonSRX followers.
     */
    inner class FollowerSRX(followerId: Int) {
        private val follower: TalonSRX = TalonSRX(followerId)
        init {
            follower.follow(this@ArsenalTalon.talonSrx)
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

        this.talonSrx.inverted = startInverted
        this.talonSrx.setSensorPhase(startingSensorPhase)
    }

}

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
