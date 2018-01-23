package frc.team6223.utils.drive

import com.ctre.phoenix.motorcontrol.ControlMode
import com.kauailabs.navx.frc.AHRS


data class DriveControllerOutput(val controlMode: ControlMode, val left: Double, val right: Double);

interface DriveController {

    public fun calculateMotorOutput(controllerInput: ControllerInput): DriveControllerOutput;
    public fun start();
    public fun stop();

}

class ControllerInput(leftEncoder: Int, leftEncoderRate: Int, rightEncoder: Int, rightEncoderRate: Int,
                      navX: AHRS) {
    val leftRotations = leftEncoder
    val leftRotationRate = leftEncoderRate
    val rightRotations = rightEncoder
    val rightRotationRate = rightEncoderRate

    val yawRotation = navX.yaw
    val yawRate = navX.rate

    val pitchRotation = navX.pitch
    val rollRotation = navX.roll
}