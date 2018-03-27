package frc.team6223.arsenalFramework.software.controllers

import edu.wpi.first.wpilibj.Timer
import edu.wpi.first.wpilibj.internal.HardwareTimer
import frc.team6223.arsenalFramework.drive.ControllerInput
import frc.team6223.arsenalFramework.drive.DriveController
import frc.team6223.arsenalFramework.drive.DriveControllerOutput
import frc.team6223.arsenalFramework.hardware.currentTimeSec
import frc.team6223.arsenalFramework.hardware.motor.MotorControlMode
import frc.team6223.arsenalFramework.software.units.Distance
import frc.team6223.arsenalFramework.software.units.Time
import frc.team6223.arsenalFramework.software.units.TimeUnits

class ForceMovementController(private val deltaTime: Time, private val leftMotorOut: Double, private val rightMotorOut: Double):
        DriveController {

    private lateinit var startTime: Time

    override fun calculateMotorOutput(controllerInput: ControllerInput): DriveControllerOutput {
        return DriveControllerOutput(MotorControlMode.VoltagePercentOut, leftMotorOut, rightMotorOut)
    }

    override fun start(leftInitial: Int, rightInitial: Int) {
        println("Starting FMC")
        startTime = currentTimeSec()
    }

    override fun stop() {
        println("Stopping FMC")
    }

    override fun isFinished(): Boolean {
        return (currentTimeSec() - startTime).numericValue(TimeUnits.SECONDS) >= deltaTime.numericValue(TimeUnits.SECONDS)
    }

    override fun dashboardPeriodic() {

    }
}