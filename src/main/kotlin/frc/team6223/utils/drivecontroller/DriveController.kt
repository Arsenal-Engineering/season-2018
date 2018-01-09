package frc.team6223.utils.drivecontroller

import com.ctre.phoenix.motorcontrol.ControlMode


data class DriveControllerOutput(val controlMode: ControlMode, val left: Double, val right: Double);

interface DriveController {

    public fun calculateMotorOutput(controllerInput: ControllerInput): DriveControllerOutput;
    public fun start();
    public fun stop();

}