package frc.team6223.arsenalFramework.drive

import com.ctre.phoenix.motorcontrol.ControlMode
import com.kauailabs.navx.frc.AHRS
import frc.team6223.arsenalFramework.hardware.MotorControlMode
import frc.team6223.arsenalFramework.software.units.Distance
import frc.team6223.arsenalFramework.software.units.Velocity


interface DriveController {

    fun calculateMotorOutput(controllerInput: ControllerInput): DriveControllerOutput;
    fun start();
    fun stop();

}

data class DriveControllerOutput(val controlMode: MotorControlMode, val left: Double, val right: Double);

data class ControllerInput(val leftEncoder: Distance, val leftEncoderRate: Velocity,
                           val rightEncoder: Distance, val rightEncoderRate: Velocity,
                           val yawRotation: Float, val yawRate: Double,
                           val pitchRotation: Float,
                           val rollRotation: Float) {

    constructor(leftEncoder: Distance, leftEncoderRate: Velocity, rightEncoder: Distance, rightEncoderRate: Velocity,
                navX: AHRS): this(leftEncoder, leftEncoderRate, rightEncoder, rightEncoderRate,
            navX.yaw, navX.rate,
            navX.pitch,
            navX.roll)
}