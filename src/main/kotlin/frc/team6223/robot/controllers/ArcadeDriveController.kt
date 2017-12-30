package frc.team6223.robot.controllers

import edu.wpi.first.wpilibj.Joystick

class ArcadeDriveController(val joystick: Joystick): DriveController {

    override fun calculateMotorOutput(controllerInput: DriveControllerInput): DriveControllerOutput {
        // set the DriveControllerOutput based on the controller input

        var x = joystick.x;
        var y = joystick.y;

        // Square the inputs (while preserving the sign) to increase fine control
        // while permitting full power.
        y = Math.copySign(y * y, y)
        x = Math.copySign(x * x, x)

        val maxInput = Math.copySign(Math.max(Math.abs(y), Math.abs(x)), y)

        // If we're moving forward, we must be in the 1st or 2nd quadrant
        if (y >= 0.0) {
            // If we're turning right (or moving forward with no turning), we're in the 1st quadrant
            // Otherwise, we're in the second quadrant
            if (x >= 0.0) {
                return DriveControllerOutput(maxInput, y - x)
            } else {
                return DriveControllerOutput(y + x, maxInput)
            }
        } else {
            // If we're turning right (or moving forward with no turning, we're in the 3rd quadrant
            // Otherwise, we're in the fourth quadrant
            if (x >= 0.0) {
                return DriveControllerOutput(y + x, maxInput)
            } else {
                return DriveControllerOutput(maxInput, y - x)
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