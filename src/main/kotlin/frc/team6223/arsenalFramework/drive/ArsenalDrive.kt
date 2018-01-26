package frc.team6223.arsenalFramework.drive

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.command.Subsystem
import frc.team6223.arsenalFramework.hardware.ArsenalNavXMicro
import frc.team6223.arsenalFramework.hardware.ArsenalTalon

/**
 * A subsystem for running 2 [ArsenalTalon]'s using a [DriveController].
 *
 * The subsystem requires a NavX-MXP [ArsenalNavXMicro] passed into it so as to provide the pitch/yaw/roll data to the drive
 * controller. Furthermore, an unlimited amount of [ArsenalTalon.FollowerSRX]'s can be attached to the [ArsenalTalon]'s
 * provided
 *
 * @param driveMode The [DriveController] that the subsystem initially starts running if it is not changed
 * @property navX The [ArsenalNavXMicro] that is attached to the RoboRIO. It is essential that this NavX be in the
 * center of the drive train
 * @property leftController The [ArsenalTalon] that runs the left side of the drive train
 * @property rightController The [ArsenalTalon] that runs the right side of the drive train
 * @constructor Creates a basic drive subsystem that runs on 2 Talon controllers
 *
 */
class ArsenalDrive(driveMode: DriveController,
                   private val navX: ArsenalNavXMicro,
                   private val leftController: ArsenalTalon,
                   private val rightController: ArsenalTalon): Subsystem() {

    /**
     * The current [DriveController] used by the [ArsenalDrive] to move the robot.
     *
     * When replaced, the old [DriveController] is stopped and the new [DriveController] is enabled.
     */
    var driveMode: DriveController = driveMode
        set(value) {
            this.driveMode.stop()
            field = value
            this.driveMode.start()
        }

    override fun initDefaultCommand() {}

    /**
     * The primary method to run the [DriveController] on the [ArsenalDrive].
     */
    fun driveMotors() {
        val driveOut = this.driveMode.calculateMotorOutput(
                ControllerInput(
                        leftController.position, leftController.velocity,
                        rightController.position, rightController.velocity,
                        navX)
        )
        leftController.set(driveOut.controlMode, driveOut.left)
        rightController.set(driveOut.controlMode, driveOut.right)

    }



}