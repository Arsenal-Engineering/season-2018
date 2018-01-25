package frc.team6223.utils.drivecontroller

import frc.team6223.utils.srx.MotorControlMode
import frc.team6223.utils.units.Distance


data class DriveControllerOutput(val controlMode: MotorControlMode, val left: Double, val right: Double);

interface DriveController {

    public fun calculateMotorOutput(controllerInput: ControllerInput): DriveControllerOutput;
    public fun start(leftInitial: Distance, rightInitial: Distance);
    public fun stop();

}