package frc.team6223.arsenalFramework.software.controllers

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.team6223.arsenalFramework.drive.ControllerInput
import frc.team6223.arsenalFramework.drive.DriveController
import frc.team6223.arsenalFramework.drive.DriveControllerOutput
import frc.team6223.arsenalFramework.hardware.motor.MotorControlMode
import frc.team6223.arsenalFramework.software.PIDFConstants
import frc.team6223.arsenalFramework.software.PIDFController
import frc.team6223.arsenalFramework.software.units.Distance
import frc.team6223.arsenalFramework.software.units.DistanceUnits

/**
 * A controller that moves a certain distance based on PID control
 *
 * @param dist The setpoint distance in feet
 */
class PIDDistanceController(private val dist: Double): DriveController {

    /**
     * The PIDF constants for the robot
     */
    private val pidfConstants = PIDFConstants(1.0, 1.0, 0.0, 0.0)

    /**
     * The internal controller for PID control
     */
    private val pidController = PIDFController(pidfConstants, 0.0)

    override fun calculateMotorOutput(controllerInput: ControllerInput): DriveControllerOutput {
        val out = this.pidController.runController(controllerInput.leftEncoder.numericValue(DistanceUnits.FEET))
        // todo: separate left and right rates
        return DriveControllerOutput(MotorControlMode.PIDDistance, out, out)
    }

    override fun start(leftInitial: Int, rightInitial: Int) {
        println("Moving $dist ft")
        pidController.setPoint = dist
    }

    override fun stop() {
        if (!pidController.isFinished) {
            println("Did not complete movement, currentError is: " + pidController.currentError)
        } else {
            println("Moved $dist ft")
        }
    }

    override fun isFinished(): Boolean {
        return pidController.isFinished
    }

    override fun dashboardPeriodic() {
        SmartDashboard.putString("Current Controller", "DistanceController")
        SmartDashboard.putNumber("Distance Target", dist)
        SmartDashboard.putNumber("Current Controller kP", pidfConstants.kP)
        SmartDashboard.putNumber("Current Controller kI", pidfConstants.kI)
        SmartDashboard.putNumber("Current Controller kD", pidfConstants.kD)
        SmartDashboard.putNumber("Current Controller kF", pidfConstants.kF)
    }
}
