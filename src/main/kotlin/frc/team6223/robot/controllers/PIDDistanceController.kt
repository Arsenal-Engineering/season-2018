package frc.team6223.robot.controllers

import frc.team6223.utils.PIDConstants
import frc.team6223.utils.PIDController

class PIDDistanceController(val dist: Double): DriveController {

    // completely not tuned and literally just a guess
    private val pidController = PIDController(PIDConstants(1.0, 1.0, null))

    override fun calculateMotorOutput(controllerInput: DriveControllerInput): DriveControllerOutput {
        val out = this.pidController.runController(controllerInput.leftEncoder.distance);
        return DriveControllerOutput(out, out);
    }

    override fun start() {
        println("Moving $dist ft");
        pidController.setPoint = dist
    }

    override fun stop() {
        println("Moved $dist ft");
    }
}