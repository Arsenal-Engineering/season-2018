package frc.team6223.arsenalFramework.software.controllers

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.team6223.arsenalFramework.drive.ControllerInput
import frc.team6223.arsenalFramework.drive.DriveController
import frc.team6223.arsenalFramework.drive.DriveControllerOutput
import frc.team6223.arsenalFramework.hardware.MotorControlMode
import frc.team6223.arsenalFramework.software.PIDFConstants
import frc.team6223.arsenalFramework.software.PIDFController
import frc.team6223.arsenalFramework.software.units.Distance
import frc.team6223.arsenalFramework.software.units.DistanceUnits

class PIDDistanceController(val dist: Double): DriveController {

    // completely not tuned and literally just a guess
    val pidfConstants = PIDFConstants(1.0, 1.0, 0.0, 0.0)
    val pidController = PIDFController(pidfConstants, 0.0)

    override fun calculateMotorOutput(controllerInput: ControllerInput): DriveControllerOutput {
        val out = this.pidController.runController(controllerInput.leftEncoder.numericValue(DistanceUnits.FEET));
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

    override fun isFinished(): Boolean {
        return pidController.isFinished
    }

    override val headers: Array<String>
        get() = arrayOf("ControllerType", "DistanceTarget", "kP", "kI", "kD", "kF")
    override val data: Array<Any>
        get() = arrayOf("DistanceController", dist, pidfConstants.kP, pidfConstants.kI, pidfConstants.kD, pidfConstants.kF)

    override fun dashboardPeriodic() {
        SmartDashboard.putString("Current Controller", "DistanceController")
        SmartDashboard.putNumber("Distance Target", dist)
        SmartDashboard.putNumber("Current Controller kP", pidfConstants.kP)
        SmartDashboard.putNumber("Current Controller kI", pidfConstants.kI)
        SmartDashboard.putNumber("Current Controller kD", pidfConstants.kD)
        SmartDashboard.putNumber("Current Controller kF", pidfConstants.kF)
    }
}
