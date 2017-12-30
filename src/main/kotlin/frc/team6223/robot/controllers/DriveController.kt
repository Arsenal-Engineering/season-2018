package frc.team6223.robot.controllers

import edu.wpi.first.wpilibj.Encoder


data class DriveControllerOutput(val left: Double, val right: Double);
data class DriveControllerInput(val leftEncoder: Encoder, val rightEncoder: Encoder);

interface DriveController {

    public fun calculateMotorOutput(controllerInput: DriveControllerInput): DriveControllerOutput;
    public fun start();
    public fun stop();

}