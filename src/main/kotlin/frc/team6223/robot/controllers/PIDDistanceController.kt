package frc.team6223.robot.controllers

import com.ctre.phoenix.motorcontrol.ControlMode
import frc.team6223.utils.drive.ControllerInput
import frc.team6223.utils.drive.DriveController
import frc.team6223.utils.drive.DriveControllerOutput
import frc.team6223.utils.software.PIDFConstants
import frc.team6223.utils.software.PIDFController

class PIDDistanceController(val dist: Double): DriveController {

    // completely not tuned and literally just a guess
    val pidController = PIDFController(PIDFConstants(1.0, 1.0, 0.0, 0.0), 0.0)

    override fun calculateMotorOutput(controllerInput: ControllerInput): DriveControllerOutput {
        val out = this.pidController.runController(controllerInput.leftEncoder.toDouble());
        // todo: separate left and right rates
        return DriveControllerOutput(ControlMode.PercentOutput, out, out);
    }

    override fun start() {
        println("Moving $dist ft");
        pidController.setPoint = dist
    }

    override fun stop() {
        if (!pidController.isFinished) {
            println("Did not complete movement, currentError is: " + pidController.currentError)
        } else {
            println("Moved $dist ft")
        }
    }
}