package frc.team6223.arsenalFramework.drive

import frc.team6223.arsenalFramework.hardware.ArsenalNavXMicro
import frc.team6223.arsenalFramework.hardware.motor.MotorControlMode
import frc.team6223.arsenalFramework.logging.Loggable
import frc.team6223.arsenalFramework.software.units.Distance
import frc.team6223.arsenalFramework.software.units.Velocity


/**
 * A [DriveController] takes input from the robot and converts it to output.
 *
 * Why use controllers for the DriveSystem? It removes the complexity in individual commands and contains the user to
 * thinking about just _where the robot needs to go_ based on viable input from the encoder and gyroscope. In turn,
 * the Commands and CommandGroups can focus on _how the robot completes tasks_ on the field, without needing to worry
 * about how the robot will get there.
 *
 */
interface DriveController: Loggable {

    /**
     * Calculates left and right motor output based on motor controller/gyroscope input.
     *
     * @param controllerInput The input from the motor controllers
     *
     * @return The output as calculated by the DriveController
     */
    fun calculateMotorOutput(controllerInput: ControllerInput): DriveControllerOutput

    /**
     * Called when the DriveController is started.
     *
     * Note that this does not necessarily line up with the beginning of a command or command group, and it is only to
     * be used for logging work and work necessary for the function of the DriveController.
     *
     * @param leftInitial The initial encoder distance of the left encoder
     * @param rightInitial The initial encoder distance of the right encoder
     */
    fun start(leftInitial: Int, rightInitial: Int)

    /**
     * Called when the DriveController is stopped.
     *
     * Note that this does not necessarily line up with the end of a command or command group, and it is only to
     * clean up the resources that may have been allocated by the DriveController
     */
    fun stop()

    /**
     * Called if a command or command group that uses a controller must determine if it is stopped.
     *
     * @return Whether or not the drive controller is stopped.
     */
    fun isFinished(): Boolean

}

/**
 * An frame of output for the robot, provided by a [DriveController]
 *
 * @param controlMode The [MotorControlMode] for the current frame
 * @param left The output for the left side of the drive system
 * @param right The output for the right side of the drive system
 */
data class DriveControllerOutput(val controlMode: MotorControlMode, val left: Double, val right: Double)

/**
 * A frame of output from the robot, provided by an [ArsenalDrive]
 *
 * @param leftEncoder The distance the left encoder has traveled
 * @param leftEncoderRate The velocity of the left side of the drive system
 * @param rightEncoder The distance the right encoder has traveled
 * @param rightEncoderRate The velocity of the right side of the drive system
 * @param yawRotation The current yaw position (relative to the start of the robot) of the complete robot
 * @param yawRate The current rate of change of the raw (relative to the start of the robot) of the complete robot
 * @param pitchRotation The current pitch position of the complete robot
 * @param rollRotation The current roll position of the complete robot
 */
data class ControllerInput(val leftEncoder: Distance, val leftEncoderRate: Velocity,
                           val rightEncoder: Distance, val rightEncoderRate: Velocity,
                           val rawLeftEncoder: Double, val rawRightEncoder: Double,
                           val yawRotation: Float, val yawRate: Double,
                           val pitchRotation: Float,
                           val rollRotation: Float) {

    /**
     * An alternate constructor that uses the NavX directly
     */
    constructor(leftEncoder: Distance, leftRawEncoder: Double, leftEncoderRate: Velocity, rightEncoder: Distance,
                rightRawEncoder: Double, rightEncoderRate: Velocity,
                navX: ArsenalNavXMicro): this(leftEncoder, leftEncoderRate, rightEncoder, rightEncoderRate,
            leftRawEncoder, rightRawEncoder,
            navX.yaw, navX.rate,
            navX.pitch,
            navX.roll)
}