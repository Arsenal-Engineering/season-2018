package frc.team6223.robot.controllers


data class DriveControllerOutput(val left: Double, val right: Double);

interface DriveController {

    public fun calculateMotorOutput(): DriveControllerOutput;
    public fun start();
    public fun stop();

}