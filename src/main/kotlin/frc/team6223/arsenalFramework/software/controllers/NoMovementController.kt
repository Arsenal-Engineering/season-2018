package frc.team6223.arsenalFramework.software.controllers

import frc.team6223.arsenalFramework.drive.ControllerInput
import frc.team6223.arsenalFramework.drive.DriveController
import frc.team6223.arsenalFramework.drive.DriveControllerOutput
import frc.team6223.arsenalFramework.hardware.motor.MotorControlMode
import frc.team6223.arsenalFramework.software.units.Distance

/**
 * A controller to continually tell the motor that there is no movement.
 */
class NoMovementController: DriveController {
    override fun calculateMotorOutput(controllerInput: ControllerInput): DriveControllerOutput {
        return DriveControllerOutput(MotorControlMode.VoltagePercentOut, 0.0, 0.0)
    }

    override fun start(leftInitial: Int, rightInitial: Int) {
        println("No Movement Controller enabled")
    }

    override fun stop() {
        println("Disabling No Movement Controller")
    }

    override fun isFinished(): Boolean {
        return false
    }

    override fun dashboardPeriodic() {}
}