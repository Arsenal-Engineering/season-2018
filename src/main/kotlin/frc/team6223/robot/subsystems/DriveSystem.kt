package frc.team6223.robot.subsystems

import com.kauailabs.navx.frc.AHRS
import edu.wpi.first.wpilibj.CounterBase
import edu.wpi.first.wpilibj.Encoder
import edu.wpi.first.wpilibj.SerialPort
import edu.wpi.first.wpilibj.command.Subsystem
import frc.team6223.robot.conf.*
import frc.team6223.robot.controllers.DriveController
import frc.team6223.robot.controllers.DriveControllerInput
import frc.team6223.utils.srx.TalonMotor

class DriveSystem(driveMode: DriveController) : Subsystem() {

    private val navX = AHRS(SerialPort.Port.kMXP)

    var driveMode: DriveController = driveMode
        set(value) {
            this.driveMode.stop()
            field = value
            this.driveMode.start()
        }

    private val leftController = TalonMotor(LEFT_DRIVE_CONTROLLER)
    private val rightController = TalonMotor(RIGHT_DRIVE_CONTROLLER)

    private val leftEncoder = Encoder(LEFT_MOTOR_ENCODER_CHANNEL_A, LEFT_MOTOR_ENCODER_CHANNEL_B, false, CounterBase.EncodingType.k4X)
    private val rightEncoder = Encoder(RIGHT_MOTOR_ENCODER_CHANNEL_A, RIGHT_MOTOR_ENCODER_CHANNEL_B, false, CounterBase.EncodingType.k4X)

    override fun initDefaultCommand() {

    }

    fun driveMotors() {
        val driveOut = this.driveMode.calculateMotorOutput(DriveControllerInput(leftEncoder, rightEncoder))
        leftController.setPercentOut(driveOut.left)
        rightController.setPercentOut(driveOut.right)
    }



}