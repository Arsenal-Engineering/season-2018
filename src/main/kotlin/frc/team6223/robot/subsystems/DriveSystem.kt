package frc.team6223.robot.subsystems

import edu.wpi.first.wpilibj.CounterBase
import edu.wpi.first.wpilibj.Encoder
import edu.wpi.first.wpilibj.Spark
import edu.wpi.first.wpilibj.command.Subsystem
import frc.team6223.robot.conf.*
import frc.team6223.robot.controllers.DriveController
import frc.team6223.robot.controllers.DriveControllerInput

class DriveSystem(driveMode: DriveController) : Subsystem() {

    private var driveMode: DriveController = driveMode
        set(value) {
            this.driveMode.stop()
            field = value
            this.driveMode.start()
        }

    private val leftController = Spark(LEFT_DRIVE_CONTROLLER)
    private val rightController = Spark(RIGHT_DRIVE_CONTROLLER)

    private val leftEncoder = Encoder(LEFT_MOTOR_ENCODER_CHANNEL_A, LEFT_MOTOR_ENCODER_CHANNEL_B, false, CounterBase.EncodingType.k4X)
    private val rightEncoder = Encoder(RIGHT_MOTOR_ENCODER_CHANNEL_A, RIGHT_MOTOR_ENCODER_CHANNEL_B, false, CounterBase.EncodingType.k4X)

    override fun initDefaultCommand() {

    }

    public fun driveMotors() {
        val driveOut = this.driveMode.calculateMotorOutput(DriveControllerInput(leftEncoder, rightEncoder))
        leftController.set(driveOut.left)
        rightController.set(driveOut.right)
    }



}