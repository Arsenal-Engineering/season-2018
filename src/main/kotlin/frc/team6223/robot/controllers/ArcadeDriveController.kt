package frc.team6223.robot.controllers

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.Joystick
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import frc.team6223.utils.drivecontroller.ControllerInput
import frc.team6223.utils.drivecontroller.DriveController
import frc.team6223.utils.drivecontroller.DriveControllerOutput
import frc.team6223.utils.srx.MotorControlMode
import frc.team6223.utils.units.Distance

class ArcadeDriveController(val joystick: Joystick): DriveController {

    override fun calculateMotorOutput(controllerInput: ControllerInput): DriveControllerOutput {
        // set the DriveControllerOutput based on the controller input

        var rotateValue = joystick.x
        var moveValue = -joystick.y

        // apply dead zone
        if (moveValue in -0.25..0.25) {
            moveValue = 0.0
        }

        if (rotateValue in -0.25..0.25) {
            rotateValue = 0.0
        }

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

    override fun start(leftInitial: Distance, rightInitial: Distance) {
        println("Starting Arcade Drive");
    }

    override fun stop() {
        println("Stopping Arcade Drive");
    }
}