package frc.team6223.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.CounterBase
import edu.wpi.first.wpilibj.Encoder
import edu.wpi.first.wpilibj.SerialPort
import edu.wpi.first.wpilibj.command.Subsystem
import frc.team6223.robot.conf.*
import frc.team6223.robot.teleoperated.TeleopUtilities
import frc.team6223.utils.drivecontroller.ControllerInput
import frc.team6223.utils.drivecontroller.DriveController
import frc.team6223.utils.srx.MotorControlMode
import frc.team6223.utils.srx.TalonMotor

/**
 * A subsystem for running 2 [TalonMotor]'s using a [DriveController].
 *
 * The subsystem requires a NavX-MXP [AHRS] passed into it so as to provide the pitch/yaw/roll data to the drive
 * controller. Furthermore, an unlimited amount of [TalonMotor.FollowerSRX]'s can be attached to the [TalonMotor]'s
 * provided
 *
 * @param driveMode The [DriveController] that the subsystem initially starts running if it is not changed
 * @property navX The [AHRS] that is attached to the RoboRIO. It is essential that this NavX be in the center of the
 * drive train
 * @property leftController The [TalonMotor] that runs the left side of the drive train
 * @property rightController The [TalonMotor] that runs the right side of the drive train
 * @constructor Creates a basic drive subsystem that runs on 2 Talon controllers
 *
 */
class DriveSystem(driveMode: DriveController,
                  private val navX: AHRS,
                  private val leftController: TalonMotor,
                  private val rightController: TalonMotor): Subsystem() {

    /**
     * The current [DriveController] used by the [DriveSystem] to move the robot.
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
     * The primary method to run the [DriveController] on the [DriveSystem].
     */
    fun driveMotors() {
        val driveOut = this.driveMode.calculateMotorOutput(
                // todo: test talon's with proper encoder rates.
                ControllerInput(0.0, 0.0, 0.0, 0.0, navX)
        )
        leftController.set(driveOut.controlMode, driveOut.left)
        rightController.set(driveOut.controlMode, driveOut.right)

    }

    fun dashboardPeriodic() {
        TeleopUtilities.putValuesOnDash(leftController, rightController, navX)
    }



}