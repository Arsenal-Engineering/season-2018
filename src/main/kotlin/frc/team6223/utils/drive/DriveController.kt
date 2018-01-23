package frc.team6223.utils.drive

import com.ctre.phoenix.motorcontrol.ControlMode
import com.kauailabs.navx.frc.AHRS


interface DriveController {

    fun calculateMotorOutput(controllerInput: ControllerInput): DriveControllerOutput;
    fun start();
    fun stop();

}

data class DriveControllerOutput(val controlMode: ControlMode, val left: Double, val right: Double);

data class ControllerInput(val leftEncoder: Int, val leftEncoderRate: Int,
                           val rightEncoder: Int, val rightEncoderRate: Int,
                           val yawRotation: Float, val yawRate: Double,
                           val pitchRotation: Float,
                           val rollRotation: Float) {

    constructor(leftEncoder: Int, leftEncoderRate: Int, rightEncoder: Int, rightEncoderRate: Int,
                navX: AHRS): this(leftEncoder, leftEncoderRate, rightEncoder, rightEncoderRate,
            navX.yaw, navX.rate,
            navX.pitch,
            navX.roll)
}