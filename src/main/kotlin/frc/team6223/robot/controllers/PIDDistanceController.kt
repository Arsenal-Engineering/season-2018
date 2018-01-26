package frc.team6223.robot.controllers

import com.ctre.phoenix.motorcontrol.ControlMode
import frc.team6223.arsenalFramework.drive.ControllerInput
import frc.team6223.arsenalFramework.drive.DriveController
import frc.team6223.arsenalFramework.drive.DriveControllerOutput
import frc.team6223.arsenalFramework.hardware.MotorControlMode
import frc.team6223.arsenalFramework.software.PIDFConstants
import frc.team6223.arsenalFramework.software.PIDFController
import frc.team6223.arsenalFramework.software.units.DistanceUnits

class PIDDistanceController(val dist: Double): DriveController {

    // completely not tuned and literally just a guess
    val pidController = PIDFController(PIDFConstants(1.0, 1.0, 0.0, 0.0), 0.0)

    override fun calculateMotorOutput(controllerInput: ControllerInput): DriveControllerOutput {
        val out = this.pidController.runController(controllerInput.leftEncoder.numericValue(DistanceUnits.FEET));
        // todo: separate left and right rates
        return DriveControllerOutput(MotorControlMode.PIDDistance, out, out);
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
