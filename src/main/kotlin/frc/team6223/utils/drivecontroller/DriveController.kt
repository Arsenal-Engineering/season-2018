package frc.team6223.utils.drivecontroller

import frc.team6223.utils.srx.MotorControlMode


data class DriveControllerOutput(val controlMode: MotorControlMode, val left: Double, val right: Double);

interface DriveController {

    public fun calculateMotorOutput(controllerInput: ControllerInput): DriveControllerOutput;
    public fun start();
    public fun stop();

}