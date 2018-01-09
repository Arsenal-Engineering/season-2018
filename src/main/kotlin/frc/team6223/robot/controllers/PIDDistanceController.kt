package frc.team6223.robot.controllers

import com.ctre.phoenix.motorcontrol.ControlMode
import frc.team6223.utils.drivecontroller.ControllerInput
import frc.team6223.utils.drivecontroller.DriveController
import frc.team6223.utils.drivecontroller.DriveControllerOutput
import frc.team6223.utils.pid.PIDFConstants
import frc.team6223.utils.pid.PIDFController

class PIDDistanceController(val dist: Double): DriveController {

    // completely not tuned and literally just a guess
    private val pidController = PIDFController(PIDFConstants(1.0, 1.0, 0.0, 0.0), 0.0)

    override fun calculateMotorOutput(controllerInput: ControllerInput): DriveControllerOutput {
        val out = this.pidController.runController(controllerInput.leftRotations);
        return DriveControllerOutput(ControlMode.PercentOutput, out, out);
    }

    override fun start() {
        println("Moving $dist ft");
        pidController.setPoint = dist
    }

    override fun stop() {
        println("Moved $dist ft");
    }
}