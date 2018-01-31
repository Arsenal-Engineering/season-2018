package frc.team6223.robot.controllers

import com.ctre.phoenix.motorcontrol.ControlMode
import frc.team6223.utils.drivecontroller.ControllerInput
import frc.team6223.utils.drivecontroller.DriveController
import frc.team6223.utils.drivecontroller.DriveControllerOutput
import frc.team6223.utils.pid.PIDFConstants
import frc.team6223.utils.pid.PIDFController
import frc.team6223.utils.srx.MotorControlMode
import frc.team6223.utils.units.Distance

class PIDDistanceController(val dist: Double): DriveController {

    // completely not tuned and literally just a guess
    val pidController = PIDFController(PIDFConstants(1.0, 1.0, 0.0, 0.0), 0.0)

    override fun calculateMotorOutput(controllerInput: ControllerInput): DriveControllerOutput {
        val out = this.pidController.runController(controllerInput.leftRotations);
        // todo: separate left and right rates
        return DriveControllerOutput(MotorControlMode.PIDDistance, out, out);
    }

    override fun start(leftInitial: Distance, rightInitial: Distance) {
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