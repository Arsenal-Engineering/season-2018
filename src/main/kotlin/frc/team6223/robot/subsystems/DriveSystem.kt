package frc.team6223.robot.subsystems

import edu.wpi.first.wpilibj.Spark
import edu.wpi.first.wpilibj.command.Subsystem
import frc.team6223.robot.conf.LEFT_DRIVE_CONTROLLER
import frc.team6223.robot.conf.RIGHT_DRIVE_CONTROLLER
import frc.team6223.robot.controllers.DriveController

class DriveSystem(driveMode: DriveController) : Subsystem() {

    private var driveMode: DriveController = driveMode
        set(value) {
            this.driveMode.stop()
            field = value
            this.driveMode.start()
        }

    private val leftController = Spark(LEFT_DRIVE_CONTROLLER)
    private val rightController = Spark(RIGHT_DRIVE_CONTROLLER)

    override fun initDefaultCommand() {

    }

    public fun driveMotors() {
        val driveOut = this.driveMode.calculateMotorOutput()
        leftController.set(driveOut.left)
        rightController.set(driveOut.right)
    }



}