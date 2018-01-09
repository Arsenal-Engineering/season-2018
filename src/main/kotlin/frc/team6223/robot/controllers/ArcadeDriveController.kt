package frc.team6223.robot.controllers

import com.ctre.phoenix.motorcontrol.ControlMode
import edu.wpi.first.wpilibj.Joystick
import frc.team6223.utils.drivecontroller.ControllerInput
import frc.team6223.utils.drivecontroller.DriveController
import frc.team6223.utils.drivecontroller.DriveControllerOutput

class ArcadeDriveController(val joystick: Joystick): DriveController {

    override fun calculateMotorOutput(controllerInput: ControllerInput): DriveControllerOutput {
        // set the DriveControllerOutput based on the controller input

        var x = joystick.x;
        var y = joystick.y;

        // Square the inputs (while preserving the sign) to increase fine control
        // while permitting full power.
        y = Math.copySign(y * y, y)
        x = Math.copySign(x * x, x)

        y = when {
            y >= 1.0 -> 1.0
            y <= -1.0 -> -1.0
            else -> y
        }

        x = when {
            x >= 1.0 -> 1.0
            x <= -1.0 -> -1.0
            else -> x
        }

        val maxInput = Math.copySign(Math.max(Math.abs(y), Math.abs(x)), y)

        return when {
            // If we're moving forward, we must be in the 1st or 2nd quadrant
            y >= 0.0 ->
                when {
                    // If we're turning right (or moving forward with no turning), we're in the 1st quadrant
                    x >= 0.0 -> DriveControllerOutput(ControlMode.PercentOutput, maxInput, y - x)
                    // Otherwise, we're in the second quadrant
                    else -> DriveControllerOutput(ControlMode.PercentOutput, y + x, maxInput)
                }
            else ->
                when {
                    // If we're turning right (or moving forward with no turning, we're in the 3rd quadrant
                    x >= 0.0 -> DriveControllerOutput(ControlMode.PercentOutput, y + x, maxInput)
                    // Otherwise, we're in the fourth quadrant
                    else -> DriveControllerOutput(ControlMode.PercentOutput, maxInput, y - x)
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