package frc.team6223.arsenalFramework.software.controllers

import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.team6223.arsenalFramework.drive.ControllerInput
import frc.team6223.arsenalFramework.drive.DriveController
import frc.team6223.arsenalFramework.drive.DriveControllerOutput
import frc.team6223.arsenalFramework.hardware.motor.MotorControlMode
import frc.team6223.arsenalFramework.operator.ArsenalJoystick
import frc.team6223.arsenalFramework.software.units.Distance

/**
 * A controller that moves the robot based on the joystick inputs.
 *
 * @param joystick The primary driving joystick
 */
class ArcadeDriveController(private val joystick: Joystick, private val scaleFactor: Double = 1.0): DriveController {

    /**
     * Calculate the motor output based on the joystick provided
     *
     * @param controllerInput The input from the robot
     *
     * @return The frame of output for the robot
     */
    override fun calculateMotorOutput(controllerInput: ControllerInput): DriveControllerOutput {
        // set the DriveControllerOutput based on the controller input

        var rotateValue = joystick.x * scaleFactor
        var moveValue = -joystick.y * scaleFactor

        // Square the inputs (while preserving the sign) to increase fine control
        // while permitting full power.
        moveValue = Math.copySign(moveValue * moveValue, moveValue)
        rotateValue = Math.copySign(rotateValue * rotateValue, rotateValue)

        moveValue = when {
            moveValue >= 1.0 -> 1.0
            moveValue <= -1.0 -> -1.0
            else -> moveValue
        }

        rotateValue = when {
            rotateValue >= 1.0 -> 1.0
            rotateValue <= -1.0 -> -1.0
            else -> rotateValue
        }

        val maxInput = Math.copySign(Math.max(Math.abs(moveValue), Math.abs(rotateValue)), moveValue)

        val leftOut: Double
        val rightOut: Double

        when {
            // If we're moving forward, we must be in the 1st or 2nd quadrant
            moveValue >= 0.0 ->
                when {
                    // If we're turning right (or moving forward with no turning), we're in the 1st quadrant
                    rotateValue >= 0.0 -> { leftOut = maxInput; rightOut = moveValue - rotateValue; }
                    // Otherwise, we're in the second quadrant
                    else -> { leftOut = rotateValue + moveValue; rightOut = maxInput; }
                }
            else ->
                when {
                    // If we're turning right (or moving forward with no turning, we're in the 3rd quadrant
                    rotateValue >= 0.0 -> { leftOut = rotateValue + moveValue; rightOut = maxInput; }
                    // Otherwise, we're in the fourth quadrant
                    else -> { leftOut = maxInput; rightOut = moveValue - rotateValue; }
                }
        }

        SmartDashboard.putNumber("RotateValue", rotateValue)
        SmartDashboard.putNumber("MoveValue", moveValue)
        SmartDashboard.putNumber("Left Output", leftOut)
        SmartDashboard.putNumber("Right Output", rightOut)

        return DriveControllerOutput(MotorControlMode.VoltagePercentOut, leftOut, rightOut)
    }

    override fun start(leftInitial: Int, rightInitial: Int) {
        println("Starting Arcade Drive")
    }

    override fun stop() {
        println("Stopping Arcade Drive")
    }

    override fun isFinished(): Boolean {
        return false
    }

    override fun dashboardPeriodic() {
        SmartDashboard.putString("CurrentController", "ArcadeController")
        SmartDashboard.putNumber("CurrentControllerMoveValue", joystick.y)
        SmartDashboard.putNumber("CurrentControllerRotateValue", joystick.x)
    }
}
