package frc.team6223.robot.controllers

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.Joystick
import frc.team6223.utils.drivecontroller.ControllerInput
import frc.team6223.utils.drivecontroller.DriveController
import frc.team6223.utils.drivecontroller.DriveControllerOutput

class ArcadeDriveController(val joystick: Joystick): DriveController {

    override fun calculateMotorOutput(controllerInput: ControllerInput): DriveControllerOutput {
        // set the DriveControllerOutput based on the controller input

        var moveValue = joystick.y;
        var rotateValue = joystick.x;

        // Square the inputs (while preserving the sign) to increase fine control
        // while permitting full power.
        rotateValue = Math.copySign(rotateValue * rotateValue, rotateValue)
        moveValue = Math.copySign(moveValue * moveValue, moveValue)

        rotateValue = when {
            rotateValue >= 1.0 -> 1.0
            rotateValue <= -1.0 -> -1.0
            else -> rotateValue
        }

        moveValue = when {
            moveValue >= 1.0 -> 1.0
            moveValue <= -1.0 -> -1.0
            else -> moveValue
        }

        val maxInput = Math.copySign(Math.max(Math.abs(rotateValue), Math.abs(moveValue)), rotateValue)

        return when {
            // If we're moving forward, we must be in the 1st or 2nd quadrant
            rotateValue >= 0.0 ->
                when {
                    // If we're turning right (or moving forward with no turning), we're in the 1st quadrant
                    moveValue >= 0.0 -> DriveControllerOutput(ControlMode.PercentOutput, maxInput, rotateValue - moveValue)
                    // Otherwise, we're in the second quadrant
                    else -> DriveControllerOutput(ControlMode.PercentOutput, rotateValue + moveValue, maxInput)
                }
            else ->
                when {
                    // If we're turning right (or moving forward with no turning, we're in the 3rd quadrant
                    moveValue >= 0.0 -> DriveControllerOutput(ControlMode.PercentOutput, rotateValue + moveValue, maxInput)
                    // Otherwise, we're in the fourth quadrant
                    else -> DriveControllerOutput(ControlMode.PercentOutput, maxInput, rotateValue - moveValue)
                }
        }
    }

    override fun start() {
        println("Starting Arcade Drive");
    }

    override fun stop() {
        println("Stopping Arcade Drive");
    }
}